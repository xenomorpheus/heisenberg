package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class AbilityScoresTableTest {

    @Test
    public void testAbilityScoresTable() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        AbilityScoresTable basicPanel = new AbilityScoresTable();
        basicPanel.setItem(pc);
        assertNotNull("BasicPanel not null", basicPanel);
    }

}
