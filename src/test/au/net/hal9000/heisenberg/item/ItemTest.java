package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.units.*;

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
		float volumeBase = 20F;
		Cookie cookie = new Cookie();
		cookie.setVolumeBase(volumeBase);
		float v = cookie.getVolumeBase();
		assertEquals("getVolumeBase=", volumeBase, v, 0.0001F);
	}

	@Test
	public void testEquals() {
		Cookie first = new Cookie();
		Cookie second = new Cookie();
		assertTrue("equals true for self", first.equals(second));
		assertTrue("equals true for other", second.equals(first));
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
		Cookie original = new Cookie();
		Cookie clone = null;
		try {
			clone = (Cookie) original.clone();
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertNotSame("x.clone() != x", clone, original);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertSame("x.clone().getClass() == x.getClass()", clone.getClass(),
				original.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		assertEquals("x.clone().equals(x)", clone, original);

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

		// weightBase
		float weightBase = original.getWeightBase();
		weightBase += 1f;
		original.setWeightBase(weightBase);
		assertFalse("x.clone().equals(x) weightBase", clone.equals(original));
		weightBase -= 1f;
		original.setWeightBase(weightBase);
		assertTrue("x.clone().equals(x) weightBase restored",
				clone.equals(original));

		// volumeBase
		float volumeBase = original.getVolumeBase();
		volumeBase += 1f;
		original.setVolumeBase(volumeBase);
		assertFalse("x.clone().equals(x) volumeBase", clone.equals(original));
		volumeBase -= 1f;
		original.setVolumeBase(volumeBase);
		assertTrue("x.clone().equals(x) volumeBase restored",
				clone.equals(original));

		// valueBase
		Currency valueBase = original.getValueBase();
		if (valueBase != null) {
			int gp = valueBase.getGp();
			gp++;
			valueBase.setGp(gp);
			assertFalse("x.clone().equals(x) valueBase", clone.equals(original));
			valueBase.setGp(gp - 1);
			assertTrue("x.clone().equals(x) valueBase restored", clone.equals(original));
		}

		// location is *NOT* cloned.

	}

	// TODO re-factor and combine with above.
	@Test
	public void testCloneItem() {
		Cookie origianl = new Cookie();
		Cookie clone = null;
		clone = (Cookie) origianl.clone(new Cookie());

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("x.clone() != x", clone != origianl);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("x.clone().getClass() == x.getClass()",
				clone.getClass() == origianl.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		assertTrue("x.clone().equals(x)", clone.equals(origianl));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

		// weightBase
		float weightBase = origianl.getWeightBase();
		weightBase += 1f;
		origianl.setWeightBase(weightBase);
		assertFalse("x.clone().equals(x)", clone.equals(origianl));
		weightBase -= 1f;
		origianl.setWeightBase(weightBase);
		assertTrue("x.clone().equals(x) restored", clone.equals(origianl));

		// volumeBase
		float volumeBase = origianl.getVolumeBase();
		volumeBase += 1f;
		origianl.setVolumeBase(volumeBase);
		assertFalse("x.clone().equals(x)", clone.equals(origianl));
		volumeBase -= 1f;
		origianl.setVolumeBase(volumeBase);
		assertTrue("x.clone().equals(x) restored", clone.equals(origianl));

		// valueBase
		Currency valueBase = origianl.getValueBase();
		if (valueBase != null) {
			int gp = valueBase.getGp();
			gp++;
			valueBase.setGp(gp);
			assertFalse("x.clone().equals(x)", clone.equals(origianl));
			valueBase.setGp(gp - 1);
			assertTrue("x.clone().equals(x) restored", clone.equals(origianl));
		}

		// location is *NOT* cloned.

	}

}
