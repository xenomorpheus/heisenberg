package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.DescriptionPane;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Demonstrate the Description Pane. */
public class DescriptionPaneMain { // NO_UCD (unused code)

  /** frame width. */
  static final int FRAME_WIDTH = 800;

  /** frame height. */
  static final int FRAME_HEIGHT = 600;

  /**
   * Launch the application.
   *
   * @param args line args.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            try {
              DemoEnvironment.setup();
              JFrame guiFrame = new JFrame();

              // make sure the program exits when the frame closes
              guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              guiFrame.setTitle("Description");
              guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

              // This will center the JFrame in the middle of the screen
              guiFrame.setLocationRelativeTo(null);

              DescriptionPane window = new DescriptionPane();
              CharacterSheet pc = DemoEnvironment.getCharacterSheet();
              window.setCharacterSheet(pc);

              // add to JFrame
              guiFrame.add(window);
              guiFrame.setVisible(true);

            } catch (ConfigurationError e) {
              e.printStackTrace();
            }
          }
        });
  }
}
