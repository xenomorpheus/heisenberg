package au.net.hal9000.heisenberg.ai;

import java.util.List;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.units.Position;

public class ModelStateEvaluatorAgentGoal implements ModelStateEvaluator {

    /** error. */
    final String GOAL_MAY_MAY_NOT_BE_NULL = "goal may not be null";
    /** error. */
    final String AGENT_MAY_MAY_NOT_BE_NULL = "agent may not be null";

    /**
     * Field GOAL_TOLERANCE.
     */
    // Don't change this. isAtGoal uses Position.equals()
    public static final double GOAL_TOLERANCE = Position.DEFAULT_AXIS_TOLERANCE;

    /** {@inheritDoc} */
    public ModelStateEvaluatorAgentGoal() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public double costToGoalEstimate(ModelState modelState) {
        ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
        Position agentPosition = modelStateAgentGoal.getAgentPosition();
        if (null == agentPosition) {
            throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
        }
        Position goalPosition = modelStateAgentGoal.getGoalPosition();
        if (null == goalPosition) {
            throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
        }
        return agentPosition.distance(goalPosition);
    }

    @Override
    public boolean isAtGoal(ModelState modelState) {
        return costToGoalEstimate(modelState) < GOAL_TOLERANCE;
    }

    @Override
    public boolean modelStateInAdded(List<ModelState> addedModelStates,
            ModelState modelState) {

        // Check if we have been here.
        boolean hereBefore = false;
        for (ModelState modelStateOther : addedModelStates) {
            if (modelState.equals(modelStateOther)){
                hereBefore = true;
                break;
            }
        }
        return hereBefore;
    }

}
