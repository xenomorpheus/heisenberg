package au.net.hal9000.heisenberg.item.exception;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class InvalidTypeException extends Exception {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for InvalidTypeException.
     * 
     * @param string
     *            String
     */
    public InvalidTypeException(String string) {
        super(string);
    }

}
