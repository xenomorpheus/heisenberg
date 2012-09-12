package test.au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.item.exception.*;

public class Backlog {

	private void println(String string){
		// System.out.println(string);
	}
	
    // Scenario 01
	@Test
	public void character_mounts_a_horse() {
		println("\n** A character mounts a horse.");
		// Hobbit hobbit = new Hobbit();
		// Horse horse = new Horse();
		try {
			// TODO hobbit.setMount(horse);
			println("Hobbit mounted the Horse");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionCantWear e) {
			fail("cant wear");
		}
	}

    // Scenario 01
	@Test
	public void character_puts_on_cloak() {
		println("\n** A character puts on a cloak.");
		Human human = new Human();
		println("Human created with name: " + human);
		Cloak cloak = new Cloak();
		println("Cloak created with name: " + cloak);
		try {
			// TODO human.wear(cloak);
			println("Human wearing cloak.");
		} catch (ExceptionCantWear e) {
			fail("Cant wear");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		println("Cloak location: " + cloak.getLocation());
	}
	
	
}
