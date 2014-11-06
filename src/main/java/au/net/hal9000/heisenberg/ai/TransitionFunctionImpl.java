package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class TransitionFunctionImpl implements TransitionFunction {

    /**
     * Constructor.
     */
    public TransitionFunctionImpl() {
        super();
    }

    /**
     * Method transition.
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
        if (!(modelState instanceof ModelStateGoal)) {
            throw new IllegalArgumentException("Expecting ModelStateGoal but got "
                    + modelState.getClass().getSimpleName());
        }
        if (!(action instanceof ActionMoveImpl)) {
            throw new IllegalArgumentException(
                    "Expecting ActionMoveImpl but got "
                            + action.getClass().getSimpleName());
        }
        ActionMoveImpl actionAgentMove = (ActionMoveImpl) action;
        // Clone the ModelState
        ModelState newModelState = new ModelStateGoal((ModelStateGoal) modelState);

        // Apply the action
        newModelState.agentPositionChange(actionAgentMove.getDelta());
        return newModelState;
    }

}
