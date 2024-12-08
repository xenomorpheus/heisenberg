package au.net.hal9000.heisenberg.item.exception;

/** */
public final class TooHeavyException extends RuntimeException {

  /** serial verison id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for TooHeavyException.
   *
   * @param errorText String
   */
  public TooHeavyException(String errorText) {
    super(errorText);
  }
}
