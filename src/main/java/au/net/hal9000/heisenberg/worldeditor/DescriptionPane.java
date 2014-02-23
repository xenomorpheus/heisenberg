package au.net.hal9000.heisenberg.worldeditor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.PcRace;

/**
 * Description Pane.
 * @author bruins
 *
 * @version $Revision: 1.0 $
 */
public class DescriptionPane extends JScrollPane {

    /** serial version id. */
    private static final long serialVersionUID = 1L;
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(DescriptionPane.class
            .getName());
    /**
     * Field pc.
     */
    private PcRace pc;
    /**
     * Field descriptionTextArea.
     */
    private JTextArea descriptionTextArea = new JTextArea(30, 25);

    /**
     * Constructor for DescriptionPane.
     */
    DescriptionPane() {
        super();

        // Description Panel

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setLineWrap(true);

        // Add to JScrollPane (ourselves)
        getViewport().add(descriptionTextArea);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);

        // Update the text when the description gets focus
        descriptionTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (null == pc) {
                    descriptionTextArea.setText("");
                    LOGGER.error("pc is NULL");
                } else {
                    descriptionTextArea.setText(pc.detailedDescription());
                }
            }
        });
    }

    /**
     * Set the PcClass object to show values for.
     * 
     * @param pc
     *            the PcClass object to show values for.
     * 
     *            Note we pass the PcClass rather than the values needed to do
     *            the display. We do this because the values to display may be
     *            changed by other tabs, and passing by pc allows a refresh of
     *            values.
     */
    public void setItem(final PcRace pc) {
        this.pc = pc;
        if (null == pc) {
            descriptionTextArea.setText("");
        } else {
            descriptionTextArea.setText(pc.detailedDescription());
        }
    }
}
