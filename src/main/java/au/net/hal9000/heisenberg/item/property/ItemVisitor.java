package au.net.hal9000.heisenberg.item.property;

import java.util.Vector;

import au.net.hal9000.heisenberg.item.Item;

/**
 * Visitor Design Pattern for Item class.
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface ItemVisitor {
    
    /**
     * Visit this item.
     * @param item item to visit.
     */
    void visit(Item item);

    /**
     * Visit these items.
     * @param items set of items.
     */
    void visit(Vector<Item> items);

}
