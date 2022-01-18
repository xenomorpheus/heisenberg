package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds agent position and goal position.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ModelStateAgentGoalImpl implements ModelStateAgentGoal {

  /** The agent moving to goal. */
  private Position agentPosition;

  /** Where the agent wants to be. */
  private Position goalPosition;

  /**
   * Constructor.
   *
   * @param agentPosition agent position.
   * @param goalPosition goal position.
   */
  ModelStateAgentGoalImpl(Position agentPosition, Position goalPosition) {
    super();
    if (null == agentPosition) {
      throw new IllegalArgumentException("agentPosition may not be null");
    }
    if (null == goalPosition) {
      throw new IllegalArgumentException("goalPosition may not be null");
    }
    this.goalPosition = goalPosition;
    this.agentPosition = agentPosition;
  }

  // Getters and Setters

  /** {@inheritDoc} */
  @Override
  public Position getAgentPosition() {
    return agentPosition;
  }

  /** {@inheritDoc} */
  @Override
  public void setAgentPosition(Position position3d) {
    agentPosition = position3d;
  }

  /** {@inheritDoc} */
  @Override
  public Position getGoalPosition() {
    return goalPosition;
  }

  // overridden methods

  /** {@inheritDoc} */
  @Override
  public ModelStateAgentGoalImpl duplicate() {
    return new ModelStateAgentGoalImpl(
        new Position(getAgentPosition()), new Position(getGoalPosition()));
  }

  // Misc

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((agentPosition == null) ? 0 : agentPosition.hashCode());
    result = prime * result + ((goalPosition == null) ? 0 : goalPosition.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ModelStateAgentGoalImpl other = (ModelStateAgentGoalImpl) obj;
    if (agentPosition == null) {
      if (other.agentPosition != null) return false;
    } else if (!agentPosition.equals(other.agentPosition)) return false;
    if (goalPosition == null) {
      if (other.goalPosition != null) return false;
    } else if (!goalPosition.equals(other.goalPosition)) return false;
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "=[agent "
        + getAgentPosition()
        + ", goal "
        + getGoalPosition()
        + "]";
  }
}
