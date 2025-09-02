package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.PathBlockDetails;
import au.net.hal9000.heisenberg.ai.PathImpl;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate movement Action objects in the shape of a wheel spoke, using current position as centre.
 * <br>
 * This class also knows about Barriers, that which blocks movement.
 */
public class ActionGeneratorMovementAbsoluteBarriers implements ActionGenerator {
  private double stepSizeMax;
  private int directionCount;
  private double bodyRadiusMax;

  /**
   * Constructs an ActionGeneratorMovementAbsoluteBarriers instance with the specified parameters.
   *
   * @param stepSizeMax The maximum step size the agent can take in one movement.
   * @param directionCount The number of directions to generate movement actions.
   * @param bodyRadiusMax The maximum radius of the agent's body.
   */
  public ActionGeneratorMovementAbsoluteBarriers(
      double stepSizeMax, int directionCount, double bodyRadiusMax) {
    this.stepSizeMax = stepSizeMax;
    this.directionCount = directionCount;
    this.bodyRadiusMax = bodyRadiusMax;
  }

  // TODO unit tests MovementAbsoluteBarriers

  @Override
  public Path generateActions(ModelState modelState) {
    if (stepSizeMax < Position.DEFAULT_AXIS_TOLERANCE) {
      throw new RuntimeException("Agent's default step size is below Position tolerance.");
    }
    Path actions = new PathImpl();

    // If we know where the goal is, then have a try an action in that
    // direction.
    if (modelState instanceof ModelStateAgentGoal) {
      Position agentPositionDelta = new Position(0, stepSizeMax);
      ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
      Position agentPos = modelStateAgentGoal.getAgentPosition();
      Position goalPos = modelStateAgentGoal.getGoalPosition();
      if (goalPos != null) {
        agentPositionDelta = goalPos.subtract(agentPos);
        double goalDist = agentPositionDelta.length();

        // Limit step size to what agent can achieve
        if (goalDist > stepSizeMax) {
          goalDist = stepSizeMax;
          agentPositionDelta.setVectorLength(goalDist);
        }
        // This line is very important. It is the short step at the end.
        if (goalDist < stepSizeMax) {
          actions.add(new ActionAgentMoveAbsoluteImpl(goalPos.duplicate(), goalDist));
        }
      }
      agentPositionDelta.setVectorLength(stepSizeMax);

      // Get a list of Barriers from memorySet.
      List<Barrier> barriers = new ArrayList<>();
      if (modelState instanceof ModelStateAgentGoalMemorySet) {
        ModelStateAgentGoalMemorySet modelStateMemorySet =
            (ModelStateAgentGoalMemorySet) modelState;
        MemorySet memorySet = modelStateMemorySet.getMemorySet();
        if (memorySet != null) {
          for (Memory memory : memorySet) {
            if (memory instanceof MemoryOfBarrier) {
              barriers.add(((MemoryOfBarrier) memory).getBarrier());
            }
          }
        }
      }

      // Add movement Action objects in the shape of a wheel spoke, using
      // current position as centre.
      // TODO consider creating a lesser interface that only knows about
      // Agent Position.
      // This will allow ModelState where Goal position not known.
      actions.addAll(
          generate(agentPos, agentPositionDelta, directionCount, bodyRadiusMax, barriers));
    }
    return actions;
  }

  /**
   * Generate movement Action objects in the shape of a wheel spoke, using current position as
   * centre.
   *
   * @param position The starting position of the agent.
   * @param positionDelta The change in position for each movement step.
   * @param directionCount The number of directions to generate movement actions.
   * @param bodyRadiusMax The maximum radius of the agent's body.
   * @param barriers A list of barriers that may block movement.
   * @return A path containing the generated movement actions.
   */
  private Path generate(
      Position position,
      Position positionDelta,
      int directionCount,
      double bodyRadiusMax,
      List<Barrier> barriers) {
    Path actions = new PathImpl();
    // Build a list of spokes from this Position.
    List<Position> spokes = Geometry.generateSpokesZ(positionDelta, directionCount);
    SpokeLoop:
    for (Position spoke : spokes) {

      Position movementPartial = spoke.duplicate();
      // For any actions, remove likely invalid actions.
      // E.g. don't try to walk through a barrier.

      // Plan if action is a legal move at this ModelState.
      // Use memories of blockers.
      Position positionNew = position.duplicate().add(spoke);

      // Predict if we movement may be blocked by a barrier.
      if (barriers != null) {
        for (Barrier barrier : barriers) {
          PathBlockDetails pathBlockDetails =
              barrier.getPathBlockDetailsDetails(position, positionNew);
          // Are we blocked in this direction?
          if (pathBlockDetails != null) {
            movementPartial = pathBlockDetails.getBlockingPoint().subtract(position);

            // Reduce movement to allow for body width.
            // This isn't very accurate as angle of incidence may
            // be very acute.
            movementPartial.setVectorLength(Math.min(0, movementPartial.length() - bodyRadiusMax));

            // If movement too small, ignore this Action.
            if (movementPartial.length() < Position.DEFAULT_AXIS_TOLERANCE) {
              continue SpokeLoop;
            }
            // update agentPosNew
            positionNew.set(position);
            positionNew.applyDelta(movementPartial);
          }
        }
      }

      // Using length travelled as a crude value of cost.
      actions.add(new ActionAgentMoveAbsoluteImpl(positionNew, movementPartial.length()));
    }
    return actions;
  }
}
