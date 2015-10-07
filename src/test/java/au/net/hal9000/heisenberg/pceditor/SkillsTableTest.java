package au.net.hal9000.heisenberg.pceditor;

//Imports
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.pceditor.SkillsTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class SkillsTableTest {

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {

        PcRace pc = DemoEnvironment.getPcRace();

        SkillsTable skillsTable = new SkillsTable();
        assertNotNull("not Null", skillsTable);
        skillsTable.setPcRace(pc);

    }

}
