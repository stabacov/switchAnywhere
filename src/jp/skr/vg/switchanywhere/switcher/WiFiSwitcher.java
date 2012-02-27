package jp.skr.vg.switchanywhere.switcher;

import jp.skr.vg.switchanywhere.HardwareNotSupportedException;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;

public class WiFiSwitcher extends AbstractSystemInt1Switcher {

	@Override
	protected String getName() {
		return Secure.WIFI_ON;
	}

	@Override
	public void setEnabled(Context context, boolean enable) throws Exception {
		WifiManager manager = (WifiManager) context.getApplicationContext()
				.getSystemService(Context.WIFI_SERVICE);
		if (manager == null) { // たぶん無い
			throw new HardwareNotSupportedException(
					"Wi-Fi is not available on your hardware.");
		}
		boolean result = manager.setWifiEnabled(enable);
		if (!result) {
			throw new RuntimeException("Changing Wi-Fi state to " + enable
					+ " is failed.");
		}
	}
}
