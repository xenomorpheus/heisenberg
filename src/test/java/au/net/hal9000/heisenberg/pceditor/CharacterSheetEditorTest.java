package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/** */
public class CharacterSheetEditorTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {
    CharacterSheet pc = DemoEnvironment.getCharacterSheet();
    CharacterSheetEditor editor = new CharacterSheetEditor();
    assertNotNull("not Null", editor);
    editor.setCharacterSheet(pc);
  }
}
