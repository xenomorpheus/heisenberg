package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.units.Currency;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/** Unit tests for the {@link Location} class and related functionality. */
public class LocationTest {

  static float TOLERANCE = 0.0001F;

  // TODO Add unit test for respecting max weight and volume of outer bag,
  // when adding Item to an inner bag.
  // Will require some kind of change notification system.

  // testEquals() is a waste of time.  All Item objects are different due to id field being random.

  /** Method testSetVolumeMax. */
  @Test
  public void testSetVolumeMax() {
    float volumeMax = 20F;
    Location location = new Location();
    location.setVolumeMax(volumeMax);
    float v = location.getVolumeMax();
    assertEquals("location.getVolumeMax=", volumeMax, v, 0.0001F);
  }

  /** Method testAdd. */
  @Test
  public void testAdd() {
    float volumeMax = 10F;
    float weightMax = 20F;
    // Location
    Location location = new Location();
    location.setWeightMax(weightMax);
    location.setVolumeMax(volumeMax);
    // Item
    Biscuit i = new Biscuit();
    // This should just fit
    i.setVolumeBase(volumeMax);
    i.setWeightBase(weightMax);
    location.add(i);
  }

  @Test
  public void testAddDoesRemove() {
    ItemContainer bagStart = new Bag();
    ItemContainer bagFinal = new Bag();
    Biscuit biscuit = new Biscuit();
    assertEquals("Setup - bagStart count setup ", 0, bagStart.size());
    assertEquals("Setup - bagFinal count setup ", 0, bagFinal.size());
    assertNull("Setup - biscuit's container", biscuit.getContainer());
    assertFalse("Setup - bagStart contains biscuit", bagStart.contains(biscuit));
    assertFalse("Setup - bagFinal contains biscuit", bagStart.contains(biscuit));
    // add biscuit to one bag
    bagStart.add(biscuit);
    assertEquals("Start - bagStart count setup ", 1, bagStart.size());
    assertEquals("Start - bagFinal count setup ", 0, bagFinal.size());
    assertEquals("Start - biscuit's container", bagStart, biscuit.getContainer());
    assertTrue("Start - bagStart contains biscuit", bagStart.contains(biscuit));
    assertFalse("Start - bagFinal contains biscuit", bagFinal.contains(biscuit));
    // transfer biscuit to other bag
    bagFinal.add(biscuit);
    assertEquals("Final - bagStart count setup ", 0, bagStart.size());
    assertEquals("Final - bagFinal count setup ", 1, bagFinal.size());
    assertEquals("Final - biscuit's container", bagFinal, biscuit.getContainer());
    assertFalse("Final - bagStart contains biscuit", bagStart.contains(biscuit));
    assertTrue("Final - bagFinal contains biscuit", bagFinal.contains(biscuit));
  }

  @Test
  public void testAddAll() {
    final int size = 3;
    Biscuit c1 = new Biscuit();
    Biscuit c2 = new Biscuit();
    Biscuit c3 = new Biscuit();
    List<Item> items = new ArrayList<Item>();
    items.add(c1);
    items.add(c2);
    items.add(c3);
    ItemContainer bag = new Bag();
    bag.addAll(items);
    assertEquals("add multi size", size, bag.size());
    ItemContainer newBag = new Bag();
    bag.empty(newBag);
    assertEquals("bag empty size", 0, bag.size());
    assertEquals("New ItemContainer size", size, newBag.size());
  }

  @Test
  public void testSize() {
    final ItemContainer bag = new Bag();
    final Biscuit c1 = new Biscuit();
    final Biscuit c2 = new Biscuit();
    final Biscuit c3 = new Biscuit();
    bag.add(c1);
    bag.add(c2);
    bag.add(c3);
    assertEquals("count", 3, bag.size());
  }

  @Test
  public void testGetWeight() {
    ItemContainer bag = new Bag();
    bag.setWeightBase(10);
    Biscuit c1 = new Biscuit();
    c1.setWeightBase(1);
    Biscuit c2 = new Biscuit();
    c2.setWeightBase(2);
    Biscuit c3 = new Biscuit();
    c3.setWeightBase(4);
    bag.add(c1);
    bag.add(c2);
    bag.add(c3);
    assertEquals("weight", 17, bag.totalWeight(), TOLERANCE);
  }

