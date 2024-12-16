package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.ai.api.ModelStateAgentGoalMemorySet;
import au.net.hal9000.heisenberg.units.Position;

/** A very simple model state. It holds agent position and memories. */
public class ModelStateAgentGoalMemorySetImpl extends ModelStateAgentGoalImpl
    implements ModelStateAgentGoalMemorySet {

  /** Memories e.g. Walls */
  private MemorySet memorySet;

  /**
   * Constructor.
   *
   * @param agentPosition agent position.
   * @param memorySet memories e.g. walls.
   */
  public ModelStateAgentGoalMemorySetImpl(
      Position agentPosition, Position goalPosition, MemorySet memorySet) {
    super(agentPosition, goalPosition);
    if (null == memorySet) {
      throw new IllegalArgumentException("memorySet may not be null");
    }
    this.memorySet = memorySet;
  }

  // getters and setters

  @Override
  public MemorySet getMemorySet() {
    return memorySet;
  }

  // overridden methods

  @Override
  public ModelStateAgentGoalMemorySetImpl duplicate() {
    // TODO for all duplicate() methods do something smarter with copying properties of super
    // classes.
    return new ModelStateAgentGoalMemorySetImpl(
        getAgentPosition().duplicate(), getGoalPosition().duplicate(), getMemorySet().duplicate());
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()
        + "=[agent "
        + getAgentPosition()
        + ", goal "
        + getGoalPosition()
        + ", "
        + memorySet
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((memorySet == null) ? 0 : memorySet.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ModelStateAgentGoalMemorySetImpl other = (ModelStateAgentGoalMemorySetImpl) obj;
    if (memorySet == null) {
      if (other.memorySet != null) {
        return false;
      }
    } else if (!memorySet.equals(other.memorySet)) {
      return false;
    }
    return true;
  }
}
