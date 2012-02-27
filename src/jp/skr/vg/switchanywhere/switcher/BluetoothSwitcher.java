package jp.skr.vg.switchanywhere.switcher;

import jp.skr.vg.switchanywhere.HardwareNotSupportedException;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.provider.Settings.Secure;

public class BluetoothSwitcher extends AbstractSystemInt1Switcher {

	@Override
	protected String getName() {
		return Secure.BLUETOOTH_ON;
	}

	@Override
	public void setEnabled(Context context, boolean enable)
			throws HardwareNotSupportedException, Exception {
		setEnabled(enable);
	}

	private boolean setEnabled(boolean enable)
			throws HardwareNotSupportedException {
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if (adapter == null) {
			throw new HardwareNotSupportedException(
					"Cannot get BluetoothAdapter");
		}
		if (enable) {
			return adapter.enable();
		} else {
			return adapter.disable();
		}
	}
}
