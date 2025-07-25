package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.RecipesTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Demonstrate the Recipes Table functionality. */
public class RecipesTableMain { // NO_UCD (unused code)

  /** frame width. */
  static final int FRAME_WIDTH = 2400;

  /** frame height. */
  static final int FRAME_HEIGHT = 300;

  /**
   * app to test the world editor.
   *
   * @param args not used
   */
  public static void main(String[] args) {

    // Use the event dispatch thread for Swing components
    SwingUtilities.invokeLater(
        () -> {
          try {
            DemoEnvironment.setup();
            var frame = new JFrame();

            // make sure the program exits when the frame closes
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Recipes Table");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

            var recipesTable = new RecipesTable();
            var pc = DemoEnvironment.getCharacterSheet();
            recipesTable.setCharacterSheet(pc);

            // add to JFrame
            frame.add(recipesTable);
            frame.pack();
            // This will centre the JFrame in the middle of the screen
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
          } catch (ConfigurationError e) {
            e.printStackTrace();
          }
        });
  }
}
