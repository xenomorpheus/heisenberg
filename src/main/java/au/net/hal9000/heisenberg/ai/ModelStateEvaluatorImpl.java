package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

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
     * Field GOAL_TOLERANCE.
     * (value is 0.01)
     */
    public static final double GOAL_TOLERANCE = 0.01;

    /** Constructor. */
    public ModelStateEvaluatorImpl() {
        super();
    }

    /**
     * Method evaluate.
     * @param modelState ModelState
     * @return double
     * @see au.net.hal9000.heisenberg.ai.ModelStateEvaluator#evaluate(ModelState)
     */
    @Override
    public double evaluate(ModelState modelState) {
        double result;
        if (modelState instanceof ModelStateImpl) {
            ModelState modelV1 = (ModelState) modelState;
            Point3d agentPosition = modelV1.getAgentPosition();
            if (null == agentPosition) {
                throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
            }
            Point3d goalPosition = modelV1.getGoalPosition();
            if (null == goalPosition) {
                throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
            }
            result = agentPosition.distance(goalPosition);
        } else {
            throw new IllegalArgumentException(MODEL_STATE_NOT_SUPPORTED);
        }
        return result;
    }

    /**
     * Method isAtGoal.
     * @param modelState ModelState
     * @return boolean
     * @see au.net.hal9000.heisenberg.ai.ModelStateEvaluator#isAtGoal(ModelState)
     */
    @Override
    public boolean isAtGoal(ModelState modelState) {
        return evaluate(modelState) < GOAL_TOLERANCE;
    }
}
