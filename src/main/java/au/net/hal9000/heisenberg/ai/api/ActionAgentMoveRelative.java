package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

/**
 * Very simple actions - Agent movement only.<br>
 * Position is the change in position.<br>
 * TODO add apply() method which will call the agent's move method.
 */
public interface ActionAgentMoveRelative extends Action {

  /**
   * Get the amount of movement.
   *
   * @return the amount of movement.
   */
  Position getPositionDelta();

  /**
   * Set the amount of movement.
   *
   * @param position the amount of movement to set.
   */
  void setPositionDelta(Position position);
}
