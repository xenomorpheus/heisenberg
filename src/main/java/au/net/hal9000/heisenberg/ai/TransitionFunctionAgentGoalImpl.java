package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
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
        if (!(action instanceof ActionMove)) {
            throw new IllegalArgumentException(
                    "Expecting ActionMove but got "
                            + action.getClass().getSimpleName());
        }
        ActionMove actionAgentMove = (ActionMove) action;

        if (!(modelState instanceof ModelStateAgentGoal)) {
            throw new IllegalArgumentException(
                    "Expecting ModelStateAgentGoal but got "
                            + modelState.getClass().getSimpleName());
        }
        
        ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal)modelState;
        // Clone the ModelState
        ModelStateAgentGoal newModelState = modelStateAgentGoal.duplicate();

        // Apply the action
        newModelState.agentPositionChange(actionAgentMove.getPositionDelta());
        return newModelState;
    }

}
