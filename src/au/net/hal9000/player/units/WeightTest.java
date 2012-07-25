package au.net.hal9000.player.units;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class WeightTest {

	private static final float WITHING_MARGIN = 0.00009F;

	@Test
	public void testEquals() {
		Weight ref = new Weight(1.0F);
		Weight eq = new Weight(1.0F);
		Weight within = new Weight(1.0F + WITHING_MARGIN);
		Weight over = new Weight(1.0F + (2 * WITHING_MARGIN));

		assertTrue("equals 0 to 0", ref.equals(eq));
		assertTrue("equals 0 to -1", ref.equals(within));
		assertFalse("equals 0 to +1", ref.equals(over));
	}
	@Test
	public void testPersistence() {
		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"weight_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
		Weight old = new Weight(1.45f);
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
			Weight newObj = (Weight) in.readObject();
			in.close();
			assertTrue("deserialized object equals old object", old.equals(newObj));
			assertTrue("deserialized object equals old object", newObj.equals(old));
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
