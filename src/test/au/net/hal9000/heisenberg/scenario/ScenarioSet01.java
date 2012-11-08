package test.au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.exception.*;

public class ScenarioSet01 {

	private void println(String string){
		// System.out.println(string);
	}
	
	@Test
	public void sword_into_scabbard() {
		println("\n** A sword is placed in a scabbard.");
		Sword sword = new Sword();
		println("Sword created with name: " + sword);
		Scabbard scabbard = new Scabbard();
		println("Scabbard created with name: " + scabbard);
		try {
			scabbard.add(sword);
			println("Sword now in scabbard.");
		} catch (ExceptionCantWear e) {
			fail("Could not sheath the sword.");
		}
		println("Sword location: " + sword.getLocation());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void full_backpack_into_box() {
		println("\n** A full backpack is placed in a box.");
		Backpack backpack = new Backpack();
		println("Backpack created with name: " + backpack);
		Box box = new Box();
		println("Box created with name: " + box);
		// TODO fill it
		try {
			box.add(backpack);
			println("Full backpack placed into box.");
			println("Backpack location: "
					+ backpack.getLocation());
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
	}

	@Test
	public void crossbow_is_loaded() {
		println("\n** A crossbow is loaded.");
		Crossbow crossbow = new Crossbow();
		println("Crossbow created with name: " + crossbow);
		CrossbowBolt bolt = new CrossbowBolt();
		println("CrossbowBolt created with name: " + bolt);
		Cookie ground = new Cookie("Ground");
		bolt.setLocation(ground);
		crossbow.setLoadedBolt(bolt);
		CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("bow's bolt",bolt, got);
        assertEquals("bolt's location", ground, got.getLocation());
		println("Crossbow has been loaded with the bolt");
	}
	
	@Test
	public void torch_is_lit() {
		println("\n** A torch is lit.");
		Torch torch = new Torch();
		println("Torch description:\n" + torch.getDescription());
		println("Light the torch.");
		torch.light();
		println("Torch description:\n" + torch.getDescription());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void shieldAdd() {
		println("\n** A Shield is equipped.");
		Shield shield = new Shield();
		Human human = new Human();
		println("Equip the shield.");
		try {
			human.add(shield);
		} catch (Exception e) {
			fail("Humanoid could not wear shield");
		}
		println("Shield location: " + shield.getLocation());
	}

	@Test
	public void quiver_is_filled() {
		println("\n** A quiver is filled.");
		Quiver quiver = new Quiver();
		println("Quiver created.");
		println("TODO - set quiver size");
		Arrow arrow = new Arrow();
		println("Arrow created.");
		try {
			quiver.add(arrow);
			quiver.add(arrow);
			quiver.add(arrow);
			quiver.add(arrow);
			quiver.add(arrow);
			quiver.add(arrow);
		} catch (Exception e) {
			fail(e.toString());
		}
		println("Arrow location: " + arrow.getLocation());
	}

	// TODO candle runs out
	@Test
	public void candleRunsOut() {
		println("\n** A candle runs out.");
		Candle candle = new Candle();
		println("Candle description:\n" + candle.getDescription());
		// TODO candle.run_out();
		println("Torch description:\n" + candle.getDescription());
	}


	@Test
	public void hobbitEatsACookie() {
		println("\n** A hobbit eats a cookie.");
		Halfling hobbit = new Halfling();
		Cookie cookie = new Cookie();
		try {
			hobbit.eat(cookie);
			println("Hobbit ate a cookie");
		} catch (Exception e) {
			fail("Failed to eat Cookie because "+e.toString());
		}
	}

}
