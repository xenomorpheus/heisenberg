package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Generate new ModelState objects from current object.
 * 
 * @author bruins
 * 
 */
public final class SuccessorFunctionV1 implements SuccessorFunction {

    private static final ActionAgentMove NORTH = new ActionAgentMove(new Point3d(0, 1, 0));
    private static final ActionAgentMove SOUTH = new ActionAgentMove(new Point3d(0, -1, 0));
    private static final ActionAgentMove EAST = new ActionAgentMove(new Point3d(-1, 0, 0));
    private static final ActionAgentMove WEST = new ActionAgentMove(new Point3d(1, 0, 0));
    private static final ActionAgentMove[] DIRECTIONS = {NORTH, SOUTH, EAST, WEST };

    private TransitionFunction transitionFunction;
    
    /**
     * Constructor.
     */
    public SuccessorFunctionV1(TransitionFunction transitionFunction) {
        this.transitionFunction = transitionFunction;
    }


    @Override
    public Queue<Successor> generateSuccessors(ModelState modelState) {
        // TODO add something smarter in the future.
        Queue<Successor> list = new LinkedList<Successor>();
        for (ActionAgentMove action : DIRECTIONS) {
            ModelState newModelState = transitionFunction.transition(modelState, action);
            // TODO handle cases where action is not a legal move at this ModelState.
            list.add(new Successor((Action) action, (ModelState) newModelState));
        }
        return list;
    }
}
