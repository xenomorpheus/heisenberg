package au.net.hal9000.dnd.scenario;

import static org.junit.Assert.*;

import org.junit.Test;
import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.exception.*;

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
	public void character_puts_on_cloak() {
		System.out.println("\n** A character puts on a cloak.");
		Human human = new Human();
		System.out.println("Human created with name: " + human);
		Cloak cloak = new Cloak();
		System.out.println("Cloak created with name: " + cloak);
		try {
			human.wear(cloak);
			System.out.println("Human wearing cloak.");
		} catch (ExceptionCantWear e) {
			fail("Cant wear");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		System.out.println("Cloak location: " + cloak.getLocation());
	}

	@Test
	public void crossbow_is_loaded() {
		System.out.println("\n** A crossbow is loaded.");
		Crossbow crossbow = new Crossbow();
		System.out.println("Crossbow created with name: " + crossbow);
		CrossbowBolt bolt = new CrossbowBolt();
		System.out.println("CrossbowBolt created with name: " + bolt);
		Location ground = new Location("Ground");
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
		Human humanoid = new Human();
		System.out.println("Equip the shield.");
		try {
			humanoid.setShield(shield);
		} catch (Exception e) {
			fail("Humanoid could not wear shield");
		}
		System.out
				.println("Shield location: " + shield.getLocation());
	}

	// TODO
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
			System.out.println("TODO - add more arrows till filled");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		System.out.println("Arrow location: " + arrow.getLocation());
	}

	// TODO
	@Test
	public void candle_runs_out() {
		System.out.println("\n** A candle runs out.");
		Candle candle = new Candle();
		System.out.println("Candle description:\n" + candle.getDescription());
		System.out.println("TODO");
		// candle.run_out();
		System.out.println("Torch description:\n" + candle.getDescription());
	}

	@Test
	public void character_mounts_a_horse() {
		System.out.println("\n** A character mounts a horse.");
		Hobbit hobbit = new Hobbit();
		Horse horse = new Horse();
		try {
			hobbit.setMount(horse);
			System.out.println("Hobbit mounted the Horse");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionCantWear e) {
			fail("cant wear");
		}
	}

	// TODO
	@Test
	public void hobbit_eats_a_cookie() {
		System.out.println("\n** A hobbit eats a cookie.");
		Hobbit hobbit = new Hobbit();
		Cookie cookie = new Cookie();
		try {
			hobbit.eat(cookie);
			System.out.println("Hobbit ate a cookie");
		} catch (ExceptionInvalidType e) {
			fail("Failed to eat Cookie");
		}
	}

}
