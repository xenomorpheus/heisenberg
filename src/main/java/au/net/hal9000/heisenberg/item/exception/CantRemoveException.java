package au.net.hal9000.heisenberg.item.exception;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class CantRemoveException extends Exception {

    /**
     * Constructor for CantRemoveException.
     * @param string String
     */
    public CantRemoveException(String string) {
        super(string);
    }
}
