package au.net.hal9000.heisenberg.item.exception;

/**
 * Thrown when an Item can't be worn.
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class CantWearException extends Exception {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for CantWearException.
     * 
     * @param string
     *            String
     */
    public CantWearException(String string) {
        super(string);
    }
}
