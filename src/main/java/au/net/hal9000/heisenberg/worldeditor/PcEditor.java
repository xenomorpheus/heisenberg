package au.net.hal9000.heisenberg.worldeditor;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.util.TreeMap;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.PcClass;

public class PcEditor {

    private Configuration config;
    private PcRace pc;
    private JFrame frame;

    // tab - description
    JTextArea descriptionTextArea;

    /**
     * Create the application.
     */
    public PcEditor(final PcRace pPc, final Configuration pConfig) {
        pc = pPc;
        config = pConfig;
        initialise();
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialise() {

        frame = new JFrame();
        frame.setBounds(100, 100, 894, 634);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Basics", null, new BasicPanel(pc, config), null);
        tabbedPane.addTab("Abilities", null,
                new AbilityScoresTable(pc, config), null);
        tabbedPane.addTab("Skills", null, new SkillsTable(pc, config), null);
        tabbedPane.addTab("Recipes", null, new RecipesTable(pc, config), null);
        tabbedPane.addTab("Description", null, descriptionPane(pc), null);
        frame.getContentPane().add(tabbedPane);
    }

    private JComponent descriptionPane(final PcRace pc) {

        // Description Panel
        JPanel descriptionPanel = new JPanel();

        descriptionTextArea = new JTextArea(30, 25);
        descriptionTextArea.setEditable(false);
        descriptionPanel.add(descriptionTextArea);
        descriptionTextArea.setLineWrap(true);

        // Update the text when the description gets focus
        descriptionTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                descriptionTextArea.setText(pc.getDetailedDescription());
            }
        });
        JScrollPane descriptionScr = new JScrollPane(descriptionPanel);
        descriptionScr
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return descriptionScr;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Configuration config = new Configuration(
                            "src/test/resources/config.xml");
                    TreeMap<String, PcClass> pcClasses = config.getPcClasses();
                    PcRace pc = (PcRace) Factory.createItem("Elf");
                    pc.setName("Jane");
                    pc.setPcClass(pcClasses.get("Paladin"));
                    pc.setDescription("The Paladin");
                    pc.setGender("Female"); // TODO get from config
                    pc.setSize("Small");
                    pc.setLevel(3);
                    pc.skillsAdd(new String[] { "testSkill1", "testSkill2",
                            "testSkill3" });
                    pc.recipesAdd(new String[] { "testItem1",
                            "testFireGround1", "testSpell1" });

                    PcEditor window = new PcEditor(pc, config); // TODO
                                                                // config pass
                                                                // in
                    window.frame.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
