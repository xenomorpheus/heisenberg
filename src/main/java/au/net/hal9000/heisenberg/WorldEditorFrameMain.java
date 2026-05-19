package au.net.hal9000.heisenberg;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.WorldEditorFrame;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.SwingUtilities;
import lombok.extern.log4j.Log4j2;

/** World Editor Frame Main. Has menus. */
@Log4j2
public class WorldEditorFrameMain { // NO_UCD (unused code)

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
