package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class ItemPanelTest {

    public static void main(String arg[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Configuration config = DummyData.config();
                    Location location = DummyData.getDemoWorld();
                    ItemTreePanel itemTreePanel = new ItemTreePanel(config,location);
                    itemTreePanel.setVisible(true);
                    JFrame jFrame = new JFrame("ItemTeePanel Test");
                    jFrame.setContentPane(itemTreePanel);
                    jFrame.setSize(400, 600);
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //location.setWeightMax(100000);
                //location.setVolumeMax(100000);
            }
        });
    }
}
