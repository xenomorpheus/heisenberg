package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.exception.*;

public class ScenarioSet01 {

	@Test
	public void sword_into_scabbard() {
		System.out.println("\n** A sword is placed in a scabbard.");
		Sword sword = new Sword();
		System.out.println("Sword created with name: " + sword);
		Scabbard scabbard = new Scabbard();
		System.out.println("Scabbard created with name: " + scabbard);
		try {
			scabbard.add(sword);
			System.out.println("Sword now in scabbard.");
		} catch (ExceptionCantWear e) {
			fail("Could not sheath the sword.");
		}
		System.out.println("Sword location: " + sword.getLocation());
	}

	@Test
	public void full_backpack_into_box() {
		System.out.println("\n** A full backpack is placed in a box.");
		Backpack backpack = new Backpack();
		System.out.println("Backpack created with name: " + backpack);
		Box box = new Box();
		System.out.println("Box created with name: " + box);
		// TODO fill it
		try {
			box.add(backpack);
			System.out.println("Full backpack placed into box.");
			System.out.println("Backpack location: "
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
		System.out.println("\n** A crossbow is loaded.");
		Crossbow crossbow = new Crossbow();
		System.out.println("Crossbow created with name: " + crossbow);
		CrossbowBolt bolt = new CrossbowBolt();
		System.out.println("CrossbowBolt created with name: " + bolt);
		Cookie ground = new Cookie("Ground");
		bolt.setLocation(ground);
		crossbow.setLoadedBolt(bolt);
		CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("bow's bolt",bolt, got);
        assertEquals("bolt's location", ground, got.getLocation());
		System.out.println("Crossbow has been loaded with the bolt");
	}
	
	@Test
	public void torch_is_lit() {
		System.out.println("\n** A torch is lit.");
		Torch torch = new Torch();
		System.out.println("Torch description:\n" + torch.getDescription());
		System.out.println("Light the torch.");
		torch.light();
		System.out.println("Torch description:\n" + torch.getDescription());
	}

	@Test
	public void shield_equip() {
		System.out.println("\n** A Shield is equipped.");
		Shield shield = new Shield();
		Human human = new Human();
		System.out.println("Equip the shield.");
		try {
			human.equip(shield);
		} catch (Exception e) {
			fail("Humanoid could not wear shield");
		}
		System.out
				.println("Shield location: " + shield.getLocation());
	}

	@Test
	public void quiver_is_filled() {
		System.out.println("\n** A quiver is filled.");
		Quiver quiver = new Quiver();
		System.out.println("Quiver created.");
		System.out.println("TODO - set quiver size");
		Arrow arrow = new Arrow();
		System.out.println("Arrow created.");
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
		System.out.println("Arrow location: " + arrow.getLocation());
	}

	// TODO candle runs out
	@Test
	public void candleRunsOut() {
		System.out.println("\n** A candle runs out.");
		Candle candle = new Candle();
		System.out.println("Candle description:\n" + candle.getDescription());
		// TODO candle.run_out();
		System.out.println("Torch description:\n" + candle.getDescription());
	}


	@Test
	public void hobbitEatsACookie() {
		System.out.println("\n** A hobbit eats a cookie.");
		Hobbit hobbit = new Hobbit();
		Cookie cookie = new Cookie();
		try {
			hobbit.eat(cookie);
			System.out.println("Hobbit ate a cookie");
		} catch (Exception e) {
			fail("Failed to eat Cookie because "+e.toString());
		}
	}

}
