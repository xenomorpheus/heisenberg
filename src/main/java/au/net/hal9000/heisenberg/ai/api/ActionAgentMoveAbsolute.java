package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

/**
 * Very simple actions - Agent movement only.<br>
 * Position is the desired position.<br>
 * TODO add apply() method which will call the agent's move method.
 */
public interface ActionAgentMoveAbsolute extends Action {

  /**
   * Get the Agent's desired/target position.
   *
   * @return the Agent's desired/target position.
   */
  Position getAgentTarget();

  /**
   * Set the Agent's desired/target position.
   *
   * @param position the Agent's desired/target position.
   */
  void setAgentTarget(Position position);
}
