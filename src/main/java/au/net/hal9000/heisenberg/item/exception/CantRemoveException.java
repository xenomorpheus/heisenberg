package au.net.hal9000.heisenberg.item.exception;

/**
 */
public final class CantRemoveException extends Exception {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for CantRemoveException.
     * @param string String
     */
    public CantRemoveException(String string) {
        super(string);
    }
}
