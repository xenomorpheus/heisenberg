package au.net.hal9000.heisenberg.ai;

/**
 * Base class for all search algorithms
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public abstract class Search {

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
     *            a function that determines how close Agent is to goal.
     */

    public Search(SuccessorFunction successorFunction,
            ModelStateEvaluator modelStateEvaluator) {
        super();
        this.successorFunction = successorFunction;
        this.modelStateEvaluator = modelStateEvaluator;
    }

    public SuccessorFunction getSuccessorFunction() {
        return successorFunction;
    }

    public void setSuccessorFunction(SuccessorFunction successorFunction) {
        this.successorFunction = successorFunction;
    }

    public ModelStateEvaluator getModelStateEvaluator() {
        return modelStateEvaluator;
    }

    public void setModelStateEvaluator(ModelStateEvaluator modelStateEvaluator) {
        this.modelStateEvaluator = modelStateEvaluator;
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
    public abstract Path findPathToGoal(ModelState modelState);

}
