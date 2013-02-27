package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.IItem;

public class ItemSearchMagical extends ItemSearch {

    public ItemSearchMagical() {
        super();
    }

    public void visit(IItem iItem) {
        if (ItemProperty.isMagical(iItem)) {
            this.addMatchingItems(iItem);
        }
    }

    public void visit(Vector<IItem> itemVector) {
        Iterator<IItem> itr = itemVector.iterator();
        while (itr.hasNext()) {
            IItem iItem = itr.next();
            if (ItemProperty.isMagical(iItem)) {
                this.addMatchingItems(iItem);
            }
        }
    }

}
