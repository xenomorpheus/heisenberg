package au.net.hal9000.heisenberg.worldeditor;

//Imports
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class SkillsTableTest {

    /**
     * Method doTest.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();

        SkillsTable skillsTable = new SkillsTable();
        assertNotNull("not Null", skillsTable);
        skillsTable.setItem(pc);

    }

}
