package au.net.hal9000.heisenberg.pceditor;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import au.net.hal9000.heisenberg.crafting.Recipe;

/** Recipes Panel */
@Log4j2
public class RecipesPanel extends JPanel {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor for RecipesPane. */
  public RecipesPanel(@NonNull Set<String> skillIds, @NonNull Map<String, Recipe> skillDetails) {
    super();

    setLayout(new BorderLayout());

    var recipesTable = new RecipesTable(skillIds, skillDetails);
    var scrollPane = new JScrollPane(recipesTable);
    scrollPane.getViewport().add(recipesTable);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane, BorderLayout.CENTER);

    var southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // Create a JComboBox for recipes in skillDetails keys, but not in skillIds.
    List<String> missingRecipes = skillDetails.keySet().stream().filter(id -> !skillIds.contains(id)).toList();

    JComboBox<String> recipesMissingComboBox = new JComboBox<>(missingRecipes.toArray(new String[0]));
    southPanel.add(recipesMissingComboBox);

    var submitButton = new JButton("Add/Remove Recipe");
    southPanel.add(submitButton);

    add(southPanel, BorderLayout.SOUTH);

    // submit button action listener to add the selected skill to the recipes table,
    // or remove it if it's already in the table
    submitButton.addActionListener(e -> {
      var selectedRecipe = (String) recipesMissingComboBox.getSelectedItem();
      if (selectedRecipe != null) {
        if (skillIds.contains(selectedRecipe)) {
          skillIds.remove(selectedRecipe);
        } else {
          skillIds.add(selectedRecipe);
        }
        recipesTable.update();
      }
    });

    // Update the text when the recipes gets focus
    recipesTable.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        recipesTable.update();
      }
    });
  }
}
