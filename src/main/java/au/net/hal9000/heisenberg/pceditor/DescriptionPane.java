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

  /** Field charactersheet. */
  private CharacterSheet charactersheet;

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
            if (null == charactersheet) {
              descriptionTextArea.setText("");
              LOGGER.error("charactersheet is NULL");
            } else {
              descriptionTextArea.setText(charactersheet.detailedDescription());
            }
          }
        });
  }

  /**
   * Set the PcClass object to show values for.
   *
   * @param charactersheet the PcClass object to show values for.
   *     <p>Note we pass the PcClass rather than the values needed to do the display. We do this
   *     because the values to display may be changed by other tabs, and passing by pc allows a
   *     refresh of values.
   */
  public void setCharacterSheet(final CharacterSheet charactersheet) {
    this.charactersheet = charactersheet;
    if (null == charactersheet) {
      descriptionTextArea.setText("");
    } else {
      descriptionTextArea.setText(charactersheet.detailedDescription());
    }
  }
}
