package au.net.hal9000.heisenberg.ai.api;

import java.util.List;

/**
 * Evaluate the state of the model and give an evaluation rating on how good it
 * is. e.g. how close we are to the goal state.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public interface ModelStateEvaluator {

    /**
     * How close are we to the goal?
     * 
     * @param modelState
     *            the state of the model.
     * 
     * @return The estimated cost to reach the goal.
     */
    double costToGoalEstimate(ModelState modelState);

    /**
     * Are we at the goal?
     * 
     * @param modelState
     *            the state of the model.
     * 
     * @return Are we at the goal?
     */

    boolean isAtGoal(ModelState modelState);

    /**
     * Has the modelState been added to the list? This will be used to determine
     * duplicates when adding model states to the search fringe.
     * 
     * @param addedModelStates
     *            list of places already visited.
     * @param modelState
     *            state of model.
     * @return true IFF agent has visited this location.
     */
    boolean modelStateInAdded(List<ModelState> addedModelStates,
            ModelState modelState);
}
