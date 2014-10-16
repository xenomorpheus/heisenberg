package au.net.hal9000.heisenberg.ai;

import java.util.Date;

import au.net.hal9000.heisenberg.item.Item;

/**
 * Holds a memory for a period of time. Try to consider immutable.
 * 
 * @author bruins
 *
 */

public class MemoryItem extends Memory {
    /** the item this memory is about. */
    private Item item;

    /**
     * Constructor.
     * 
     * @param createdDate
     *            date memory created.
     * @param expiryDate
     *            date memory expires.
     * @param item
     *            the item this memory is about.
     */
    MemoryItem(Date createdDate, Date expiryDate, Item item) {
        super(createdDate, expiryDate);
        this.item = item;
    }

    /**
     * @return get the item.
     */
    public Item getItem() {
        return item;
    }

}
