package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class BasicPanelTest {

    /**
     * Method doTest.
     * @throws ConfigurationError
     */
    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        BasicPanel basicPanel = new BasicPanel();
        assertNotNull("not Null", basicPanel);
        basicPanel.setItem(pc);
    }
}
