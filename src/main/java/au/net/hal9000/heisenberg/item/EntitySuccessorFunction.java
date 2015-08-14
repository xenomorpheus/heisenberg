package au.net.hal9000.heisenberg.item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.PathBlockDetails;
import au.net.hal9000.heisenberg.ai.SuccessorImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.action.ActionAgentMoveAbsoluteImpl;
import au.net.hal9000.heisenberg.item.action.ActionEat;
import au.net.hal9000.heisenberg.item.modelstate.ModelStateCatRat;
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
     * @param barriers
     *            a list of barriers.
     * @return a list of possible actions from this model state.
     */
    private List<Action> generateActionsMovement(ModelState modelState,
            List<Barrier> barriers) {
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
                    actions.add(new ActionAgentMoveAbsoluteImpl(goalPos
                            .duplicate(), goalDist));
                }
            }

            // Add various movements to the list of actions.
            // Build a list of spokes from this Position.
            agentPositionDelta.setVectorLength(stepSize);
            spokes = Geometry.generateSpokesZ(agentPositionDelta,
                    directionCount);
            SpokeLoop: for (Position spoke : spokes) {

                Position movementPartial = spoke.duplicate();
                // For any actions, remove likely invalid actions.
                // E.g. don't try to walk through a barrier.

                // Plan if action is a legal move at this ModelState.
                // Use memories of blockers.
                Position agentPosNew = modelStateAgentGoal.getAgentPosition()
                        .duplicate().add(spoke);

                // Predict if we movement may be blocked by a barrier.
                for (Barrier barrier : barriers) {
                    PathBlockDetails pathBlockDetails = barrier
                            .getPathBlockDetailsDetails(agentPos, agentPosNew);
                    // Are we blocked in this direction?
                    if (null != pathBlockDetails) {
                        movementPartial = pathBlockDetails.getBlockingPoint()
                                .subtract(agentPos);

                        // Reduce movement to allow for body width.
                        // This isn't very accurate as angle of incidence may
                        // be very acute.
                        movementPartial.setVectorLength(Math
                                .min(0, movementPartial.length()
                                        - getEntityRadiusMax()));

                        // If movement too small, ignore this Action.
                        if (movementPartial.length() < Position.DEFAULT_AXIS_TOLERANCE) {
                            continue SpokeLoop;
                        }
                        // update agentPosNew
                        agentPosNew = modelStateAgentGoal.getAgentPosition()
                                .duplicate().add(movementPartial);
                    }
                }

                // Using length travelled as a crude value of cost.
                actions.add(new ActionAgentMoveAbsoluteImpl(agentPosNew,
                        movementPartial.length()));
            }

        }
        // TODO add other actions, e.g. attempt to eat prey if close enough.
        if (modelState instanceof ModelStateCatRat) {
            ModelStateCatRat modelStateCatRat = (ModelStateCatRat) modelState;
            Cat cat = modelStateCatRat.getCat();
            Position catPos = cat.getPosition();
            Rat rat = modelStateCatRat.getRat();
            Position ratPos = rat.getPosition();
            Position delta = ratPos.subtract(catPos);
            double goalDist = delta.length();
            if (goalDist < 1.0f){
                    actions.add(new ActionEat(cat, rat, 1f));
            }
        }
        // TODO add other actions, e.g. looking - updates memory of barriers.
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
        Queue<Successor> successors = new LinkedList<>();

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
        List<Action> actions = generateActionsMovement(currentModelState,
                barriers);

        for (Action action : actions) {
            ModelState modelStateNew = transitionFunction.transition(
                    currentModelState, action);
            successors.add(new SuccessorImpl(modelStateNew, action, action
                    .getCost()));
        }
        return successors;
    }

}
