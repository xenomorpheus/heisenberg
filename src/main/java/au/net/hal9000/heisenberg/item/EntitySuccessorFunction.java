package au.net.hal9000.heisenberg.item;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.ActionMoveImpl;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.PathBlockDetails;
import au.net.hal9000.heisenberg.ai.SuccessorImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;
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
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class EntitySuccessorFunction implements SuccessorFunction {

    /** A Transition Function allows movement from one model state to another. */
    private TransitionFunction transitionFunction;
    /** How far to move. */
    private double stepSize = 1.0;
    /** How many directions to consider. */
    private int directionCount = 4;

    /** Maximum radius of Entity. Used to avoid barriers */
    private float entityRadiusMax;

    /**
     * Constructor.
     * 
     * @param transitionFunction
     *            a Transition Function.
     */
    public EntitySuccessorFunction(TransitionFunction transitionFunction) {
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
    public EntitySuccessorFunction(TransitionFunction transitionFunction,
            double stepSize, int directionCount) {
        this.transitionFunction = transitionFunction;
        this.stepSize = stepSize;
        this.directionCount = directionCount;
    }

    // Getters and Setters

    public void setEntityRadiusMax(float entityRadiusMax) {
        this.entityRadiusMax = entityRadiusMax;
    }

    public float getEntityRadiusMax() {
        return entityRadiusMax;
    }

    // Misc
    /**
     * Generate a list of possible actions from this model state.
     * 
     * @param modelState
     *            current model state
     * @return a list of possible actions from this model state.
     */
    private List<Action> buildActions(ModelState modelState) {
        List<Action> actions = new ArrayList<>();
        Position agentPositionDelta = new Position(0, stepSize);
        List<Position> spokes;

        if (stepSize < Position.DEFAULT_AXIS_TOLERANCE) {
            throw new RuntimeException(
                    "Agent's default step size is below Position tollerance.");
        }

        // If we know where the goal is, then have a try an action in that
        // direction.
        if (modelState instanceof ModelStateAgentGoal) {
            ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
            Position agentPos = modelStateAgentGoal.getAgentPosition();
            Position goalPos = modelStateAgentGoal.getGoalPosition();
            if (null != goalPos) {
                agentPositionDelta = goalPos.subtract(agentPos);
                double goalDist = agentPositionDelta.length();

                // Limit step size to what agent can achieve
                if (goalDist > stepSize) {
                    goalDist = stepSize;
                    agentPositionDelta.setVectorLength(goalDist);
                }
                // This line is very important. It is the short step at the end.
                if (goalDist < stepSize) {
                    actions.add(new ActionMoveImpl(new Position(
                            agentPositionDelta)));
                }
            }
        }

        // Add various movements to the list of actions.
        // Build a list of spokes from this Position.
        agentPositionDelta.setVectorLength(stepSize);
        spokes = Geometry.generateSpokesZ(agentPositionDelta, directionCount);
        for (Position spoke : spokes) {
            actions.add(new ActionMoveImpl(spoke));
        }

        // TODO add other actions, e.g. attempt to eat prey if close enough.

        return actions;
    }

    /**
     * Generate a list of possible successors from current model state.
     * 
     * @param currentModelState
     *            current ModelState
     * @return A list of successors from current model state.
     */
    @Override
    public Queue<Successor> generateSuccessors(ModelState currentModelState) {
        if (!(currentModelState instanceof ModelStateAgentGoal)) {
            throw new RuntimeException("Expecting ModelStateAgentGoal");
        }
        ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) currentModelState;
        Queue<Successor> successors = new LinkedList<>();
        List<Action> actions = buildActions(currentModelState);
        Position agentPos = modelStateAgentGoal.getAgentPosition();

        // Get a list of Barriers from memorySet.
        List<Barrier> barriers = new ArrayList<>();
        if (currentModelState instanceof ModelStateAgentGoalMemorySet) {
            ModelStateAgentGoalMemorySet modelStateMemorySet = (ModelStateAgentGoalMemorySet) currentModelState;
            MemorySet memorySet = modelStateMemorySet.getMemorySet();
            for (Memory memory : memorySet) {
                if (memory instanceof MemoryOfBarrier) {
                    barriers.add(((MemoryOfBarrier) memory).getBarrier());
                }
            }
        }

        // For any actions, remove likely invalid actions.
        // E.g. don't try to walk through a barrier.
        ActionLoop: for (Action action : actions) {
            ModelState modelStateNew = transitionFunction.transition(
                    currentModelState, action);
            if (!(modelStateNew instanceof ModelStateAgentGoal)) {
                throw new RuntimeException("Expecting ModelStateAgentGoal");
            }
            ModelStateAgentGoal modelStateAgentGoalNew = (ModelStateAgentGoal) modelStateNew;
            // Using length travelled as a crude value of cost.
            double actionCost = stepSize;

            if (action instanceof ActionMove) {
                ActionMove actionMove = (ActionMove) action;
                // Plan if action is a legal move at this ModelState.
                // Use memories of blockers.
                Position agentPosNew = modelStateAgentGoalNew
                        .getAgentPosition();

                for (Barrier barrier : barriers) {
                    // Plan if we are blocked by this barrier.
                    // Movement from old agent position to new agent position.
                    Line2D movement = new Line2D.Double(agentPos.getX(),
                            agentPos.getY(), agentPosNew.getX(),
                            agentPosNew.getY());
                    PathBlockDetails pathBlockDetails = barrier
                            .getPathBlockDetailsDetails(movement);
                    // Are we blocked in this direction?
                    if (null != pathBlockDetails) {
                        Position movementPartial = pathBlockDetails
                                .getBlockingPoint().subtract(agentPos);

                        // need to allow for body width.
                        movementPartial.setVectorLength(Math.min(0 , movementPartial.length() - getEntityRadiusMax()));

                        // If movement too small, ignore this Action.
                        if (movementPartial.length() < Position.DEFAULT_AXIS_TOLERANCE) {
                            continue ActionLoop;
                        }
                        actionCost = movementPartial.length();
                        actionMove.setPositionDelta(new Position(
                                movementPartial));

                        modelStateAgentGoalNew.setAgentPosition(agentPos
                                .add(movementPartial));
                    }
                }
            }
            successors
                    .add(new SuccessorImpl(modelStateNew, action, actionCost));
        }
        return successors;
    }

}
