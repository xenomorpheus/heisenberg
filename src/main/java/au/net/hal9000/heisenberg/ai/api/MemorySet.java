package au.net.hal9000.heisenberg.ai.api;

import java.util.List;

import au.net.hal9000.heisenberg.ai.api.Memory;

public interface MemorySet extends Iterable<Memory> {

    // Getters and setters.
    List<Memory> getMemorySet();

    void setMemorySet(List<Memory> memorySet);

    // Misc

    void add(Memory memory);

    boolean isEmpty();

}
