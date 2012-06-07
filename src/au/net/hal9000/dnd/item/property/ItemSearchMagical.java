package au.net.hal9000.dnd.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.Item;

public class ItemSearchMagical extends ItemSearch {

	public ItemSearchMagical() {
		super();
	}

	public void visit(Item item) {
		// System.out.println("Checking for Magical " + item + " "
		// 		+ item.isMagical());
		// System.out.println("  Class " + item.getClass().getName());
		if (item.isMagical()) {
			this.addMatchingItems(item);
			System.out.println("Matching " + item + "  count  "
					+ this.getMatchingItemsCount());
		}
	}

	public void visit(Vector<Item> itemVector) {
		Iterator<Item> itr = itemVector.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			// System.out.println("Checking for Magical " + item + " "
			//		+ item.isMagical());
			// System.out.println("  Class " + item.getClass().getName());
			if (item.isMagical()) {
				this.addMatchingItems(item);
				System.out.println("Matching " + item + "  count  "
						+ this.getMatchingItemsCount());
			}
		}
	}

}
