package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import lombok.NonNull;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusListener;

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

  /** Create the application. */
  public CharacterSheetEditor(@NonNull CharacterSheet charactersheet) {

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

    var basicPanel = new BasicPanel(charactersheet);
    tabbedPane.addTab("Basics", null, basicPanel, null);

    var abilityScoresTable = new AbilityScoresTable(charactersheet.getAbilityScores());
    var abilityScoreScrollPane = new JScrollPane(abilityScoresTable);
    abilityScoreScrollPane.setFocusable(false);
    tabbedPane.addTab("Abilities", null, abilityScoreScrollPane, null);

    var config = Configuration.lastConfig();

    var skillsPane = new SkillsPanel(charactersheet.getSkills(), config.getSkillDetails());
    skillsPane.setFocusable(false);
    tabbedPane.addTab("Skills", null, skillsPane, null);

    var recipesTable = new RecipesTable(charactersheet.getRecipes(), config.getRecipeDetails());
    var recipesScrollPane = new JScrollPane(recipesTable);
    recipesScrollPane.setFocusable(false);
    tabbedPane.addTab("Recipes", null, recipesScrollPane, null);

    var descriptionPane = new DescriptionPanel(charactersheet);
    tabbedPane.addTab("Description", null, descriptionPane, null);

    cons.gridx = 0;
    cons.gridy = 0;
    gridBag.setConstraints(tabbedPane, cons);
    add(tabbedPane);

    // listen for selection changes
    tabbedPane.addChangeListener(e -> {
      int index = tabbedPane.getSelectedIndex();
      var selected = tabbedPane.getComponentAt(index);
      System.out.println("Selected tab: " + index + ", component: " + selected.getClass().getSimpleName());

      if (selected instanceof JScrollPane) {
        var viewport = ((JScrollPane) selected).getViewport();
        selected = viewport.getView();
        System.out.println("Viewport view is: " + selected.getClass().getSimpleName());
      }

      if (selected instanceof FocusListener) {
        System.out.println("Focusing " + selected.getClass().getSimpleName());
        ((FocusListener) selected).focusGained(null);
      }
    });

    // Button(s)
    JPanel butPanel = new JPanel();

    JButton closeButton = new JButton("Close Character Sheet Editor");
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

}
