package au.net.hal9000.dnd.item;

public final class ExceptionCantRemove extends Throwable {
	private static final long serialVersionUID = 1L;

	public ExceptionCantRemove(String string) {
		super(string);
	}
}
