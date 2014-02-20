package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Generate new ModelState objects from current object. This version is
 * particularly dumb. 1. Doesn't know about walls.<br>
 * 2. Only knows about movement.<br>
 * 
 * @author bruins
 * 
 */
public final class SuccessorFunctionV1 implements SuccessorFunction {

    /** move agent North. */
    static final ActionAgentMoveV1 NORTH = new ActionAgentMoveV1("North",
            new Point3d(0, 1, 0));
    /** move agent South. */
    static final ActionAgentMoveV1 SOUTH = new ActionAgentMoveV1("South",
            new Point3d(0, -1, 0));
    /** move agent East. */
    static final ActionAgentMoveV1 EAST = new ActionAgentMoveV1("East",
            new Point3d(1, 0, 0));
    /** move agent West. */
    static final ActionAgentMoveV1 WEST = new ActionAgentMoveV1("West",
            new Point3d(-1, 0, 0));
    /** A list of directions that might be possible. */
    private static final ActionAgentMoveV1[] DIRECTIONS = {NORTH, SOUTH, EAST, WEST };

    /** a Transition Function. */
    private TransitionFunction transitionFunction;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionV1(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    @Override
    public Queue<Successor> generateSuccessors(ModelState modelState) {
        // TODO add something smarter in the future.
        Queue<Successor> list = new LinkedList<Successor>();
        for (ActionAgentMoveV1 action : DIRECTIONS) {
            ModelState newModelState = transitionFunction.transition(
                    modelState, action);
            // TODO handle cases where action is not a legal move at this
            // ModelState.
            list.add(new Successor(action, newModelState));
        }
        return list;
    }
}
