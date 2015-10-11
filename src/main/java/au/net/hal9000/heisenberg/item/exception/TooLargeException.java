package au.net.hal9000.heisenberg.item.exception;

/**
 */
public final class TooLargeException extends RuntimeException {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for TooBigException.
     * @param string String
     */
    public TooLargeException(String string) {
        super(string);
    }

}
