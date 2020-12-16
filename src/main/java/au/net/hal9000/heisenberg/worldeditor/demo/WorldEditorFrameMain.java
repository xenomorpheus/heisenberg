package au.net.hal9000.heisenberg.worldeditor.demo;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.WorldEditorFrame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;

public class WorldEditorFrameMain {
  /**
   * app to test the world editor.
   *
   * @param args not used
   */
  /** Field LOGGER. */
  static final Logger LOGGER = Logger.getLogger(WorldEditorFrameMain.class.getName());

  public static void main(String[] args) {

    // Use the event dispatch thread for Swing components
    SwingUtilities.invokeLater(
        new Runnable() {

          @Override
          public void run() {
            try {
              DemoEnvironment.setup();
              WorldEditorFrame worldEditor = new WorldEditorFrame();
              // Kill app
              worldEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              worldEditor.pack();
              // Centre
              worldEditor.setLocationRelativeTo(null);
              worldEditor.setVisible(true);
            } catch (ConfigurationError e) {
              e.printStackTrace();
            }
          }
        });
  }
}
