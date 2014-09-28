package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
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
     * @throws CloneNotSupportedException 
     */
    @Override
    public Path findPathToGoal(ModelState modelState) {
        /** fringe of states to expand */
        Queue<FringeElement> fringe = new LinkedBlockingQueue<>();
        fringe.add(new FringeElementImpl(modelState, new PathImpl(), 0f));

        /**
         * places we have already searched. This is to break loops in graph
         * searches
         */
        // TODO implement
        List<Point3d> visited = new ArrayList<>();

        while (!fringe.isEmpty()) {
            FringeElement fringeElement = fringe.remove();
            ModelState currentModelState = fringeElement.getModelState();
            Path pathSoFar = fringeElement.getPathSoFar();
            double costSoFar = fringeElement.getCostSoFar();

            if (getModelStateEvaluator().isAtGoal(currentModelState)) {
                return pathSoFar;
            }
            visited.add(currentModelState.getAgentPosition());

            if (!hasVisited(visited, currentModelState, DISTANCE_THRESHOLD)) {
                Queue<Successor> successors = getSuccessorFunction()
                        .generateSuccessors(currentModelState);
                for (Successor successor : successors) {
                    Path newPathSoFar;
                    try {
                        newPathSoFar = pathSoFar.clone();
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException("Failed to clone path",e);
                    }
                    pathSoFar.add(successor.getAction());
                    double newCostSoFar = costSoFar + successor.getCost();
                    fringe.add(new FringeElementImpl(modelState, newPathSoFar,
                            newCostSoFar));
                }
            }

        }
        return null;
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
