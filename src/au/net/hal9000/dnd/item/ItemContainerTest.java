package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;
import au.net.hal9000.dnd.item.exception.*;

public class ItemContainerTest {

	@Test
	public void addTest() {
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
		try {
			bag.add(i);
		} catch (ExceptionTooHeavy e) {
			fail("too heavy :" + e.getMessage());
		} catch (ExceptionTooBig e) {
			fail("too big: " + e.getMessage());
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: " + e.getMessage());
		}
		// This should just break the volume
		Bag bag2 = new Bag();
		bag2.setWeightMax(weightMax);
		bag2.setVolumeMax(volumeMax);
		Cookie i2 = new Cookie();
		i2.setVolumeBase(volumeMax + 0.01F);
		i2.setWeightBase(weightMax);
		try {
			bag2.add(i2);
			fail("should fail due to volume");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy: " + e.getMessage());
		} catch (ExceptionTooBig e) {
			// nothing to do
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: " + e.getMessage());
		}
		// This should just break the weight
		Bag bag3 = new Bag();
		bag3.setWeightMax(weightMax);
		bag3.setVolumeMax(volumeMax);
		Cookie i3 = new Cookie();
		i3.setVolumeBase(volumeMax);
		i3.setWeightBase(weightMax + 0.01F);
		try {
			bag3.add(i3);
			fail("should fail due to size");
		} catch (ExceptionTooHeavy e) {
			// nothing to do
		} catch (ExceptionTooBig e) {
			fail("too big: " + e.getMessage());
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: " + e.getMessage());
		}

	}

	@Test
	public void test_add_multi() {
		Bag bag = new Bag();
		Cookie c1 = new Cookie();
		Cookie c2 = new Cookie();
		Cookie c3 = new Cookie();
		Location ground = new Location("Ground");
		Vector<Item> items = new Vector<Item>();
		items.add(c1);
		items.add(c2);
		items.add(c3);
		bag.add(items);
		assertEquals("add multi size", 3, bag.getContentsCount());
		bag.empty(ground);
		assertEquals("empty size", 0, bag.getContentsCount());
	}

	@Test
	public void test_beNot() {
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

}
