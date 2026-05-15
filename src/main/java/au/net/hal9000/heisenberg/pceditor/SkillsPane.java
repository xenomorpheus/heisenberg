package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.units.SkillId;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.JScrollPane;

/** Skills Pane. */
@Log4j2
public class SkillsPane extends JScrollPane {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor for SkillsPane. */
  public SkillsPane(@NonNull Set<SkillId> skillIds, @NonNull Map<SkillId, SkillDetail> skillDetails) {
    super();

    // Skills Panel
    var skillsTable = new SkillsTable(skillIds, skillDetails);

    // Add to JScrollPane (ourselves)
    getViewport().add(skillsTable);
    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    setVisible(true);

    // Update the text when the skills gets focus
    skillsTable.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            log.info("TODO Skills table got focus, updating text");
            //  skillsTable.setText(characterSheet.detailedSkills());
          }
        });
  }
}
