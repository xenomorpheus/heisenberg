package au.net.hal9000.heisenberg.worldeditor;

//Imports
import java.awt.*;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.*;

import nu.xom.ValidityException;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

class SkillsTableRun extends JFrame {
    /**
  * 
  */
    private static final long serialVersionUID = 1L;
    // Instance attributes used in this example
    private JPanel topPanel;
    private JTable table;
    private JScrollPane scrollPane;

    // Constructor of main frame
    public SkillsTableRun(PcRace pc, Configuration config) {
        // Set the frame characteristics
        setTitle("Advanced Table Application");
        setSize(300, 200);
        setBackground(Color.gray);

        // Create a panel to hold all other components
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        // Create a new table instance
        table = new SkillsTable(pc, config);

        // Configure some of JTable's paramters
        table.setShowHorizontalLines(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);

        // Change the selection colour
        table.setSelectionForeground(Color.white);
        table.setSelectionBackground(Color.red);

        // Add the table to a scrolling pane
        scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
    }

    // Main entry point for this example
    public static void main(String args[]) {

        // Create an instance of the test application
        try {
            Configuration config = new Configuration("src/test/resources/config.xml");
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
            pc.powerWordsAdd(new String[] { "testPowerWord1", "testPowerWord2",
                    "testPowerWord3" });
            pc.recipesAdd("testItem1");

            SkillsTableRun mainFrame = new SkillsTableRun(pc, config);
            mainFrame.setVisible(true);
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
    }
}