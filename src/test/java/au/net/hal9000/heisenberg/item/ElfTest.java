package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Elf;
import au.net.hal9000.heisenberg.item.Humanoid;
import au.net.hal9000.heisenberg.item.Item;

public class ElfTest {

	@Test
	public void testInstanceof() {

		Elf elf = new Elf();
		assertTrue("is Elf", elf instanceof Elf);
		assertTrue("is Humanoid", elf instanceof Humanoid);
		assertTrue("is Entity", elf instanceof Entity);
		assertTrue("is item", elf instanceof Item);
	}

	@Test
	public void testGetRace() {
		Elf elf = new Elf();
		assertEquals("Elf", elf.getName());
	}
}
