package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.CharacterSheetEditor;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Demonstrate the Character Sheet Editor. */
public class CharacterSheetEditorMain { // NO_UCD (unused code)

  /**
   * Launch the application.
   *
   * @param args line args.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(
        () -> {
          try {
            DemoEnvironment.setup();
            var cs = DemoEnvironment.getCharacterSheet();
            var editor = new CharacterSheetEditor();
            editor.setCharacterSheet(cs);
            var frame = new JFrame();
            frame.add(editor);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centre
            frame.setVisible(true);
          } catch (ConfigurationError e) {
            e.printStackTrace();
          }
        });
  }
}
