package au.net.hal9000.heisenberg.item.property;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemSearchMagicalTest {

    /**
     * Method testAccept. Ordinary non-magical, non-container.
     */
    @Test
    public void testAccept1() {
        Cookie cookie = new Cookie();
        ItemSearch searchCookie = new ItemSearchMagical();
        cookie.accept(searchCookie);
        assertEquals(0, searchCookie.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Ordinary non-magical, container.
     */
    @Test
    public void testAccept2() {
        Box box = new Box();
        ItemSearch searchBox = new ItemSearchMagical();
        box.accept(searchBox);
        assertEquals("count", 0, searchBox.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Empty magical container.
     */
    @Test
    public void testAccept3() {
        BagOfHolding boh = new BagOfHolding(1);
        ItemSearch searchBoh = new ItemSearchMagical();
        boh.accept(searchBoh);
        assertEquals("count", 1, searchBoh.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Ordinary container with non-magical inside.
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAccept4() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Box box = new Box();
        Cookie cookie = new Cookie();
        box.setWeightMax(cookie.getWeight());
        box.setVolumeMax(cookie.getVolume());
        box.add(cookie);
        ItemSearch searchBox2 = new ItemSearchMagical();
        box.accept(searchBox2);
        assertEquals("count", 0, searchBox2.getMatchingItemsCount());
    }

    /**
     * Method testAccept. Ordinary container with magical inside.
     * 
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
        ItemSearch searchBox = new ItemSearchMagical();
        box.accept(searchBox);
        assertEquals("count", 1, searchBox.getMatchingItemsCount());
        assertEquals("found", boh, searchBox.getMatchingItems().elementAt(0));
    }

    /**
     * Method testAccept. Ordinary container with magical and non-magical
     * inside.
     * 
     * 
     * 
     * @throws InvalidTypeException
     *             *
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAccept6() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Box box = new Box();
        BagOfHolding boh = new BagOfHolding(1);
        Cookie cookie = new Cookie();
        box.setWeightMax(boh.getWeight() + cookie.getWeight());
        box.setVolumeMax(boh.getVolume() + cookie.getVolume());
        box.add(boh);
        box.add(cookie);
        ItemSearch searchBox = new ItemSearchMagical();
        box.accept(searchBox);
        assertEquals("count", 1, searchBox.getMatchingItemsCount());
        assertEquals("found", boh, searchBox.getMatchingItems().elementAt(0));
    }

    /**
     * Method testAccept. java Container with magical and non-magical inside.
     */
    @Test
    public void testAccept7() {
        Box box = new Box();
        BagOfHolding boh = new BagOfHolding(1);
        Cookie cookie = new Cookie();
        Vector<Item> container = new Vector<Item>();
        container.add(box);
        container.add(boh);
        container.add(cookie);

        ItemSearch search = new ItemSearchMagical();
        search.visit(container);

        box.accept(search);
        assertEquals("count", 1, search.getMatchingItemsCount());
        assertEquals("found", boh, search.getMatchingItems().elementAt(0));
    }

}
