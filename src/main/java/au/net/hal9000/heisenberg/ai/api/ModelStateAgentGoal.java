package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

public interface ModelStateAgentGoal extends ModelState {

  /** @return get agent position. */
  Position getAgentPosition();

  /** @return get goal position. */
  Position getGoalPosition();

  /** @param position set agent position. */
  void setAgentPosition(Position agentPositionTarget);
}
