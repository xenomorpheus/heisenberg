package au.net.hal9000.heisenberg.item.being;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.Hand;
import au.net.hal9000.heisenberg.item.HumanoidHead;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.HumanoidArmClothing;
import au.net.hal9000.heisenberg.item.api.HumanoidCoreClothing;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.api.ItemList;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.mixin.AnimalConsumeSustenance;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Humanoid is more to do with the physical body shape, so can be undead. */
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Humanoid extends Being implements Animal, ItemList {

  /** head percentage of max weight and volume. */
  private static final float HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;

  /** left hand percentage of max weight and volume. */
  private static final float LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;

  /** right hand percentage of max weight and volume. */
  private static final float RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME = 9;

  /** core percentage of max weight and volume. */
  private static final float CORE_PERCENTAGE_MAX_WEIGHT_VOLUME = 36;

  /** Field head. */
  private HumanoidHead head = new HumanoidHead();

  /** Field leftHand. */
  private Hand leftHand = new Hand();

  /** Field rightHand. */
  private Hand rightHand = new Hand();

  /** Field core. */
  private ItemContainer core = new Location();

  /** We are implementing ItemList. */
  private List<Item> items;

  // Constructors
  /**
   * Constructor for Humanoid.
   *
   * @param name String
   */
  protected Humanoid() {
    super();
    head.setName("Head");
    leftHand.setName("Left Hand");
    rightHand.setName("Right Hand");
    core.setName("Core");
    items = new ArrayList<Item>();
    items.add(head);
    items.add(rightHand);
    items.add(leftHand);
    items.add(core);
  }

  // Getters and Setters

  /**
   * Get the head.
   *
   * @return HumanoidHead
   */
  public HumanoidHead getHead() {
    return head;
  }

  /**
   * Get left hand.
   *
   * @return Hand
   */
  public Hand getLeftHand() {
    return leftHand;
  }

  /**
   * Get right hand.
   *
   * @return Hand
   */
  public Hand getRightHand() {
    return rightHand;
  }

  /**
   * Get core.
   *
   * @return Core
   */
  public ItemContainer getCore() {
    return core;
  }

  // Weight and capacity is spread across body.
  /**
   * @return The max weight that can be carried.
   */
  public float getWeightMax() {
    return head.getWeightMax()
        + leftHand.getWeightMax()
        + rightHand.getWeightMax()
        + core.getWeightMax();
  }

  /**
   * Set the max weight that may be carried.
   *
   * @param weightMax The max weight that can be carried.
   */
  public void setWeightMax(float weightMax) {
    head.setWeightMax(weightMax * HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME);
    leftHand.setWeightMax(weightMax * LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    rightHand.setWeightMax(weightMax * RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    core.setWeightMax(weightMax * CORE_PERCENTAGE_MAX_WEIGHT_VOLUME);
  }

  /**
   * get the maximum volume that this item can hold.
   *
   * @return the maximum volume that this item can hold.
   */
  public float getVolumeMax() {
    float volumeMax = 0; // TODO set a real Humanoid volumeMax
    return volumeMax;
  }

  /**
   * Set the maximum volume that this item can hold.
   *
   * @param volumeMax the maximum volume that this item can hold.
   */
  public void setVolumeMax(float volumeMax) {
    final float corePercentageTotal = 100;
    float corePercentage = corePercentageTotal;
    // head
    head.setVolumeMax(volumeMax * HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME);
    corePercentage -= HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME;
    // left hand
    leftHand.setVolumeMax(volumeMax * LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    corePercentage -= LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME;
    // right hand
    rightHand.setVolumeMax(volumeMax * RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    corePercentage -= RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME;
    // core
    core.setVolumeMax(volumeMax * corePercentage);
  }

  // Implement Animal

  @Override
  public void eat(Item food) {
    AnimalConsumeSustenance.eat(this, food);
  }

  @Override
  public void drink(Item food) {
    AnimalConsumeSustenance.drink(this, food);
  }

  // Implement ItemList

  @Override
  // TODO consider wear could fail, e.g. spot occupied.
  public void add(Item item) {
    if ((rightHand != null) && (item instanceof HumanoidArmClothing)) {
      // TODO consider implementing wear on hands.
      // TODO consider wear could fail, e.g. spot occupied.
      rightHand.add(item);
    } else if ((leftHand != null) && (item instanceof HumanoidArmClothing)) {
      leftHand.add(item);
    } else if (item instanceof HumanoidCoreClothing) {
      core.add(item);
    } else {
      if (!ItemProperty.isClothing(item)) {
        throw new InvalidTypeException(getName() + " can't wear " + item.getName());
      }
      core.add(item);
    }
  }

  @Override
  public float totalWeight() {
    float total = 0;
    for (Item item : items) {
      total += item.totalWeight();
    }
    return total;
  }

  @Override
  public float totalVolume() {
    float total = 0;
    for (Item item : items) {
      total += item.totalVolume();
    }
    return total;
  }

  @Override
  public void beNot() {
    // Call beNot on the Items directly declared in this class.
    if (null != leftHand) {
      leftHand.beNot();
    }
    if (null != rightHand) {
      rightHand.beNot();
    }
    core.beNot();
    head.beNot();
    // Get super to do the rest.
    super.beNot();
  }

  @Override
  public int size() {
    return items.size();
  }

  @Override
  public Item get(int index) {
    return items.get(index);
  }

  @Override
  public void add(int index, Item item) {
    items.add(index, item);
  }

  @Override
  public int indexOf(Item child) {
    return items.indexOf(child);
  }

  @Override
  public void remove(Item item) {
    items.remove(item);
  }

  @Override
  public void remove(int index) {
    items.remove(index);
  }

  @Override
  public Iterator<Item> iterator() {
      return new Iterator<Item>() {
          private int index = 0;

          @Override
          public boolean hasNext() {
              return index < items.size();
          }

          @Override
          public Item next() {
              return items.get(index++);
          }
      };
  }

}
