package au.net.hal9000.heisenberg.ai.api;

public interface GoalEstFunction {

    /**
     * @param modelState
     *            the state of the model being evaluated.
     * @return estimated cost to reach goal state
     */
    double estimatedCostToGoal(ModelState modelState);

}
