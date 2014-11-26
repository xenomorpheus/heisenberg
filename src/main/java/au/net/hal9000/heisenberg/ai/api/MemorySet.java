package au.net.hal9000.heisenberg.ai.api;

import java.util.List;

/**
 * A collection of memories.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 *
 */
public interface MemorySet extends Iterable<Memory> {

    // Getters and setters.
    /**
     * @return the Memory objects in this set.
     */
    List<Memory> getMemorySet();

    /**
     * @param memorySet
     *            replace Memory set with supplied set.
     */
    void setMemorySet(List<Memory> memorySet);

    // Misc

    /**
     * Add a memory to the set.
     * 
     * @param memory
     */
    void add(Memory memory);

    /**
     * @return true IFF no memories in this set.
     */
    boolean isEmpty();

}
