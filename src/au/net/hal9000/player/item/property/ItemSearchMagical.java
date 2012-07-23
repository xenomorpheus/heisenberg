package au.net.hal9000.player.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.player.item.IItem;

public class ItemSearchMagical extends ItemSearch {

	public ItemSearchMagical() {
		super();
	}

	public void visit(IItem iItem) {
		// System.out.println("Checking for Magical " + item + " "
		// 		+ item.isMagical());
		// System.out.println("  Class " + item.getClass().getName());
		if (iItem.isMagical()) {
			this.addMatchingItems(iItem);
			System.out.println("Matching " + iItem + "  count  "
					+ this.getMatchingItemsCount());
		}
	}

	public void visit(Vector<IItem> itemVector) {
		Iterator<IItem> itr = itemVector.iterator();
		while (itr.hasNext()) {
			IItem iItem = itr.next();
			// System.out.println("Checking for Magical " + item + " "
			//		+ item.isMagical());
			// System.out.println("  Class " + item.getClass().getName());
			if (iItem.isMagical()) {
				this.addMatchingItems(iItem);
				System.out.println("Matching " + iItem + "  count  "
						+ this.getMatchingItemsCount());
			}
		}
	}

}
