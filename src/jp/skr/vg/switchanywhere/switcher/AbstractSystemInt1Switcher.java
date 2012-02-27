package jp.skr.vg.switchanywhere.switcher;

import android.content.Context;
import android.provider.Settings.System;

public abstract class AbstractSystemInt1Switcher implements Switcher {

	abstract protected String getName();

	@Override
	public boolean isEnabled(Context context) throws Exception {
		int value = System.getInt(context.getContentResolver(), getName());
		return value == 1;
	}

	@Override
	public void setEnabled(Context context, boolean enable) throws Exception {
		int value = enable ? 1 : 0;
		boolean result = System.putInt(context.getContentResolver(), getName(),
				value);
		if (!result) {
			throw new RuntimeException("update failed.");
		}
	}
}
