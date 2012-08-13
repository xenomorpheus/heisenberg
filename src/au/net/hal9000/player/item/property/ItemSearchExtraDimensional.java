package au.net.hal9000.player.item.property;

import java.util.Iterator;
import java.util.Vector;
import au.net.hal9000.player.item.property.ExtraDimensional;

import au.net.hal9000.player.item.IItem;

public class ItemSearchExtraDimensional extends ItemSearch {

    public ItemSearchExtraDimensional(){
    	super();
    }

	public void visit(IItem iItem) {
		if (iItem instanceof ExtraDimensional) {
			this.addMatchingItems(iItem);
		}
	}

	public void visit(Vector<IItem> itemVector) {
		Iterator<IItem> itr = itemVector.iterator();
		while (itr.hasNext()) {
			IItem iItem = itr.next();
			if (iItem instanceof ExtraDimensional) {
				this.addMatchingItems(iItem);
			}
		}
	}

}
