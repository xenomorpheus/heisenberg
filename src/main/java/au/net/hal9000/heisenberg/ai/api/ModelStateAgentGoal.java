package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

public interface ModelStateAgentGoal extends ModelState {

  /**
   * @return get agent position.
   */
  Position getAgentPosition();

  /**
   * @return get goal position.
   */
  Position getGoalPosition();

  /**
   * Sets the position of the agent in the model state.
   *
   * @param agentPositionTarget the position to set for the agent
   */
  void setAgentPosition(Position agentPositionTarget);
}
