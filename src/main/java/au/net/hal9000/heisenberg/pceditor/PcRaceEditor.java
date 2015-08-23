package au.net.hal9000.heisenberg.pceditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import au.net.hal9000.heisenberg.item.entity.PcRace;

/**
 */
public class PcRaceEditor extends JPanel {
    /** window default x pos. */
    private static final int X_POS = 100;
    /** window default y pos. */
    private static final int Y_POS = 100;
    /** window width. */
    private static final int WIDTH = 894;
    /** window height. */
    private static final int HEIGHT = 634;

    /** serial version id.  */
    private static final long serialVersionUID = 1L;
    /**
     * Field pcRace.
     */
    private PcRace pcRace;

    /**
     * Field basicPanel.
     */
    private BasicPanel basicPanel = new BasicPanel();
    /**
     * Field abilityScoresTable.
     */
    private AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
    /**
     * Field skillsTable.
     */
    private SkillsTable skillsTable = new SkillsTable();
    /**
     * Field recipesTable.
     */
    private RecipesTable recipesTable = new RecipesTable();
    /**
     * Field descriptionPane.
     */
    private DescriptionPane descriptionPane = new DescriptionPane();

    /**
     * Create the application.
     */
    public PcRaceEditor() {
        setBounds(X_POS, Y_POS, WIDTH, HEIGHT);

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
    
     * @return the PcClass object we are editing. */

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
        pcRace = pc;
        basicPanel.setPcRace(pc);
        abilityScoresTable.setPcRace(pc);
        skillsTable.setPcRace(pc);
        recipesTable.setPcRace(pc);
        descriptionPane.setPcRace(pc);
    }

}
