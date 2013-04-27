package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.exception.*;
import au.net.hal9000.heisenberg.item.property.ExtraDimensional;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;

public class BagOfHoldingTest {
    private static final float WITHIN_MARGIN = 0.00009F;

    @Test
    public void typeTest() {
        Bag ordinaryBag = new Bag();
        for (int type = 1; type <= 4; type++) {
            float weightBase = 0F;
            float weightMax = 0F;
            float volumeMax = 0F;
            Currency cost = new Currency(0, 0, 0, 0);

            if (type == 1) {
                weightBase = 15F;
                weightMax = 250F;
                volumeMax = 30F;
                cost = new Currency(0, 2500, 0, 0);
            }
            if (type == 2) {
                weightBase = 25F;
                weightMax = 500F;
                volumeMax = 70F;
                cost = new Currency(0, 5000, 0, 0);
            }
            if (type == 3) {
                weightBase = 35F;
                weightMax = 1000F;
                volumeMax = 150F;
                cost = new Currency(0, 7400, 0, 0);
            }
            if (type == 4) {
                weightBase = 60F;
                weightMax = 1500F;
                volumeMax = 150F;
                cost = new Currency(0, 10000, 0, 0);
            }
            BagOfHolding boh = new BagOfHolding(type);
            assertEquals("type", boh.getType(), type);
            assertEquals("type=" + type + ", weight", boh.getWeight(),
                    weightBase, WITHIN_MARGIN);
            assertEquals("type=" + type + ", weightBase", boh.getWeightBase(),
                    weightBase, WITHIN_MARGIN);
            assertEquals("type=" + type + ", volume", boh.getVolume(), 2F,
                    WITHIN_MARGIN);
            assertEquals("type=" + type + ", volumeBase", boh.getVolumeBase(),
                    2F, WITHIN_MARGIN);
            assertTrue("type=" + type + ", cost",
                    boh.getValueBase().equals(cost));
            // Should look like an ordinary bag :-)
            assertEquals("type=" + type + ", description",
                    boh.getDescription(), ordinaryBag.getDescription());

            // Check weight and volume limits.
            // This cookie should only just fit.
            Cookie i = new Cookie();
            i.setVolumeBase(volumeMax);
            i.setWeightBase(weightMax);
            boh.add(i);
        }

    }

    @Test
    public void testMagical() {
        BagOfHolding bag = new BagOfHolding(1);
        assertTrue("Magical", ItemProperty.isMagical(bag));
    }

    @Test
    public void testExtraDimensional() {
        BagOfHolding bag = new BagOfHolding(1);
        assertTrue("is ExtraDimensional", bag instanceof ExtraDimensional);
    }

    @Test
    public void testAdd() {
        Cookie cookie = new Cookie();
        cookie.setContainer(new Human());
        BagOfHolding bag = new BagOfHolding(1);
        bag.add(cookie);
        assertEquals("cookie location", bag, cookie.getContainer());
    }

    // sharp exposed object causes rupture.
    @Test
    public void testAddSharp() {
        Sword sword = new Sword();
        Human human = new Human();
        sword.setContainer(human);
        BagOfHolding bag = new BagOfHolding(1);
        try {
            bag.add(sword);
            fail("Expecting invalid type");
        } catch (ExceptionInvalidType e) {
            // nothing to do
        } catch (ExceptionTooHeavy e) {
            fail("too heavy");
        } catch (ExceptionTooBig e) {
            fail("too big");
        }
        assertEquals("cookie location", human, sword.getContainer());
    }

    // add a wrapped sword to a bag of holding.
    @Test
    public void testAddWrappedSharp() {
        Human human = new Human();
        Sword sword = new Sword();
        sword.setContainer(human);
        Scabbard scabbard = new Scabbard();
        scabbard.setContainer(human);
        scabbard.add(sword);
        // Check that locations are what we expect
        assertEquals("sword location", scabbard, sword.getContainer());
        assertEquals("scabard location", human, scabbard.getContainer());
        BagOfHolding bag = new BagOfHolding(1);

        // Try adding the scabbard to the BOH
        bag.add(scabbard);

        // Check that locations are what we expect
        assertEquals("sword location", scabbard, sword.getContainer());
        assertEquals("scabard location", bag, scabbard.getContainer());
    }

    @Test
    public void testAddMultidimensional() {
        Human human = new Human();
        BagOfHolding bag_inner = new BagOfHolding(1);
        bag_inner.setContainer(human);
        BagOfHolding bag = new BagOfHolding(1);
        try {
            bag.add(bag_inner);
            fail("expecting exception");
        } catch (ExceptionInvalidType e) {
            //  nothing to do.
        }
        assertEquals("cookie location", human, bag_inner.getContainer());
    }

}
