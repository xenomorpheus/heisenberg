package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

// import org.apache.log4j.BasicConfigurator;
/** The container has control of the movement of Item objects within it. */
@Entity
public class Location extends ItemImpl implements ItemContainer {
  /** */
  private static final long serialVersionUID = 1L;

  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(Location.class.getName());

  /** Field weightMax. */
  private float weightMax = -1;

  /** Field volumeMax. */
  private float volumeMax = -1;

  /** Field contents. */
  private List<Item> contents = new ArrayList<Item>();

  /** Constructor. */
  public Location() {
    super();
  }

  // Getters and Setters

  @Override
  public List<Item> getContents() {
    return contents;
  }

  @Override
  public float getWeightMax() {
    return weightMax;
  }

  @Override
  public void setWeightMax(float weightMax) {
    this.weightMax = weightMax;
  }

  @Override
  public float getVolumeMax() {
    return volumeMax;
  }

  @Override
  public void setVolumeMax(float volumeMax) {
    this.volumeMax = volumeMax;
  }

  @Override
  public boolean contains(Item item) {
    return contents.contains(item);
  }

  // Misc

  @Override
  public int size() {
    int count = 0;
    if (null != contents) {
      count = contents.size();
    }
    return count;
  }

  @Override
  public Item get(final int index) {
    return contents.get(index);
  }

  /**
   * Used for tree display etc. <br>
   * 0-based index, -1 for missing.
   *
   * @param child item to locate.
   * @return the index of the child item.
   */
  public int indexOf(final Item child) {
    return contents.indexOf(child);
  }

  /**
   * @return Total weight, including contents.
   */
  @Override
  public float totalWeight() {
    float total = getWeightBase();
    total += getContentsWeight();
    return total;
  }

  /**
   * @return Total volume, including contents.
   */
  @Override
  public float totalVolume() {
    float total = getVolumeBase();
    total += getContentsVolume();
    return total;
  }

  /**
   * @return Total value, including contents.
   */
  @Override
  public Currency totalValue() {
    Currency total = new Currency(getValueBase());
    total.add(getContentsValue());
    return total;
  }

  // end of setters and getters

  // misc methods

  /**
   * Method hashCode.
   *
   * @return int
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((null == contents) ? 0 : contents.hashCode());
    result = prime * result + Float.floatToIntBits(volumeMax);
    result = prime * result + Float.floatToIntBits(weightMax);
    return result;
  }

  /**
   * Method equals.
   *
   * @param obj Object
   * @return boolean
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    // Code almost certainly won't reach here as Item id values are random
    // and very likely to be different.
    if (!(obj instanceof Location)) {
      return false;
    }
    Location other = (Location) obj;
    if (null == contents) {
      if (null != other.contents) {
        return false;
      }
    } else if (!contents.equals(other.contents)) {
      return false;
    }
    if (Float.floatToIntBits(volumeMax) != Float.floatToIntBits(other.volumeMax)) {
      return false;
    }
    if (Float.floatToIntBits(weightMax) != Float.floatToIntBits(other.weightMax)) {
      return false;
    }
    return true;
  }

  @Override
  public void add(Item item) {
    add(contents.size(), item);
  }

  @Override
  public void add(int index, Item item) {
    ItemContainer itemCurrentContainer = item.getContainer();
    if (null != itemCurrentContainer) {
      if (equals(itemCurrentContainer)) {
        LOGGER.error("Item " + this + " was already in container " + itemCurrentContainer);
        // Nothing to do.
        return;
      }
    }
    // TODO consider re-factor into a visitor pattern that totals
    // up the weight and volume in one pass.

    // check Weight
    final float weightMax = getWeightMax();
    if (weightMax > 0f) {
      float total = getContentsWeight();
      total += item.totalWeight();
      if (total > weightMax) {
        throw new TooHeavyException(
            "TooHeavy - Adding "
                + item.getName()
                + " weighing "
                + item.totalWeight()
                + " will total "
                + total
                + ", which is too heavy for "
                + getName()
                + ", weightMax="
                + weightMax);
      }
    }

    // check Volume
    final float volumeMax = getVolumeMax();
    if (volumeMax > 0) {
      float total = getContentsVolume();
      total += item.totalVolume();
      if (total > volumeMax) {
        throw new TooLargeException(
            "TooLarge - Adding "
                + item.getName()
                + " weighing "
                + item.totalWeight()
                + " will total "
                + total
                + ", which is too heavy for "
                + getName()
                + ", weightMax="
                + weightMax);
      }
    }

    // remove item from existing location
    if (null != itemCurrentContainer) {
      itemCurrentContainer.remove(item);
    }

    // Add the item and update the item's location.
    int currentSize = contents.size();
    if (index > (currentSize + 1)) {
      throw new RuntimeException(
          "Requested position "
              + index
              + " much greater "
              + currentSize
              + ". container is a "
              + getName()
              + ", added item is "
              + item.getName());
    }
    contents.add(index, item);
    // check
    int pos = contents.indexOf(item);
    if (pos != index) {
      throw new RuntimeException("Requested position " + index + " != reported position " + pos);
    }
    item.setContainer(this);
  }

  /**
   * Add multiple items to the contents.
   *
   * @param items the items to add.
   */
  @Override
  public void addAll(List<Item> items) {
    // TODO make add call this method and only calculate container volume and weight once.
    for (Item item : items) {
      add(item);
    }
  }

