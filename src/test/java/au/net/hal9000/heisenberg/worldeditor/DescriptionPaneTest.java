package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class DescriptionPaneTest {

    /**
     * Method doTest.
     * @throws ConfigurationError
     */
    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        DescriptionPane window = new DescriptionPane();
        assertNotNull("not Null", window);
        window.setItem(pc);
    }

}
