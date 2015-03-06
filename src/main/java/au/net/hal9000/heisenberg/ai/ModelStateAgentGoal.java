package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds agent position and goal position.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public class ModelStateAgentGoal implements ModelState {

    /** The agent moving to goal. */
    private Position agentPosition;

    /** Where the agent wants to be. */
    private Position goalPosition;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param goalPosition
     *            goal position.
     */
    ModelStateAgentGoal(Position agentPosition, Position goalPosition) {
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

    // getters and setters

    /**
     * @return get goal position.
     */
    public Position getGoalPosition() {
        return goalPosition;
    }

    /**
     * @param position3d
     *            goal position to set.
     */
    public void setGoalPosition(Position position3d) {
        goalPosition = position3d;
    }

    // overridden methods

    /** {@inheritDoc} */
    @Override
    public ModelStateAgentGoal duplicate() {
        return new ModelStateAgentGoal(new Position(getAgentPosition()),
                new Position(getGoalPosition()));
    }

    // Getters and Setters
    /**
     * @return get agent position.
     */
    public Position getAgentPosition() {
        return agentPosition;
    }

    /**
     * @param position
     *            set agent position.
     */
    public void setAgentPosition(Position position3d) {
        agentPosition = position3d;
    }

    // Misc
    /**
     * @param delta
     *            alter agent position.
     */
    // void agentPositionChange(Position delta);


    // Misc
    public void agentPositionChange(Position delta) {
        agentPosition.applyDelta(delta);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((agentPosition == null) ? 0 : agentPosition.hashCode());
        result = prime * result
                + ((goalPosition == null) ? 0 : goalPosition.hashCode());
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
        ModelStateAgentGoal other = (ModelStateAgentGoal) obj;
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
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "=[agent " + getAgentPosition()
                + ", goal " + getGoalPosition() + "]";
    }

}
