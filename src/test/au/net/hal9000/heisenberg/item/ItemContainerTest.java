package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.exception.*;

public class ItemContainerTest {

	// TODO Add unit test for respecting max weight and volumen of outer bag,
	// when adding Item to an inner bag.
	// Will require some kind of change notification system.

	@Test
	public void testSetVolumeMax() {
		float volumeMax = 20F;
		Bag bag = new Bag();
		bag.setVolumeMax(volumeMax);
		float v = bag.getVolumeMax();
		assertEquals("bag.getVolumeMax=", volumeMax, v, 0.0001F);
	}

	@Test
	public void testAdd() {
		float volumeMax = 10F;
		float weightMax = 20F;
		{
			// Bag
			Bag bag = new Bag();
			bag.setWeightMax(weightMax);
			bag.setVolumeMax(volumeMax);
			// IItem
			Cookie i = new Cookie();
			// This should just fit
			i.setVolumeBase(volumeMax);
			i.setWeightBase(weightMax);
			try {
				bag.add(i);
			} catch (Exception e) {
				fail(e.getMessage());
			}
			// Check location is set
			assertEquals("location set", bag, i.getLocation());
		}
		{
			// This should just break the volume
			Bag bag = new Bag();
			bag.setWeightMax(weightMax);
			bag.setVolumeMax(volumeMax);
			Cookie i2 = new Cookie();
			i2.setVolumeBase(volumeMax + 0.01F);
			i2.setWeightBase(weightMax);
			try {
				bag.add(i2);
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
			Bag bag = new Bag();
			bag.setWeightMax(weightMax);
			bag.setVolumeMax(volumeMax);
			Cookie i3 = new Cookie();
			i3.setVolumeBase(volumeMax);
			i3.setWeightBase(weightMax + 0.01F);
			try {
				bag.add(i3);
				fail("should fail due to size");
			} catch (ExceptionTooHeavy e) {
				// nothing to do
			} catch (ExceptionTooBig e) {
				fail("too big: " + e.getMessage());
			} catch (ExceptionInvalidType e) {
				fail("invlaid type: " + e.getMessage());
			}
		}

		{
		    // add function should be smart enough to cause the removal
		    // from the losing container.
            Bag bag1 = new Bag();
            Bag bag2 = new Bag();
            Cookie cookie = new Cookie();
            assertEquals("bag1 count setup ",0, bag1.getChildCount());            	    
            assertEquals("bag2 count setup ",0, bag2.getChildCount());                  
            // add cookie to one bag
            bag1.add(cookie);
            assertEquals("bag1 count setup ",1, bag1.getChildCount());                  
            // transfer cookie to other bag
            bag2.add(cookie);
            assertEquals("bag1 count setup ",0, bag1.getChildCount());                  
            assertEquals("bag2 count setup ",1, bag2.getChildCount());                  		    
		}
	
	}

	@Test
	public void testAddMulti() {
		Bag bag = new Bag();
		Cookie c1 = new Cookie();
		Cookie c2 = new Cookie();
		Cookie c3 = new Cookie();
		Bag newBag = new Bag("New Bag");
		Vector<IItem> items = new Vector<IItem>();
		items.add(c1);
		items.add(c2);
		items.add(c3);
		bag.add(items);
		assertEquals("add multi size", 3, bag.getContentsCount());
		bag.empty(newBag);
		assertEquals("bag empty size", 0, bag.getContentsCount());
		assertEquals("New Bag size", 3, newBag.getContentsCount());
	}

	@Test
	public void testBeNot() {
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

	@Test
	public void testClone() {
		Bag original = new Bag();
		Cookie c1 = new Cookie();
		original.add(c1);
		Bag clone = null;
		try {
			clone = (Bag) original.clone();
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("x.clone() != x", clone != original);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("x.clone().getClass() == x.getClass()",
				clone.getClass() == original.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its super-classes (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.

		// Clones have differen IDs
		// Boolean bar = clone.equals(original);
		// assertTrue("x.clone().equals(x)", bar);

        // Clones have differen IDs
		// equals from the other direction
		// Boolean foo = original.equals(clone);
		// assertTrue("x.equals(clone)", foo);

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

		// weightMax
		float weightMax = original.getWeightMax();
		weightMax += 1f;
		original.setWeightMax(weightMax);
		// TODO assertFalse("x.clone().equals(x) weightMax", clone.equals(original));
		weightMax -= 1f;
		original.setWeightMax(weightMax);
		// TODO assertTrue("x.clone().equals(x) weightMax restored ",		clone.equals(original));

		// volumeMax
		float volumeMax = original.getVolumeMax();
		volumeMax += 1f;
		original.setVolumeMax(volumeMax);
		// TODO assertFalse("x.clone().equals(x) volumeMax", clone.equals(original));
		volumeMax -= 1f;
		original.setVolumeMax(volumeMax);
		// TODO assertTrue("x.clone().equals(x) volumeMaxrestored ",clone.equals(original));

		// items
		{
			// Make a change to a non-primative, non-mutable
			//

			String c1NameOld = c1.getName();
			// TODO test the name is the same
			c1.setName(c1.getName() + "fred");
			// TODO test the name is different assertFalse("deep clone", clone.equals(original));
			c1.setName(c1NameOld);
		}

	}

	@Test
	public void testPersistenceShallow() {

		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"empty_bag_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Bag old = new Bag();
		// Store the object
		try {
			old.freezeToFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back
		Bag newObj = null;
		try {
			newObj = Bag.thawFromFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}
		assertTrue("newObj not null", newObj != null);
		assertTrue("deserialized Bag equals old cookie", old.equals(newObj));

	}

	@Test
	public void testPersistenceDeep() {
		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"bag_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Bag old = new Bag("World");
		old.add(new Cookie());
		// Store the object
		try {
			old.freezeToFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back
		Bag newObj = null;
		try {
			newObj = Bag.thawFromFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}
		assertTrue("newObj not null", newObj != null);
		assertTrue("deserialized equals old", old.equals(newObj));

	}

	@Test
	public void isLeaf() {
		Bag bag = new Bag();
		assertFalse("isLeaf", bag.isLeaf());
	}

	@Test
	public void testGetChildCount() {
		Bag bag = new Bag();
		assertEquals("getChildCount", 0, bag.getChildCount());
		bag.add(new Cookie());
		assertEquals("getChildCount", 1, bag.getChildCount());
	}

	@Test
	public void testGetChild() {
		Bag bag = new Bag();
		Cookie cookie = new Cookie();
		Scabbard scabbard = new Scabbard();
		bag.add(cookie);
		assertEquals("getChildCount", cookie, bag.getChild(0));
		bag.add(scabbard);
		assertEquals("getChildCount", cookie, bag.getChild(0));
		assertEquals("getChildCount", scabbard, bag.getChild(1));
	}

	@Test
	public void testGetIndexOfChild() {
		Bag bag = new Bag();
		Cookie cookie1 = new Cookie();
		Scabbard scabbard = new Scabbard();
		Cookie cookie2 = new Cookie("Cookie2");
		Cookie cookie3 = new Cookie();
		assertEquals("getIndexOfChild - empty", -1,
				bag.getIndexOfChild(cookie1));
		bag.add(cookie1);
		assertEquals("getIndexOfChild - only child", 0,
				bag.getIndexOfChild(cookie1));
		bag.add(scabbard);
		assertEquals("getIndexOfChild - first child", 0,
				bag.getIndexOfChild(cookie1));
		assertEquals("getIndexOfChild - second child", 1,
				bag.getIndexOfChild(scabbard));
		assertEquals("getIndexOfChild - not present", -1,
				bag.getIndexOfChild(cookie2));
		assertEquals(
				"getIndexOfChild - not present but cookie3 equal to cookie1",
				-1, bag.getIndexOfChild(cookie3));
	}

}
