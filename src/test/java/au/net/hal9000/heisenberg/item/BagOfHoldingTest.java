package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;
import org.junit.Test;

/** Test the BagOfHolding class. */
public class BagOfHoldingTest {
  /** how close do the floats need to be to match. */
  private static final float FLOAT_TOLERANCE = 0.00009F;

  /** test the properties for each type of bag. */
  @Test
  public void typeTest() {
    Bag ordinaryBag = new Bag();
    for (int type = BagOfHolding.TYPE_I; type <= BagOfHolding.TYPE_IV; type++) {
      float expectedWeightBase = 0F;
      float expectedWeightMax = 0F;
      float expectedVolumeMax = 0F;
      Currency expectedCost = new Currency(0, 0, 0, 0);

      if (type == BagOfHolding.TYPE_I) {
        expectedWeightBase = BagOfHolding.TYPE_I_WEIGHT_BASE;
        expectedWeightMax = BagOfHolding.TYPE_I_WEIGHT_MAX;
        expectedVolumeMax = BagOfHolding.TYPE_I_VOLUME_MAX;
        expectedCost = new Currency(0, BagOfHolding.TYPE_I_VALUE_GP, 0, 0);
      }
      if (type == BagOfHolding.TYPE_II) {
        expectedWeightBase = BagOfHolding.TYPE_II_WEIGHT_BASE;
        expectedWeightMax = BagOfHolding.TYPE_II_WEIGHT_MAX;
        expectedVolumeMax = BagOfHolding.TYPE_II_VOLUME_MAX;
        expectedCost = new Currency(0, BagOfHolding.TYPE_II_VALUE_GP, 0, 0);
      }
      if (type == BagOfHolding.TYPE_III) {
        expectedWeightBase = BagOfHolding.TYPE_III_WEIGHT_BASE;
        expectedWeightMax = BagOfHolding.TYPE_III_WEIGHT_MAX;
        expectedVolumeMax = BagOfHolding.TYPE_III_VOLUME_MAX;
        expectedCost = new Currency(0, BagOfHolding.TYPE_III_VALUE_GP, 0, 0);
      }
      if (type == BagOfHolding.TYPE_IV) {
        expectedWeightBase = BagOfHolding.TYPE_IV_WEIGHT_BASE;
        expectedWeightMax = BagOfHolding.TYPE_IV_WEIGHT_MAX;
        expectedVolumeMax = BagOfHolding.TYPE_IV_VOLUME_MAX;
        expectedCost = new Currency(0, BagOfHolding.TYPE_IV_VALUE_GP, 0, 0);
      }

      BagOfHolding boh = new BagOfHolding(type);
      assertEquals("type", boh.getType(), type);
      assertEquals(
          "type=" + type + ", weight", boh.getWeight(), expectedWeightBase, FLOAT_TOLERANCE);
      assertEquals(
          "type=" + type + ", weightBase",
          boh.getWeightBase(),
          expectedWeightBase,
          FLOAT_TOLERANCE);
      assertEquals("type=" + type + ", volume", boh.getVolume(), 2F, FLOAT_TOLERANCE);
      assertEquals("type=" + type + ", volumeBase", boh.getVolumeBase(), 2F, FLOAT_TOLERANCE);
      assertTrue("type=" + type + ", cost", expectedCost.equals(boh.getValueBase()));
      // Should look like an ordinary bag :-)
      assertEquals(
          "type=" + type + ", description", boh.getDescription(), ordinaryBag.getDescription());

      // Check weight and volume limits.
      // This biscuit should only just fit.
      Biscuit i = new Biscuit();
      i.setVolumeBase(expectedVolumeMax);
      i.setWeightBase(expectedWeightMax);
      boh.add(i);
    }
  }

  /** testing that the BoH is magical. */
  @Test
  public void testMagical() {
    BagOfHolding bag = new BagOfHolding(1);
    assertTrue("Magical", ItemProperty.isMagical(bag));
  }

  /** testing that an ordinary Item may be added to the bag. */
  @Test
  public void testAdd() {
    Biscuit biscuit = new Biscuit();
    biscuit.setContainer(new Box());
    BagOfHolding bagOfHolding = new BagOfHolding(1);
    bagOfHolding.add(biscuit);
    assertEquals("biscuit location", bagOfHolding, biscuit.getContainer());
  }

  /** an exposed sharp object causes rupture. */
  @Test(expected = InvalidTypeException.class)
  public void testAddSharp() {
    Sword sword = new Sword();
    Box box = new Box();
    sword.setContainer(box);
    BagOfHolding bag = new BagOfHolding(1);
    try {
      bag.add(sword);
    } catch (InvalidTypeException e) {
      // The Exception we want
      assertEquals("biscuit location", box, sword.getContainer());
      throw e;
    }
  }

  /** a covered sharp object may be added. */
  @Test
  public void testAddWrappedSharp() {
    Box box = new Box();
    Sword sword = new Sword();
    sword.setContainer(box);
    Scabbard scabbard = new Scabbard();
    scabbard.setContainer(box);
    scabbard.add(sword);
    // Check that locations are what we expect
    assertEquals("sword location", scabbard, sword.getContainer());
    assertEquals("scabbard location", box, scabbard.getContainer());
    BagOfHolding bag = new BagOfHolding(1);

    // Try adding the scabbard to the BOH
    bag.add(scabbard);

    // Check that locations are what we expect
    assertEquals("sword location", scabbard, sword.getContainer());
    assertEquals("scabbard location", bag, scabbard.getContainer());
  }

  /** an extra-dimensional object causes rupture. */
  @Test(expected = InvalidTypeException.class)
  public void testAddMultidimensional() {
    Box box = new Box();
    BagOfHolding bagInner = new BagOfHolding(1);
    bagInner.setContainer(box);
    BagOfHolding bag = new BagOfHolding(1);
    try {
      bag.add(bagInner);
    } catch (InvalidTypeException e) {
      assertEquals("biscuit location", box, bagInner.getContainer());
      throw e;
    }
  }
}
