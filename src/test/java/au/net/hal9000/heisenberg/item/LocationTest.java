package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.IItem;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.*;

public class LocationTest {

	@Test
	public void testSetVolumeMax() {
		float volumeMax = 20F;
		Location location = new Location("World");
		location.setVolumeMax(volumeMax);
		float v = location.getVolumeMax();
		assertEquals("location.getVolumeMax=", volumeMax, v, 0.0001F);
	}

	@Test
	public void testAdd() {
		float volumeMax = 10F;
		float weightMax = 20F;
		{
			// Location
			Location location = new Location("World");
			location.setWeightMax(weightMax);
			location.setVolumeMax(volumeMax);
			// IItem
			Cookie i = new Cookie();
			// This should just fit
			i.setVolumeBase(volumeMax);
			i.setWeightBase(weightMax);
			try {
				location.add(i);
			} catch (ExceptionTooHeavy e) {
				fail("too heavy :" + e.getMessage());
			} catch (ExceptionTooBig e) {
				fail("too big: " + e.getMessage());
			} catch (ExceptionInvalidType e) {
				fail("invlaid type: " + e.getMessage());
			}
		}
		{
			// This should just break the volume
			Location location = new Location("World");
			location.setWeightMax(weightMax);
			location.setVolumeMax(volumeMax);
			Cookie i2 = new Cookie();
			i2.setVolumeBase(volumeMax + 0.01F);
			i2.setWeightBase(weightMax);
			try {
				location.add(i2);
				fail("should fail due to volume");
			} catch (ExceptionTooHeavy e) {
				fail("too heavy: " + e.getMessage());
			} catch (ExceptionTooBig e) {
				// nothing to do
			} catch (ExceptionInvalidType e) {
				fail("invlaid type: " + e.getMessage());
			}
		}
		{
			// This should just break the weight
			Location location = new Location("World");
			location.setWeightMax(weightMax);
			location.setVolumeMax(volumeMax);
			Cookie i3 = new Cookie();
			i3.setVolumeBase(volumeMax);
			i3.setWeightBase(weightMax + 0.01F);
			try {
				location.add(i3);
				fail("should fail due to size");
			} catch (ExceptionTooHeavy e) {
				// nothing to do
			} catch (ExceptionTooBig e) {
				fail("too big: " + e.getMessage());
			} catch (ExceptionInvalidType e) {
				fail("invlaid type: " + e.getMessage());
			}
		}
	}

	@Test
	public void testAddMulti() {
		Location location = new Location("World");
		Location newLocation = new Location("New Location");
		Cookie c1 = new Cookie();
		Cookie c2 = new Cookie();
		Cookie c3 = new Cookie();
		Vector<IItem> items = new Vector<IItem>();
		items.add(c1);
		items.add(c2);
		items.add(c3);
		location.add(items);
		assertEquals("add multi size", 3, location.getContentsCount());
		location.empty(newLocation);
		assertEquals("location size after empty", 0,
				location.getContentsCount());
		assertEquals("newLocation size", 3, newLocation.getContentsCount());
	}

	@Test
	public void testBeNot() {
		Location location = new Location("World");
		Cookie c1 = new Cookie();
		Cookie c2 = new Cookie();
		Cookie c3 = new Cookie();
		location.add(c1);
		location.add(c2);
		location.add(c3);
		assertEquals("add multi size", 3, location.getContentsCount());
		location.beNot();
		assertEquals("empty size", 0, location.getContentsCount());
	}

	@Test
	public void testPersistenceShallow() {

		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"empty_location_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Location old = new Location("World");
		// Store the object
		try {
			old.freezeToFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back
		Location newObj = null;
		try {
			newObj = Location.thawFromFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}
		assertTrue("newObj not null", newObj != null);
		assertTrue("deserialized Location equals old cookie",
				old.equals(newObj));

	}

	@Test
	public void testPersistenceDeep() {
		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"location_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Location old = new Location("World");
		old.add(new Cookie());
		// Store the object
		try {
			old.freezeToFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back
		Location newObj = null;
		try {
			newObj = Location.thawFromFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}
		assertTrue("newObj not null", newObj != null);
		assertTrue("deserialized Location equals old cookie",
				old.equals(newObj));

	}
}
