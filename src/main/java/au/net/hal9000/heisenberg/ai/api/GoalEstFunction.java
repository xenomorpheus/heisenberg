package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.ai.ModelStateGoal;

/**
 * A goal estimate function will estimate the cost to reach the goal from the
 * current state of the model.
 * 
 * @author bruins
 *
 */
public interface GoalEstFunction {

    /**
     * @param modelState
     *            the state of the model being evaluated.
     * @return estimated cost to reach goal state
     */
    double estimatedCostToGoal(ModelStateGoal modelState);

}
