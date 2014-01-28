package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find a better ModelState.
 * 
 * @author bruins
 * 
 */
public class Search {

    private TransitionFunction transitionFunction;
    private SuccessorFunction successorFunction;
    private ModelStateEvaluator modelStateEvaluator;
    private CostFunction costFunction;

    /**
     * Constructor.
     */
    public Search(TransitionFunction transitionFunction,
            SuccessorFunctionV1 successorFunction,
            ModelStateEvaluator modelStateEvaluator, CostFunction costFunction) {
        this.transitionFunction = transitionFunction;
        this.successorFunction = successorFunction;
        this.modelStateEvaluator = modelStateEvaluator;
        this.costFunction = costFunction;
    }

    /**
     * Find a ModelState closer to the Goal state.
     * 
     * @param modelState
     *            the current ModelState.
     * @return a ModelState closer to the Goal state.
     */

    public Successor findBestSuccessor(ModelState modelState) {
        Queue<Successor> successors = successorFunction
                .generateSuccessors(modelState);
        Successor bestSuccessor = successors.remove();
        double bestValuationSoFar = modelStateEvaluator.evaluate(bestSuccessor
                .getModelState());
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

    public Queue<Action> findPath(ModelState modelState) {
        // double cost = costFunction.calculateCost(modelState,
        // bestSuccessor.getAction(), bestSuccessor.getModelState());

        Queue<Action> path = new LinkedList<Action>();
        while (!goal(modelState)) {
            Successor successor = findBestSuccessor(modelState);
            path.add(successor.getAction());
            modelState = successor.getModelState();
        }
        return path;
    }

}
