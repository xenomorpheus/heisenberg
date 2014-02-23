package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemSearchMagical extends ItemSearch {

    /**
     * Constructor for ItemSearchMagical.
     */
    public ItemSearchMagical() {
        super();
    }

    /**
     * Method visit.
     * @param item Item
     * @see au.net.hal9000.heisenberg.item.ItemVisitor#visit(Item)
     */
    public void visit(Item item) {
        if (ItemProperty.isMagical(item)) {
            this.addMatchingItems(item);
        }
    }

    /**
     * Method visit.
     * @param itemVector Vector<Item>
     * @see au.net.hal9000.heisenberg.item.ItemVisitor#visit(Vector<Item>)
     */
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
