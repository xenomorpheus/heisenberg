package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.SkillsTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Demonstrate the Skills Table. */
public class SkillsTableMain { // NO_UCD (unused code)
  /** frame width. */
  static final int FRAME_WIDTH = 800;

  /** frame height. */
  static final int FRAME_HEIGHT = 300;

  /**
   * app to test the skills table.
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
            frame.setTitle("Skills Table");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

            // This will center the JFrame in the middle of the screen
            frame.setLocationRelativeTo(null);

            var skillsTable = new SkillsTable();
            var pc = DemoEnvironment.getCharacterSheet();
            skillsTable.setCharacterSheet(pc);

            // add to JFrame
            frame.add(skillsTable);
            frame.pack();
            frame.setVisible(true);
          } catch (ConfigurationError e) {
            e.printStackTrace();
          }
        });
  }
}
