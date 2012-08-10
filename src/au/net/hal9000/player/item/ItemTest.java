package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import au.net.hal9000.player.units.*;

public class ItemTest {
	private static final float WITHIN_MARGIN = 0.00009F;

	@Test
	public void testItem() {
		Cookie i = new Cookie();
		assertEquals("IItem() name", "Cookie", i.getName());
		assertEquals("IItem() description", "", i.getDescription());
		// assertTrue("IItem() weightBase", i.getWeightBase().equals(0F));
		// assertTrue("IItem() weightMax", i.getWeightMax().equals(0F));
		// assertTrue("IItem() volumeBase", i.getVolumeBase().equals(0F));
		assertEquals("IItem() location", null, i.getLocation());
	}

	@Test
	public void testItemWithName() {
		Cookie i = new Cookie("The Name");
		assertEquals("IItem() name", "The Name", i.getName());
	}

	@Test
	public void testItemWithNameAndDescription() {
		Cookie i = new Cookie("The Name", "The Description");
		assertEquals("IItem() name", "The Name", i.getName());
		assertEquals("IItem() description", "The Description",
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
		assertEquals("weightBase", i.getWeightBase(), 0.123F, WITHIN_MARGIN);
	}

	@Test
	public void testWeight() {
		Cookie i = new Cookie();
		i.setWeightBase(0.123F);
		assertEquals("weight", i.getWeight(), 0.123F, WITHIN_MARGIN);
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
		float v = cookie.getVolumeMax();
		assertEquals("getVolumeMax=", volumeMax, v, 0.0001F);
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
		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"cookie_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Cookie old = new Cookie();
		// Store the object
		try {
			old.freezeToFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		}
		// Get the object back
		Cookie newObj = null;
		try {
			newObj = Cookie.thawFromFile(filename);
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}
		assertTrue("newObj not null", newObj != null);
		assertTrue("deserialized Cookie equals old cookie", old.equals(newObj));

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
		float weightBase = x.getWeightBase();
		weightBase += 1f;
		x.setWeightBase(weightBase);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		weightBase -= 1f;
		x.setWeightBase(weightBase);

		// weightMax
		float weightMax = x.getWeightMax();
		weightMax += 1f;
		x.setWeightMax(weightMax);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		weightMax -= 1f;
		x.setWeightMax(weightMax);

		// volumeBase
		float volumeBase = x.getVolumeBase();
		volumeBase += 1f;
		x.setVolumeBase(volumeBase);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		volumeBase -= 1f;
		x.setVolumeBase(volumeBase);

		// volumeMax
		float volumeMax = x.getVolumeMax();
		volumeMax += 1f;
		x.setVolumeMax(volumeMax);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		volumeMax -= 1f;
		x.setVolumeMax(volumeMax);

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

	// TODO re-factor and combine with above.
	@Test
	public void testCloneItem() {
		Cookie x = new Cookie();
		Cookie clone = null;
		try {
			clone = (Cookie) x.clone(x);
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
		float weightBase = x.getWeightBase();
		weightBase += 1f;
		x.setWeightBase(weightBase);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		weightBase -= 1f;
		x.setWeightBase(weightBase);

		// weightMax
		float weightMax = x.getWeightMax();
		weightMax += 1f;
		x.setWeightMax(weightMax);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		weightMax -= 1f;
		x.setWeightMax(weightMax);

		// volumeBase
		float volumeBase = x.getVolumeBase();
		volumeBase += 1f;
		x.setVolumeBase(volumeBase);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		volumeBase -= 1f;
		x.setVolumeBase(volumeBase);

		// volumeMax
		float volumeMax = x.getVolumeMax();
		volumeMax += 1f;
		x.setVolumeMax(volumeMax);
		assertFalse("x.clone().equals(x)", clone.equals(x));
		volumeMax -= 1f;
		x.setVolumeMax(volumeMax);

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
