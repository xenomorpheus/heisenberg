package au.net.hal9000.heisenberg.item.exception;

public final class ExceptionTooBig extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExceptionTooBig(String string) {
		super(string);
	}

}