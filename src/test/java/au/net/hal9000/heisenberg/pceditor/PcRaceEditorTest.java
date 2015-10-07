package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.pceditor.PcRaceEditor;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class PcRaceEditorTest {

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {
        PcRace pc = DemoEnvironment.getPcRace();
        PcRaceEditor window = new PcRaceEditor();
        assertNotNull("not Null", window);
        window.setPcRace(pc);
    }

}
