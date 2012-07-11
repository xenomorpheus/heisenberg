package au.net.hal9000.dnd.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.exception.*;

public class Backlog {

    // Scenario 01
	@Test
	public void character_mounts_a_horse() {
		System.out.println("\n** A character mounts a horse.");
		// Hobbit hobbit = new Hobbit();
		// Horse horse = new Horse();
		try {
			// TODO hobbit.setMount(horse);
			System.out.println("Hobbit mounted the Horse");
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionCantWear e) {
			fail("cant wear");
		}
	}

    // Scenario 01
	@Test
	public void character_puts_on_cloak() {
		System.out.println("\n** A character puts on a cloak.");
		Human human = new Human();
		System.out.println("Human created with name: " + human);
		Cloak cloak = new Cloak();
		System.out.println("Cloak created with name: " + cloak);
		try {
			// TODO human.wear(cloak);
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
	
	
}
