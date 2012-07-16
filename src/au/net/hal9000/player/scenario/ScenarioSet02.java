package au.net.hal9000.player.scenario;

import org.junit.Test;

import au.net.hal9000.player.item.*;

public class ScenarioSet02 {

	@Test
	public void forgeAddTwoCookies() {
		System.out.println("\n** Worldforge add two cookies.");
		Cookie cookie = new Cookie();
		Cookie cookie2 = new Cookie();
		Location world = new Location("world");
		world.add(cookie);
		world.add(cookie2);
	}

}
