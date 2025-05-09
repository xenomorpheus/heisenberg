package au.net.hal9000.heisenberg.worldeditor.demo;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.worldeditor.ItemTreePanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Demonstrate the ItemTreePanel functionality. */
public class ItemTreePanelMain { // NO_UCD (unused code)

  /** panel width. */
  private static final int PANEL_WIDTH = 400;

  /** panel height. */
  private static final int PANEL_HEIGHT = 400;

  /**
   * Method main.
   *
   * @param arg String[]
   */
  public static void main(String[] arg) {
    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            DemoEnvironment.setup();
            Configuration config = Configuration.lastConfig();
            Location location = DemoEnvironment.getDemoWorld();
            ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
            itemTreePanel.setVisible(true);
            JFrame frame = new JFrame("ItemTreePanel Test");
            frame.setContentPane(itemTreePanel);
            frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
            frame.pack();
            // Centre
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
          }
        });
  }
}
