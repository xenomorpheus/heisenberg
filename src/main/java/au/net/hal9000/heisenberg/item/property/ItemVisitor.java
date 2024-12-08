package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.Item;
import java.util.List;

/** Visitor Design Pattern for Item class. */
public interface ItemVisitor {

  /**
   * Visit this item.
   *
   * @param item item to visit.
   */
  void visit(Item item);

  /**
   * Visit these items.
   *
   * @param items set of items.
   */
  void visit(List<Item> items);
}
