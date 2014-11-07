package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds agent position.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public interface ModelState {

    // Getters and Setters
    /**
     * 
     * @return get agent position.
     */
    Position getAgentPosition();

    /**
     * @param position
     *            set agent position.
     */
    void setAgentPosition(Position position);

    // Misc
    /**
     * @param delta
     *            alter agent position.
     */
    void agentPositionChange(Position delta);

    /**
     * Similar to clone, but now exceptions.
     */
    ModelState duplicate();

}
