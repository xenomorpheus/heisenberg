package au.net.hal9000.heisenberg.worldeditor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.PcRace;

public class DescriptionPane extends JScrollPane {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DescriptionPane.class
            .getName());
    private PcRace pc;
    private JTextArea descriptionTextArea = new JTextArea(30, 25);

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
                if (pc == null) {
                    descriptionTextArea.setText("");
                    logger.error("pc is NULL");
                } else {
                    descriptionTextArea.setText(pc.getDetailedDescription());
                }
            }
        });
    }

    /**
     * Set the PcClass object to show values for.
     * @param pc the PcClass object to show values for.
     * 
     * Note we pass the PcClass rather than the values needed to do the display.
     * We do this because the values to display may be changed by other tabs, and
     * passing by pc allows a refresh of values.
     */
    public void setItem(final PcRace pc) {
        this.pc = pc;
        if (pc == null){
            descriptionTextArea.setText("");
        }
        else{
            descriptionTextArea.setText(pc.getDetailedDescription());
        }
    }
}
