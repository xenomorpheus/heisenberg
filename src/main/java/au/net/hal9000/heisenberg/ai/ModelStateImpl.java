package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds agent position.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public class ModelStateImpl implements ModelState {

    /** The agent moving to goal. */
    private Position agentPosition;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     */
    public ModelStateImpl(Position agentPosition) {
        if (null == agentPosition) {
            throw new IllegalArgumentException("agentPosition may not be null");
        }
        this.agentPosition = agentPosition;
    }

    /**
     * {@inheritDoc}
     */
    public ModelState duplicate() {
        return new ModelStateImpl(new Position(getAgentPosition()));
    }

    /** {@inheritDoc} */
    @Override
    public Position getAgentPosition() {
        return agentPosition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((agentPosition == null) ? 0 : agentPosition.hashCode());
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
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void setAgentPosition(Position position3d) {
        agentPosition = position3d;
    }

    // Misc
    @Override
    public void agentPositionChange(Position delta) {
        agentPosition.applyDelta(delta);
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[agent=" + agentPosition + "]";
    }

}
