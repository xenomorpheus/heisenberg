package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class DummyDataTest {

    /**
     * Method testElf.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testElf() throws ConfigurationError {
        PcRace pc = DemoEnvironment.getPcRace();
        assertNotNull(pc);
    }

    /**
     * Method testGetDemoWorld.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetDemoWorld() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Location loc = DemoEnvironment.getDemoWorld();
        assertNotNull(loc);
    }

}
