package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveRelative;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoal;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Very simple actions - Agent movement only.<br>
 * Position is the change in position.<br>
 */
public final class ActionAgentMoveRelativeImpl extends ActionBase
    implements ActionAgentMoveRelative {

  /** Agent's movement relative to current position. */
  private Position agentPositionDelta;

  /**
   * Constructor.
   *
   * @param agentPositionDelta Agent's movement relative to current position.
   * @param cost the effort / cost of performing the action
   */
  public ActionAgentMoveRelativeImpl(final Position agentPositionDelta, double cost) {
    super(cost);
    this.agentPositionDelta = agentPositionDelta;
  }

  // Getters and Setters

  @Override
  public Position getPositionDelta() {
    return agentPositionDelta;
  }

  @Override
  public void setPositionDelta(Position position) {
    agentPositionDelta = position;
  }

  // Misc
  @Override
  public void apply(ModelState modelState) {
    if (!(modelState instanceof ModelStateAgentGoal)) {
      throw new RuntimeException("Expecting ModelStateAgentGoal");
    }
    ModelStateAgentGoal modelStateAgentGoal = (ModelStateAgentGoal) modelState;
    modelStateAgentGoal.getAgentPosition().applyDelta(agentPositionDelta);
    // TODO consider reduce energy of Entity
  }

  // Misc
  /**
   * Method toString.
   *
   * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(3);
    sb.append("ActionMove=[delta=");
    sb.append(agentPositionDelta);
    sb.append(']');
    return sb.toString();
  }

  /**
   * Method hashCode.
   *
   * @return int
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(getCost());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((agentPositionDelta == null) ? 0 : agentPositionDelta.hashCode());
    return result;
  }

  /**
   * Method equals.
   *
   * @param obj Object
   * @return boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ActionAgentMoveRelativeImpl other = (ActionAgentMoveRelativeImpl) obj;
    if (Double.doubleToLongBits(getCost()) != Double.doubleToLongBits(other.getCost()))
      return false;
    if (agentPositionDelta == null) {
      if (other.getPositionDelta() != null) {
        return false;
      }
    } else if (!agentPositionDelta.equals(other.getPositionDelta())) {
      return false;
    }
    return true;
  }
}