  @Test
  public void testGetVolume() {
    ItemContainer bag = new Bag();
    bag.setVolumeBase(10);
    Biscuit c1 = new Biscuit();
    c1.setVolumeBase(1);
    Biscuit c2 = new Biscuit();
    c2.setVolumeBase(2);
    Biscuit c3 = new Biscuit();
    c3.setVolumeBase(4);
    bag.add(c1);
    bag.add(c2);
    bag.add(c3);
    assertEquals("volume", 17, bag.totalVolume(), TOLERANCE);
  }

  @Test
  public void testGetValue() {
    ItemContainer bag = new Bag();
    bag.setValueBase(new Currency(1, 0, 0, 0));
    Biscuit c1 = new Biscuit();
    c1.setValueBase(new Currency(0, 1, 0, 0));
    Biscuit c2 = new Biscuit();
    c2.setValueBase(new Currency(0, 0, 1, 0));
    Biscuit c3 = new Biscuit();
    c3.setValueBase(new Currency(0, 0, 0, 1));
    bag.add(c1);
    bag.add(c2);
    bag.add(c3);
    assertEquals("value", new Currency(1, 1, 1, 1), bag.totalValue());
  }

  @Test
  public void testGetChildCount() {
    ItemContainer bag = new Bag();
    assertEquals("getChildCount", 0, bag.size());
    bag.add(new Biscuit());
    assertEquals("getChildCount", 1, bag.size());
  }

  @Test
  public void testGetChildAt() {
    ItemContainer bag = new Bag();
    Biscuit biscuit = new Biscuit();
    Scabbard scabbard = new Scabbard();
    bag.add(biscuit);
    assertEquals("getChildCount", biscuit, bag.get(0));
    bag.add(scabbard);
    assertEquals("getChildCount", biscuit, bag.get(0));
    assertEquals("getChildCount", scabbard, bag.get(1));
  }

  @Test
  public void testGetIndexOfChild() {
    ItemContainer bag = new Bag();
    Biscuit biscuit1 = new Biscuit();
    assertEquals("getIndexOfChild - empty", -1, bag.indexOf(biscuit1));
    bag.add(biscuit1);
    assertEquals("getIndexOfChild - only child", 0, bag.indexOf(biscuit1));
    Scabbard scabbard = new Scabbard();
    bag.add(scabbard);
    assertEquals("getIndexOfChild - first child", 0, bag.indexOf(biscuit1));
    assertEquals("getIndexOfChild - second child", 1, bag.indexOf(scabbard));
    Biscuit biscuit2 = new Biscuit();
    assertEquals("getIndexOfChild - not present", -1, bag.indexOf(biscuit2));
    Biscuit biscuit3 = new Biscuit();
    assertEquals(
        "getIndexOfChild - not present but biscuit3 equal to biscuit1", -1, bag.indexOf(biscuit3));
  }

  @Test(expected = TooHeavyException.class)
  public void testAddTooHeavy() {
    ItemContainer bag = new Bag();
    bag.setWeightMax(2);
    Biscuit biscuit = new Biscuit();
    biscuit.setWeightBase(3);
    try {
      bag.add(biscuit);
    } catch (TooHeavyException e) {
      assertEquals("container", null, biscuit.getContainer());
      throw e;
    }
  }

  @Test(expected = TooLargeException.class)
  public void testAddTooLarge() {
    ItemContainer bag = new Bag();
    bag.setVolumeMax(2);
    Biscuit biscuit = new Biscuit();
    biscuit.setVolumeBase(3);
    try {
      bag.add(biscuit);
    } catch (TooLargeException e) {
      assertEquals("container", null, biscuit.getContainer());
      throw e;
    }
  }

  /** Method testAddMulti. */
  @Test
  public void testAddMulti() {
    Location location = new Location();
    Location newLocation = new Location();
    Biscuit c1 = new Biscuit();
    Biscuit c2 = new Biscuit();
    Biscuit c3 = new Biscuit();
    List<Item> items = new ArrayList<Item>();
    items.add(c1);
    items.add(c2);
    items.add(c3);
    location.addAll(items);
    assertEquals("add multi size", 3, location.size());
    location.empty(newLocation);
    assertEquals("location size after empty", 0, location.size());
    assertEquals("newLocation size", 3, newLocation.size());
  }

  /** Method testBeNot. */
  @Test
  public void testBeNot() {
    Location location = new Location();
    Biscuit c1 = new Biscuit();
    Biscuit c2 = new Biscuit();
    Biscuit c3 = new Biscuit();
    location.add(c1);
    location.add(c2);
    location.add(c3);
    assertEquals("add multi size", 3, location.size());
    location.beNot();
    assertEquals("empty size", 0, location.size());
  }
}
