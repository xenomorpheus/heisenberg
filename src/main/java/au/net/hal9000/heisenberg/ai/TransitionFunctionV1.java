package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class TransitionFunctionV1 implements TransitionFunction {

    @Override
    public ModelState transition(ModelState modelState, Action action) {
        if (!(modelState instanceof ModelStateV1)) {
            throw new IllegalArgumentException("Expecting ModelStateV1 but got"
                    + modelState.getClass().getSimpleName());
        }
        if (!(action instanceof ActionAgentMoveV1)) {
            throw new IllegalArgumentException(
                    "Expecting ActionAgentMove but got"
                            + action.getClass().getSimpleName());
        }
        ModelStateV1 modelStateV1 = (ModelStateV1) modelState;
        ActionAgentMoveV1 actionAgentMove = (ActionAgentMoveV1) action;
        // Clone the ModelState
        ModelStateV1 newModelState;
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
