package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;
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

/** Basic panel. */
public class BasicPanel extends JPanel {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  // Misc
  /** Field characterSheet. */
  private CharacterSheet characterSheet;

  /** Field config. */
  private Configuration config = Configuration.lastConfig();

  // Fields
  // Row 0
  /** Field nameTextField. */
  private JTextField nameTextField;

  /** Field classComboBox. */
  private JComboBox<PcClass> classComboBox;

  // Row 1
  /** Field descriptionTextField. */
  private JTextField descriptionTextField;

  /** Field speciesComboBox. */
  private JComboBox<String> speciesComboBox;

  // Row 2
  /** Field comTextField. */
  private JTextField comTextField;

  /** Field magTextField. */
  private JTextField magTextField;

  /** Field steTextField. */
  private JTextField steTextField;

  /** Field genTextField. */
  private JTextField genTextField;

  // Row 3
  /** Field levelSpinner. */
  private JSpinner levelSpinner;

  /** Field levelModel. */
  private SpinnerModel levelModel;

  /** Field actionPointsTextField. */
  private JTextField actionPointsTextField;

  /** Field healthTextField. */
  private JTextField healthTextField;

  /** Field manaTextField. */
  private JTextField manaTextField;

  // Row 4
  /** Field sizeComboBox. */
  private JComboBox<String> sizeComboBox;

  /** Field genderComboBox. */
  private JComboBox<String> genderComboBox;

  /** Constructor for BasicPanel. */
  public BasicPanel() {
    super();

    addComponents();

    // Add change handler
    BasicTextFieldListener basicTextFieldListener = new BasicTextFieldListener();
    BasicTextFieldFocusEvent basicTextFieldFocusEvent = new BasicTextFieldFocusEvent();
    nameTextField.addActionListener(basicTextFieldListener);
    nameTextField.addFocusListener(basicTextFieldFocusEvent);
    descriptionTextField.addActionListener(basicTextFieldListener);
    descriptionTextField.addFocusListener(basicTextFieldFocusEvent);

    BasicItemListener basicItemListener = new BasicItemListener();
    classComboBox.addItemListener(basicItemListener);
    speciesComboBox.addItemListener(basicItemListener);
    sizeComboBox.addItemListener(basicItemListener);
    genderComboBox.addItemListener(basicItemListener);

    // Listen for changes to PC's level
    levelSpinner.addChangeListener(
        new ChangeListener() {

          public void stateChanged(ChangeEvent e) {
            SpinnerModel levelModel = levelSpinner.getModel();
            if (levelModel instanceof SpinnerNumberModel) {
              int newPcLevel =
                  Integer.parseInt(((SpinnerNumberModel) levelModel).getValue().toString());
              characterSheet.setLevel(newPcLevel);
            }
          }
        });
  }

  /**
   * Set the CharacterSheet object to show values for.
   *
   * @param characterSheet the CharacterSheet object to show values for. Note we pass the
   *     CharacterSheet rather than the values needed to do the display. We do this because the
   *     values to display may be changed by other tabs, and passing by pc allows a refresh of
   *     values.
   */
  public void setCharacterSheet(final CharacterSheet characterSheet) {
    this.characterSheet = characterSheet;
    updateForm();
  }

  /** Add components to Basic panel. */
  private void addComponents() {

    // Use the GridBagConstraints to determine how the component
    // is displayed within the GridBagLayout. ipadx and ipady are used
    // to pad the component within the cell(s). The weightx and weighty
    // are used to give a weighting to the component to specify how the
    // any extra space is handled by a row or column. gridx and gridy are
    // the position coordinates for the component within the grid. fill
    // determines what happens to sizing of a component if it doesn't
    // completely fill its cell. gridwidth and gridheight specify how many
    // cells (either in a row or column) the component will take up.
    GridBagConstraints cons = new GridBagConstraints();
    cons.fill = GridBagConstraints.BOTH;
    cons.ipady = 0;
    cons.ipadx = 0;

    // create GridBagLayout and the GridBagLayout Constraints
    GridBagLayout gridBag = new GridBagLayout();
    setLayout(gridBag);
    row0(gridBag, cons);
    row1(gridBag, cons);
    row2(gridBag, cons);
    row3(gridBag, cons);
    row4(gridBag, cons);
  }

