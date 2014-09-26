package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Depth First Search
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchDepthFirst extends SearchBase {
    /** how close do points need to be to be considered already visited */
    static double DISTANCE_THRESHOLD = 0.5;

    /** constructor */
    public SearchDepthFirst(SuccessorFunction successorFunction,
            ModelStateEvaluator modelStateEvaluator) {
        super(successorFunction, modelStateEvaluator);
    }

    /**
     * Return a list of actions to get from the current model state to reach the
     * goal model state.
     * 
     * @param modelState
     *            current model state.
     * 
     * @return list of actions.
     */
    @Override
    public Path findPathToGoal(ModelState modelState) {
        /** fringe of states to expand */
        // TODO should these be Successor objects ?
        Queue<Successor> fringe = new LinkedBlockingQueue<Successor>();

        // TODO fringe.add(modelState);

        /** list of actions from Start to Goal. */
        Path path = new Path();

        /**
         * places we have already searched. This is to break loops in graph
         * searches
         */
        List<Point3d> visited = new ArrayList<>();

        while (!fringe.isEmpty()) {
            Successor successor = fringe.remove();
            ModelState currentModelState = successor.getModelState();

            if (getModelStateEvaluator().isAtGoal(currentModelState)) {
                // TODO Done!
            }

            if (!hasVisited(visited, currentModelState, DISTANCE_THRESHOLD)) {
                fringe.addAll(getSuccessorFunction().generateSuccessors(
                        currentModelState));
            }

        }

        return path;
    }

    /**
     * has the agent been to the list of visited places.
     * 
     * @param visited
     *            list of places already visited.
     * @param modelState
     *            state of model.
     * @param proximityThreshold
     *            how close to places to be considered visited.
     * @return
     */
    private boolean hasVisited(List<Point3d> visited, ModelState modelState,
            double proximityThreshold) {
        Point3d agentPos = modelState.getAgentPosition();

        // Check if we have been here.
        boolean hereBefore = false;
        for (Point3d v : visited) {
            if (agentPos.distance(v) <= proximityThreshold) {
                hereBefore = true;
                break;
            }
        }
        return hereBefore;
    }

}
