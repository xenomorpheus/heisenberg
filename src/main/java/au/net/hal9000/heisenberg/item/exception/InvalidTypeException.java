package au.net.hal9000.heisenberg.item.exception;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class InvalidTypeException extends RuntimeException {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for InvalidTypeException.
   *
   * @param errorText String
   */
  public InvalidTypeException(String errorText) {
    super(errorText);
  }
}
