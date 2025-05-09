package au.net.hal9000.heisenberg.item.api;

/**
 * An ordered list of Item objects e.g. inside a backpack. For a Humanoid this might be one for each
 * of core, head, arms and legs. <br>
 * Note that this object doesn't need to be an Item.
 */
public interface ItemList {

  /**
   * Get the number of items top level Item objects in the container.Items with other items don't
   * add to the count as they are *NOT* directly contained.
   *
   * @return the number of items directly inside the container.
   */
  int size();

  /**
   * Used for tree display.
   *
   * @param index get child with with this index in list of items.
   * @return the item requested.
   */
  Item get(int index);

  /**
   * Add the Item to the contents.
   *
   * @param item the item to add
   */
  void add(Item item);

  /**
   * Add the Item to the contents.
   *
   * @param index index position to add at.
   * @param item item to add.
   */
  void add(int index, Item item);

  public int indexOf(Item child);

  /**
   * Remove the Item from the container.
   *
   * @param item the item to remove.
   */
  void remove(Item item);

  /**
   * Remove the Item at position index from the container.
   *
   * @param index the position to remove.
   */
  void remove(int index);
}
