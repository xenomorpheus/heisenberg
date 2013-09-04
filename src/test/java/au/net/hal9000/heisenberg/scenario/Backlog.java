package au.net.hal9000.heisenberg.scenario;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;

public class Backlog {

	private void println(String string){
		// System.out.println(string);
	}
	
    // Scenario 01
	@Test
	public void character_mounts_a_horse() {
		println("\n** A character mounts a horse.");
		// Halfling hobbit = new Halfling();
		// Horse horse = new Horse();
		// TODO hobbit.setMount(horse);
	}

    // Scenario 01
	@Test
	public void character_puts_on_cloak() {
		println("\n** A character puts on a cloak.");
		Human human = new Human();
		println("Human created with name: " + human);
		Cloak cloak = new Cloak();
		println("Cloak created with name: " + cloak);
        // TODO human.wear(cloak);
		println("Cloak location: " + cloak.getContainer());
	}
	
	
}
