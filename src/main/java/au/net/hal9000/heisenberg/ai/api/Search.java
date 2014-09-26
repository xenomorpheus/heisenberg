package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.ai.Path;

public interface Search {

    public abstract SuccessorFunction getSuccessorFunction();

    public abstract void setSuccessorFunction(
            SuccessorFunction successorFunction);

    public abstract ModelStateEvaluator getModelStateEvaluator();

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
