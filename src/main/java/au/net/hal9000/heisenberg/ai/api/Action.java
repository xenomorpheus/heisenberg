package au.net.hal9000.heisenberg.ai.api;

/**
 * Apply an Action object to a ModelState to produce a new ModelState.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface Action {


	/**
     * Get the cost of this acion.
	 * @return
	 */
    double getCost();
    /**
     * Set the cost of this acion.
     * @param cost
     */
    void setCost(double cost);
	
    /**
     * Apply the action to the modelState.
     * 
     * @param modelState
     *            the modelState that will be mutated by the action.
     */
    void apply(ModelState modelState);

    /**
     * {@inheritDoc}
     * 
     * @return String
     */
    @Override
    String toString();

}
