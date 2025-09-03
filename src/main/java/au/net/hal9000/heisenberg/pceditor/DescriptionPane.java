package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Description Pane. */
public class DescriptionPane extends JScrollPane {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Field descriptionTextArea. */
  private JTextArea descriptionTextArea = new JTextArea(30, 25);

  /** Constructor for DescriptionPane. */
  public DescriptionPane(CharacterSheet characterSheet) {
    super();
    if (characterSheet == null) {
      throw new IllegalArgumentException("characterSheet is NULL");
    }

    // Description Panel

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setLineWrap(true);
    descriptionTextArea.setText(characterSheet.detailedDescription());

    // Add to JScrollPane (ourselves)
    getViewport().add(descriptionTextArea);
    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    setVisible(true);

    // Update the text when the description gets focus
    descriptionTextArea.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
              descriptionTextArea.setText(characterSheet.detailedDescription());
          }
        });
  }
}
