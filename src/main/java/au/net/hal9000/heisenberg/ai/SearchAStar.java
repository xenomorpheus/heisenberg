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
    private static final double DISTANCE_THRESHOLD = 0.1;

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
         * Remember the places we have already added to the fringe. <br>
         * This dramatically reduces the size of fringe.
         */
        List<Position> fringeAdded = new ArrayList<>();

        /** fringe of states to expand */
        PriorityQueue<FringeElement> fringe = new PriorityQueue<>();

        fringe.add(new FringeElementImpl(modelState, new PathImpl(), 0f, 0));
        fringeAdded.add(modelState.getAgentPosition());

        resultPath = null;
        while (!fringe.isEmpty()) {

            FringeElement fringeElement = fringe.remove();
            System.out.println("remove "+fringeElement );
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

                ModelState successorModelState = successor.getModelState();
                
                // Don't add state to the fringe more than once.
                if (successorInFringeAdded(fringeAdded, successorModelState)) {
                    continue;
                }
                fringeAdded.add(successorModelState.getAgentPosition());

                // Add a new fringe element, with the extra action and cost.
                Path newPathSoFar = pathSoFar.duplicate();
                newPathSoFar.add(successor.getAction());
                double newCostSoFar = costSoFar + successor.getCost();
                double distanceToGoalEst = 0;
                if ((goalEstCostFunction != null)
                        && (successorModelState instanceof ModelStateGoal)) {
                    distanceToGoalEst = goalEstCostFunction
                            .estimatedCostToGoal((ModelStateGoal) successorModelState);
                }
                FringeElement fringeElementNew = new FringeElementImpl(successorModelState,
                        newPathSoFar, newCostSoFar, newCostSoFar
                        + distanceToGoalEst);
                System.out.println("ADD "+fringeElementNew );
                fringe.add(fringeElementNew);
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
     * @return true IFF agent has visited this location.
     */
    private boolean successorInFringeAdded(List<Position> visited, ModelState modelState) {

        // TODO make this code generic giving ModelState the
        // concept of similar states (within tolerance)

        Position agentPos = modelState.getAgentPosition();

        // TODO distance must be less than movement.
        double proximityThreshold = DISTANCE_THRESHOLD;

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
