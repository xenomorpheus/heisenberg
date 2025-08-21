package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/** CharacterSheet editor. */
public class CharacterSheetEditor extends JPanel {
  /** window default x pos. */
  private static final int X_POS = 100;

  /** window default y pos. */
  private static final int Y_POS = 100;

  /** window width. */
  private static final int WIDTH = 894;

  /** window height. */
  private static final int HEIGHT = 634;

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Field CharacterSheet. */
  private CharacterSheet charactersheet;

  /** Field basicPanel. */
  private BasicPanel basicPanel = new BasicPanel();

  /** Field abilityScoresTable. */
  private AbilityScoresTable abilityScoresTable = new AbilityScoresTable();

  /** Field skillsTable. */
  private SkillsTable skillsTable = new SkillsTable();

  /** Field recipesTable. */
  private RecipesTable recipesTable = new RecipesTable();

  /** Field descriptionPane. */
  private DescriptionPane descriptionPane = new DescriptionPane();

  /** Create the application. */
  public CharacterSheetEditor() {
    setBounds(X_POS, Y_POS, WIDTH, HEIGHT);

    // Main container
    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.ipady = 0;
    cons.ipadx = 0;
    GridBagLayout gridBag = new GridBagLayout();
    setLayout(gridBag);

    // Tabbed Pane
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("Basics", null, basicPanel, null);
    tabbedPane.addTab("Abilities", null,  new JScrollPane(abilityScoresTable), null);
    tabbedPane.addTab("Skills", null, new JScrollPane(skillsTable), null);
    tabbedPane.addTab("Recipes", null, new JScrollPane(recipesTable), null);
    tabbedPane.addTab("Description", null, descriptionPane, null);
    cons.gridx = 0;
    cons.gridy = 0;
    gridBag.setConstraints(tabbedPane, cons);
    add(tabbedPane);

    // Button(s)
    JPanel butPanel = new JPanel();

    JButton closeButton = new JButton("Close");
    // Action inside panel to close the owning JFrame
    closeButton.addActionListener(e -> {
      var window = SwingUtilities.getWindowAncestor(butPanel);
      if (window != null) {
        window.dispose(); // closes just this frame
      }
    });
    butPanel.add(closeButton);

    cons.gridx = 0;
    cons.gridy = 1;
    gridBag.setConstraints(butPanel, cons);
    add(butPanel);
  }

  /**
   * Get the CharacterSheet object we are editing.
   *
   * @return the CharacterSheet object we are editing.
   */
  public CharacterSheet getCharactersheet() {
    return charactersheet;
  }

  /**
   * Set the CharacterSheet we are editing.
   *
   * @param cs the CharacterSheet we are editing.
   */
  public void setCharacterSheet(CharacterSheet cs) {
    charactersheet = cs;
    basicPanel.setCharacterSheet(cs);
    abilityScoresTable.setCharacterSheet(cs);
    skillsTable.setCharacterSheet(cs);
    recipesTable.setCharacterSheet(cs);
    descriptionPane.setCharacterSheet(cs);
  }
}
