package au.net.hal9000.heisenberg.util;

/**
 * 
 * @author bruins
 * 
 */
public class ConfigurationError extends Throwable {
    /**
     * The serial ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The original exception.
     */
    private Exception exception;

    /**
     * Constructor.
     * 
     * @param pException
     *            the original exception
     */
    public ConfigurationError(Exception pException) {
        exception = pException;
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
