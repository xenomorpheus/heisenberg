package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * A very simple model state. It holds agent position and memories.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

public class ModelStateGoalMemorySet extends ModelStateGoal implements
        ModelState {

    /** Memories. e.g. Walls */
    private MemorySet memorySet;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param memorySet
     *            memories e.g. walls.
     */
    public ModelStateGoalMemorySet(Position agentPosition,
            Position goalPosition, MemorySet memorySet) {
        super(agentPosition, goalPosition);
        if (null == memorySet) {
            throw new IllegalArgumentException("memorySet may not be null");
        }
        this.memorySet = memorySet;
    }

    // getters and setters

    public MemorySet getMemorySet() {
        return memorySet;
    }

    // overridden methods

    /** {@inheritDoc} */
    @Override
    public ModelState duplicate() {
        return new ModelStateGoalMemorySet(new Position(getAgentPosition()),
                new Position(getGoalPosition()), new MemorySetImpl(
                        getMemorySet()));
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        // TODO string buffer
        return getClass().getSimpleName() + "=[agent " + getAgentPosition()
                + ", goal " + getGoalPosition() + ", " + memorySet + "]";
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((memorySet == null) ? 0 : memorySet.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelStateGoalMemorySet other = (ModelStateGoalMemorySet) obj;
        if (memorySet == null) {
            if (other.memorySet != null)
                return false;
        } else if (!memorySet.equals(other.memorySet))
            return false;
        return true;
    }

}
