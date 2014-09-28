package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Generate new ModelState objects from current ModelState object and
 * transitionFunction.<br>
 * This version is particularly dumb. <br>
 * 1. Doesn't know about walls.<br>
 * 2. It generates 4 Successors at 90 degrees to each other, with a step of 1.0
 * unit.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class SuccessorFunctionSpoke implements SuccessorFunction {
    /** a Transition Function. */
    private TransitionFunction transitionFunction;
    
    // We may want to someday set these by the constructor
    /** how far to move */
    private double stepSize = 1.0;
    /** how many directions to consider */
    private int directionCount = 4;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionSpoke(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    /**
     * Method generateSuccessors.
     * 
     * @param modelState
     *            ModelState
     * @return Queue<Successor>
     * @see au.net.hal9000.heisenberg.ai.api.SuccessorFunction#generateSuccessors(ModelState)
     */
    @Override
    public Queue<Successor> generateSuccessors(ModelState modelState) {

        Queue<Successor> list = new LinkedList<>();
        if (modelState instanceof ModelStateImpl) {
            ModelState modelStateV1 = (ModelState) modelState;
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
                ActionMove action = new ActionMoveImpl(delta);
                ModelState newModelState = transitionFunction.transition(
                        modelState, action);
                // TODO handle cases where action is not a legal move at
                // this ModelState.
                list.add(new SuccessorImpl(newModelState, action, action.getDelta().length()));
                delta = delta.duplicate();
                delta.rotateZ(theta);
            }
        }
        return list;
    }
}
