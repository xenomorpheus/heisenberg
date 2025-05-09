package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.log4j.Logger;

/** Description Pane. */
public class DescriptionPane extends JScrollPane {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Logger. */
  private static final Logger LOGGER = Logger.getLogger(DescriptionPane.class.getName());

  /** Field characterSheet. */
  private CharacterSheet characterSheet;

  /** Field descriptionTextArea. */
  private JTextArea descriptionTextArea = new JTextArea(30, 25);

  /** Constructor for DescriptionPane. */
  public DescriptionPane() {
    super();

    // Description Panel

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setLineWrap(true);

    // Add to JScrollPane (ourselves)
    getViewport().add(descriptionTextArea);
    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    setVisible(true);

    // Update the text when the description gets focus
    descriptionTextArea.addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            if (null == characterSheet) {
              descriptionTextArea.setText("");
              LOGGER.error("characterSheet is NULL");
            } else {
              descriptionTextArea.setText(characterSheet.detailedDescription());
            }
          }
        });
  }

  /**
   * Set the PcClass object to show values for.
   *
   * @param characterSheet the PcClass object to show values for. Note we pass the PcClass rather
   *     than the values needed to do the display. We do this because the values to display may be
   *     changed by other tabs, and passing by pc allows a refresh of values.
   */
  public void setCharacterSheet(final CharacterSheet characterSheet) {
    this.characterSheet = characterSheet;
    if (null == characterSheet) {
      descriptionTextArea.setText("");
    } else {
      descriptionTextArea.setText(characterSheet.detailedDescription());
    }
  }
}
