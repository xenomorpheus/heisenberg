package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.Item;
import java.util.ArrayList;
import java.util.List;

/** A helper class for finding Item objects using visitor pattern. */
public abstract class ItemSearch implements ItemVisitor {

  /** Field matchingItems. */
  private List<Item> matchingItems;

  /** Constructor for ItemSearch. */
  protected ItemSearch() {
    super();
    matchingItems = new ArrayList<Item>();
  }

  /**
   * Adds an item to the search list.
   *
   * @param item the item to add to the search list
   */
  void addMatchingItems(Item item) {
    matchingItems.add(item);
  }

  /**
   * Retrieves a list of items matching the search criteria.
   *
   * @return a list of items matching the search criteria
   */
  public List<Item> getMatchingItems() {
    return matchingItems;
  }

  /**
   * Method getMatchingItemsCount.
   *
   * @return int
   */
  public int getMatchingItemsCount() {
    return matchingItems.size();
  }

  /**
   * Processes a single item using the visitor pattern.
   *
   * @param item the item to process
   * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(Item)
   */
  @Override
  public abstract void visit(Item item);

}
