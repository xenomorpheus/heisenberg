package au.net.hal9000.heisenberg.item.property;

import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemVisitor;


/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public abstract class ItemSearch implements ItemVisitor {

    /**
     * Field matchingItems.
     */
    private Vector<Item> matchingItems;

    /**
     * Constructor for ItemSearch.
     */
    protected ItemSearch() {
        super();
        matchingItems = new Vector<Item>();
    }

    /**
     * Method addMatchingItems.
     * @param item Item
     */
    public void addMatchingItems(Item item) {
        matchingItems.add(item);
    }

    /**
     * Method getMatchingItems.
     * @return Vector<Item>
     */
    public Vector<Item> getMatchingItems() {
        return matchingItems;
    }

    /**
     * Method getMatchingItemsCount.
     * @return int
     */
    public int getMatchingItemsCount() {
        return matchingItems.size();
    }

    /**
     * Method visit.
     * @param item Item
     * @see au.net.hal9000.heisenberg.item.ItemVisitor#visit(Item)
     */
    public abstract void visit(Item item);

    /**
     * Method visit.
     * @param item Vector<Item>
     * @see au.net.hal9000.heisenberg.item.ItemVisitor#visit(Vector<Item>)
     */
    public abstract void visit(Vector<Item> item);
}
