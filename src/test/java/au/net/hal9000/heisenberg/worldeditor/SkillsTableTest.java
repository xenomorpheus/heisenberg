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
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;
    
    /**
     * Method doTest.
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
