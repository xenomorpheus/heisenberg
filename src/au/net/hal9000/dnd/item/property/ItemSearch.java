package au.net.hal9000.dnd.item.property;

import au.net.hal9000.dnd.item.Item;
import java.util.Vector;

public abstract class ItemSearch {

	public ItemSearch() {
	}

	public abstract void searchItem(Item item);

	public abstract void searchItem(Vector<Item> itemVector);

	public abstract Vector<Item> getMatchingItems();

	public abstract int getMatchingItemsCount();
}
