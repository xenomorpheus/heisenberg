package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.units.Currency;

/**
 */
public class ItemContainerTest {

    static float TOLLERANCE = 0.0001F;

    // TODO Add unit test for respecting max weight and volume of outer bag,
    // when adding Item to an inner bag.
    // Will require some kind of change notification system.

    // testEquals() is a waste of time.  All Item objects are different due to id field being random.

    @Test
    public void testSetVolumeMax() {
        float volumeMax = 20F;
        ItemContainer bag = new Bag();
        bag.setVolumeMax(volumeMax);
        float v = bag.getVolumeMax();
        assertEquals("bag.getVolumeMax=", volumeMax, v, TOLLERANCE);
    }

    @Test
    public void testAdd() {
        float volumeMax = 10F;
        float weightMax = 20F;
        // ItemContainer
        ItemContainer bag = new Bag();
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

    @Test
    public void testAddDoesRemove() {
        ItemContainer bagStart = new Bag("Start");
        ItemContainer bagFinal = new Bag("Final");
        Cookie cookie = new Cookie();
        assertEquals("Setup - bagStart count setup ", 0, bagStart.size());
        assertEquals("Setup - bagFinal count setup ", 0, bagFinal.size());
        assertNull("Setup - cookie's container", cookie.getContainer());
        assertFalse("Setup - bagStart contains cookie",
                bagStart.contains(cookie));
        assertFalse("Setup - bagFinal contains cookie",
                bagStart.contains(cookie));
        // add cookie to one bag
        bagStart.add(cookie);
        assertEquals("Start - bagStart count setup ", 1, bagStart.size());
        assertEquals("Start - bagFinal count setup ", 0, bagFinal.size());
        assertEquals("Start - cookie's container", bagStart,
                cookie.getContainer());
        assertTrue("Start - bagStart contains cookie",
                bagStart.contains(cookie));
        assertFalse("Start - bagFinal contains cookie",
                bagFinal.contains(cookie));
        // transfer cookie to other bag
        bagFinal.add(cookie);
        assertEquals("Final - bagStart count setup ", 0, bagStart.size());
        assertEquals("Final - bagFinal count setup ", 1, bagFinal.size());
        assertEquals("Final - cookie's container", bagFinal,
                cookie.getContainer());
        assertFalse("Final - bagStart contains cookie",
                bagStart.contains(cookie));
        assertTrue("Final - bagFinal contains cookie",
                bagFinal.contains(cookie));
    }

    @Test
    public void testAddAll() {
        final int size = 3;
        ItemContainer bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        ItemContainer newBag = new Bag("New ItemContainer");
        List<Item> items = new ArrayList<Item>();
        items.add(c1);
        items.add(c2);
        items.add(c3);
        bag.addAll(items);
        assertEquals("add multi size", size, bag.size());
        bag.empty(newBag);
        assertEquals("bag empty size", 0, bag.size());
        assertEquals("New ItemContainer size", size, newBag.size());
    }

    @Test
    public void testSize() {
        final ItemContainer bag = new Bag();
        final Cookie c1 = new Cookie();
        final Cookie c2 = new Cookie();
        final Cookie c3 = new Cookie();
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("count", 3, bag.size());
    }

    @Test
    public void testGetWeight() {
        ItemContainer bag = new Bag();
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

    @Test
    public void testGetVolume() {
        ItemContainer bag = new Bag();
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

    @Test
    public void testGetValue() {
        ItemContainer bag = new Bag();
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

    @Test
    public void testBeNot() {
        ItemContainer bag = new Bag();
        Cookie c1 = new Cookie();
        Cookie c2 = new Cookie();
        Cookie c3 = new Cookie();
        bag.add(c1);
        bag.add(c2);
        bag.add(c3);
        assertEquals("add multi size", 3, bag.size());
        bag.beNot();
        assertEquals("empty size", 0, bag.size());
    }

    @Test
    public void testGetChildCount() {
        ItemContainer bag = new Bag();
        assertEquals("getChildCount", 0, bag.size());
        bag.add(new Cookie());
        assertEquals("getChildCount", 1, bag.size());
    }

    @Test
    public void testGetChildAt() {
        ItemContainer bag = new Bag();
        Cookie cookie = new Cookie();
        Scabbard scabbard = new Scabbard();
        bag.add(cookie);
        assertEquals("getChildCount", cookie, bag.get(0));
        bag.add(scabbard);
        assertEquals("getChildCount", cookie, bag.get(0));
        assertEquals("getChildCount", scabbard, bag.get(1));
    }

    @Test
    public void testGetIndexOfChild() {
        ItemContainer bag = new Bag();
        Scabbard scabbard = new Scabbard();
        Cookie cookie1 = new Cookie("Cookie1");
        Cookie cookie2 = new Cookie("Cookie2");
        Cookie cookie3 = new Cookie();
        assertEquals("getIndexOfChild - empty", -1, bag.indexOf(cookie1));
        bag.add(cookie1);
        assertEquals("getIndexOfChild - only child", 0, bag.indexOf(cookie1));
        bag.add(scabbard);
        assertEquals("getIndexOfChild - first child", 0, bag.indexOf(cookie1));
        assertEquals("getIndexOfChild - second child", 1,
                bag.indexOf(scabbard));
        assertEquals("getIndexOfChild - not present", -1, bag.indexOf(cookie2));
        assertEquals(
                "getIndexOfChild - not present but cookie3 equal to cookie1",
                -1, bag.indexOf(cookie3));
    }

    @Test(expected = TooHeavyException.class)
    public void testAddTooHeavy() {
        ItemContainer bag = new Bag();
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

    @Test(expected = TooLargeException.class)
    public void testAddTooLarge() {
        ItemContainer bag = new Bag();
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
