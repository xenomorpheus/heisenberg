package au.net.hal9000.heisenberg.item.property;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class ItemSearchExtraDimensionalTest {

    /**
     * Method testAccept. Ordinary non-ED, non-container.
     */
    @Test
    public void testAccept1()

    {
        Cookie cookie = new Cookie();
        ItemSearch searchCookie = new ItemSearchExtraDimensional();
        cookie.accept(searchCookie);
        assertEquals("count", 0, searchCookie.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Ordinary non-ED, container.
     */
    @Test
    public void testAccept2() {
        Box box = new Box();
        ItemSearch searchBox = new ItemSearchExtraDimensional();
        box.accept(searchBox);
        assertEquals("count", 0, searchBox.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Empty ED container.
     */
    @Test
    public void testAccept3() {
        BagOfHolding boh = new BagOfHolding(2);
        ItemSearch searchBoh = new ItemSearchExtraDimensional();
        boh.accept(searchBoh);
        assertEquals("count", 1, searchBoh.getMatchingItemsCount());
    }

    // Ordinary container with ED inside
    /**
     * Method testAccept.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAccept4() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Box box = new Box();
        BagOfHolding boh = new BagOfHolding(1);
        box.setWeightMax(boh.getWeight());
        box.setVolumeMax(boh.getVolume());
        box.add(boh);
        ItemSearch searchBox2 = new ItemSearchExtraDimensional();
        box.accept(searchBox2);
        assertEquals("count", 1, searchBox2.getMatchingItemsCount());
    }

    // Ordinary container with non-ED inside
    /**
     * Method testAccept.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAccept5() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Box box = new Box();
        BagOfHolding boh = new BagOfHolding(1);
        box.setWeightMax(boh.getWeight());
        box.setVolumeMax(boh.getVolume());
        box.add(boh);
        ItemSearch searchBox3 = new ItemSearchExtraDimensional();
        box.accept(searchBox3);
        assertEquals("count", 1, searchBox3.getMatchingItemsCount());
    }

    // java Container with ED and non-ED inside
    /**
     * Method testAccept.
     */
    @Test
    public void testAccept6() {
        Box box = new Box();
        BagOfHolding boh = new BagOfHolding(1);
        Cookie cookie = new Cookie();
        List<Item> container = new ArrayList<Item>();
        container.add(box);
        container.add(boh);
        container.add(cookie);

        ItemSearch search = new ItemSearchExtraDimensional();
        search.visit(container);

        box.accept(search);
        assertEquals("count", 1, search.getMatchingItemsCount());
        assertEquals("found", boh, search.getMatchingItems().get(0));
    }

}
