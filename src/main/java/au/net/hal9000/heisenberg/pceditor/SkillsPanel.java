package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.units.SkillId;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
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

    var skillsTable = new SkillsTable(skillIds, skillDetails);
    var scrollPane = new JScrollPane(skillsTable);
    scrollPane.getViewport().add(skillsTable);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(scrollPane, BorderLayout.CENTER);

    JButton button = new JButton("Submit");
    add(button, BorderLayout.SOUTH);

    // Update the text when the skills gets focus
    skillsTable.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        log.info("TODO Skills table got focus, updating text");
        // skillsTable.setText(characterSheet.detailedSkills());
      }
    });
  }
}
