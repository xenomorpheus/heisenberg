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
			fail("wear magic ring");
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
		Location newLocation = new Location();

		try {
			hand.ringWear(ring1);
			hand.ringRemove(ring1, newLocation);
		} catch (ExceptionItemNotPresent e) {
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
		Location newLocation = new Location();

		try {
			hand.ringWear(ring1);
			hand.ringWear(ring2);
			hand.ringRemove(ring1, newLocation);
			hand.ringRemove(ring2, newLocation);
		} catch (ExceptionItemNotPresent e) {
			fail("remove 2 rings - not present");
		} catch (Exception e) {
			fail("remove 2 rings - exception");
		}
	}

	@Test
	public void testRingRemoveRingNotPresent() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Location newLocation = new Location();

		try {
			hand.ringRemove(ring1, newLocation);
		} catch (ExceptionItemNotPresent e) {
			fail("wear ring1");
		} catch (Exception e) {
			fail("exception - remove ring");
		}
	}

	@Test
	public void testRingRemoveX() {
		Hand hand = new Hand();
		Ring ring1 = new Ring();
		Ring ring2 = new Ring();
		MagicRing magicRing1 = new MagicRing();
		MagicRing magicRing2 = new MagicRing();
		Location newLocation = new Location();

		try {
			hand.ringWear(ring1);
			hand.ringRemove(ring1, newLocation);
		} catch (ExceptionItemNotPresent e) {
			fail("wear ring1");
		} catch (Exception e) {
			fail("exception - remove ring");
		}
	}

	@Test
	public void testRingRemove() {
		// public void ringRemove(Ring ring, Location newLocation)
		fail("todo");
	}

}
