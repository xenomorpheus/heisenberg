package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class ItemTreePanelMain {

    /** panel width. */
    private static final int PANEL_WIDTH = 400;

    /** panel height. */
    private static final int PANEL_HEIGHT = 400;

    /**
     * Method main.
     * 
     * @param arg
     *            String[]
     */
    public static void main(String[] arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Configuration config = DummyData.config();
                    Location location = DummyData.getDemoWorld();
                    // location.setWeightMax(100000);
                    // location.setVolumeMax(100000);
                    ItemTreePanel itemTreePanel = new ItemTreePanel(config,
                            location);
                    itemTreePanel.setVisible(true);
                    JFrame jFrame = new JFrame("ItemTeePanel Test");
                    jFrame.setContentPane(itemTreePanel);
                    jFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvalidTypeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (CantWearException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (TooHeavyException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (TooLargeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
