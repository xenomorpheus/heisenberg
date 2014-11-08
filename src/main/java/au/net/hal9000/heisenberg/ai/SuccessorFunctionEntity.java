package au.net.hal9000.heisenberg.ai;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
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

    /** A Transition Function allows movement from one model state to another. */
    private TransitionFunction transitionFunction;
    /** How far to move. */
    private double stepSize = 1.0;
    /** How many directions to consider. */
    private int directionCount = 4;

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
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     * @param stepSize
     *            maximum distance to travel.
     * @param directionCount
     *            maximum directions to consider from any one state.
     */
    public SuccessorFunctionEntity(TransitionFunction transitionFunction,
            double stepSize, int directionCount) {
        this.transitionFunction = transitionFunction;
        this.stepSize = stepSize;
        this.directionCount = directionCount;
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
        Position agentPos = modelState.getAgentPosition();
        List<Action> actions = new ArrayList<>();
        List<Barrier> barriers = new ArrayList<>();
        double agentStepSize = stepSize;
        Position agentPositionDelta = new Position(0, agentStepSize);

        // Get a list of Barriers from memorySet.
        if (modelState instanceof ModelStateMemorySet) {
            ModelStateMemorySet modelStateMemorySet = (ModelStateMemorySet) modelState;
            MemorySet memorySet = modelStateMemorySet.getMemorySet();
            for (MemoryImpl memory : memorySet) {
                if (memory instanceof MemoryOfBarrier) {
                    barriers.add(((MemoryOfBarrier) memory).getBarrier());
                }
            }
        }

        // If we know where the goal is, then the have a possible action in that
        // direction.
        if (modelState instanceof ModelStateGoal) {
            ModelStateGoal modelStateGoal = (ModelStateGoal) modelState;
            Position goalPos = modelStateGoal.getGoalPosition();
            if (null != goalPos) {
                agentPositionDelta = goalPos.subtract(agentPos);
                agentStepSize = agentPositionDelta.length();
                // Limit step size to what agent can achieve
                if (agentStepSize > stepSize) {
                    agentStepSize = stepSize;
                    agentPositionDelta.setVectorLength(stepSize);
                }
            }
        }

        // Add various movements to the list of actions.
        // Build a list of spokes from this Position.
        List<Position> spokes = Geometry.generateSpokesZ(agentPositionDelta,
                directionCount);
        for (Position spoke : spokes) {
            actions.add(new ActionMoveImpl(spoke));
        }

        // For any actions, plan if likely valid action.
        // E.g. not walk into a barrier.
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
                        agentPos.getY(), agentNewPos.getX(), agentNewPos.getY());
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
                        agentStepSize));
            }
        }
        return successors;
    }
}
