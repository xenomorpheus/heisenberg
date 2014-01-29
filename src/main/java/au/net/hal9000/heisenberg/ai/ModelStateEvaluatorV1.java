package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ModelStateEvaluatorV1 implements ModelStateEvaluator {

    /** error. */
    public static final String GOAL_MAY_MAY_NOT_BE_NULL = "goal may not be null";
    /** error. */
    public static final String AGENT_MAY_MAY_NOT_BE_NULL = "agent may not be null";
    /** error. */
    public static final String MODEL_NOT_SUPPORTED = "model not supported";

    /** Constructor.*/
    public ModelStateEvaluatorV1() {
    }

    @Override
    public double evaluate(ModelState model) {
        double result;
        if (model instanceof ModelStateV1) {
            ModelStateV1 modelV1 = (ModelStateV1) model;
            Point3d agentPosition = modelV1.getAgentPosition();
            if (agentPosition == null) {
                throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
            }
            Point3d goalPosition = modelV1.getGoalPosition();
            if (goalPosition == null) {
                throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
            }
            result = agentPosition.distance(goalPosition);
        } else {
            throw new IllegalArgumentException(MODEL_NOT_SUPPORTED);
        }
        return result;
    }

}
