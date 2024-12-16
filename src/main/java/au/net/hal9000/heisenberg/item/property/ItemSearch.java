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
   * Method addMatchingItems.
   *
   * @param item Item
   */
  void addMatchingItems(Item item) {
    matchingItems.add(item);
  }

  /**
   * Method getMatchingItems.
   *
   * @return List<Item>
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
   * Method visit.
   *
   * @param item Item
   * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(Item)
   */
  public abstract void visit(Item item);

  /**
   * Method visit.
   *
   * @param item List<Item>
   * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(List)
   */
  public abstract void visit(List<Item> item);
}
