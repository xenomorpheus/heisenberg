package au.net.hal9000.heisenberg.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.net.hal9000.heisenberg.ai.api.MemorySet;

public class MemorySetImpl implements MemorySet {
    List<MemoryImpl> memorySet = new ArrayList<MemoryImpl>();

    /** {@inheritDoc} */
    @Override
    public Iterator<MemoryImpl> iterator() {
        return memorySet.iterator();
    }

    /** {@inheritDoc} */
    @Override
    public void add(MemoryImpl memory) {
        memorySet.add(memory);
        // TODO don't add duplicated memory objects.
        
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return memorySet.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public MemorySet duplicate() {
        // TODO Auto-generated method stub
        throw new RuntimeException("todo");
    }

}
