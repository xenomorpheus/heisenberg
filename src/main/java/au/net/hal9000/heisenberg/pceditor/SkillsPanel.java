package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.units.SkillId;
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

/** Skills Panel */
@Log4j2
public class SkillsPanel extends JPanel {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor for SkillsPane. */
  public SkillsPanel(@NonNull Set<SkillId> skillIds, @NonNull Map<SkillId, SkillDetail> skillDetails) {
    super();

    setLayout(new BorderLayout());

    var skillsTable = new SkillsTable(skillIds, skillDetails);
    var scrollPane = new JScrollPane(skillsTable);
    scrollPane.getViewport().add(skillsTable);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane, BorderLayout.CENTER);

    var southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // Create a JComboBox for skills in skillDetails keys, but not in skillIds.
    List<SkillId> missingSkills = skillDetails.keySet().stream().filter(id -> !skillIds.contains(id)).toList();

    JComboBox<SkillId> skillsMissingComboBox = new JComboBox<>(missingSkills.toArray(new SkillId[0]));
    southPanel.add(skillsMissingComboBox);

    var submitButton = new JButton("Add/Remove Skill");
    southPanel.add(submitButton);

    add(southPanel, BorderLayout.SOUTH);

    // submit button action listener to add the selected skill to the skills table,
    // or remove it if it's already in the table
    submitButton.addActionListener(e -> {
      var selectedSkill = (SkillId) skillsMissingComboBox.getSelectedItem();
      if (selectedSkill != null) {
        if (skillIds.contains(selectedSkill)) {
          skillIds.remove(selectedSkill);
        } else {
          skillIds.add(selectedSkill);
        }
        skillsTable.update();
      }
    });

    // Update the text when the skills gets focus
    skillsTable.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        skillsTable.update();
      }
    });
  }
}
