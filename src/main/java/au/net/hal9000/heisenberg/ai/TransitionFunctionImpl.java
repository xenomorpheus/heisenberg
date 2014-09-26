package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class TransitionFunctionImpl implements TransitionFunction {

    /**
     * Method transition.
     * @param modelState ModelState
     * @param action Action
     * @return ModelState
     * @see au.net.hal9000.heisenberg.ai.api.TransitionFunction#transition(ModelState, Action)
     */
    @Override
    public ModelState transition(ModelState modelState, Action action) {
        if (!(modelState instanceof ModelStateImpl)) {
            throw new IllegalArgumentException("Expecting ModelStateV1 but got"
                    + modelState.getClass().getSimpleName());
        }
        if (!(action instanceof ActionMoveImpl)) {
            throw new IllegalArgumentException(
                    "Expecting ActionAgentMove but got"
                            + action.getClass().getSimpleName());
        }
        ModelState modelStateV1 = (ModelState) modelState;
        ActionMoveImpl actionAgentMove = (ActionMoveImpl) action;
        // Clone the ModelState
        ModelState newModelState;
        try {
            newModelState = modelStateV1.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        // Apply the action
        Point3d agentPosition = newModelState.getAgentPosition();
        agentPosition.applyDelta(actionAgentMove.getDelta());
        return newModelState;
    }

}
