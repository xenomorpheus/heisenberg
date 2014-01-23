package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;

public class ItemSearchExtraDimensional extends ItemSearch {

    public ItemSearchExtraDimensional() {
        super();
    }

    public void visit(Item item) {
        if (item instanceof ExtraDimensional) {
            this.addMatchingItems(item);
        }
    }

    public void visit(Vector<Item> itemVector) {
        Iterator<Item> itr = itemVector.iterator();
        while (itr.hasNext()) {
            Item item = itr.next();
            if (item instanceof ExtraDimensional) {
                this.addMatchingItems(item);
            }
        }
    }

}
