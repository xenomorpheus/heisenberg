package au.net.hal9000.heisenberg.ai.api;

import java.util.List;

import au.net.hal9000.heisenberg.ai.Memory;
import au.net.hal9000.heisenberg.units.Position;

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

    /**
     * 
     * @return get goal position.
     */
    Position getGoalPosition();

    /**
     * @param position
     *            set goal position.
     */
    void setGoalPosition(Position position);


    /**
     * @return the memories e.g. walls
     */
    List<Memory> getMemories();
    
    // Misc
    /**
     * @param delta
     *            alter agent position.
     */
    void agentPositionChange(Position delta);


}
