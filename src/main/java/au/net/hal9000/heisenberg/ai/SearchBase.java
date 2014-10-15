package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Search;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;

/**
 * Base class for all search algorithms
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public abstract class SearchBase implements Search {

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

    protected SearchBase(SuccessorFunction successorFunction,
            ModelStateEvaluator modelStateEvaluator) {
        super();
        this.successorFunction = successorFunction;
        this.modelStateEvaluator = modelStateEvaluator;
    }

    /** {@inheritDoc} */
    @Override
    public SuccessorFunction getSuccessorFunction() {
        return successorFunction;
    }

    /** {@inheritDoc} */
    @Override
    public void setSuccessorFunction(SuccessorFunction successorFunction) {
        this.successorFunction = successorFunction;
    }

    /** {@inheritDoc} */
    @Override
    public ModelStateEvaluator getModelStateEvaluator() {
        return modelStateEvaluator;
    }

    /** {@inheritDoc} */
    @Override
    public void setModelStateEvaluator(ModelStateEvaluator modelStateEvaluator) {
        this.modelStateEvaluator = modelStateEvaluator;
    }

    /** {@inheritDoc} */
    @Override
    public abstract Path findPathToGoal(ModelState modelState);

}
