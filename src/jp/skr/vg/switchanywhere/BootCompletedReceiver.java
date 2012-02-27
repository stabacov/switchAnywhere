package jp.skr.vg.switchanywhere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (isStartup(intent) && isAutoStart(context)) {
			ServiceManager sm = new ServiceManager(context);
			sm.startService(SwitchAnywareService.class);
		}
	}

	private boolean isStartup(Intent intent) {
		return Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction());
	}

	private boolean isAutoStart(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean b = prefs.getBoolean(PreferenceKeys.AUTO_START, false);
		Log.d("Receiver", "isAutoStart: " + b);
		return b;
	}
}
