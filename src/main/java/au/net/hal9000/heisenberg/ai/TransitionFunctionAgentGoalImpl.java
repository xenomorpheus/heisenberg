package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class TransitionFunctionAgentGoalImpl implements TransitionFunction {

    /**
     * Constructor.
     */
    public TransitionFunctionAgentGoalImpl() {
        super();
    }

    /**
     * Create a new ModelState by cloning the existing ModelState, then applying
     * the Action to it.
     * 
     * @param modelState
     *            ModelState
     * @param action
     *            Action
     * @return ModelState
     * @see au.net.hal9000.heisenberg.ai.api.TransitionFunction#transition(ModelState,
     *      Action)
     */
    @Override
    public ModelState transition(ModelState modelState, Action action) {
        ModelState newModelState = modelState.duplicate();
        action.apply(newModelState);
        return newModelState;
    }

}
