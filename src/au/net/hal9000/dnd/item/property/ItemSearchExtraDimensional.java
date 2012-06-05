package au.net.hal9000.dnd.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.Item;

public class ItemSearchExtraDimensional extends ItemSearch {

	private Vector<Item> matchingItems;

	public ItemSearchExtraDimensional() {
		super();
		this.matchingItems = new Vector<Item>();
	}

	public Vector<Item> getMatchingItems() {
		return matchingItems;
	}

	public void searchItem(Item item) {
		System.out.println("Checking "+item);
		if (item.isExtraDimensional()) {
			this.matchingItems.add(item);
		}
	}

	public void searchItem(Vector<Item> itemVector) {
		Iterator<Item> itr = itemVector.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			System.out.println("Checking "+item);
			if (item.isExtraDimensional()) {
				matchingItems.add(item);
			}
		}
	}

}
