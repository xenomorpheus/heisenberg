package au.net.hal9000.heisenberg.item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
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
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAdd() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        float volumeMax = 10F;
        float weightMax = 20F;
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

    /**
     * Add function should be smart enough to cause the removal from the losing
     * container.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAddDoesRemove() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Bag bagStart = new Bag("Start");
        Bag bagFinal = new Bag("Final");
        Cookie cookie = new Cookie();
        assertEquals("Setup - bagStart count setup ", 0,
                bagStart.getChildCount());
        assertEquals("Setup - bagFinal count setup ", 0,
                bagFinal.getChildCount());
        assertNull("Setup - cookie's container", cookie.getContainer());
        assertFalse("Setup - bagStart contains cookie",
                bagStart.contains(cookie));
        assertFalse("Setup - bagFinal contains cookie",
                bagStart.contains(cookie));
        // add cookie to one bag
        bagStart.add(cookie);
        assertEquals("Start - bagStart count setup ", 1,
                bagStart.getChildCount());
        assertEquals("Start - bagFinal count setup ", 0,
                bagFinal.getChildCount());
        assertEquals("Start - cookie's container", bagStart,
                cookie.getContainer());
        assertTrue("Start - bagStart contains cookie",
                bagStart.contains(cookie));
        assertFalse("Start - bagFinal contains cookie",
                bagFinal.contains(cookie));
        // transfer cookie to other bag
        bagFinal.add(cookie);
        assertEquals("Final - bagStart count setup ", 0,
                bagStart.getChildCount());
        assertEquals("Final - bagFinal count setup ", 1,
                bagFinal.getChildCount());
        assertEquals("Final - cookie's container", bagFinal,
                cookie.getContainer());
        assertFalse("Final - bagStart contains cookie",
                bagStart.contains(cookie));
        assertTrue("Final - bagFinal contains cookie",
                bagFinal.contains(cookie));
    }

    /**
     * Method testAddMulti.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAddMulti() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        final int size = 3;
        Bag bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        Bag newBag = new Bag("New Bag");
        List<Item> items = new ArrayList<Item>();
        items.add(c1);
        items.add(c2);
        items.add(c3);
        bag.addItems(items);
        assertEquals("add multi size", size, bag.getContentsCount());
        bag.empty(newBag);
        assertEquals("bag empty size", 0, bag.getContentsCount());
        assertEquals("New Bag size", size, newBag.getContentsCount());
    }

    /**
     * Method testGetContentsCount.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetContentsCount() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        final Bag bag = new Bag();
        final Cookie c1 = new Cookie();
        final Cookie c2 = new Cookie();
        final Cookie c3 = new Cookie();
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("count", 3, bag.getContentsCount());
    }

    /**
     * Method testGetWeight.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetWeight() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
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
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetVolume() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
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
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetValue() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
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
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testBeNot() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
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
     * Method testGetChildCount.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetChildCount() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Bag bag = new Bag();
        assertEquals("getChildCount", 0, bag.getChildCount());
        bag.add(new Cookie());
        assertEquals("getChildCount", 1, bag.getChildCount());
    }

    /**
     * Method testGetChildAt.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetChildAt() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
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
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetIndexOfChild() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
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
     * 
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test(expected = TooHeavyException.class)
    public void testAddTooHeavy() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Bag bag = new Bag();
        bag.setWeightMax(2);
        Cookie cookie = new Cookie();
        cookie.setWeightBase(3);
        try {
            bag.add(cookie);
        } catch (TooHeavyException e) {
            assertEquals("container", null, cookie.getContainer());
            throw e;
        }
    }

    /**
     * 
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test(expected = TooLargeException.class)
    public void testAddTooLarge() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Bag bag = new Bag();
        bag.setVolumeMax(2);
        Cookie cookie = new Cookie();
        cookie.setVolumeBase(3);
        try {
            bag.add(cookie);
        } catch (TooLargeException e) {
            assertEquals("container", null, cookie.getContainer());
            throw e;
        }
    }

}
