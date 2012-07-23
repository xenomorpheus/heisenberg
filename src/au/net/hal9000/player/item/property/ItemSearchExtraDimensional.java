package au.net.hal9000.player.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.player.item.IItem;

public class ItemSearchExtraDimensional extends ItemSearch {

    public ItemSearchExtraDimensional(){
    	super();
    }

	public void visit(IItem iItem) {
	//	System.out.println("\n\nChecking for ExtraDimensial " + item + " "
	//			+ item.isExtraDimensional());
	//	System.out.println("  Class " + item.getClass().getName());
		if (iItem.isExtraDimensional()) {
			this.addMatchingItems(iItem);
		}
	}

	public void visit(Vector<IItem> itemVector) {
		Iterator<IItem> itr = itemVector.iterator();
		while (itr.hasNext()) {
			IItem iItem = itr.next();
	//		System.out.println("Checking for ExtraDimensial " + item + " "
	//				+ item.isExtraDimensional());
	//		System.out.println("  Class " + item.getClass().getName());
			if (iItem.isExtraDimensional()) {
				this.addMatchingItems(iItem);
			}
		}
	}

}
