package au.net.hal9000.heisenberg.item.exception;

public final class TooBigException extends RuntimeException {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    public TooBigException(String string) {
        super(string);
    }

}