  /**
   * A row of the UI.
   *
   * @param gridBag the gridBag layout.
   * @param cons cell constraints.
   */
  private void row0(GridBagLayout gridBag, GridBagConstraints cons) {

    final int row = 0;
    int pos = 0;
    // Name
    JLabel nameLbl = new JLabel("Name");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(nameLbl, cons);
    add(nameLbl);
    pos += cons.gridwidth;

    nameTextField = new JTextField();
    nameTextField.setColumns(10);
    nameTextField.putClientProperty("id", "name");
    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 6;
    gridBag.setConstraints(nameTextField, cons);
    add(nameTextField);
    pos += cons.gridwidth;
    cons.gridwidth = 1;

    // Class
    JLabel classLbl = new JLabel("Class");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(classLbl, cons);
    add(classLbl);
    pos += cons.gridwidth;

    classComboBox = new JComboBox<>();
    Collection<PcClass> pcClassesItr = config.getPcClasses().values();
    for (var pcClass : pcClassesItr) {
      classComboBox.addItem(pcClass);
    }

    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 4;
    gridBag.setConstraints(classComboBox, cons);
    add(classComboBox);
    cons.gridwidth = 1;
    pos += cons.gridwidth;
  }

  /**
   * A row of the UI.
   *
   * @param gridBag the gridBag layout.
   * @param cons cell constraints.
   */
  private void row1(GridBagLayout gridBag, GridBagConstraints cons) {

    // Row 1
    final int row = 1;
    int pos = 0;

    // Description
    JLabel descriptionLbl = new JLabel("Description");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(descriptionLbl, cons);
    add(descriptionLbl);
    pos += cons.gridwidth;

    descriptionTextField = new JTextField();
    descriptionTextField.setColumns(10);
    descriptionTextField.putClientProperty("id", "description");
    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 6;
    gridBag.setConstraints(descriptionTextField, cons);
    add(descriptionTextField);
    pos += cons.gridwidth;
    cons.gridwidth = 1;

    // Species
    JLabel speciesLbl = new JLabel("Species");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(speciesLbl, cons);
    add(speciesLbl);
    pos += cons.gridwidth;

    speciesComboBox = new JComboBox<>();
    Collection<String> speciesItr = config.getSpecies();
    for (var name : speciesItr) {
      speciesComboBox.addItem(name);
    }

    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 6;
    gridBag.setConstraints(speciesComboBox, cons);
    add(speciesComboBox);
    pos += cons.gridwidth;
    cons.gridwidth = 1;
  }

  /**
   * A row of the UI.
   *
   * @param gridBag the gridBag layout.
   * @param cons cell constraints.
   */
  private void row2(GridBagLayout gridBag, GridBagConstraints cons) {

    // Row 2
    final int row = 2;
    int pos = 0;

    // Com
    JLabel comLbl = new JLabel("Com");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(comLbl, cons);
    add(comLbl);
    pos += cons.gridwidth;

    comTextField = new JTextField();
    comTextField.setColumns(3);
    comTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(comTextField, cons);
    add(comTextField);
    pos += cons.gridwidth;

    // Mag
    JLabel magLbl = new JLabel("Mag");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(magLbl, cons);
    add(magLbl);
    pos += cons.gridwidth;

    magTextField = new JTextField();
    magTextField.setColumns(3);
    magTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(magTextField, cons);
    add(magTextField);
    pos += cons.gridwidth;

    // Ste
    JLabel steLbl = new JLabel("Ste");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(steLbl, cons);
    add(steLbl);
    pos += cons.gridwidth;

    steTextField = new JTextField();
    steTextField.setColumns(3);
    steTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(steTextField, cons);
    add(steTextField);
    pos += cons.gridwidth;

    // Gen
    JLabel genLbl = new JLabel("Gen");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(genLbl, cons);
    add(genLbl);
    pos += cons.gridwidth;

    genTextField = new JTextField();
    genTextField.setColumns(3);
    genTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(genTextField, cons);
    add(genTextField);
    pos += cons.gridwidth;
  }

