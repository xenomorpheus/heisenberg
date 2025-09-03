package au.net.hal9000.heisenberg.pceditor.demo;

import au.net.hal9000.heisenberg.pceditor.DescriptionPane;
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
        () -> {
          try {
            DemoEnvironment.setup();
            var frame = new JFrame();

            // make sure the program exits when the frame closes
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Description");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

            // This will center the JFrame in the middle of the screen
            frame.setLocationRelativeTo(null);

            var pane = new DescriptionPane(DemoEnvironment.getCharacterSheet());

            // add to JFrame
            frame.add(pane);
            frame.setVisible(true);

          } catch (ConfigurationError e) {
            e.printStackTrace();
          }
        });
  }
}
