package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class AbilityScoresTableTest {

    /**
     * Method testAbilityScoresTable.
     * @throws ConfigurationError
     */
    @Test
    public void testAbilityScoresTable() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        AbilityScoresTable basicPanel = new AbilityScoresTable();
        basicPanel.setItem(pc);
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
