package au.net.hal9000.dnd.units;

import static org.junit.Assert.*;

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
	public void persistence() {

		String filename = "/tmp/weight_persit_test.ser"; // TODO unique filename
		Weight old = new Weight();
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
}
