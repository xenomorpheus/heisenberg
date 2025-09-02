package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.ai.MemoryAbstract;
import au.net.hal9000.heisenberg.ai.MemorySetImpl;
import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public final class PlayableState implements Serializable {

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  /**
   * A measure of the amount of things the entity can do in the current round. This is the
   * remaining, not the maximum.
   */
  @Getter @Setter
  private int actionPoints;

  /** The size of the magic user's fuel tank. This is the remaining, not the maximum. */
  @Getter @Setter
  private int mana;

  private MemorySet memorySet = null;

  public PlayableState() {
    super();
  }

  // Getters and Setters

  /**
   * Adjust the amount of action points of this item.
   *
   * @param adjust amount to adjust by.
   */
  public void actionPointsAdjust(final int adjust) {
    actionPoints += adjust;
  }

  /**
   * Adjust the amount of mana.
   *
   * @param adjust amount to adjust by.
   */
  public void manaAdjust(int adjust) {
    mana += adjust;
  }

  /**
   * get MemorySet.
   *
   * @return MemorySet
   */
  public MemorySet getMemorySet() {
    if (memorySet == null) {
      memorySet = new MemorySetImpl();
    }
    return memorySet;
  }

  public void memoryAdd(MemoryAbstract memory) {
    if (memorySet == null) {
      memorySet = new MemorySetImpl();
    }
    memorySet.add(memory);
  }

  // Misc

  public String toString() {
    StringBuilder text = new StringBuilder();
    if (0 != actionPoints) {
      text.append("Action Points: " + actionPoints + System.lineSeparator());
    }
    if (0 != mana) {
      text.append("Mana: " + mana + System.lineSeparator());
    }
    if (null != memorySet && !memorySet.isEmpty()) {
      text.append("MemorySet:" + System.lineSeparator());
      for (Memory memory : memorySet) {
        text.append("  " + memory + System.lineSeparator());
      }
    }
    return text.toString();
  }
}
