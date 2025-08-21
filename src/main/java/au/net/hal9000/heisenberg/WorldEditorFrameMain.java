package au.net.hal9000.heisenberg;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.WorldEditorFrame;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;

/** World Editor Frame Main. Has menus. */
public class WorldEditorFrameMain { // NO_UCD (unused code)

  /** Field LOGGER. */
  static final Logger LOGGER = Logger.getLogger(WorldEditorFrameMain.class.getName());

  /**
   * app to test the world editor.
   *
   * @param args not used
   */
  public static void main(String[] args) {

    // Use the event dispatch thread for Swing components
    SwingUtilities.invokeLater(() -> {
      try {
        DemoEnvironment.setup();
        var frame = new WorldEditorFrame();
        frame.pack();
        frame.setLocationRelativeTo(null); // Centre
        frame.setVisible(true);
      } catch (ConfigurationError e) {
        e.printStackTrace();
      }
    });
  }
}
