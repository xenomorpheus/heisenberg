package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import java.awt.EventQueue;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.PcClass;

public class BasicPanelRun {
    JFrame guiFrame;

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Configuration config = new Configuration(
                            "src/test/resources/config.xml");
                    TreeMap<String, PcClass> pcClasses = config.getPcClasses();
                    // TODO remove - these are only for testing.
                    PcRace pc = (PcRace) Factory.createItem("Elf");
                    pc.setName("Jane");
                    pc.setPcClass(pcClasses.get("Paladin"));
                    pc.setDescription("The Paladin");
                    pc.setGender("Female"); // TODO get from config
                    pc.setSize("Small");
                    pc.setLevel(3);
                    pc.skillsAdd(new String[] { "testSkill1", "testSkill2",
                            "testSkill3" });
                    pc.recipeAdd("testItem1");

                    new BasicPanelRun(pc, config);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public BasicPanelRun(final PcRace pcClass, final Configuration config) {

        guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("PC Editor");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        JPanel basicPanel = new BasicPanel(pcClass, config);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

}