  @Override
  public void empty(ItemContainer newLocation) {
    while (!contents.isEmpty()) {
      Item item = contents.remove(0);
      newLocation.add(item);
    }
  }

  /**
   * Use getWeight() to get total including contents. Magic containers will override getWeight().
   *
   * @return the weight of just the contents.
   */
  private float getContentsWeight() {
    float total = 0;
    for (Item item : getContents()) {
      total += item.totalWeight();
    }
    return total;
  }

  /**
   * Use getVolume() to get total including contents. Magic containers will override getVolume().
   *
   * @return the volume of just the contents.
   */
  private float getContentsVolume() {
    float total = 0;
    for (Item item : getContents()) {
      total += item.totalVolume();
    }
    return total;
  }

  /**
   * Use getValue() to get total including contents. Magic containers will override getValue().
   *
   * @return the value of just the contents.
   */
  private Currency getContentsValue() {
    Currency total = new Currency();
    for (Item item : getContents()) {
      total.add(item.totalValue());
    }
    return total;
  }

  @Override
  public void accept(ItemVisitor visitor) {
    // Search the Items directly declared in this class.
    for (Item item : getContents()) {
      item.accept(visitor);
    }
    // Get super to do the rest.
    visitor.visit(this);
  }

  /** Destroy this object. */
  public void beNot() {
    // Call beNot on the Items directly declared in this class.
    while (!contents.isEmpty()) {
      Item item = contents.remove(0);
      item.beNot();
    }
    // Get super to do the rest.
    super.beNot();
  }

  @Override
  public void remove(Item item) {
    contents.remove(item);
    item.setContainer(null);
  }

  @Override
  public void remove(int index) {
    remove(contents.get(index));
  }

  @Override
  public void moveItemAbsolute(Item item, Position requestedPosition) {
    ItemContainer container = item.getContainer();
    if (container != this) {
      throw new RuntimeException(
          "attempting to move item not in container. item=" + item + ", container=" + container);
    }
    item.setPosition(requestedPosition);
  }

  @Override
  public void moveItemDelta(Item item, Position delta) {
    ItemContainer container = item.getContainer();
    if (container != this) {
      throw new RuntimeException(
          "attempting to move item not in container. item=" + item + ", container=" + container);
    }
    item.applyDelta(delta);
  }

  @Override
  public void setAllFrom(Item item) {
    super.setAllFrom(item);
    // TODO set local properties
    // TODO Add unit tests
    // TODO Do we copy contents? Thinking no.
  }
}
