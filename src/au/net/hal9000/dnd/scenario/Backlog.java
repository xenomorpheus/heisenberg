package au.net.hal9000.dnd.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.dnd.item.*;
import au.net.hal9000.dnd.item.exception.*;

public class Backlog {


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

}
