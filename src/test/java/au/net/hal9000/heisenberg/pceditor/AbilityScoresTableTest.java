package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.pceditor.AbilityScoresTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class AbilityScoresTableTest {

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {
        PcRace pc = DummyData.getPcRace();
        AbilityScoresTable basicPanel = new AbilityScoresTable();
        basicPanel.setPcRace(pc);
        assertNotNull("BasicPanel not null", basicPanel);
    }

    /**
     * Method testGetRowCount.
     */
    @Test
    public void testGetRowCount() {

        AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
        assertEquals(0, abilityScoresTable.getRowCount());
    }

}
