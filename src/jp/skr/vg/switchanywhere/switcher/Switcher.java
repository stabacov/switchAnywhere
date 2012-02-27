package jp.skr.vg.switchanywhere.switcher;

import jp.skr.vg.switchanywhere.HardwareNotSupportedException;
import android.content.Context;

public interface Switcher {

	boolean isEnabled(Context context) throws Exception;

	void setEnabled(Context context, boolean enable)
			throws HardwareNotSupportedException, Exception;
}
