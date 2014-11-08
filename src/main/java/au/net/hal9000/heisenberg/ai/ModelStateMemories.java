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

public class ModelStateMemories extends ModelStateImpl implements ModelState {

    /** Memories. e.g. Walls */
    private MemorySet memorySet;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param memories
     *            memories e.g. walls.
     */
    public ModelStateMemories(Position agentPosition, MemorySet memories) {
        super(agentPosition);
        if (null == agentPosition) {
            throw new IllegalArgumentException("agentPosition may not be null");
        }
        if (null == memories) {
            throw new IllegalArgumentException("memories may not be null");
        }
        this.memorySet = memories;
    }

    /**
     * Constructor.
     * 
     * @param modelState
     *            to copy.
     */
    public ModelStateMemories(ModelStateMemories modelState) {
        this(new Position(modelState.getAgentPosition()), modelState
                .getMemorySet().duplicate());
    }

    // getters and setters

    private void setMemorySet(MemorySet memorySet) {
        this.memorySet = memorySet;
    }

    public MemorySet getMemorySet() {
        return memorySet;
    }

    // overridden methods

    /** {@inheritDoc} */
    @Override
    public ModelState duplicate() {
        return new ModelStateMemories(new Position(getAgentPosition()),
                getMemorySet().duplicate());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        // TODO string buffer
        return "[agentPos=" + getAgentPosition() + "memories="
                + memorySet.toString() + "]";
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
        ModelStateMemories other = (ModelStateMemories) obj;
        if (memorySet == null) {
            if (other.memorySet != null)
                return false;
        } else if (!memorySet.equals(other.memorySet))
            return false;
        return true;
    }

}
