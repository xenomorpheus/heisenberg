package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** A very simple collection of Memory objects. */
public class MemorySetImpl implements MemorySet {

  /** A collection of Memory objects. */
  private List<Memory> memorySet = new ArrayList<>();

  /** Constructor. */
  public MemorySetImpl() {
    super();
  }

  /** Constructor. Similar to clone */
  MemorySetImpl(MemorySet memorySet) {
    super();
    if (null == memorySet) {
      setMemorySet(null);
    } else {
      setMemorySet(new ArrayList<Memory>(memorySet.getMemorySet()));
    }
  }

  // Getters and Setters
  /** {@inheritDoc} */
  @Override
  public List<Memory> getMemorySet() {
    return memorySet;
  }

  /** {@inheritDoc} */
  @Override
  public void setMemorySet(List<Memory> memorySet) {
    this.memorySet = memorySet;
  }

  // Misc
  /** {@inheritDoc} */
  @Override
  public Iterator<Memory> iterator() {
    return memorySet.iterator();
  }

  /** {@inheritDoc} */
  @Override
  public void add(Memory memory) {
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((memorySet == null) ? 0 : memorySet.hashCode());
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MemorySetImpl other = (MemorySetImpl) obj;
    if (memorySet == null) {
      if (other.memorySet != null) {
        return false;
      }
    } else if (!memorySet.equals(other.memorySet)) {
      return false;
    }
    return true;
  }

  /**
   * Method toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    final int stringsPerMemory = 2;
    StringBuilder stringB = new StringBuilder(getMemorySet().size() * stringsPerMemory + 3);
    stringB.append(getClass().getSimpleName()).append("=[");
    for (Memory memory : getMemorySet()) {
      stringB.append(", ").append(memory);
    }
    stringB.append(']');
    return stringB.toString();
  }

  @Override
  public MemorySet duplicate() {
    return new MemorySetImpl(this);
  }
}
