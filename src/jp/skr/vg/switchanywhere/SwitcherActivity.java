package jp.skr.vg.switchanywhere;

import jp.skr.vg.switchanywhere.switcher.Switcher;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class SwitcherActivity extends Activity {

	private static final String TAG = "SwitcherActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switcher);
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
		boolean result = updateViews();
		if (!result) {
			Toast.makeText(this, R.string.error_update, Toast.LENGTH_LONG)
					.show();
		}
	}

	private boolean updateViews() {
		boolean success = true;
		for (Status target : Status.values()) {
			try {
				updateView(target);
			} catch (Exception e) {
				Log.e(TAG, "Exception caused while updating " + target
						+ " view", e);
				success = false;
			}
		}
		return success;
	}

	private void updateView(Status target) throws Exception {
		boolean value = getSwitcher(target).isEnabled(this);
		findCheckBox(target.viewID).setChecked(value);
	}

	private Switcher getSwitcher(Status target) throws InstantiationException,
			IllegalAccessException {
		Class<? extends Switcher> cls = target.switcherClass;
		return cls.newInstance();
	}

	private CheckBox findCheckBox(int id) {
		CheckBox cb = (CheckBox) findViewById(id);
		if (cb == null) {
			throw new IllegalArgumentException("id [" + id + "] is not defined");
		}
		return cb;
	}

	public void onCheckBoxClick(View view) {
		CheckBox cb = (CheckBox) view;
		Status target = Status.findByID(view.getId());
		if (target == null) {
			throw new IllegalStateException("Cannot find target Switch.");
		}
		try {
			getSwitcher(target).setEnabled(this, cb.isChecked());
		} catch (HardwareNotSupportedException e) {
			Log.w(TAG, "Hardware not supported: " + target);
			Toast.makeText(this, R.string.error_hardware, Toast.LENGTH_LONG)
					.show();
			updateViewIgnoreException(target);
		} catch (Exception e) {
			Log.e(TAG,
					"Exception caused while switch " + target + " to "
							+ cb.isChecked(), e);
			Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
			updateViewIgnoreException(target);
		}
	}

	private void updateViewIgnoreException(Status target) {
		try {
			updateView(target);
		} catch (Exception e) {
			Log.d(TAG,
					"Exception caused while updating view, with ignore option.",
					e);
			// ignore.
		}
	}

	public void onEndButtonClick(View view) {
		ServiceManager sm = new ServiceManager(getApplicationContext());
		sm.stopService(SwitchAnywareService.class);
		finish();
	}
}
