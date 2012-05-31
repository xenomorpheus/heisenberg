package au.net.hal9000.dnd.scenario;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.exception.ExceptionCantWear;
import au.net.hal9000.dnd.item.exception.ExceptionInvalidType;
import au.net.hal9000.dnd.item.exception.ExceptionTooBig;
import au.net.hal9000.dnd.item.exception.ExceptionTooHeavy;

public class Scenario01 {

	public static void sword_into_scabbard() {
		System.out.println("\n** A sword is placed in a scabbard.");
		Sword sword = new Sword();
		System.out.println("Sword created with name: " + sword.getName());
		Scabbard scabbard = new Scabbard();
		System.out.println("Scabbard created with name: " + scabbard.getName());
		try {
			scabbard.add(sword);
			System.out.println("Sword now in scabbard.");
		} catch (ExceptionCantWear e) {
			System.out.println("Could not sheath the sword.");
		}
		System.out.println("Sword location: " + sword.getLocation().getName());
	}

	public static void full_backpack_into_box() {
		System.out.println("\n** A full backpack is placed in a box.");
		Backpack backpack = new Backpack();
		System.out.println("Backpack created with name: " + backpack.getName());
		Box box = new Box();
		System.out.println("Box created with name: " + box.getName());
		// TODO fill it
		try {
			box.add(backpack);
			System.out.println("Full backpack placed into box.");
			System.out.println("Backpack location: "
					+ backpack.getLocation().getName());
		} catch (ExceptionInvalidType e) {
			System.out.println("invalid type");
		} catch (ExceptionTooHeavy e) {
			System.out.println("too heavy");
		} catch (ExceptionTooBig e) {
			System.out.println("too big");
		}
	}

	public static void character_puts_on_cloak() {
		System.out.println("\n** A character puts on a cloak.");
		Human human = new Human();
		System.out.println("Human created with name: " + human.getName());
		Cloak cloak = new Cloak();
		System.out.println("Cloak created with name: " + cloak.getName());
		try {
			human.wear(cloak);
			System.out.println("Human wearing cloak.");
		} catch (ExceptionCantWear e) {
			System.out.println("Cant wear");
		} catch (ExceptionInvalidType e) {
			System.out.println("invalid type");
		} catch (ExceptionTooHeavy e) {
			System.out.println("too heavy");
		} catch (ExceptionTooBig e) {
			System.out.println("too big");
		}
		System.out.println("Cloak location: " + cloak.getLocation().getName());
	}

	public static void crossbow_is_loaded() {
		System.out.println("\n** A crossbow is loaded.");
		System.out.println("TODO");  // TODO
	}

	public static void torch_is_lit() {
		System.out.println("\n** A torch is lit.");
		Torch torch = new Torch();
		System.out.println("Torch description:\n" + torch.getDescription());
		System.out.println("Light the torch.");
		torch.light();
		System.out.println("Torch description:\n" + torch.getDescription());
	}

	public static void shield_equip() {
		System.out.println("\n** A Shield is equipped.");
		Shield shield = new Shield();
		Human humanoid = new Human();
		System.out.println("Equip the shield.");
		try {
			humanoid.setShield(shield);
		} catch (Exception e) {
			System.out.println("Humanoid could not wear shield");
		}
		System.out.println("Shield location: " + shield.getLocation().getName());
	}

	// TODO
	public static void quiver_is_filled() {
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
			System.out.println("invalid type");
		} catch (ExceptionTooHeavy e) {
			System.out.println("too heavy");
		} catch (ExceptionTooBig e) {
			System.out.println("too big");
		}
		System.out.println("Arrow location: "+arrow.getLocation().getName());
	}

    //TODO
	public static void candle_runs_out() {
		System.out.println("\n** A candle runs out.");
		Candle candle = new Candle();
		System.out.println("TODO");
	}

	public static void character_mounts_a_horse() {
		System.out.println("\n** A character mounts a horse.");
		Hobbit hobbit = new Hobbit();
		Horse horse = new Horse();
		try {
			hobbit.setMount(horse);
		} catch (ExceptionInvalidType e) {
			System.out.println("invalid type");
		} catch (ExceptionCantWear e) {
			System.out.println("cant wear");
		}
		System.out.println("Horse location: " + horse.getLocation().getName());		
	}

    //TODO
	public static void hobbit_eats_a_cookie() {
		System.out.println("\n** A hobbit eats a cookie.");
		Hobbit hobbit = new Hobbit();
		Cookie cookie = new Cookie();
		// TODO hobbit.eat(cookie);
		System.out.println("TODO");
	}

	public static void main(String arg[]) {
		sword_into_scabbard();
		full_backpack_into_box();
		character_puts_on_cloak();
		crossbow_is_loaded();
		torch_is_lit();
		shield_equip();
		quiver_is_filled();
		candle_runs_out();
		character_mounts_a_horse();
		hobbit_eats_a_cookie();
	}
}
