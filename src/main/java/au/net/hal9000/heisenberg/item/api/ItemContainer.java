package au.net.hal9000.heisenberg.item.api;

import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Position;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;

/**
 * This is an Item that extends the ItemList interface with more advanced features. This container
 * is still an ordered list.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface ItemContainer extends Item, ItemList {

  // Getters and Setters
  /**
   * Get the contents.
   *
   * @return the contents
   */
  List<Item> getContents();

  /**
   * @return The max weight that can be carried.
   */
  float getWeightMax();

  /**
   * Set the max weight that may be carried.
   *
   * @param weightMax The max weight that can be carried.
   */
  void setWeightMax(float weightMax);

  /**
   * get the maximum volume that this item can hold.
   *
   * @return the maximum volume that this item can hold.
   */
  float getVolumeMax();

  /**
   * Set the maximum volume that this item can hold.
   *
   * @param volumeMax the maximum volume that this item can hold.
   */
  void setVolumeMax(float volumeMax);

  /**
   * Does the Item exist in the ItemContainer ?
   *
   * @param item Item looking for
   * @return true iff found
   */
  boolean contains(Item item);

  /**
   * Empty the bag into this location.
   *
   * @param newLocation
   */
  void empty(ItemContainer newLocation);

  // TODO rename Visitor Pattern style
  // Find contents that match the criteria
  /**
   * Method accept.
   *
   * @param visitor ItemVisitor
   */
  void accept(ItemVisitor visitor);

  /**
   * Change the position of the item within the ItemContainer.
   *
   * <p>Note: The request can fail or partially complete.<br>
   * E.g Can't pass through walls.
   *
   * @param item the item to move withing the container.
   * @param requestedPosition the requested final position within the container. The position is NOT
   *     a delta but rather absolute WRT the position of the container.
   */
  void moveItemAbsolute(Item item, Position requestedPosition);

  /**
   * Add multiple items to the contents.
   *
   * @param items the items to add.
   */
  void addAll(List<Item> items);

  /**
   * Attempt to move Item's position within the ItemContainer by this amount.
   *
   * <p>The container has control of the movement of Item objects within it. Partial or no movement
   * may occur.
   *
   * @param item the item to be moved.
   * @param delta the amount to move within the container.
   */
  void moveItemDelta(Item item, Position delta);
}
