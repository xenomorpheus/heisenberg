package au.net.hal9000.heisenberg.worldeditor.demo;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.worldeditor.ItemTreePanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
            // location.setWeightMax(100000);
            // location.setVolumeMax(100000);
            ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
            itemTreePanel.setVisible(true);
            JFrame jFrame = new JFrame("ItemTreePanel Test");
            jFrame.setContentPane(itemTreePanel);
            jFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
            jFrame.pack();
            // Centre
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
          }
        });
  }
}
