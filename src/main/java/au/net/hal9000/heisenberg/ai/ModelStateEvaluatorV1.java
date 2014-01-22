package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.item.Item;

public class ModelStateEvaluatorV1 implements ModelStateEvaluator {

    ModelStateEvaluatorV1() {
    }

    @Override
    public double evaluate(ModelState state) {
        double result;
        if (state instanceof ModelStateV1) {
            ModelStateV1 modelStateV1 = (ModelStateV1) state;
            Item agent = modelStateV1.getAgent();
            if (agent == null) {
                throw new RuntimeException("agent may not be null");
            }
            Item goal = modelStateV1.getGoal();
            if (goal == null) {
                throw new RuntimeException("goal may not be null");
            }
            result = -1.0 * agent.distanceEuclidean(goal);
            // TODO consider also implement distanceManhattan
        } else {
            throw new RuntimeException("Invalid state object of class "
                    + state.getClass().getSimpleName());
        }
        return result;
    }

}
