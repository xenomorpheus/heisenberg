package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.ai.api.ModelStateConsumerConsumable;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.units.Position;
import java.util.List;

public class ModelStateEvaluatorAgentGoal implements ModelStateEvaluator {

  /** error. */
  private final String GOAL_MAY_MAY_NOT_BE_NULL = "goal may not be null";

  /** error. */
  private final String AGENT_MAY_MAY_NOT_BE_NULL = "agent may not be null";

  /**
   * How close do we need to be to consider two states equal. Both consideration for isAtGoal, and
   * if we have added a state to the fringe.
   */
  private double positionTolerance = Position.DEFAULT_AXIS_TOLERANCE;

  // Constructor(s)

  public ModelStateEvaluatorAgentGoal() {
    super();
  }

  // Getters and Setters
  public double getPositionTolerance() {
    return positionTolerance;
  }

  public void setPositionTolerance(double positionDistance) {
    if (positionDistance < Position.DEFAULT_AXIS_TOLERANCE) {
      throw new RuntimeException("too low");
    }
    this.positionTolerance = positionDistance;
  }

  // Misc

  @Override
  public double costToGoalEstimate(ModelState modelState) {
    ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
    Position agentPosition = modelStateAgentGoal.getAgentPosition();
    if (agentPosition == null) {
      throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
    }
    Position goalPosition = modelStateAgentGoal.getGoalPosition();
    if (goalPosition == null) {
      throw new IllegalArgumentException(GOAL_MAY_MAY_NOT_BE_NULL);
    }
    return agentPosition.distance(goalPosition);
  }

  @Override
  public boolean isAtGoal(ModelState modelState) {
    return costToGoalEstimate(modelState) < positionTolerance;
  }

  @Override
  public boolean isModelStateInAdded(List<ModelState> addedModelStates, ModelState modelState) {

    // Check if we have been here.
    boolean hereBefore = false;
    for (ModelState modelStateOther : addedModelStates) {

      if (modelState.getClass() != modelStateOther.getClass()) {
        continue;
      }

      // ModelStateAgentGoal
      if (modelState instanceof ModelStateAgentGoal) {
        ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
        Position agentPosition = modelStateAgentGoal.getAgentPosition();
        if (agentPosition == null) {
          throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
        }

        ModelStateAgentGoal modelStateOtherAgentGoal = (ModelStateAgentGoal) modelStateOther;
        Position agentOtherPosition = modelStateOtherAgentGoal.getAgentPosition();
        if (agentOtherPosition == null) {
          throw new IllegalArgumentException(AGENT_MAY_MAY_NOT_BE_NULL);
        }

        double costEst = agentPosition.distance(agentOtherPosition);
        if (costEst > positionTolerance) {
          continue;
        }
      }

      // ModelStateConsumerConsumable
      if (modelState instanceof ModelStateConsumerConsumable) {
        ModelStateConsumerConsumable modelStateConsumerConsumable =
            (ModelStateConsumerConsumable) modelState;
        ModelStateConsumerConsumable modelStateConsumerConsumableOther =
            (ModelStateConsumerConsumable) modelStateOther;
        if (modelStateConsumerConsumable.getConsumed()
            != modelStateConsumerConsumableOther.getConsumed()) {
          continue;
        }
      }

      hereBefore = true;
      break;
    }
    return hereBefore;
  }
}
