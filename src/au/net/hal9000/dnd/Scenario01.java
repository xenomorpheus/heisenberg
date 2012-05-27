package au.net.hal9000.dnd;
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
		} catch (ExceptionTooHeavy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionTooBig e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void character_puts_on_cloak() {
		System.out.println("\n** A character puts on a cloak.");
		Human human = new Human();
		System.out.println("Human created with name: " + human.getName());
		Cloak cloak = new Cloak();
		System.out.println("Cloak created with name: " + cloak.getName());
		// TODO fill it
		try {
			human.wear(cloak);
			System.out.println("Human wearing cloak.");
		} catch (ExceptionCantWear e) {
			System.out.println("Human could not wear cloak");
		}
		System.out.println("Cloak location: " + cloak.getLocation().getName());
	}

	public static void torch_is_lit() {
		System.out.println("\n** A torch is lit.");
		Torch torch = new Torch();
		System.out.println("Torch description: " + torch.getDescription1());
		System.out.println("Light the torch.");
		torch.light();
		System.out.println("Torch description: " + torch.getDescription1());
	}

	public static void main(String arg[]) {

		sword_into_scabbard();
		full_backpack_into_box();
		character_puts_on_cloak();
		// A crossbow is loaded.
		torch_is_lit();

		// A Shield is equipped.
		// A quiver is filled.
		// A candle runs out.
		// A character mounts a horse.
		// A hobbit eats a cookie.
	}
}
