package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

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
