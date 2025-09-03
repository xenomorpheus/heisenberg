package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.AbilityScoresTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Abilities Score Table Main. */
public class AbilitiesScoresTableMain { // NO_UCD (unused code)

  /** Default constructor for AbilitiesScoresTableMain. */
  public AbilitiesScoresTableMain() {
    super();
  }

  /** frame width. */
  static final int FRAME_WIDTH = 800;

  /** frame height. */
  static final int FRAME_HEIGHT = 300;

  /**
   * for testing the config editor.
   *
   * @param args command line args.
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
            frame.setTitle("Basic Panel");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

            var abilityScoresTable = new AbilityScoresTable(DemoEnvironment.getCharacterSheet());

            // add to JFrame
            frame.add(abilityScoresTable);
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
