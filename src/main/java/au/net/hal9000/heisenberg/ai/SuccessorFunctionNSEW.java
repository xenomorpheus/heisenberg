package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * Generate new ModelState objects from current object. This version is
 * particularly dumb.<br>
 * 1. Doesn't know about walls.<br>
 * 2. Only moves horizontally or vertically.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class SuccessorFunctionNSEW implements SuccessorFunction {

    /** A list of directions that might be possible. */
    private static final ActionMove[] DIRECTIONS = {
            ActionMoveImpl.NORTH, ActionMoveImpl.SOUTH,
            ActionMoveImpl.EAST, ActionMoveImpl.WEST };

    /** a Transition Function. */
    private TransitionFunction transitionFunction;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionNSEW(TransitionFunction transitionFunction) {
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
        // TODO add something smarter in the future.
        Queue<Successor> list = new LinkedList<>();
        for (ActionMove action : DIRECTIONS) {
            ModelState newModelState = transitionFunction.transition(
                    modelState, action);
            // TODO handle cases where action is not a legal move at this
            // ModelState.
            list.add(new SuccessorImpl(newModelState, action, action.getDelta().length()));
        }
        return list;
    }
}
