package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Elf;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.entity.Humanoid;

/**
 */
public class ElfTest {

    /**
     * Method testInstanceof.
     */
    @Test
    public void testInstanceof() {

        Object elf = new Elf();
        assertTrue("is Elf", elf instanceof Elf);
        assertTrue("is Humanoid", elf instanceof Humanoid);
        assertTrue("is Entity", elf instanceof Entity);
        assertTrue("is item", elf instanceof Item);
    }

    /**
     * Method testGetRace.
     */
    @Test
    public void testGetRace() {
        Elf elf = new Elf();
        assertEquals("Elf", elf.getName());
    }
}
