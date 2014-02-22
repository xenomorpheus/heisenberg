package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.units.Currency;

/**
 */
public class ItemContainerTest {

    /**
     * Field TOLLERANCE.
     */
    static float TOLLERANCE = 0.0001F;

    // TODO Add unit test for respecting max weight and volume of outer bag,
    // when adding Item to an inner bag.
    // Will require some kind of change notification system.

    /**
     * Method testSetVolumeMax.
     */
    @Test
    public void testSetVolumeMax() {
        float volumeMax = 20F;
        Bag bag = new Bag();
        bag.setVolumeMax(volumeMax);
        float v = bag.getVolumeMax();
        assertEquals("bag.getVolumeMax=", volumeMax, v, TOLLERANCE);
    }

    /**
     * Method testAdd.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testAdd() throws InvalidTypeException, CantWearException {
        float volumeMax = 10F;
        float weightMax = 20F;
        {
            // Bag
            Bag bag = new Bag();
            bag.setWeightMax(weightMax);
            bag.setVolumeMax(volumeMax);
            // Item
            Cookie i = new Cookie();
            // This should just fit
            i.setVolumeBase(volumeMax);
            i.setWeightBase(weightMax);
            bag.add(i);
            // Check location is set
            assertEquals("location set", bag, i.getContainer());
        }

        {
            // add function should be smart enough to cause the removal
            // from the losing container.
            Bag bag1 = new Bag();
            Bag bag2 = new Bag();
            Cookie cookie = new Cookie();
            assertEquals("bag1 count setup ", 0, bag1.getChildCount());
            assertEquals("bag2 count setup ", 0, bag2.getChildCount());
            // add cookie to one bag
            bag1.add(cookie);
            assertEquals("bag1 count setup ", 1, bag1.getChildCount());
            // transfer cookie to other bag
            bag2.add(cookie);
            assertEquals("bag1 count setup ", 0, bag1.getChildCount());
            assertEquals("bag2 count setup ", 1, bag2.getChildCount());
        }

    }

    /**
     * Method testAddMulti.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testAddMulti() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        Bag newBag = new Bag("New Bag");
        Vector<Item> items = new Vector<Item>();
        items.add(c1);
        items.add(c2);
        items.add(c3);
        bag.add(items);
        assertEquals("add multi size", 3, bag.getContentsCount());
        bag.empty(newBag);
        assertEquals("bag empty size", 0, bag.getContentsCount());
        assertEquals("New Bag size", 3, newBag.getContentsCount());
    }

    /**
     * Method testGetContentsCount.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testGetContentsCount() throws InvalidTypeException,
            CantWearException {
        Bag bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("count", 3, bag.getContentsCount());
    }

    /**
     * Method testGetWeight.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testGetWeight() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        bag.setWeightBase(10);
        Cookie c1 = new Cookie();
        c1.setWeightBase(1);
        Cookie c2 = new Cookie();
        c2.setWeightBase(2);
        Cookie c3 = new Cookie();
        c3.setWeightBase(4);
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("weight", 17, bag.getWeight(), TOLLERANCE);
    }

    /**
     * Method testGetVolume.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testGetVolume() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        bag.setVolumeBase(10);
        Cookie c1 = new Cookie();
        c1.setVolumeBase(1);
        Cookie c2 = new Cookie();
        c2.setVolumeBase(2);
        Cookie c3 = new Cookie();
        c3.setVolumeBase(4);
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("volume", 17, bag.getVolume(), TOLLERANCE);
    }

    /**
     * Method testGetValue.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testGetValue() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        bag.setValueBase(new Currency(1, 0, 0, 0));
        Cookie c1 = new Cookie();
        c1.setValueBase(new Currency(0, 1, 0, 0));
        Cookie c2 = new Cookie();
        c2.setValueBase(new Currency(0, 0, 1, 0));
        Cookie c3 = new Cookie();
        c3.setValueBase(new Currency(0, 0, 0, 1));
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("value", new Currency(1, 1, 1, 1), bag.getValue());
    }

    /**
     * Method testBeNot.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testBeNot() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("add multi size", 3, bag.getContentsCount());
        bag.beNot();
        assertEquals("empty size", 0, bag.getContentsCount());
    }

    /**
     * Method isLeaf.
     */
    @Test
    public void isLeaf() {
        Bag bag = new Bag();
        assertFalse("isLeaf", bag.isLeaf());
    }

    /**
     * Method testGetChildCount.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testGetChildCount() throws InvalidTypeException,
            CantWearException {
        Bag bag = new Bag();
        assertEquals("getChildCount", 0, bag.getChildCount());
        bag.add(new Cookie());
        assertEquals("getChildCount", 1, bag.getChildCount());
    }

    /**
     * Method testGetChildAt.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testGetChildAt() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        Cookie cookie = new Cookie();
        Scabbard scabbard = new Scabbard();
        bag.add(cookie);
        assertEquals("getChildCount", cookie, bag.getChildAt(0));
        bag.add(scabbard);
        assertEquals("getChildCount", cookie, bag.getChildAt(0));
        assertEquals("getChildCount", scabbard, bag.getChildAt(1));
    }

    /**
     * Method testGetIndexOfChild.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testGetIndexOfChild() throws InvalidTypeException,
            CantWearException {
        Bag bag = new Bag();
        Scabbard scabbard = new Scabbard();
        Cookie cookie1 = new Cookie("Cookie1");
        Cookie cookie2 = new Cookie("Cookie2");
        Cookie cookie3 = new Cookie();
        assertEquals("getIndexOfChild - empty", -1,
                bag.getIndexOfChild(cookie1));
        bag.add(cookie1);
        assertEquals("getIndexOfChild - only child", 0,
                bag.getIndexOfChild(cookie1));
        bag.add(scabbard);
        assertEquals("getIndexOfChild - first child", 0,
                bag.getIndexOfChild(cookie1));
        assertEquals("getIndexOfChild - second child", 1,
                bag.getIndexOfChild(scabbard));
        assertEquals("getIndexOfChild - not present", -1,
                bag.getIndexOfChild(cookie2));
        assertEquals(
                "getIndexOfChild - not present but cookie3 equal to cookie1",
                -1, bag.getIndexOfChild(cookie3));
    }

    /**
     * Method testAddLogging.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     */
    @Test
    public void testAddLogging() throws InvalidTypeException, CantWearException {
        Bag bag = new Bag();
        bag.setWeightMax(2);
        bag.setVolumeMax(2);
        Cookie cookie = new Cookie();
        cookie.setWeightBase(3);
        cookie.setVolumeBase(3);
        bag.add(cookie);
        // Re-add
        bag.add(cookie);
    }

}
