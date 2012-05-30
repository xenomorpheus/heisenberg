package au.net.hal9000.dnd.item;
import static org.junit.Assert.*;

import org.junit.Test;

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
			fail("too heavy :"+e.getMessage());
		} catch (ExceptionTooBig e) {
			fail("too big: "+e.getMessage());
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: "+e.getMessage());
		}
		// This should just break the volume
		Bag bag2 = new Bag();
		bag2.setWeightMax(weightMax);
		bag2.setVolumeMax(volumeMax);
		Cookie i2 = new Cookie();
		i2.setVolumeBase(volumeMax+0.01F);
		i2.setWeightBase(weightMax);
		try {
			bag2.add(i2);
			fail("should fail due to volume");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy: "+e.getMessage());
		} catch (ExceptionTooBig e) {
			// nothing to do
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: "+e.getMessage());
		}
		// This should just break the weight
		Bag bag3 = new Bag();
		bag3.setWeightMax(weightMax);
		bag3.setVolumeMax(volumeMax);
		Cookie i3 = new Cookie();
		i3.setVolumeBase(volumeMax);
		i3.setWeightBase(weightMax+0.01F);
		try {
			bag3.add(i3);
			fail("should fail due to size");
		} catch (ExceptionTooHeavy e) {
			// nothing to do
		} catch (ExceptionTooBig e) {
			fail("too big: "+e.getMessage());
		} catch (ExceptionInvalidType e) {
			fail("invlaid type: "+e.getMessage());
		}

	}

}
