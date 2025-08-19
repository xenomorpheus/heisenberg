package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemList;


/** Item search magical. */
class ItemSearchMagical extends ItemSearch {

  /** Constructor for ItemSearchMagical. */
  public ItemSearchMagical() {
    super();
  }

  /**
   * Method visit.
   *
   * @param item Item
   * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(Item)
   */
  @Override
  public void visit(Item item) {
    if (ItemProperty.isMagical(item)) {
      addMatchingItems(item);
    }
    if (item instanceof ItemList) {
      var itemList = (ItemList) item;
      for (int i = 0; i < itemList.size(); i++) {
        itemList.get(i).accept(this);
      }
    }
  }

}
