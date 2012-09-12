package test.au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;

public class ScenarioSet02 {

	private void println(String string){
		// System.out.println(string);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void forgeAddTwoCookies() {
		println("\n** Worldforge world with two cookies.");
		Location old = new Location("world");
		Cookie cookie = new Cookie();
		Cookie cookie2 = new Cookie();
		old.add(cookie);
		old.add(cookie2);

		File fileObj = new File(System.getProperty("java.io.tmpdir"),
				"location_persit_test.ser");
		String filename = fileObj.getAbsolutePath();
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
