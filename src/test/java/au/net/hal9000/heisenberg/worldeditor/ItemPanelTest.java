package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 * Test the ItemPanel. * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ItemPanelTest {
    /** panel width. */
    private static final int PANEL_WIDTH = 400;

    /** panel height. */
    private static final int PANEL_HEIGHT = 400;

    /**
     * Test itemTreePanel.
     * 
     * 
     * @throws ConfigurationError
     * @throws CantWearException
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void itemTreePanel() throws ConfigurationError,
            InvalidTypeException, CantWearException, TooHeavyException,
            TooLargeException {
        Configuration config = DummyData.config();
        Location location = DummyData.getDemoWorld();
        ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
        assertNotNull("Not Null", itemTreePanel);
    }

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
