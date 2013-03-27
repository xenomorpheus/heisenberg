package au.net.hal9000.heisenberg.worldeditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class BasicPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JComboBox raceComboBox;
    private JTextField nameTextField;
    private JTextField comTextField;
    private JTextField steTextField;
    private JTextField magTextField;
    private JTextField genTextField;
    private JTextField actionPointsTextField;
    private JTextField healthTextField;
    private JTextField manaTextField;
    private JComboBox classComboBox;
    private JComboBox sizeComboBox;
    private JComboBox genderComboBox;
    private JSpinner levelSpinner;
    private int pcLevel;

    public BasicPanel(Configuration config) {
        super();

        addComponents(config);

        // Add change hander
        BasicItemListener basicItemListener = new BasicItemListener();
        classComboBox.addItemListener(basicItemListener);
        sizeComboBox.addItemListener(basicItemListener);
        genderComboBox.addItemListener(basicItemListener);
        raceComboBox.addItemListener(basicItemListener);

        // Listen for changes to level
        levelSpinner.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                SpinnerModel levelModel = levelSpinner.getModel();
                if (levelModel instanceof SpinnerNumberModel) {
                    pcLevel = Integer
                            .parseInt(((SpinnerNumberModel) levelModel)
                                    .getValue().toString());
                    // TODO pc.setLevel(pcLevel);
                    // showDetails();
                }
            }
        });

    }

    private void addComponents(Configuration config) {
        Collection<String> races = config.getRaces();
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
        // Com
        JLabel comLbl = new JLabel("Com");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(comLbl, cons);
        this.add(comLbl);
        pos += cons.gridwidth;

        comTextField = new JTextField();
        comTextField.setColumns(3);
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
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genTextField, cons);
        this.add(genTextField);
        pos += cons.gridwidth;

        // Row 2
        row = 2;
        pos = 0;
        // Race
        JLabel raceLbl = new JLabel("Race");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(raceLbl, cons);
        this.add(raceLbl);
        pos += cons.gridwidth;

        raceComboBox = new JComboBox();
        for (String raceString : races) {
            PcRace item = (PcRace) Factory.createItem(raceString);
            // TODO remove - these are only for testing.
            item.skillsAdd(new String[] { "skill1", "skill2", "skill3" });
            item.powerWordsAdd(new String[] { "powerWord1", "powerWord2",
                    "powerWord3" });
            item.recipesAdd("recipe1");
            raceComboBox.addItem(item);
        }

        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 3;
        gridBag.setConstraints(raceComboBox, cons);
        this.add(raceComboBox);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

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
        SpinnerModel levelModel = new SpinnerNumberModel(1, // initial
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
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(manaTextField, cons);
        this.add(manaTextField);
        pos += cons.gridwidth;

    }

    // Listens to multiple form elements
    private class BasicItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                JComboBox comboBox = (JComboBox) evt.getSource();

                // Class
                if (comboBox == classComboBox) {
                    PcClass pcClass = (PcClass) comboBox.getSelectedItem();
                    // TODO pc.setPcClass(pcClass);
                }

                // Size
                if (comboBox == sizeComboBox) {
                    // TODO pc.setSize((String) comboBox.getSelectedItem());
                }

                // Gender
                if (comboBox == genderComboBox) {
                    // TODO pc.setGender((String) comboBox.getSelectedItem());
                }

                // Race
                if (comboBox == raceComboBox) {
                    PcRace raceNew = (PcRace) raceComboBox.getSelectedItem();
                    // TODO if (!pc.equals(raceNew)) {
                    // TODO raceNew.setAllFrom(pc);
                    // TODO pc = raceNew;
                    // TODO }
                }

                // Show results
                // TODO showDetails();
            }
        }
    }

}
