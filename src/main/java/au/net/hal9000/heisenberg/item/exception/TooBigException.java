package au.net.hal9000.heisenberg.item.exception;

/**
 */
public final class TooBigException extends RuntimeException {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for TooBigException.
     * @param string String
     */
    public TooBigException(String string) {
        super(string);
    }

}
