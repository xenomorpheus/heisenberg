package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;

public class ItemSearchMagical extends ItemSearch {

    public ItemSearchMagical() {
        super();
    }

    public void visit(Item item) {
        if (ItemProperty.isMagical(item)) {
            this.addMatchingItems(item);
        }
    }

    public void visit(Vector<Item> itemVector) {
        Iterator<Item> itr = itemVector.iterator();
        while (itr.hasNext()) {
            Item item = itr.next();
            if (ItemProperty.isMagical(item)) {
                this.addMatchingItems(item);
            }
        }
    }

}
