package jp.skr.vg.switchanywhere;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SwitchAnywareService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("Service", "onStartCommand");
		enableNotification();

		return Service.START_STICKY;
	}

	private void enableNotification() {
		NotificationManager manager = getNotificationManager();

		Notification notification = new Notification();
		notification.icon = R.drawable.system3;
		notification.when = 0;
//		RemoteViews remoteViews =  new RemoteViews(getPackageName(), R.layout.notification);
//		notification.contentView = remoteViews;
		notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		notification.defaults = 0;
		notification.tickerText = getText(R.string.ticker_text);
		Intent contentIndent = new Intent(getApplicationContext(), SwitcherActivity.class);
		contentIndent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		notification.contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, contentIndent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(getApplicationContext(),
				getString(R.string.app_name), getString(R.string.notification_text),
				PendingIntent.getActivity(getApplicationContext(), 0, contentIndent, PendingIntent.FLAG_UPDATE_CURRENT));

		manager.notify(1, notification);
	}

	@Override
	public void onDestroy() {
		NotificationManager manager = getNotificationManager();
		manager.cancelAll();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	private NotificationManager getNotificationManager() {
		return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}
}
