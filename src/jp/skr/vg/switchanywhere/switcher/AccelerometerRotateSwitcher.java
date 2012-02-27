package jp.skr.vg.switchanywhere.switcher;

import android.provider.Settings.System;

public class AccelerometerRotateSwitcher extends AbstractSystemInt1Switcher {

	@Override
	protected String getName() {
		return System.ACCELEROMETER_ROTATION;
	}
}
