package au.net.hal9000.heisenberg.unused;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.ActionMoveImpl;
import au.net.hal9000.heisenberg.ai.ModelStateImpl;
import au.net.hal9000.heisenberg.ai.SuccessorImpl;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Geometry;

/**
 * Generate new ModelState objects from current ModelState object and
 * transitionFunction.<br>
 * This version is particularly dumb. <br>
 * 1. It generates 4 Successors at 90 degrees to each other, with a step of 1.0
 * unit. <br>
 * 2. Doesn't know about walls.<br>
 * 3. Does know about goals.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class SuccessorFunctionGoalSpoke implements SuccessorFunction {

    // We may want to someday set these by the constructor
    /** how far to move. */
    private final double stepSize = 1.0;
    /** how many directions to consider. */
    private final int directionCount = 4;

    /** a Transition Function. */
    private TransitionFunction transitionFunction;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public SuccessorFunctionGoalSpoke(TransitionFunction transitionFunction) {
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
            Position agent = modelStateV1.getAgentPosition();
            Position goal = modelStateV1.getGoalPosition();
            Position delta = agent.add(goal);
            double length = delta.length();
            // Limit step size to what agent can achieve
            if (length > stepSize) {
                length = stepSize;
                delta.setVectorLength(stepSize);
            }

            List<Position> spokes = Geometry.generateSpokesZ(delta,
                    directionCount);
            for (Position spoke : spokes) {
                ActionMove action = new ActionMoveImpl(spoke);
                ModelState newModelState = transitionFunction.transition(
                        modelState, action);
                // TODO handle cases where action is not a legal move at
                // this ModelState.
                // Using length traveled as a crude value of cost.
                list.add(new SuccessorImpl(newModelState, action, length));
            }
        }
        return list;
    }
}
