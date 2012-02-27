package jp.skr.vg.switchanywhere;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jp.skr.vg.switchanywhere.switcher.AccelerometerRotateSwitcher;
import jp.skr.vg.switchanywhere.switcher.BluetoothSwitcher;
import jp.skr.vg.switchanywhere.switcher.Switcher;
import jp.skr.vg.switchanywhere.switcher.WiFiSwitcher;

enum Status {
	ACCELORATE_ROTATION(R.id.cbRotate, AccelerometerRotateSwitcher.class),
	BLUETOOTH(R.id.cbBluetooth, BluetoothSwitcher.class),
	WIFI(R.id.cbWiFi, WiFiSwitcher.class);

	private static final Map<Integer, Status> viewIDMap;
	static {
		Map<Integer, Status> map = new HashMap<Integer, Status>(
				Status.values().length + 1);
		for (Status value : Status.values()) {
			map.put(value.viewID, value);
		}
		viewIDMap = Collections.unmodifiableMap(map);
	}

	public static Status findByID(int viewID) {
		return viewIDMap.get(viewID);
	}

	public final int viewID;
	public final Class<? extends Switcher> switcherClass;

	private Status(int viewID, Class<? extends Switcher> switcherClass) {
		this.viewID = viewID;
		this.switcherClass = switcherClass;
	}
}