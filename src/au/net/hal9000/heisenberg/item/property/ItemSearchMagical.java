package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.IItem;
import au.net.hal9000.heisenberg.item.property.Magical;

public class ItemSearchMagical extends ItemSearch {

    public ItemSearchMagical() {
        super();
    }

    public void visit(IItem iItem) {
        if (iItem instanceof Magical) {
            this.addMatchingItems(iItem);
        }
    }

    public void visit(Vector<IItem> itemVector) {
        Iterator<IItem> itr = itemVector.iterator();
        while (itr.hasNext()) {
            IItem iItem = itr.next();
            if (iItem instanceof Magical) {
                this.addMatchingItems(iItem);
            }
        }
    }

}
