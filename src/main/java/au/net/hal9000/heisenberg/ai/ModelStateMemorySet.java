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

public class ModelStateMemorySet extends ModelStateImpl implements ModelState {

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
    public ModelStateMemorySet(Position agentPosition, MemorySet memorySet) {
        super(agentPosition);
        if (null == agentPosition) {
            throw new IllegalArgumentException("agentPosition may not be null");
        }
        if (null == memorySet) {
            throw new IllegalArgumentException("memorySet may not be null");
        }
        this.memorySet = memorySet;
    }

    /**
     * Constructor.
     * 
     * @param modelState
     *            to copy.
     */
    public ModelStateMemorySet(ModelStateMemorySet modelState) {
        this(new Position(modelState.getAgentPosition()), modelState
                .getMemorySet().duplicate());
    }

    // getters and setters

    public MemorySet getMemorySet() {
        return memorySet;
    }

    // overridden methods

    /** {@inheritDoc} */
    @Override
    public ModelState duplicate() {
        return new ModelStateMemorySet(new Position(getAgentPosition()),
                getMemorySet().duplicate());
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        // TODO string buffer
        return "[agentPos=" + getAgentPosition() + "memorySet="
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
        ModelStateMemorySet other = (ModelStateMemorySet) obj;
        if (memorySet == null) {
            if (other.memorySet != null)
                return false;
        } else if (!memorySet.equals(other.memorySet))
            return false;
        return true;
    }

}
