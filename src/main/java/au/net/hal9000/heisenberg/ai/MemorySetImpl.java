package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.net.hal9000.heisenberg.ai.api.MemorySet;

public class MemorySetImpl implements MemorySet {
    List<MemoryImpl> memories = new ArrayList<MemoryImpl>();

    /** {@inheritDoc} */
    @Override
    public Iterator<MemoryImpl> iterator() {
        return memories.iterator();
    }

    /** {@inheritDoc} */
    @Override
    public void add(MemoryImpl memory) {
        memories.add(memory);
        // TODO don't add duplicated memories.
        
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return memories.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public MemorySet duplicate() {
        // TODO Auto-generated method stub
        throw new RuntimeException("todo");
    }

}
