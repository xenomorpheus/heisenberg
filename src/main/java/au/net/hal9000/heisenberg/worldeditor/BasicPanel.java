package au.net.hal9000.heisenberg.worldeditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class BasicPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Misc
    private PcRace pc;
    private Configuration config = Configuration.lastConfig();

    // Fields
    // Row 0
    private JTextField nameTextField;
    private JComboBox classComboBox;
    // Row 1
    private JTextField descriptionTextField;
    private JTextField raceTextField;
    // Row 2
    private JTextField comTextField;
    private JTextField magTextField;
    private JTextField steTextField;
    private JTextField genTextField;
    // Row 3
    private JSpinner levelSpinner;
    private SpinnerModel levelModel;
    private JTextField actionPointsTextField;
    private JTextField healthTextField;
    private JTextField manaTextField;
    // Row 4
    private JComboBox sizeComboBox;
    private JComboBox genderComboBox;

    public BasicPanel() {
        super();

        addComponents();

        // Add change hander
        BasicTextFieldListener basicTextFieldListener = new BasicTextFieldListener();
        BasicTextFieldFocusEvent basicTextFieldFocusEvent = new BasicTextFieldFocusEvent();
        nameTextField.addActionListener(basicTextFieldListener);
        nameTextField.addFocusListener(basicTextFieldFocusEvent);
        descriptionTextField.addActionListener(basicTextFieldListener);
        descriptionTextField.addFocusListener(basicTextFieldFocusEvent);

        BasicItemListener basicItemListener = new BasicItemListener();
        classComboBox.addItemListener(basicItemListener);
        sizeComboBox.addItemListener(basicItemListener);
        genderComboBox.addItemListener(basicItemListener);

        // Listen for changes to level
        levelSpinner.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                SpinnerModel levelModel = levelSpinner.getModel();
                if (levelModel instanceof SpinnerNumberModel) {
                    int pcLevel = Integer
                            .parseInt(((SpinnerNumberModel) levelModel)
                                    .getValue().toString());
                    pc.setLevel(pcLevel);
                    updateForm();
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
        updateForm();
    }

    private void addComponents() {
        Collection<PcClass> pcClassesItr = config.getPcClasses().values();
        Collection<String> sizes = config.getSizes();
        Collection<String> genders = config.getGenders();

        // create GribBagLayout and the GridBagLayout Constraints
        GridBagLayout gridBag = new GridBagLayout();
        // Use the GridBagConstraints to determine how the component
        // is displayed within the GridBagLayout. ipadx and ipady are used
        // to pad the component within the cell(s). The weightx and weighty
        // are used to give a weighting to the component to specify how the
        // any extra space is handled by a row or column. gridx and gridy are
        // the position coordinates for the component within the grid. fill
        // determines what happens to sizing of a component if it doesn't
        // completely
        // fill its cell. gridwidth and gridheight specify how many cells
        // (either
        // in a row or column) the component will take up.
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;
        cons.ipady = 0;
        cons.ipadx = 0;

        this.setLayout(gridBag);

        int row = 0;
        int pos = 0;
        // Name
        JLabel nameLbl = new JLabel("Name");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(nameLbl, cons);
        this.add(nameLbl);
        pos += cons.gridwidth;

        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.putClientProperty("id", "name");
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 6;
        gridBag.setConstraints(nameTextField, cons);
        this.add(nameTextField);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Class
        JLabel classLbl = new JLabel("Class");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(classLbl, cons);
        this.add(classLbl);
        pos += cons.gridwidth;

        classComboBox = new JComboBox();
        for (PcClass pcClass : pcClassesItr) {
            classComboBox.addItem(pcClass);
        }

        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 4;
        gridBag.setConstraints(classComboBox, cons);
        this.add(classComboBox);
        cons.gridwidth = 1;
        pos += cons.gridwidth;

        // Row 1
        row = 1;
        pos = 0;

        // Description
        JLabel descriptionLbl = new JLabel("Description");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(descriptionLbl, cons);
        this.add(descriptionLbl);
        pos += cons.gridwidth;

        descriptionTextField = new JTextField();
        descriptionTextField.setColumns(10);
        descriptionTextField.putClientProperty("id", "description");
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 6;
        gridBag.setConstraints(descriptionTextField, cons);
        this.add(descriptionTextField);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Race
        JLabel raceLbl = new JLabel("Race");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(raceLbl, cons);
        this.add(raceLbl);
        pos += cons.gridwidth;

        raceTextField = new JTextField();
        raceTextField.setColumns(10);
        raceTextField.putClientProperty("id", "race");
        raceTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 6;
        gridBag.setConstraints(raceTextField, cons);
        this.add(raceTextField);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        
        
        
        // Row 2
        row = 2;
        pos = 0;

        // Com
        JLabel comLbl = new JLabel("Com");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(comLbl, cons);
        this.add(comLbl);
        pos += cons.gridwidth;

        comTextField = new JTextField();
        comTextField.setColumns(3);
        comTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(comTextField, cons);
        this.add(comTextField);
        pos += cons.gridwidth;

        // Mag
        JLabel magLbl = new JLabel("Mag");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(magLbl, cons);
        this.add(magLbl);
        pos += cons.gridwidth;

        magTextField = new JTextField();
        magTextField.setColumns(3);
        magTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(magTextField, cons);
        this.add(magTextField);
        pos += cons.gridwidth;

        // Ste
        JLabel steLbl = new JLabel("Ste");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(steLbl, cons);
        this.add(steLbl);
        pos += cons.gridwidth;

        steTextField = new JTextField();
        steTextField.setColumns(3);
        steTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(steTextField, cons);
        this.add(steTextField);
        pos += cons.gridwidth;

        // Gen
        JLabel genLbl = new JLabel("Gen");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genLbl, cons);
        this.add(genLbl);
        pos += cons.gridwidth;

        genTextField = new JTextField();
        genTextField.setColumns(3);
        genTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genTextField, cons);
        this.add(genTextField);
        pos += cons.gridwidth;

        // Row 3
        row = 3;
        pos = 0;
        // Level
        JLabel levelLbl = new JLabel("Level");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(levelLbl, cons);
        this.add(levelLbl);
        pos += cons.gridwidth;

        // Level Spinner
        levelModel = new SpinnerNumberModel(1, // initial
                // value
                0, // min
                999, // max
                1); // step

        levelSpinner = new JSpinner(levelModel);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(levelSpinner, cons);
        this.add(levelSpinner);
        pos += cons.gridwidth;

        // Action Points
        JLabel apLbl = new JLabel("Action Points");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(apLbl, cons);
        this.add(apLbl);
        pos += cons.gridwidth;

        actionPointsTextField = new JTextField();
        actionPointsTextField.setColumns(3);
        actionPointsTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(actionPointsTextField, cons);
        this.add(actionPointsTextField);
        pos += cons.gridwidth;

        // Health
        JLabel healthLbl = new JLabel("Health");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(healthLbl, cons);
        this.add(healthLbl);
        pos += cons.gridwidth;

        healthTextField = new JTextField();
        healthTextField.setColumns(3);
        healthTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(healthTextField, cons);
        this.add(healthTextField);
        pos += cons.gridwidth;

        // Mana
        JLabel manaLbl = new JLabel("Mana");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(manaLbl, cons);
        this.add(manaLbl);
        pos += cons.gridwidth;

        manaTextField = new JTextField();
        manaTextField.setColumns(3);
        manaTextField.setEditable(false);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(manaTextField, cons);
        this.add(manaTextField);
        pos += cons.gridwidth;

        // Row 4
        row = 4;
        pos = 0;

        // Size
        JLabel sizeLbl = new JLabel("Size");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(sizeLbl, cons);
        this.add(sizeLbl);
        pos += cons.gridwidth;

        // Size List
        sizeComboBox = new JComboBox();
        for (String aSize : sizes) {
            sizeComboBox.addItem(aSize);
        }
        sizeComboBox.setSelectedIndex(0);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 3;
        gridBag.setConstraints(sizeComboBox, cons);
        this.add(sizeComboBox);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Gender
        JLabel genderLbl = new JLabel("Gender");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genderLbl, cons);
        this.add(genderLbl);
        pos += cons.gridwidth;

        // Gender List
        genderComboBox = new JComboBox();
        for (String aGender : genders) {
            genderComboBox.addItem(aGender);
        }
        genderComboBox.setSelectedIndex(0);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 3;
        gridBag.setConstraints(genderComboBox, cons);
        this.add(genderComboBox);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

    }

    /**
     * Handle changes in text fields.
     * 
     * @author bruins
     * 
     */
    private class BasicTextFieldListener implements ActionListener {

        /**
         * Handle changes in text fields when user hits return.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nameTextField) {
                pc.setName(nameTextField.getText());
            } else if (e.getSource() == descriptionTextField) {
                pc.setDescription(descriptionTextField.getText());
            }
        }

    }

    /**
     * Handle changes in text fields.
     * 
     * @author bruins
     * 
     */
    private class BasicTextFieldFocusEvent implements FocusListener {

        @Override
        public void focusGained(FocusEvent arg0) {
            // No action

        }

        /**
         * Handle changes in text fields. Changes are processed when field loses
         * focus. No need to updateForm as changes are already visible.
         */
        @Override
        public void focusLost(FocusEvent e) {
            Object component = e.getComponent();
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (textField == nameTextField) {
                    pc.setName(nameTextField.getText());
                } else if (textField == descriptionTextField) {
                    pc.setDescription(descriptionTextField.getText());
                }
            }
        }
    }

    // Listens to multiple form elements
    private class BasicItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent evt) {
            Object source = evt.getSource();
            if (source instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) source;

                if (evt.getStateChange() == ItemEvent.SELECTED) {

                    // Class
                    if (comboBox == classComboBox) {
                        PcClass pcClass = (PcClass) comboBox.getSelectedItem();
                        pc.setPcClass(pcClass);
                    }

                    // Size
                    else if (comboBox == sizeComboBox) {
                        String newSize = (String) comboBox.getSelectedItem();
                        if (!newSize.equals(pc.getSize())) {
                            pc.setSize(newSize);
                        }
                    }

                    // Gender
                    else if (comboBox == genderComboBox) {
                        pc.setGender((String) comboBox.getSelectedItem());
                    }

                }
            }
        }
    }

    private void updateForm() {
        if (pc != null) {

            // TODO only change values if required.
            // e.g. don't trigger needless change events.
            // Fields
            // Row 0
            nameTextField.setText(pc.getName());
            classComboBox.setSelectedItem(pc.getPcClass());
            // Row 1
            descriptionTextField.setText(pc.getDescription());
            raceTextField.setText(pc.getRace());
            // Row 2
            comTextField.setText(Integer.toString(pc.getCombatDice()));
            magTextField.setText(Integer.toString(pc.getMagicDice()));
            steTextField.setText(Integer.toString(pc.getStealthDice()));
            genTextField.setText(Integer.toString(pc.getGeneralDice()));
            // Row 3
            levelModel.setValue(pc.getLevel());
            actionPointsTextField
                    .setText(Integer.toString(pc.getActionPoints()));
            healthTextField.setText(Integer.toString(pc.getHealth()));
            manaTextField.setText(Integer.toString(pc.getMana()));
            // Row 4
            sizeComboBox.setSelectedItem(pc.getSize());
            genderComboBox.setSelectedItem(pc.getGender());
        }
    }

}
