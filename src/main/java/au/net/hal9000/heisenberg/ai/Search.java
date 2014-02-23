package au.net.hal9000.heisenberg.ai;

import java.util.Queue;

/**
 * Find a better ModelState.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class Search {

    /**
     * a function that supplies a list of possible actions from current model
     * state.
     */
    private SuccessorFunction successorFunction;

    /** a function that determines how good a model state is . */
    private ModelStateEvaluator modelStateEvaluator;

    /**
     * Constructor.
     * 
     * @param successorFunction
     *            a function that supplies a list of possible actions from
     *            current model state.
     * @param modelStateEvaluator
     *            a function that determines how good a model state is .
     */

    public Search(SuccessorFunction successorFunction,
            ModelStateEvaluator modelStateEvaluator) {
        this.successorFunction = successorFunction;
        this.modelStateEvaluator = modelStateEvaluator;
    }

    /**
     * Find a ModelState closer to the Goal state.
     * 
     * @param modelState
     *            the current ModelState.
    
     * @return a ModelState closer to the Goal state. */

    public Successor findBestSuccessor(ModelState modelState) {
        Queue<Successor> successors = successorFunction
                .generateSuccessors(modelState);
        // TODO handle when no successors from current position.
        // Get first successor off the list.
        Successor bestSuccessor = successors.remove();
        double bestValuationSoFar = modelStateEvaluator.evaluate(bestSuccessor
                .getModelState());
        // Work through the remainder successors looking for a better one.
        for (Successor successor : successors) {
            double valuation = modelStateEvaluator.evaluate(successor
                    .getModelState());
            if (valuation < bestValuationSoFar) {
                bestSuccessor = successor;
                bestValuationSoFar = valuation;
            }
        }
        return bestSuccessor;
    }

    /**
     * Return a list of actions to get from the current model state to reach the
     * goal model state.
     * 
     * @param modelState
     *            current model state.
    
     * @return list of actions. */
    public Path findPath(ModelState modelState) {
        // double cost = costFunction.calculateCost(modelState,
        // bestSuccessor.getAction(), bestSuccessor.getModelState());

        Path path = new Path();
        while (!modelStateEvaluator.atGoal(modelState)) {
            Successor successor = findBestSuccessor(modelState);
            path.add(successor.getAction());
            modelState = successor.getModelState();
        }
        return path;
    }

}
