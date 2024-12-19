package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.ai.MemoryAbstract;
import au.net.hal9000.heisenberg.ai.MemorySetImpl;
import au.net.hal9000.heisenberg.ai.api.Memory;
import au.net.hal9000.heisenberg.ai.api.MemorySet;
import java.io.Serializable;

public final class PlayableState implements Serializable {

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  /**
   * A measure of the amount of things the entity can do in the current round. This is the
   * remaining, not the maximum.
   */
  private int actionPoints;

  /** The size of the magic user's fuel tank. This is the remaining, not the maximum. */
  private int mana;

  private MemorySet memorySet = null;

  public PlayableState() {
    super();
  }

  // Getters and Setters

  /**
   * @return the actionPoints
   */
  public final int getActionPoints() {
    return actionPoints;
  }

  /**
   * @param actionPoints the actionPoints to set
   */
  public final void setActionPoints(final int actionPoints) {
    this.actionPoints = actionPoints;
  }

  /**
   * Adjust the amount of action points of this item.
   *
   * @param adjust amount to adjust by.
   */
  public void actionPointsAdjust(final int adjust) {
    actionPoints += adjust;
  }

  /**
   * @return the mana
   */
  public final int getMana() {
    return mana;
  }

  /**
   * @param mana the mana to set
   */
  public final void setMana(int mana) {
    this.mana = mana;
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
    if (null == memorySet) {
      memorySet = new MemorySetImpl();
    }
    return memorySet;
  }

  public void memoryAdd(MemoryAbstract memory) {
    if (null == memorySet) {
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
