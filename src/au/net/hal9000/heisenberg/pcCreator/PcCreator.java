package au.net.hal9000.heisenberg.pcCreator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import nu.xom.ValidityException;

import au.net.hal9000.heisenberg.pc.CharacterSheet;
import au.net.hal9000.heisenberg.pc.PcClass;
import au.net.hal9000.heisenberg.util.Configuration;

import java.io.IOException;
import java.util.Iterator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

public class PcCreator {

	private Configuration config;
	private CharacterSheet characterSheet;
	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldCom;
	private JTextField textFieldSte;
	private JTextField textFieldMag;
	private JTextField textFieldGen;
	private JTextField textFieldLevel;

	// TODO make more generic when it listen to other items.
	private class MyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				JComboBox comboBox = (JComboBox) evt.getSource();
				PcClass pcClass = (PcClass) comboBox.getSelectedItem();
				if ( characterSheet == null){
					characterSheet = new CharacterSheet(pcClass);
				}
				else{
					characterSheet.setPcClass(pcClass);
				}
				showDice();
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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

		// Level
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(6, 75, 61, 16);
		frame.getContentPane().add(lblLevel);

		textFieldLevel = new JTextField();
		textFieldLevel.setBounds(79, 72, 29, 28);
		frame.getContentPane().add(textFieldLevel);
		textFieldLevel.setColumns(10);

		// Combat Dice
		JLabel lblCom = new JLabel("Com");
		lblCom.setBounds(6, 109, 40, 16);
		frame.getContentPane().add(lblCom);

		textFieldCom = new JTextField();
		textFieldCom.setEditable(false);
		textFieldCom.setBounds(79, 103, 40, 28);
		frame.getContentPane().add(textFieldCom);
		textFieldCom.setColumns(10);

		// Stealth Dice
		JLabel lblSte = new JLabel("Ste");
		lblSte.setBounds(6, 137, 40, 16);
		frame.getContentPane().add(lblSte);

		textFieldSte = new JTextField();
		textFieldSte.setEditable(false);
		textFieldSte.setColumns(10);
		textFieldSte.setBounds(79, 131, 40, 28);
		frame.getContentPane().add(textFieldSte);

		// Magic Dice
		JLabel lblMag = new JLabel("Mag");
		lblMag.setBounds(140, 109, 40, 16);
		frame.getContentPane().add(lblMag);

		textFieldMag = new JTextField();
		textFieldMag.setEditable(false);
		textFieldMag.setColumns(10);
		textFieldMag.setBounds(206, 103, 40, 28);
		frame.getContentPane().add(textFieldMag);

		// General Dice
		JLabel lblGen = new JLabel("Gen");
		lblGen.setBounds(140, 137, 40, 16);
		frame.getContentPane().add(lblGen);

		textFieldGen = new JTextField();
		textFieldGen.setEditable(false);
		textFieldGen.setColumns(10);
		textFieldGen.setBounds(206, 131, 40, 28);
		frame.getContentPane().add(textFieldGen);

 		// Name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 12, 61, 16);
		frame.getContentPane().add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(79, 6, 205, 28);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		// PC Class
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(6, 40, 61, 16);
		frame.getContentPane().add(lblClass);

		JComboBox classChoice = new JComboBox();

		// Add change hander
		classChoice.addItemListener(new MyItemListener());
		// Load choices
		classChoice.setBounds(79, 40, 205, 26);
		Iterator<PcClass> pcClassesItr = config.getPcClasses().values()
				.iterator();
		while (pcClassesItr.hasNext()) {
			classChoice.addItem(pcClassesItr.next());
		}

		frame.getContentPane().add(classChoice);

	}
	
	private void showDice() {
        if (characterSheet != null){
        	textFieldCom.setText(Integer.toString(characterSheet.getCombatDice()));
        	textFieldSte.setText(Integer.toString(characterSheet.getStealthDice()));
        	textFieldMag.setText(Integer.toString(characterSheet.getMagicDice()));
        	textFieldGen.setText(Integer.toString(characterSheet.getGeneralDice()));
        }
	}

}
