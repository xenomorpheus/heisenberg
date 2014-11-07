package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.ai.MemoryImpl;

public interface MemorySet extends Iterable<MemoryImpl> {

    void add(MemoryImpl memory);

    boolean isEmpty();

    MemorySet duplicate();
    

}
