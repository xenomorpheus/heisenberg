package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Generate new ModelState objects from current object.<br>
 * This version is particularly dumb. <br>
 * 1. Doesn't know about walls.<br>
 * 
 * @author bruins
 * 
 */
public final class SuccessorFunctionV2 implements SuccessorFunction {
    /** a Transition Function. */
    private TransitionFunction transitionFunction;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionV2(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    @Override
    public Queue<Successor> generateSuccessors(ModelState modelState) {
        final double stepSize = 1.0; // TODO movement rate
        final int directionCount = 4;

        Queue<Successor> list = new LinkedList<Successor>();
        if (modelState instanceof ModelStateV1) {
            ModelStateV1 modelStateV1 = (ModelStateV1) modelState;
            Point3d agent = modelStateV1.getAgentPosition();
            Point3d goal = modelStateV1.getGoalPosition();
            Point3d delta = agent.delta(goal);
            double crowFliesDistanceToGoal = delta.length();
            // Limit step size to what agent can achieve
            if (crowFliesDistanceToGoal > stepSize) {
                delta.setVectorLength(stepSize);
            }
            // full circle divided equally into directions.
            double theta = Math.PI * 2 / directionCount;

            for (int directionIndex = 0; directionIndex < directionCount; directionIndex++) {
                Action action = new ActionAgentMoveV1(null, delta);
                ModelState newModelState = transitionFunction.transition(
                        modelState, action);
                // TODO handle cases where action is not a legal move at
                // this ModelState.
                list.add(new Successor(action, newModelState));
                delta = delta.clone();
                delta.rotateZ(theta);
            }
        }
        return list;
    }
}