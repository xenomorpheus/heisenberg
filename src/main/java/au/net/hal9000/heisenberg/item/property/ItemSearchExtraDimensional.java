package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.property.ExtraDimensional;


public class ItemSearchExtraDimensional extends ItemSearch {

    public ItemSearchExtraDimensional(){
    	super();
    }

	public void visit(Item Item) {
		if (Item instanceof ExtraDimensional) {
			this.addMatchingItems(Item);
		}
	}

	public void visit(Vector<Item> itemVector) {
		Iterator<Item> itr = itemVector.iterator();
		while (itr.hasNext()) {
			Item Item = itr.next();
			if (Item instanceof ExtraDimensional) {
				this.addMatchingItems(Item);
			}
		}
	}

}
