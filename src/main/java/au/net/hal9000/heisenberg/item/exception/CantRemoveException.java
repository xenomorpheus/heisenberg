package au.net.hal9000.heisenberg.item.exception;

public final class CantRemoveException extends RuntimeException {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    public CantRemoveException(String string) {
        super(string);
    }
}
