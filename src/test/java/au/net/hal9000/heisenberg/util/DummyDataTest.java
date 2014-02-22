package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;

/**
 */
public class DummyDataTest {

    /**
     * Method testElf.
     * @throws ConfigurationError
     */
    @Test
    public void testElf() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        assertNotNull(pc);
    }

    /**
     * Method testGetDemoWorld.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     */
    @Test
    public void testGetDemoWorld() throws InvalidTypeException, CantWearException {
        Location loc = DummyData.getDemoWorld();
        assertNotNull(loc);
    }
    
}
