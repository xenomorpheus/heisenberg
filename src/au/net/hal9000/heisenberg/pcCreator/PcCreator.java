package au.net.hal9000.heisenberg.pcCreator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import nu.xom.ValidityException;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

import java.io.IOException;
import java.util.Collection;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PcCreator {

	private Configuration config;
	private PcRace pc;
	private JFrame frame;

	// tab - basic
	private JComboBox raceComboBox;
	private JTextField nameTextField;
	private JTextField comTextField;
	private JTextField steTextField;
	private JTextField magTextField;
	private JTextField genTextField;
	private JTextField ActionPointsTextField;
	private JTextField healthTextField;
	private JTextField manaTextField;
	private JComboBox classComboBox;
	private JComboBox sizeComboBox;
	private JComboBox genderComboBox;
	private JSpinner levelSpinner;

	// tab - description
	JTextArea descriptionTextArea;

	// Listens to multiple form elements
	private class BasicItemListener implements ItemListener {

		public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				JComboBox comboBox = (JComboBox) evt.getSource();

				// Class
				if (comboBox == classComboBox) {
					PcRace pcClass = (PcRace) comboBox.getSelectedItem();
					pc.setPcClass(pcClass);
				}

				// Size
				if (comboBox == sizeComboBox) {
					pc.setSize((String) comboBox.getSelectedItem());
				}

				// Gender
				if (comboBox == genderComboBox) {
					pc.setGender((String) comboBox.getSelectedItem());
				}

				// Race
				if (comboBox == raceComboBox) {
					PcRace raceNew = (PcRace) raceComboBox.getSelectedItem();
					if (!pc.equals(raceNew)) {
						raceNew.setAllFrom(pc);
						pc = raceNew;
					}
				}
				
				// Show results
				showDetails();
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PcCreator window = new PcCreator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PcCreator() {
		initialise();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {

		frame = new JFrame();
		frame.setBounds(100, 100, 624, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		try {
			config = new Configuration("test/config/config.xml");
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 612, 592);

		tabbedPane.addTab("Basic", null, basicPanel(), null);
		tabbedPane.addTab("Description", null, descriptionPanel(), null);

		frame.getContentPane().add(tabbedPane);
	}

	private JPanel descriptionPanel() {

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
				descriptionTextArea.setText(pc.description());
			}
		});
		JScrollPane sbrText = new JScrollPane();
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		descriptionPanel.add(sbrText);
		return descriptionPanel;
	}

	private JPanel basicPanel() {
		JPanel pcClassPanel = new JPanel();
		pcClassPanel.setLayout(null);

		// race list
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(8, 77, 36, 16);
		pcClassPanel.add(lblRace);

		raceComboBox = new JComboBox();
		raceComboBox.setBounds(49, 73, 164, 27);
		pcClassPanel.add(raceComboBox);

		Collection<String> races = config.getRaces();
		for (String raceString : races) {
			Item item = Factory.createItem(raceString);
			raceComboBox.addItem(item);
		}

		// class (PcClass) list
		JLabel classLbl = new JLabel("Class");
		classLbl.setBounds(349, 6, 34, 27);
		pcClassPanel.add(classLbl);

		classComboBox = new JComboBox();
		classComboBox.setBounds(421, 7, 164, 27);
		pcClassPanel.add(classComboBox);

		Collection<PcRace> pcClassesItr = config.getPcClasses().values();
		for (PcRace pcClass : pcClassesItr) {
			classComboBox.addItem(pcClass);
		}

		// name
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setBounds(8, 11, 36, 16);
		pcClassPanel.add(nameLbl);

		nameTextField = new JTextField();
		nameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				pc.setName(nameTextField.getText());
			}
		});
		nameTextField.setBounds(49, 5, 288, 28);
		pcClassPanel.add(nameTextField);
		nameTextField.setColumns(10);

		// size
		JLabel sizeLabel = new JLabel("Size");
		sizeLabel.setBounds(218, 77, 29, 16);
		pcClassPanel.add(sizeLabel);

		sizeComboBox = new JComboBox();
		sizeComboBox.setBounds(244, 73, 113, 27);
		pcClassPanel.add(sizeComboBox);

		// gender
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setBounds(369, 77, 54, 16);
		pcClassPanel.add(genderLabel);

		genderComboBox = new JComboBox();
		genderComboBox.setBounds(421, 73, 164, 27);
		pcClassPanel.add(genderComboBox);

		// Com
		JLabel comLabel = new JLabel("Com");
		comLabel.setBounds(8, 45, 29, 16);
		pcClassPanel.add(comLabel);

		comTextField = new JTextField();
		comTextField.setToolTipText("Combat Dice");
		comTextField.setEditable(false);
		comTextField.setColumns(10);
		comTextField.setBounds(49, 39, 36, 28);
		pcClassPanel.add(comTextField);

		// Ste
		JLabel steLabel = new JLabel("Ste");
		steLabel.setBounds(281, 45, 19, 16);
		pcClassPanel.add(steLabel);

		steTextField = new JTextField();
		steTextField.setToolTipText("Stealth Dice");
		steTextField.setEditable(false);
		steTextField.setColumns(10);
		steTextField.setBounds(328, 39, 36, 28);
		pcClassPanel.add(steTextField);

		// Mag
		JLabel magLabel = new JLabel("Mag");
		magLabel.setBounds(147, 45, 26, 16);
		pcClassPanel.add(magLabel);

		magTextField = new JTextField();
		magTextField.setToolTipText("Magic Dice");
		magTextField.setEditable(false);
		magTextField.setColumns(10);
		magTextField.setBounds(185, 39, 32, 28);
		pcClassPanel.add(magTextField);

		// Gen
		JLabel genLabel = new JLabel("Gen");
		genLabel.setBounds(513, 46, 24, 16);
		pcClassPanel.add(genLabel);

		genTextField = new JTextField();
		genTextField.setToolTipText("General Dice");
		genTextField.setEditable(false);
		genTextField.setColumns(10);
		genTextField.setBounds(549, 39, 36, 28);
		pcClassPanel.add(genTextField);

		// Action Points
		JLabel actionPointsLabel = new JLabel("Action Points");
		actionPointsLabel.setBounds(129, 112, 84, 16);
		pcClassPanel.add(actionPointsLabel);

		ActionPointsTextField = new JTextField();
		ActionPointsTextField.setEditable(false);
		ActionPointsTextField.setBounds(218, 106, 29, 28);
		pcClassPanel.add(ActionPointsTextField);
		ActionPointsTextField.setColumns(10);

		// Health
		JLabel healthLabel = new JLabel("Health");
		healthLabel.setBounds(269, 112, 47, 16);
		pcClassPanel.add(healthLabel);

		healthTextField = new JTextField();
		healthTextField.setEditable(false);
		healthTextField.setColumns(10);
		healthTextField.setBounds(328, 106, 36, 28);
		pcClassPanel.add(healthTextField);

		// Mana
		JLabel manaLabel = new JLabel("Mana");
		manaLabel.setBounds(501, 112, 36, 16);
		pcClassPanel.add(manaLabel);

		manaTextField = new JTextField();
		manaTextField.setEditable(false);
		manaTextField.setColumns(10);
		manaTextField.setBounds(549, 106, 36, 28);
		pcClassPanel.add(manaTextField);

		// Level Spinner
		SpinnerModel levelModel = new SpinnerNumberModel(1, // initial
				// value
				0, // min
				999, // max
				1); // step

		levelSpinner = new JSpinner(levelModel);

		// Listen for changes to level
		levelSpinner.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				SpinnerModel levelModel = levelSpinner.getModel();
				if (levelModel instanceof SpinnerNumberModel) {
					if (pc != null) {
						int pcLevel = Integer
								.parseInt(((SpinnerNumberModel) levelModel)
										.getValue().toString());
						pc.setLevel(pcLevel);
						showDetails();
					}
				}
			}
		});

		levelSpinner.setBounds(49, 106, 54, 28);
		pcClassPanel.add(levelSpinner);

		JLabel levelLabel = new JLabel("Level");
		levelLabel.setBounds(8, 112, 34, 16);
		pcClassPanel.add(levelLabel);

		// Size List
		sizeComboBox.addItem(new String("Small"));
		sizeComboBox.addItem(new String("Medium"));
		sizeComboBox.addItem(new String("Large"));
		sizeComboBox.setSelectedIndex(1);

		// Gender List
		Collection<String> genders = config.getGenders();
		for (String aGender : genders) {
			genderComboBox.addItem(aGender);
		}

		// Add change hander
		BasicItemListener basicItemListener = new BasicItemListener();
		classComboBox.addItemListener(basicItemListener);
		sizeComboBox.addItemListener(basicItemListener);
		genderComboBox.addItemListener(basicItemListener);
		raceComboBox.addItemListener(basicItemListener);

		// Set some initial values
		pc = (PcRace) raceComboBox.getSelectedItem();
		pc.setPcClass((PcRace) classComboBox.getSelectedItem());
		pc.setSize((String) sizeComboBox.getSelectedItem());
		pc.setGender((String) genderComboBox.getSelectedItem());
		int pcLevel = Integer.parseInt(((SpinnerNumberModel) levelModel)
				.getValue().toString());
		pc.setLevel(pcLevel);

		// Show values
		showDetails();

		return pcClassPanel;
	}

	private void showDetails() {
		if (pc != null) {

			comTextField.setText(Integer.toString(pc.getCombatDice()));
			steTextField.setText(Integer.toString(pc.getStealthDice()));
			magTextField.setText(Integer.toString(pc.getMagicDice()));
			genTextField.setText(Integer.toString(pc.getGeneralDice()));

			ActionPointsTextField
					.setText(Integer.toString(pc.getActionPoints()));
			healthTextField.setText(Integer.toString(pc.getHealth()));
			manaTextField.setText(Integer.toString(pc.getMana()));
		}
	}
}
