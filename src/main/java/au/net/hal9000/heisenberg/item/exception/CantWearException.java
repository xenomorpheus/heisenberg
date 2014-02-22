package au.net.hal9000.heisenberg.item.exception;

public final class CantWearException extends RuntimeException {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    public CantWearException(String string) {
        super(string);
    }
}
