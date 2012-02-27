package jp.skr.vg.switchanywhere;

public class HardwareNotSupportedException extends Exception {

	private static final long serialVersionUID = 1L;

	public HardwareNotSupportedException() {
		super();
	}

	public HardwareNotSupportedException(String detailMessage,
			Throwable throwable) {
		super(detailMessage, throwable);
	}

	public HardwareNotSupportedException(String detailMessage) {
		super(detailMessage);
	}

	public HardwareNotSupportedException(Throwable throwable) {
		super(throwable);
	}
}
