package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Generate new ModelState objects from current object. This version is
 * particularly dumb.<br>
 * 1. Doesn't know about walls.<br>
 * 2. Only moves horizontally or vertically.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class SuccessorFunctionV1 implements SuccessorFunction {

    /** A list of directions that might be possible. */
    private static final ActionAgentMoveV1[] DIRECTIONS = {
            ActionAgentMoveV1.NORTH, ActionAgentMoveV1.SOUTH,
            ActionAgentMoveV1.EAST, ActionAgentMoveV1.WEST };

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

    /**
     * Method generateSuccessors.
     * 
     * @param modelState
     *            ModelState
     * @return Queue<Successor>
     * @see au.net.hal9000.heisenberg.ai.SuccessorFunction#generateSuccessors(ModelState)
     */
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
