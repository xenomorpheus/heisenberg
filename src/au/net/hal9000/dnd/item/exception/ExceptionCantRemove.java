package au.net.hal9000.dnd.item.exception;

public final class ExceptionCantRemove extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceptionCantRemove(String string) {
		super(string);
	}
}
