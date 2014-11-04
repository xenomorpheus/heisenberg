package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.List;

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
    private List<Memory> memories;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param memories
     *            memories e.g. walls.
     */
    public ModelStateMemories(Position agentPosition, List<Memory> memories) {
        super(agentPosition);
        if (null == agentPosition) {
            throw new IllegalArgumentException("agentPosition may not be null");
        }
        if (null == memories) {
            throw new IllegalArgumentException("memories may not be null");
        }
        this.memories = memories;
    }

    /**
     * Constructor.
     * 
     * @param modelState
     *            to copy.
     */
    public ModelStateMemories(ModelStateMemories modelState) {
        this(new Position(modelState.getAgentPosition()),
                new ArrayList<Memory>(modelState.getMemories()));
    }

    /** {@inheritDoc} */
    public List<Memory> getMemories() {
        return memories;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[agent=" + getAgentPosition() + "]"; // TODO
                                                     // memories
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((memories == null) ? 0 : memories.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelStateMemories other = (ModelStateMemories) obj;
        if (memories == null) {
            if (other.memories != null)
                return false;
        } else if (!memories.equals(other.memories))
            return false;
        return true;
    }

}
