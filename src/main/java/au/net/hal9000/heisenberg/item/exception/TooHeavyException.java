package au.net.hal9000.heisenberg.item.exception;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class TooHeavyException extends RuntimeException {

  /** serial verison id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for TooHeavyException.
   *
   * @param string String
   */
  public TooHeavyException(String string) {
    super(string);
  }
}
