package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.item.Item;

public class ModelStateEvaluatorV1 implements ModelStateEvaluator {

    /** error. */
    public static final String GOAL_MAY_MAY_NOT_BE_NULL = "goal may not be null";
    /** error. */
    public static final String AGENT_MAY_MAY_NOT_BE_NULL = "agent may not be null";
    /** error. */
    public static final String MODEL_NOT_SUPPORTED = "model not supported";

    ModelStateEvaluatorV1() {
    }

    @Override
    public double evaluate(Model model) {
        double result;
        if (model instanceof ModelV1) {
            ModelV1 modelV1 = (ModelV1) model;
            Item agent = modelV1.getAgent();
            if (agent == null) {
                throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
            }
            Item goal = modelV1.getGoal();
            if (goal == null) {
                throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
            }
            result = agent.distanceEuclidean(goal);
        } else {
            throw new IllegalArgumentException(MODEL_NOT_SUPPORTED);
        }
        return result;
    }

}
