package au.net.hal9000.player.units;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;


public class UnitTest {
	private static final float WITHING_MARGIN = 0.00009F;

	@Test
	public void testEquals() {
		Unit ref = new Unit(1.0F);
		Unit eq = new Unit(1.0F);
		Unit within = new Unit(1.0F + WITHING_MARGIN);
		Unit over = new Unit(1.0F + (2 * WITHING_MARGIN));

		assertTrue("equals 0 to 0", ref.equals(eq));
		assertTrue("equals 0 to -1", ref.equals(within));
		assertFalse("equals 0 to +1", ref.equals(over));
	}

	@Test
	public void testCompare() {
		Unit zero1 = new Unit(0.0F);
		Unit zero2 = new Unit(0.0F);
		Unit pos1 = new Unit(1.0F);
		Unit neg1 = new Unit(-1.0F);

		assertEquals("compare 0 to 0", 0, zero1.compare(zero2));
		assertEquals("compare 0 to -1", 1, zero1.compare(neg1));
		assertEquals("compare 0 to +1", -1, zero1.compare(pos1));

		assertEquals("compare 0 to 0F", 0, zero1.compare(0F));
		assertEquals("compare 0 to -1F", 1, zero1.compare(-1F));
		assertEquals("compare 0 to +1F", -1, zero1.compare(1F));
	}

	@Test
	public void testAdd() {
		Unit u = new Unit(0.0F);
		assertEquals("add - set 0", 0, u.compare(0F));
		u.add(1.0F);
		assertEquals("add - compare to 1", 0, u.compare(1.0F));
	}

	@Test
	public void testSubtract() {
		Unit u = new Unit(0.0F);
		assertEquals("subtract - set 0", 0, u.compare(0F));
		u.subtract(1.0F);
		assertEquals("subtract - compare to 1", 0, u.compare(-1.0F));
	}	
	
	@Test
	public void testPersistence() {

		String filename = "/tmp/unit_persit_test.ser"; // TODO unique volatile filename
		Unit old = new Unit(1.23f);
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
			Unit newObj = (Unit) in.readObject();
			in.close();
			assertTrue("deserialized object equals old object", old.equals(newObj));
		} catch (IOException ex) {
			fail(ex.toString());
		} catch (ClassNotFoundException ex) {
			fail(ex.toString());
		}

	}
	
	@Test
	public void testClone() {
		Unit x = new Unit();
		Unit clone = null;
		try {
			clone = (Unit) x.clone();
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
	}
	
}