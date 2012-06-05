package au.net.hal9000.dnd.item.exception;

public final class ExceptionCantWear extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceptionCantWear(String string) {
		super(string);
	}
}
