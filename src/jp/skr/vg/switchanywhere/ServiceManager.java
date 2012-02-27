package jp.skr.vg.switchanywhere;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class ServiceManager {

	private final Context context;

	public ServiceManager(Context context) {
		this.context = context;
	}

	private Intent createIntent(Class<?> targetClass) {
		Intent intent = new Intent(context, targetClass);
		return intent;
	}

	public ComponentName startService(Class<? extends Service> serviceClass) {
		Intent intent = createIntent(serviceClass);
		return context.startService(intent);
	}

	public boolean stopService(Class<? extends Service> serviceClass) {
		Intent intent = createIntent(serviceClass);
		return context.stopService(intent);
	}
}
