package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
