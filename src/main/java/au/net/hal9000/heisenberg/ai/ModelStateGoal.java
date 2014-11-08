package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds two positions, the agent and the goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ModelStateGoal extends ModelStateImpl implements ModelState {

    /** Where the agent wants to be. */
    private Position goalPosition;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param goalPosition
     *            goal position.
     * @param memories
     *            memories e.g. walls.
     */
    public ModelStateGoal(Position agentPosition, Position goalPosition) {
        super(agentPosition);
        if (null == agentPosition) {
            throw new IllegalArgumentException("agentPosition may not be null");
        }
        if (null == goalPosition) {
            throw new IllegalArgumentException("goalPosition may not be null");
        }
        this.goalPosition = goalPosition;
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
    public ModelState duplicate() {
        return new ModelStateGoal(new Position(getAgentPosition()),
                new Position(getGoalPosition()));
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[agentPos=" + getAgentPosition() + ", goalPos=" + getGoalPosition()
                + "]";
    }
}
