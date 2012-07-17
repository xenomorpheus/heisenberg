package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;
import au.net.hal9000.player.item.exception.*;
import au.net.hal9000.player.units.Currency;

public class BagOfHoldingTest {

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
			assertTrue("type=" + type + ", weight",
					boh.getWeight().equals(weightBase));
			assertTrue("type=" + type + ", weightBase", boh.getWeightBase()
					.equals(weightBase));
			assertTrue("type=" + type + ", volume", boh.getVolume().equals(2F));
			assertTrue("type=" + type + ", volumeBase", boh.getVolumeBase()
					.equals(2F));
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
			try {
				boh.add(i);
			} catch (ExceptionTooHeavy e) {
				fail("type=" + type + ", too heavy :" + e.getMessage());
			} catch (ExceptionTooBig e) {
				fail("type=" + type + ", too big :" + e.getMessage());
			} catch (ExceptionInvalidType e) {
				fail("type=" + type + ", invalid type :" + e.getMessage());
			}
		}

	}

	@Test
	public void test_isMagical() {
		BagOfHolding bag = new BagOfHolding(1);
		assertTrue("isMagical", bag.isMagical());
	}

	@Test
	public void test_isExtraDimensional() {
		BagOfHolding bag = new BagOfHolding(1);
		assertTrue("isExtraDimensional", bag.isExtraDimensional());
	}

	@Test
	public void ordinary_add() {
		Cookie cookie = new Cookie();
		cookie.setLocation(new Human("Ground"));
		BagOfHolding bag = new BagOfHolding(1);
		try {
			bag.add(cookie);
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		assertEquals("cookie location", bag, cookie.getLocation());
	}

	// sharp exposed object causes rupture.
	@Test
	public void sharp_add_rupture() {
		Sword sword = new Sword();
		Human human = new Human("Ground");
		sword.setLocation(human);
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
		assertEquals("cookie location", human, sword.getLocation());
	}

	// add a wrapped sword to a bag of holding.
	@Test
	public void sharp_add_wrapped_sword() {
		Human human = new Human("Ground");
		Sword sword = new Sword();
		sword.setLocation(human);
		Scabbard scabbard = new Scabbard();
		scabbard.setLocation(human);
		try {
			scabbard.add(sword);
		} catch (ExceptionCantWear e1) {
			fail("could not add sword to scabbard");
		}
		// Check that locations are what we expect
		assertEquals("sword location", scabbard, sword.getLocation());
		assertEquals("scabard location", human, scabbard.getLocation());
		BagOfHolding bag = new BagOfHolding(1);

		// Try adding the scabbard to the BOH
		try {
			bag.add(scabbard);
		} catch (ExceptionInvalidType e) {
			fail("Expecting invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		// Check that locations are what we expect
		assertEquals("sword location", scabbard, sword.getLocation());
		assertEquals("scabard location", bag, scabbard.getLocation());
	}

	@Test
	public void multidimensional_add_rupture() {
		BagOfHolding bag_inner = new BagOfHolding(1);
		Human human = new Human("Ground");
		bag_inner.setLocation(human);
		BagOfHolding bag = new BagOfHolding(1);
		try {
			bag.add(bag_inner);
			fail("Expecting invalid type");
		} catch (ExceptionInvalidType e) {
			// nothing to do
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		assertEquals("cookie location", human, bag_inner.getLocation());
	}
}