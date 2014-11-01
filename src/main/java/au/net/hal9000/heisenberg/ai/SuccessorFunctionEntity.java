package au.net.hal9000.heisenberg.ai;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Barrier;
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
public final class SuccessorFunctionEntity implements SuccessorFunction {

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
    public SuccessorFunctionEntity(TransitionFunction transitionFunction) {
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
        Queue<Successor> successors = new LinkedList<>();
        if (modelState instanceof ModelStateImpl) {
            ModelState modelStateV1 = (ModelState) modelState;
            List<Action> actions = new ArrayList<>();
            Position agentPos = modelStateV1.getAgentPosition();
            Position goalPos = modelStateV1.getGoalPosition();
            double deltaLength = stepSize;
            Position delta = new Position(0, stepSize);
            if (null != goalPos) {
                delta = goalPos.subtract(agentPos);
                deltaLength = delta.length();
                // Limit step size to what agent can achieve
                if (deltaLength > stepSize) {
                    deltaLength = stepSize;
                    delta.setVectorLength(stepSize);
                }
            }

            // Add various movements to the list of actions.
            // Build a list of spokes from this Position.
            List<Position> spokes = Geometry.generateSpokesZ(delta,
                    directionCount);
            for (Position spoke : spokes) {
                actions.add(new ActionMoveImpl(spoke));
            }

            // Get a list of Barriers from memories.
            List<Memory> memories = modelStateV1.getMemories();
            List<Barrier> barriers = new ArrayList<>();
            for (Memory memory : memories) {
                if (memory instanceof MemoryOfBarrier) {
                    barriers.add(((MemoryOfBarrier) memory).getBarrier());
                }
            }

            // For any actions, decide if likely valid action.
            for (Action action : actions) {
                ModelState modelStateNew = transitionFunction.transition(
                        modelState, action);
                // Plan if action is a legal move at this ModelState.
                // Use memories of blockers.
                Position agentNewPos = modelStateNew.getAgentPosition();
                boolean legalMove = true;
                for (Barrier barrier : barriers) {
                    // Plan if we are blocked by this barrier.
                    // Movement from old agent position to new agent position.
                    Line2D movement = new Line2D.Double(agentPos.getX(),
                            agentPos.getY(), agentNewPos.getX(),
                            agentNewPos.getY());
                    PathBlockDetails pathBlockDetails = barrier
                            .getPathBlockDetailsDetails(movement);
                    if (null != pathBlockDetails) {
                        // We are blocked in this direction.
                        legalMove = false;
                        break;
                    }
                }
                if (legalMove) {
                    // Using length traveled as a crude value of cost.
                    successors.add(new SuccessorImpl(modelStateNew, action,
                            deltaLength));
                }
            }
        }
        return successors;
    }
}
