package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.GoalEstFunction;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A-Star Search & Uniform Cost Search.<br>
 * Uniform Cost Search is just SearchAStar without an estimate of cost to goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchAStar extends SearchBase {
    /** how close do points need to be to be considered already visited. */
    private static final double DISTANCE_THRESHOLD = 0.5;

    /** estimated distance to goal. */
    private GoalEstFunction goalEstCostFunction = null;

    /**
     * constructor - A-Star search.
     * 
     * @param successorFunction
     *            the successor function.
     * @param modelStateEvaluator
     *            the model state evaluator.
     * @param goalEstCostFunction
     *            the function to estimate distance to goal.
     */
    public SearchAStar(final SuccessorFunction successorFunction,
            final ModelStateEvaluator modelStateEvaluator,
            final GoalEstFunction goalEstCostFunction) {
        this(successorFunction, modelStateEvaluator);
        this.goalEstCostFunction = goalEstCostFunction;
    }

    /**
     * constructor - Uniform Cost Search.
     * 
     * @param successorFunction
     * @param modelStateEvaluator
     */
    public SearchAStar(final SuccessorFunction successorFunction,
            final ModelStateEvaluator modelStateEvaluator) {
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
    public final Path findPathToGoal(final ModelState modelState) {

        Path resultPath;
        /**
         * places we have already searched. <br>
         * This is to break loops in graph searches
         */
        List<Position> inFringe = new ArrayList<>();

        /** fringe of states to expand */
        PriorityQueue<FringeElement> fringe = new PriorityQueue<>();

        fringe.add(new FringeElementImpl(modelState, new PathImpl(), 0f, 0));
        inFringe.add(modelState.getAgentPosition());

        resultPath = null;
        while (!fringe.isEmpty()) {
            FringeElement fringeElement = fringe.remove();
            ModelState currentModelState = fringeElement.getModelState();
            Path pathSoFar = fringeElement.getPathSoFar();
            double costSoFar = fringeElement.getCostSoFar();

            if (getModelStateEvaluator().isAtGoal(currentModelState)) {
                resultPath = pathSoFar;
                break;
            }

            Queue<Successor> successors = getSuccessorFunction()
                    .generateSuccessors(currentModelState);
            for (Successor successor : successors) {

                // Don't add states to the fringe more than once.
                ModelState successorModelState = successor.getModelState();
                if (hasVisited(inFringe, successorModelState,
                        DISTANCE_THRESHOLD)) {
                    continue;
                }
                inFringe.add(successorModelState.getAgentPosition());

                // Add a new fringe element with extra action and cost.
                Path newPathSoFar;
                try {
                    newPathSoFar = pathSoFar.duplicate();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException("Failed to clone path", e);
                }
                newPathSoFar.add(successor.getAction());
                double newCostSoFar = costSoFar + successor.getCost();
                double distanceToGoal = 0;
                if ((goalEstCostFunction != null) && (successorModelState instanceof ModelStateGoal)){
                    distanceToGoal = goalEstCostFunction
                            .estimatedCostToGoal((ModelStateGoal)successorModelState);
                }
                fringe.add(new FringeElementImpl(successorModelState,
                        newPathSoFar, newCostSoFar, newCostSoFar
                                + distanceToGoal));
            }

        }
        return resultPath;
    }

    /**
     * Has the agent been to the list of visited places?
     * 
     * @param visited
     *            list of places already visited.
     * @param modelState
     *            state of model.
     * @param proximityThreshold
     *            how close to places to be considered visited.
     * @return true IFF agent has visited this location.
     */
    private boolean hasVisited(List<Position> visited, ModelState modelState,
            double proximityThreshold) {
        Position agentPos = modelState.getAgentPosition();

        // Check if we have been here.
        boolean hereBefore = false;
        for (Position v : visited) {
            if (agentPos.distance(v) <= proximityThreshold) {
                hereBefore = true;
                break;
            }
        }
        return hereBefore;
    }

}
