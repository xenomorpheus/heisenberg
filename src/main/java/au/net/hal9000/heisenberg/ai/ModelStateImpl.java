package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds two positions, the agent and the goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ModelStateImpl implements ModelState, Cloneable {

    /** The agent moving to goal. */
    private Position agentPosition;
    /** Where the agent wants to be. */
    private Position goalPosition;
    /** Memories. e.g. Walls */
    private List<Memory> memories;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param goalPosition
     *            goal position.
     * @param list
     */
    public ModelStateImpl(Position agentPosition, Position goalPosition,
            List<Memory> memories) {
        this.agentPosition = agentPosition;
        this.goalPosition = goalPosition;
        this.memories = memories;
    }

    /**
     * Constructor.
     * 
     * @param modelState
     *            to copy.
     */
    public ModelStateImpl(ModelStateImpl modelState) {
        this(new Position(modelState.getAgentPosition()), new Position(
                modelState.getGoalPosition()), new ArrayList<Memory>(
                modelState.getMemories()));
    }

    /** {@inheritDoc} */
    @Override
    public List<Memory> getMemories() {
        return memories;
    }

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

    /** {@inheritDoc} */
    @Override
    public void setGoalPosition(Position position3d) {
        goalPosition = position3d;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((agentPosition == null) ? 0 : agentPosition.hashCode());
        result = prime * result
                + ((goalPosition == null) ? 0 : goalPosition.hashCode());
        result = prime * result
                + ((memories == null) ? 0 : memories.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelStateImpl other = (ModelStateImpl) obj;
        if (agentPosition == null) {
            if (other.agentPosition != null)
                return false;
        } else if (!agentPosition.equals(other.agentPosition))
            return false;
        if (goalPosition == null) {
            if (other.goalPosition != null)
                return false;
        } else if (!goalPosition.equals(other.goalPosition))
            return false;
        if (memories == null) {
            if (other.memories != null)
                return false;
        } else if (!memories.equals(other.memories))
            return false;
        return true;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[agent=" + agentPosition + ", goal=" + goalPosition + "]"; // TODO
                                                                           // memories
    }

    @Override
    public void agentPositionChange(Position delta) {
        agentPosition.applyDelta(delta);
    }

}
