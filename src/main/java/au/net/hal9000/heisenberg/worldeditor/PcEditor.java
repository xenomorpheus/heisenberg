package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import au.net.hal9000.heisenberg.item.PcRace;

public class PcEditor {

    private JFrame frame;
    private PcRace pc;

    private BasicPanel basicPanel = new BasicPanel();
    private AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
    private SkillsTable skillsTable = new SkillsTable();
    private RecipesTable recipesTable = new RecipesTable();
    private DescriptionPane descriptionPane = new DescriptionPane();

    /**
     * Create the application.
     */
    public PcEditor() {
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
        tabbedPane.addTab("Basics", null, basicPanel, null);
        tabbedPane.addTab("Abilities", null, abilityScoresTable, null);
        tabbedPane.addTab("Skills", null, skillsTable, null);
        tabbedPane.addTab("Recipes", null, recipesTable, null);
        tabbedPane.addTab("Description", null, descriptionPane, null);
        frame.getContentPane().add(tabbedPane);
    }

    /**
     * get the frame.
     * 
     * @return the frame.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Get the PcClass object we are editing.
     * 
     * @return the PcClass object we are editing.
     */

    public PcRace getPc() {
        return pc;
    }

    /**
     * Set the PcClass object we are editing.
     * 
     * @param pc
     *            the PcClass object we are editing.
     */
    public void setPc(PcRace pc) {
        this.pc = pc;
        basicPanel.setItem(pc);
        abilityScoresTable.setItem(pc);
        skillsTable.setItem(pc);
        recipesTable.setItem(pc);
        descriptionPane.setItem(pc);
    }

    /**
     * 
     * @param button
     * @param windowTitle
     * @param modal
     * @param pcEditor
     * @param okButtonHandler
     * @param cancellButtonHandler
     * @return
     */

    public static JDialog createDialog(JButton button, String windowTitle,
            boolean modal, PcEditor pcEditor, ItemEditor okButtonHandler,
            ItemEditor cancellButtonHandler) {
        // TODO Auto-generated method stub
        return null;
    }

}
