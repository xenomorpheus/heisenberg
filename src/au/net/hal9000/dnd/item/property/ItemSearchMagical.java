package au.net.hal9000.dnd.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.Item;

public class ItemSearchMagical extends ItemSearch {

	private Vector<Item> matchingItems;

	public ItemSearchMagical() {
		super();
		this.matchingItems = new Vector<Item>();
	}

	public Vector<Item> getMatchingItems() {
		return matchingItems;
	}

	public void searchItem(Item item) {
		System.out.println("Checking for Magical "+item+ " "+item.isMagical());
		if (item.isMagical()) {
			this.matchingItems.add(item);
			System.out.println("Matching "+item+"  count  "+this.matchingItems.size());
		}
	}

	public void searchItem(Vector<Item> itemVector) {
		Iterator<Item> itr = itemVector.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			System.out.println("Checking for Magical "+item+ " "+item.isMagical());
			if (item.isMagical()) {
				matchingItems.add(item);
				System.out.println("Matching "+item+"  count  "+this.matchingItems.size());
			}
		}
	}
}