  /**
   * A row of the UI.
   *
   * @param gridBag the gridBag layout.
   * @param cons cell constraints.
   */
  private void row3(GridBagLayout gridBag, GridBagConstraints cons) {

    // Row 3
    final int row = 3;
    int pos = 0;
    // Level
    JLabel levelLbl = new JLabel("Level");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(levelLbl, cons);
    add(levelLbl);
    pos += cons.gridwidth;

    // Level Spinner
    levelModel =
        new SpinnerNumberModel(
            1, // initial
            // value
            0, // min
            999, // max
            1); // step

    levelSpinner = new JSpinner(levelModel);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(levelSpinner, cons);
    add(levelSpinner);
    pos += cons.gridwidth;

    // Action Points
    JLabel apLbl = new JLabel("Action Points");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(apLbl, cons);
    add(apLbl);
    pos += cons.gridwidth;

    actionPointsTextField = new JTextField();
    actionPointsTextField.setColumns(3);
    actionPointsTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(actionPointsTextField, cons);
    add(actionPointsTextField);
    pos += cons.gridwidth;

    // Health
    JLabel healthLbl = new JLabel("Health");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(healthLbl, cons);
    add(healthLbl);
    pos += cons.gridwidth;

    healthTextField = new JTextField();
    healthTextField.setColumns(3);
    healthTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(healthTextField, cons);
    add(healthTextField);
    pos += cons.gridwidth;

    // Mana
    JLabel manaLbl = new JLabel("Mana");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(manaLbl, cons);
    add(manaLbl);
    pos += cons.gridwidth;

    manaTextField = new JTextField();
    manaTextField.setColumns(3);
    manaTextField.setEditable(false);
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(manaTextField, cons);
    add(manaTextField);
    pos += cons.gridwidth;
  }

  /**
   * A row of the UI.
   *
   * @param gridBag the gridBag layout.
   * @param cons cell constraints.
   */
  private void row4(GridBagLayout gridBag, GridBagConstraints cons) {
    final int row = 4;
    int pos = 0;

    // Size
    JLabel sizeLbl = new JLabel("Size");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(sizeLbl, cons);
    add(sizeLbl);
    pos += cons.gridwidth;

    // Size List
    sizeComboBox = new JComboBox<>();
    Collection<String> sizes = config.getSizes();
    for (var name : sizes) {
      sizeComboBox.addItem(name);
    }
    sizeComboBox.setSelectedIndex(0);
    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 3;
    gridBag.setConstraints(sizeComboBox, cons);
    add(sizeComboBox);
    pos += cons.gridwidth;
    cons.gridwidth = 1;

    // Gender
    JLabel genderLbl = new JLabel("Gender");
    cons.gridx = pos;
    cons.gridy = row;
    gridBag.setConstraints(genderLbl, cons);
    add(genderLbl);
    pos += cons.gridwidth;

    // Gender List
    genderComboBox = new JComboBox<>();
    Collection<String> genders = config.getGenders();
    for (var gender : genders) {
      genderComboBox.addItem(gender);
    }
    genderComboBox.setSelectedIndex(0);
    cons.gridx = pos;
    cons.gridy = row;
    cons.gridwidth = 3;
    gridBag.setConstraints(genderComboBox, cons);
    add(genderComboBox);
    pos += cons.gridwidth;
    cons.gridwidth = 1;
  }

  /** Handle changes in text fields. */
  private class BasicTextFieldListener implements ActionListener {

