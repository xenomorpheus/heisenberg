package au.net.hal9000.heisenberg.worldeditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;
import au.net.hal9000.heisenberg.worldeditor.SkillsTable;

public class SkillsTableMain {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * app to test the skills table.
     * 
     * @param args
     *            not used
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
                    guiFrame.setTitle("Skills Table");
                    guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

                    // This will center the JFrame in the middle of the screen
                    guiFrame.setLocationRelativeTo(null);

                    SkillsTable skillsTable = new SkillsTable();
                    skillsTable.setPcRace(pc);

                    // add to JFrame
                    guiFrame.add(skillsTable);
                    guiFrame.pack();
                    guiFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
