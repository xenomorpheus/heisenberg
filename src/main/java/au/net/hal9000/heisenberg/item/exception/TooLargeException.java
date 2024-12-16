package au.net.hal9000.heisenberg.item.exception;

/** The Item is too large to fit into container. */
public final class TooLargeException extends RuntimeException {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for TooBigException.
   *
   * @param error String
   */
  public TooLargeException(String error) {
    super(error);
  }
}