    /**
     * Handle changes in text fields when user hits return.
     *
     * @param e ActionEvent
     * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == nameTextField) {
        characterSheet.setName(nameTextField.getText());
      } else if (e.getSource() == descriptionTextField) {
        characterSheet.setDescription(descriptionTextField.getText());
      }
    }
  }

  /** Handle changes in text fields. */
  private class BasicTextFieldFocusEvent implements FocusListener {

    /**
     * Method focusGained.
     *
     * @param arg0 FocusEvent
     * @see java.awt.event.FocusListener#focusGained(FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent arg0) {
      // No action

    }

    /**
     * Handle changes in text fields. Changes are processed when field loses focus. No need to
     * updateForm as changes are already visible.
     *
     * @param e FocusEvent
     * @see java.awt.event.FocusListener#focusLost(FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      Object component = e.getComponent();
      if (component instanceof JTextField) {
        JTextField textField = (JTextField) component;
        if (textField == nameTextField) {
          characterSheet.setName(nameTextField.getText());
        } else if (textField == descriptionTextField) {
          characterSheet.setDescription(descriptionTextField.getText());
        }
      }
    }
  }

  /** Listens to multiple form elements. */
  private class BasicItemListener implements ItemListener {

    /**
     * Method itemStateChanged.
     *
     * @param evt ItemEvent
     * @see java.awt.event.ItemListener#itemStateChanged(ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent evt) {
      boolean updateForm = false;
      Object source = evt.getSource();
      if (source instanceof JComboBox) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {

          if (source == classComboBox) { // Class
            PcClass newPcClass = (PcClass) classComboBox.getSelectedItem();
            if (!newPcClass.equals(characterSheet.getPcClass())) {
              characterSheet.setPcClass(newPcClass);
              updateForm = true;
            }
          } else if (source == speciesComboBox) { // Species
            String newSpecies = (String) speciesComboBox.getSelectedItem();
            if (!newSpecies.equals(characterSheet.getSpecies())) {
              characterSheet.setSpecies(newSpecies);
            }
          } else if (source == sizeComboBox) { // Size
            String newSize = (String) sizeComboBox.getSelectedItem();
            if (!newSize.equals(characterSheet.getSize())) {
              characterSheet.setSize(newSize);
            }
          } else if (source == genderComboBox) { // Gender
            String newGender = (String) genderComboBox.getSelectedItem();
            if (!newGender.equals(characterSheet.getGender())) {
              characterSheet.setGender(newGender);
            }
          }
        }
      }
      if (updateForm) {
        updateForm();
      }
    }
  }

  /** update the form. */
  private void updateForm() {
    if (null != characterSheet) {

      // TODO only change values if required.
      // e.g. don't trigger needless change events.
      // Fields
      // Row 0
      nameTextField.setText(characterSheet.getName());
      classComboBox.setSelectedItem(characterSheet.getPcClass());
      // Row 1
      descriptionTextField.setText(characterSheet.getDescription());
      speciesComboBox.setSelectedItem(characterSheet.getSpecies());
      // Row 2
      comTextField.setText(Integer.toString(characterSheet.getPcClass().getCombatDice()));
      magTextField.setText(Integer.toString(characterSheet.getPcClass().getMagicDice()));
      steTextField.setText(Integer.toString(characterSheet.getPcClass().getStealthDice()));
      genTextField.setText(Integer.toString(characterSheet.getPcClass().getGeneralDice()));
      // Row 3
      levelModel.setValue(characterSheet.getLevel());
      actionPointsTextField.setText(
          Integer.toString(characterSheet.getPcClass().getActionPoints()));
      healthTextField.setText(Integer.toString(characterSheet.getPcClass().getHealth()));
      manaTextField.setText(Integer.toString(characterSheet.getPcClass().getMana()));
      // Row 4
      sizeComboBox.setSelectedItem(characterSheet.getSize());
      genderComboBox.setSelectedItem(characterSheet.getGender());
    }
  }
}
