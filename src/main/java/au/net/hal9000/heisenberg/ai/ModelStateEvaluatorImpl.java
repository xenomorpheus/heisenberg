package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.units.Position;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ModelStateEvaluatorImpl implements ModelStateEvaluator {

    /** error. */
    public static final String GOAL_MAY_MAY_NOT_BE_NULL = "goal may not be null";
    /** error. */
    public static final String AGENT_MAY_MAY_NOT_BE_NULL = "agent may not be null";
    /** error. */
    public static final String MODEL_STATE_NOT_SUPPORTED = "model state not supported";

    /**
     * Field GOAL_TOLERANCE. (value is 0.01)
     */
    public static final double GOAL_TOLERANCE = 0.01;

    /** Constructor. */
    public ModelStateEvaluatorImpl() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public double evaluate(ModelState modelState) {
        double result;
        if (modelState instanceof ModelStateGoal) {
            ModelStateGoal modelStateGoal = (ModelStateGoal) modelState;
            Position agentPosition = modelStateGoal.getAgentPosition();
            if (null == agentPosition) {
                throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
            }
            Position goalPosition = modelStateGoal.getGoalPosition();
            if (null == goalPosition) {
                throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
            }
            result = agentPosition.distance(goalPosition);
        } else {
            throw new IllegalArgumentException(MODEL_STATE_NOT_SUPPORTED);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAtGoal(ModelState modelState) {
        return evaluate(modelState) < GOAL_TOLERANCE;
    }
}
