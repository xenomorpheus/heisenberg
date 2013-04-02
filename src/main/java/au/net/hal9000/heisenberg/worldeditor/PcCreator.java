package au.net.hal9000.heisenberg.worldeditor;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.TreeMap;

import nu.xom.ValidityException;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class PcCreator {

    private Configuration config;
    private PcRace pc;
    private JFrame frame;

    // tab - description
    JTextArea descriptionTextArea;

    /**
     * Create the application.
     */
    public PcCreator(final PcRace pPc, final Configuration pConfig) {

        pc = pPc;
        // TODO config = pConfig;
        try {
            config = new Configuration("test/config/config.xml");
        } catch (ValidityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        initialise();
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialise() {

        frame = new JFrame();
        frame.setBounds(100, 100, 894, 634);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 862, 592);
        tabbedPane.addTab("Basics", null, new BasicPanel(pc, config), null);
        tabbedPane.addTab("Abilities", null,
                new AbilityScoresTable(pc, config), null);
        tabbedPane.addTab("Skills", null, new SkillsTable(pc, config), null);
        tabbedPane.addTab("PowerWords", null, new PowerWordsTable(pc, config),
                null);
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
                    // TODO remove - these are only for testing.
                    Configuration config = null;
                    try {
                        config = new Configuration("test/config/config.xml");
                    } catch (ValidityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
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
                    pc.powerWordsAdd(new String[] { "testPowerWord1",
                            "testPowerWord2", "testPowerWord3" });
                    pc.recipesAdd(new String[] { "testItem1",
                            "testFireGround1", "testSpell1" });

                    PcCreator window = new PcCreator(pc, null); // TODO
                                                                // config pass
                                                                // in
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
