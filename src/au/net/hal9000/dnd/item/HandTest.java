package au.net.hal9000.dnd.item;
import static org.junit.Assert.*;

import org.junit.Test;

public class HandTest {

	// ringWear

	@Test
	public void testRingWearRing() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();

		try {
			hand.ringWear(ring1);
		} catch (Exception e) {
			fail("wear a non-magical ring");
		}
	}

	@Test
	public void testRingWear2Rings() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Ring ring2 = new Ring();

		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
		} catch (Exception e) {
			fail("wear two non-magical rings");
		}
	}

	@Test
	public void testRingWearMagicRing() {
		Hand hand = new Hand();
		MagicRing magicRing1 = new MagicRing();

		try {
			hand.ringWear(magicRing1);
		} catch (Exception e) {
			fail("wear magic ring " + e.toString() + " because "
					+ e.getMessage());
		}
	}

	@Test
	public void testRingWearMix() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Ring ring2 = new Ring();
		MagicRing magicRing1 = new MagicRing();

		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
			hand.ringWear(magicRing1);
		} catch (Exception e) {
			fail("wear ring mix");
		}
	}

	@Test
	public void testRingWear2MagicRings() {
		Hand hand = new Hand();
		MagicRing magicRing1 = new MagicRing();
		MagicRing magicRing2 = new MagicRing();

		try {
			hand.ringWear(magicRing1);
			hand.ringWear(magicRing2);
			fail("wear two magic rings");
		} catch (ExceptionCantWear e) {
			// nothing to do
		} catch (Exception e) {
			fail("wear two magic rings - exception");
		}

	}

	// ringRemove

	@Test
	public void testRingRemoveRing() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Location newLocation = new Location("Ground");

		try {
			hand.ringWear(ring1);
			hand.ringRemove(ring1, newLocation);
		} catch (ExceptionCantRemove e) {
			fail("remove ring - not present");
		} catch (Exception e) {
			fail("remove ring - exception");
		}
	}

	@Test
	public void testRingRemove2Rings() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Ring ring2 = new Ring();
		Location newLocation = new Location("Ground");

		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
			hand.ringRemove(ring1, newLocation);
			hand.ringRemove(ring2, newLocation);
		} catch (ExceptionCantRemove e) {
			fail("remove 2 rings - not present");
		} catch (Exception e) {
			fail("remove 2 rings - exception");
		}
	}

	@Test
	public void testRingRemoveMagicRing() {
		Hand hand = new Hand();
		MagicRing magicRing1 = new MagicRing();
		Location newLocation = new Location("Ground");

		try {
			hand.ringWear(magicRing1);
			hand.ringRemove(magicRing1, newLocation);
		} catch (ExceptionCantRemove e) {
			fail("remove magic ring - not present");
		} catch (Exception e) {
			fail("remove magic ring - exception");
		}
	}

	@Test
	public void testRingRemoveRingNotPresent() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Location newLocation = new Location("Ground");

		try {
			hand.ringRemove(ring1, newLocation);
			fail("remove ring1");
		} catch (ExceptionCantRemove e) {
			// nothing to do
		} catch (Exception e) {
			fail("remove ring - exception");
		}
	}

	@Test
	public void testRingRemoveMagicRingNotPresent() {
		Hand hand = new Hand();
		MagicRing magicRing1 = new MagicRing();
		Location newLocation = new Location("Ground");

		try {
			hand.ringRemove(magicRing1, newLocation);
			fail("remove magic ring1");
		} catch (ExceptionCantRemove e) {
			// nothing to do
		} catch (Exception e) {
			fail("remove magic ring - exception");
		}
	}

	@Test
	public void testRingRemoveMix() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Ring ring2 = new Ring();
		MagicRing magicRing1 = new MagicRing();
		Location newLocation = new Location("Ground");

		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
			hand.ringWear(magicRing1);
			hand.ringRemove(ring1, newLocation);
			hand.ringRemove(ring2, newLocation);
			hand.ringRemove(magicRing1, newLocation);
		} catch (ExceptionCantRemove e) {
			fail("remove - not present");
		} catch (Exception e) {
			fail("remove ring - exception");
		}
	}

	// getWeight

	@Test
	public void testGetWeightEmpty() {
		Hand hand = new Hand();
		assertEquals("weight", hand.getWeight(), 0.0F, 0.0001F);
	}

	@Test
	public void testGetWeight1Ring() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		ring1.setWeightBase(1.1F);
		try {
			hand.ringWear(ring1);
		} catch (Exception e) {
			fail(e.toString());
		}
		assertEquals("weight", hand.getWeight(), 1.1F, 0.0001F);
	}

	@Test
	public void testGetWeight2Ring() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		MagicRing ring2 = new MagicRing();
		ring1.setWeightBase(1.1F);
		ring2.setWeightBase(2.2F);
		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
		} catch (Exception e) {
			fail(e.toString());
		}
		assertEquals("weight", hand.getWeight(), 3.3F, 0.0001F);
	}
}
