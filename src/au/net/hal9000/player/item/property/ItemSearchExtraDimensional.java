package au.net.hal9000.player.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.player.item.Item;

public class ItemSearchExtraDimensional extends ItemSearch {

    public ItemSearchExtraDimensional(){
    	super();
    }

	public void visit(Item item) {
	//	System.out.println("\n\nChecking for ExtraDimensial " + item + " "
	//			+ item.isExtraDimensional());
	//	System.out.println("  Class " + item.getClass().getName());
		if (item.isExtraDimensional()) {
			this.addMatchingItems(item);
		}
	}

	public void visit(Vector<Item> itemVector) {
		Iterator<Item> itr = itemVector.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
	//		System.out.println("Checking for ExtraDimensial " + item + " "
	//				+ item.isExtraDimensional());
	//		System.out.println("  Class " + item.getClass().getName());
			if (item.isExtraDimensional()) {
				this.addMatchingItems(item);
			}
		}
	}

}
