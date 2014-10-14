package au.net.hal9000.heisenberg.ai.api;

public interface Search {

    /**
     * @return the successor function.
     */
    public abstract SuccessorFunction getSuccessorFunction();

    /**
     * set the successor function
     * 
     * @param successorFunction
     *            the successor function.
     */
    public abstract void setSuccessorFunction(
            SuccessorFunction successorFunction);

    /**
     * @return the model state evaluator function.
     */
    public abstract ModelStateEvaluator getModelStateEvaluator();

    /**
     * @param modelStateEvaluator the model state evaluator function.
     */
    public abstract void setModelStateEvaluator(
            ModelStateEvaluator modelStateEvaluator);

    /**
     * Return a list of actions to get from the current model state to reach the
     * goal model state.
     * 
     * @param modelState
     *            current model state.
     * 
     * @return list of actions.
     */
    public abstract Path findPathToGoal(ModelState modelState);

}
