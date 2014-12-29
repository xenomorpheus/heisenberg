package au.net.hal9000.heisenberg.worldeditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;
import au.net.hal9000.heisenberg.worldeditor.BasicPanel;

/**
 */
public class BasicPanelMain {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * for testing the config editor.
     * 
     * @param args
     *            command line args.
     */
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    PcRace pc = DummyData.elf();
                    JFrame guiFrame = new JFrame();

                    // make sure the program exits when the frame closes
                    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    guiFrame.setTitle("Basic Panel");
                    guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

                    BasicPanel basicPanel = new BasicPanel();
                    basicPanel.setPcRace(pc);

                    // add to JFrame
                    guiFrame.add(basicPanel);
                    guiFrame.pack();
                    // This will centre the JFrame in the middle of the screen
                    guiFrame.setLocationRelativeTo(null);
                    guiFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}