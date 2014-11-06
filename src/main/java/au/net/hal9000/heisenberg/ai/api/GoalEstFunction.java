package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.ai.ModelStateGoal;

public interface GoalEstFunction {

    /**
     * @param modelState
     *            the state of the model being evaluated.
     * @return estimated cost to reach goal state
     */
    double estimatedCostToGoal(ModelStateGoal modelState);

}
