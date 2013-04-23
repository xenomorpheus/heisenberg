package au.net.hal9000.heisenberg.worldeditor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;

public class PcEditor {

    private Configuration config;
    private PcRace pc;
    private JFrame frame;


    // tab - description
    JTextArea descriptionTextArea;

    /**
     * Create the application.
     */
    public PcEditor(final PcRace pPc) {
        pc = pPc;
        config = Configuration.lastConfig();
        initialise();
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialise() {

        frame = new JFrame();
        frame.setBounds(100, 100, 894, 634);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Basics", null, new BasicPanel(pc, config), null);
        tabbedPane.addTab("Abilities", null,
                new AbilityScoresTable(pc, config), null);
        tabbedPane.addTab("Skills", null, new SkillsTable(pc, config), null);
        tabbedPane.addTab("Recipes", null, new RecipesTable(pc, config), null);
        tabbedPane.addTab("Description", null, descriptionPane(pc), null);
        frame.getContentPane().add(tabbedPane);
    }

    public JFrame getFrame() {
        return frame;
    }

    private JComponent descriptionPane(final PcRace pc) {

        // Description Panel
        JPanel descriptionPanel = new JPanel();

        descriptionTextArea = new JTextArea(30, 25);
        descriptionTextArea.setEditable(false);
        descriptionPanel.add(descriptionTextArea);
        descriptionTextArea.setLineWrap(true);

        // Update the text when the description gets focus
        descriptionTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                descriptionTextArea.setText(pc.getDetailedDescription());
            }
        });
        JScrollPane descriptionScr = new JScrollPane(descriptionPanel);
        descriptionScr
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return descriptionScr;
    }

}
