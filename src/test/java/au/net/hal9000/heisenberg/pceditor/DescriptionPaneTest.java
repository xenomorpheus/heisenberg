package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.pceditor.DescriptionPane;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.TestEnvironment;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class DescriptionPaneTest {

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {

        PcRace pc = TestEnvironment.getPcRace();
        DescriptionPane window = new DescriptionPane();
        assertNotNull("not Null", window);
        window.setPcRace(pc);
    }

}
