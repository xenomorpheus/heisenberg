package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import au.net.hal9000.player.units.*;

public class ItemImplTest {

	@Test
	public void testItem() {
		Cookie i = new Cookie();
		assertEquals("Item() name", "Cookie", i.getName());
		assertEquals("Item() description", "", i.getDescription());
		assertTrue("Item() weightBase", i.getWeightBase().equals(0F));
		assertTrue("Item() weightMax", i.getWeightMax().equals(0F));
		assertTrue("Item() volumeBase", i.getVolumeBase().equals(0F));
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testItemWithName() {
		Cookie i = new Cookie("The Name");
		assertEquals("Item() name", "The Name", i.getName());
	}

	@Test
	public void testItemWithNameAndDescription() {
		Cookie i = new Cookie("The Name", "The Description");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "The Description",
				i.getDescription());
	}

	@Test
	public void testName() {
		Cookie i = new Cookie("A Name");
		assertEquals("name", "A Name", i.getName());
		
		i.setName("fred");
		assertEquals("setname & getname", "fred", i.getName());
	}

	@Test
	public void testDescription() {
		Cookie i = new Cookie();
		i.setDescription("A Description");
		assertEquals("description", "A Description", i.getDescription());
	}

	@Test
	public void testBaseWeight() {
		Cookie i = new Cookie();
		i.setWeightBase(0.123F);
		assertTrue("weightBase", i.getWeightBase().equals(0.123F));
	}

	@Test
	public void testWeight() {
		Cookie i = new Cookie();
		i.setWeightBase(0.123F);
		assertTrue("weight", i.getWeight().equals(0.123F));
	}

	@Test
	public void testValueBase() {
		Cookie i = new Cookie();
		i.setValueBase(new Currency(1, 2, 4, 8));
		assertTrue("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(2, 2, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 3, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 5, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 4, 9)));
	}

	@Test
	public void testLocation() {
		Cookie cookie = new Cookie();
		Human human = new Human("human");
		cookie.setLocation(human);
		assertEquals("location", human, cookie.getLocation());
	}

	public void testToString() {
		Cookie cookie = new Cookie();
		assertEquals("toString", "some text", cookie.toString());

	}

	@Test
	public void testSetVolumeMax() {
		float volumeMax = 20F;
		Cookie cookie = new Cookie();
		cookie.setVolumeMax(volumeMax);
		Volume v = cookie.getVolumeMax();
		if (v == null) {
			fail("getVolumeMax= null");
		} else {
			assertEquals("getVolumeMax=", volumeMax, v.getValue(), 0.0001F);
		}
	}

	@Test
	public void testEquals() {
		Cookie cookie = new Cookie();
		Cookie cookie2 = new Cookie();
		assertTrue("equals true for self", cookie.equals(cookie));
		assertTrue("equals true for other", cookie2.equals(cookie));
	}

	@Test
	public void testPersistence() {

		String filename = "/tmp/cookie_persit_test.ser"; // TODO unique filename
		Cookie old = new Cookie();
		// Store the object
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(old);
			out.close();
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			Cookie newObj = (Cookie) in.readObject();
			in.close();
			assertTrue("deserialized Cookie equals old cookie",
					old.equals(newObj));
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}

	}

	@Test
	public void testClone() {
		Cookie x = new Cookie();
		Cookie clone = null;
		try {
			clone = (Cookie) x.clone();
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("x.clone() != x", clone != x);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("x.clone().getClass() == x.getClass()",
				clone.getClass() == x.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		assertTrue("x.clone().equals(x)", clone.equals(x));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

		// weightBase
		Weight weightBase = x.getWeightBase();
		if (weightBase != null) {
			weightBase.add(1f);
			assertFalse("x.clone().equals(x)", clone.equals(x));
			weightBase.subtract(1f);
		}

		// weightMax
		Weight weightMax = x.getWeightMax();
		if (weightMax != null) {
			weightMax.add(1f);
			assertFalse("x.clone().equals(x)", clone.equals(x));
			weightMax.subtract(1f);
		}

		// volumeBase
		Volume volumeBase = x.getVolumeBase();
		if (volumeBase != null) {
			volumeBase.add(1f);
			assertFalse("x.clone().equals(x)", clone.equals(x));
			volumeBase.subtract(1f);
		}

		// volumeMax
		Volume volumeMax = x.getVolumeMax();
		if (volumeMax != null) {
			volumeMax.add(1f);
			assertFalse("x.clone().equals(x)", clone.equals(x));
			volumeMax.subtract(1f);
		}

		// valueBase
		Currency valueBase = x.getValueBase();
		if (valueBase != null) {
			int gp = valueBase.getGp();
			gp++;
			valueBase.setGp(gp);
			assertFalse("x.clone().equals(x)", clone.equals(x));
			valueBase.setGp(gp - 1);
		}

		// location is *NOT* cloned.

	}
}
