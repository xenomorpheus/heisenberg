package au.net.hal9000.dnd.scenario;

import org.junit.Test;
import au.net.hal9000.dnd.item.*;

public class ScenarioSet02 {

	@Test
	public void wfAddTwoCookies() {
		System.out.println("\n** Worldforge add two cookies.");
		Cookie cookie = new Cookie();
		Cookie cookie2 = new Cookie();
		Location world = new Location("world");
		cookie.setLocation(world);
		cookie2.setLocation(world);
	}

}
