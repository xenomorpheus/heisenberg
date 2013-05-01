package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class DummyDataTest {

    @Test
    public void testElf() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        assertNotNull(pc);
    }

    @Test
    public void testGetDemoWorld() {
        Location loc = DummyData.getDemoWorld();
        assertNotNull(loc);
    }
    
}
