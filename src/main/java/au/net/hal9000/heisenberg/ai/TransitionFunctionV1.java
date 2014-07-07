package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class TransitionFunctionV1 implements TransitionFunction {

    /**
     * Method transition.
     * @param modelState ModelState
     * @param action Action
     * @return ModelState
     * @see au.net.hal9000.heisenberg.ai.TransitionFunction#transition(ModelState, Action)
     */
    @Override
    public ModelState transition(ModelState modelState, Action action) {
        if (!(modelState instanceof ModelStateV1)) {
            throw new IllegalArgumentException("Expecting ModelStateV1 but got"
                    + modelState.getClass().getSimpleName());
        }
        if (!(action instanceof ActionV1)) {
            throw new IllegalArgumentException(
                    "Expecting ActionAgentMove but got"
                            + action.getClass().getSimpleName());
        }
        ModelStateV1 modelStateV1 = (ModelStateV1) modelState;
        ActionV1 actionAgentMove = (ActionV1) action;
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
