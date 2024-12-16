package au.net.hal9000.heisenberg.item.exception;

/** Item is too heavy to fit into container. */
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
