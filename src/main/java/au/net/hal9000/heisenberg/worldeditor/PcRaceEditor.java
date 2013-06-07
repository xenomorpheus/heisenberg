package au.net.hal9000.heisenberg.worldeditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import au.net.hal9000.heisenberg.item.PcRace;

public class PcRaceEditor extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private PcRace pcRace;

    private BasicPanel basicPanel = new BasicPanel();
    private AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
    private SkillsTable skillsTable = new SkillsTable();
    private RecipesTable recipesTable = new RecipesTable();
    private DescriptionPane descriptionPane = new DescriptionPane();

    /**
     * Create the application.
     */
    public PcRaceEditor() {
        setBounds(100, 100, 894, 634);

        // Main container
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.ipady = 0;
        cons.ipadx = 0;
        setLayout(gridBag);

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Basics", null, basicPanel, null);
        tabbedPane.addTab("Abilities", null, abilityScoresTable, null);
        tabbedPane.addTab("Skills", null, skillsTable, null);
        tabbedPane.addTab("Recipes", null, recipesTable, null);
        tabbedPane.addTab("Description", null, descriptionPane, null);
        cons.gridx = 0;
        cons.gridy = 0;
        gridBag.setConstraints(tabbedPane, cons);
        add(tabbedPane);

        // Button(s)
        JPanel butPanel = new JPanel();
        // TODO button perform action
        butPanel.add(new JButton("Done"));
        cons.gridx = 0;
        cons.gridy = 1;
        gridBag.setConstraints(butPanel, cons);
        add(butPanel);
    }

    /**
     * Get the PcClass object we are editing.
     * 
     * @return the PcClass object we are editing.
     */

    public PcRace getPcRace() {
        return pcRace;
    }

    /**
     * Set the PcClass object we are editing.
     * 
     * @param pc
     *            the PcClass object we are editing.
     */
    public void setPcRace(PcRace pc) {
        this.pcRace = pc;
        basicPanel.setItem(pc);
        abilityScoresTable.setItem(pc);
        skillsTable.setItem(pc);
        recipesTable.setItem(pc);
        descriptionPane.setItem(pc);
    }

}
