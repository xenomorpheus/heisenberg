package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Hand;
import au.net.hal9000.heisenberg.item.HumanoidHead;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.HumanoidArmClothing;
import au.net.hal9000.heisenberg.item.api.HumanoidCoreClothing;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.api.ItemList;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.util.PcClass;
import java.util.ArrayList;
import java.util.List;

/**
 * Humanoid is more to do with the shape, so can be undead.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
abstract class Humanoid extends Race implements ItemList {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;
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
  private Hand leftHand = new Hand("Left Hand");
  /** Field rightHand. */
  private Hand rightHand = new Hand("Right Hand");

  /** Field core. */
  private ItemContainer core = new Location("core");

  /** We are implementing ItemList. */
  private List<Item> itemList;

  // Constructors
  /**
   * Constructor for Humanoid.
   *
   * @param name String
   */
  protected Humanoid(String name) {
    super(name);
    itemList = new ArrayList<Item>();
    itemList.add(core);
    itemList.add(head);
    itemList.add(rightHand);
    itemList.add(leftHand);
  }

  /**
   * Constructor for Humanoid.
   *
   * @param string String
   * @param description String
   */
  protected Humanoid(String string, String description) {
    this(string);
    this.setDescription(description);
  }

  /**
   * Constructor for Humanoid.
   *
   * @param name String
   * @param description String
   * @param pcClass PcClass
   */
  protected Humanoid(String name, String description, PcClass pcClass) {
    this(name, description);
    this.setPcClass(pcClass);
  }

  /**
   * Constructor for Humanoid.
   *
   * @param name String
   * @param pcClass PcClass
   */
  protected Humanoid(String name, PcClass pcClass) {
    this(name);
    this.setPcClass(pcClass);
  }

  // Getters and Setters
  // Head
  /**
   * Method getHead.
   *
   * @return HumanoidHead
   */
  public HumanoidHead getHead() {
    return head;
  }

  /**
   * Method setHead.
   *
   * @param head HumanoidHead
   */
  public void setHead(HumanoidHead head) {
    this.head = head;
  }

  // left Hand
  /**
   * Method getLeftHand.
   *
   * @return Hand
   */
  public Hand getLeftHand() {
    return leftHand;
  }

  /**
   * Method setLeftHand.
   *
   * @param hand Hand
   */
  public void setLeftHand(Hand hand) {
    leftHand = hand;
  }

  // right Hand
  /**
   * Method getRightHand.
   *
   * @return Hand
   */
  public Hand getRightHand() {
    return rightHand;
  }

  /**
   * Method setRightHand.
   *
   * @param hand Hand
   */
  public void setRightHand(Hand hand) {
    rightHand = hand;
  }

  // Weight and capacity is spread across body.
  /** @return The max weight that can be carried. */
  public float getWeightMax() {
    float weightMax = 0;
    return weightMax;
  }

  /**
   * Set the max weight that may be carried.
   *
   * @param weightMax The max weight that can be carried.
   */
  public void setWeightMax(float weightMax) {
    // head
    head.setWeightMax(weightMax * HEAD_PERCENTAGE_MAX_WEIGHT_VOLUME);
    // left hand
    leftHand.setWeightMax(weightMax * LEFT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    // right hand
    rightHand.setWeightMax(weightMax * RIGHT_HAND_PERCENTAGE_MAX_WEIGHT_VOLUME);
    // core
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

  // Misc

  /**
   * add - Wear an item.
   *
   * @param item clothing Item to wear.
   */
  // TODO consider wear could fail, e.g. spot occupied.
  public void wear(Item item) {
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
        throw new InvalidTypeException(this.getName() + " can't wear " + item.getName());
      }
      core.add(item);
    }
  }

  /** {@inheritDoc} */
  @Override
  public float getWeight() {
    float total = 0;
    for (Item item : itemList) {
      total += item.getWeight();
    }
    return total;
  }

  /** {@inheritDoc} */
  @Override
  public float getVolume() {
    float total = 0;
    for (Item item : itemList) {
      total += item.getVolume();
    }
    return total;
  }

  /** {@inheritDoc} */
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

  // Visitor Design Pattern. Find items that match the criteria
  /** {@inheritDoc} * @param visitor ItemVisitor */
  @Override
  public void accept(ItemVisitor visitor) {
    head.accept(visitor);
    if (null != leftHand) {
      leftHand.accept(visitor);
    }
    if (null != rightHand) {
      rightHand.accept(visitor);
    }
    core.accept(visitor);
    visitor.visit(this);
  }

  /** {@inheritDoc} * @return String */
  @Override
  public String getRace() {
    return this.getClass().getSimpleName();
  }

  /** {@inheritDoc} * @return int */
  @Override
  public int size() {
    return itemList.size();
  }

  /** {@inheritDoc} * @return Item */
  @Override
  public Item get(int index) {
    return itemList.get(index);
  }

  /**
   * {@inheritDoc}
   *
   * @param index where to add
   * @param item what to add
   */
  @Override
  public void add(int index, Item item) {
    itemList.add(index, item);
  }

  /** {@inheritDoc} * @return int */
  @Override
  public int indexOf(Item child) {
    return itemList.indexOf(child);
  }
}
