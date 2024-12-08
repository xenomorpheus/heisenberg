package au.net.hal9000.heisenberg.util;

/** Configuration error. */
public class ConfigurationError extends Throwable {
  /** The serial ID. */
  private static final long serialVersionUID = 1L;

  /** The original exception. */
  private Exception exception;

  /**
   * Constructor.
   *
   * @param pException the original exception
   */
  ConfigurationError(Exception exception) {
    this.exception = exception;
  }

  /**
   * Get the original exception.
   *
   * @return the original exception.
   */
  public Exception getException() {
    return exception;
  }
}
