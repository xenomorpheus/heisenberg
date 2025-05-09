package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.ExtraDimensional;
import au.net.hal9000.heisenberg.item.api.Item;
import java.util.Iterator;
import java.util.List;

/** Item search extra dimensional. */
public class ItemSearchExtraDimensional extends ItemSearch {

  /** Constructor for ItemSearchExtraDimensional. */
  public ItemSearchExtraDimensional() {
    super();
  }

  /**
   * Method visit.
   *
   * @param item Item
   * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(Item)
   */
  public void visit(Item item) {
    if (item instanceof ExtraDimensional) {
      addMatchingItems(item);
    }
  }

  /**
   * Processes a list of items in the search.
   *
   * @param itemVector the list of items to process
   */
  public void visit(List<Item> itemVector) {
    Iterator<Item> itr = itemVector.iterator();
    while (itr.hasNext()) {
      Item item = itr.next();
      if (item instanceof ExtraDimensional) {
        addMatchingItems(item);
      }
    }
  }
}
