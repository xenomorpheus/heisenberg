package au.net.hal9000.heisenberg.item.property;

import java.util.Iterator;
import java.util.List;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.api.ExtraDimensional;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemSearchExtraDimensional extends ItemSearch {

    /**
     * Constructor for ItemSearchExtraDimensional.
     */
    public ItemSearchExtraDimensional() {
        super();
    }

    /**
     * Method visit.
     * @param item Item
     * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(Item)
     */
    public void visit(Item item) {
        if (item instanceof ExtraDimensional) {
            this.addMatchingItems(item);
        }
    }

    /**
     * Method visit.
     * @param itemVector List<Item>
     * @see au.net.hal9000.heisenberg.item.property.ItemVisitor#visit(List)
     */
    public void visit(List<Item> itemVector) {
        Iterator<Item> itr = itemVector.iterator();
        while (itr.hasNext()) {
            Item item = itr.next();
            if (item instanceof ExtraDimensional) {
                this.addMatchingItems(item);
            }
        }
    }

}
