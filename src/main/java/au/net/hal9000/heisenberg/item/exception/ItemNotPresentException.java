package au.net.hal9000.heisenberg.item.exception;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ItemNotPresentException extends RuntimeException {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for ItemNotPresentException.
   *
   * @param errorText String
   */
  public ItemNotPresentException(String errorText) {
    super(errorText);
  }
  ;
}
