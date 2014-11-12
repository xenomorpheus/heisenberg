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

/**
 * A-Star Search & Uniform Cost Search.<br>
 * Uniform Cost Search is just SearchAStar without an estimate of cost to goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchAStar extends SearchBase {
    /** how close do points need to be to be considered already visited. */

    /** estimated distance to goal. */
    private GoalEstFunction goalEstCostFunction = null;
    
    /** maximum fringe expansion. */
    private int fringeExpansionMax = 0;

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

    // Setters and Getters.
    public int getFringeExpansionMax() {
        return fringeExpansionMax;
    }

    public void setFringeExpansionMax(int fringeExpansionMax) {
        this.fringeExpansionMax = fringeExpansionMax;
    }

    public void setGoalEstCostFunction(GoalEstFunction goalEstCostFunction) {
        this.goalEstCostFunction = goalEstCostFunction;
    }

    // Misc.
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

        Path resultPath = null;
        int fringeExpansionCount = 0;
        /**
         * Remember the places we have already added to the fringe. <br>
         * This dramatically reduces the size of fringe.
         */
        List<ModelState> fringeAdded = new ArrayList<>();

        /** fringe of states to expand */
        PriorityQueue<FringeElement> fringe = new PriorityQueue<>();

        fringe.add(new FringeElementImpl(modelState, new PathImpl(), 0f, 0));
        fringeAdded.add(modelState);

        resultPath = null;
        while (!fringe.isEmpty() && ((fringeExpansionMax == 0) || (fringeExpansionCount < fringeExpansionMax))) {
            fringeExpansionCount++;

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

                ModelState successorModelState = successor.getModelState();
                
                // Don't add state to the fringe more than once.
                if (getModelStateEvaluator().modelStateInAdded(fringeAdded, successorModelState)) {
                    continue;
                }
                fringeAdded.add(successorModelState);

                // Add a new fringe element, with the extra action and cost.
                Path newPathSoFar = pathSoFar.duplicate();
                newPathSoFar.add(successor.getAction());
                double newCostSoFar = costSoFar + successor.getCost();
                double costToGoalEst = 0;
                if ((goalEstCostFunction != null)
                        && (successorModelState instanceof ModelStateGoal)) {
                    costToGoalEst = goalEstCostFunction
                            .estimatedCostToGoal((ModelStateGoal) successorModelState);
                }
                FringeElement fringeElementNew = new FringeElementImpl(successorModelState,
                        newPathSoFar, newCostSoFar, newCostSoFar
                        + costToGoalEst);
                fringe.add(fringeElementNew);
            }

        }
        return resultPath;
    }



}
