package jp.skr.vg.switchanywhere;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class SwitchAnywareActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Activity", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ServiceManager sm = new ServiceManager(getApplicationContext());
		sm.startService(SwitchAnywareService.class);
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateViews();
	}

	private void updateViews() {
		updateAutoStart();
	}

	private void updateAutoStart() {
		CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
		cb.setChecked(isAutoStart());
	}

	private boolean isAutoStart() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		return prefs.getBoolean(PreferenceKeys.AUTO_START, false);
	}

	public void onAutoStartClick(View view) {
		CheckBox cb = (CheckBox) view;
		saveAutoStart(cb.isChecked());
	}

	private void saveAutoStart(boolean enabled) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = prefs.edit();
		editor.putBoolean(PreferenceKeys.AUTO_START, enabled);
		editor.apply();
	}
}