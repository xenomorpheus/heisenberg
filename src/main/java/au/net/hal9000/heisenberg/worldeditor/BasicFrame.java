package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import nu.xom.ValidityException;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class BasicFrame {
    JFrame guiFrame;

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Configuration config = null;
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

                new BasicFrame(config);
            }
        });

    }

    public BasicFrame(Configuration config) {

        guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("GridBagLayout Example");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

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

        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(gridBag);

        int row = 0;
        int pos = 0;
        // Name
        JLabel nameLbl = new JLabel("Name");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(nameLbl, cons);
        basicPanel.add(nameLbl);
        pos += cons.gridwidth;

        JTextField nameTextField = new JTextField();
        nameTextField.setColumns(10);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 6;
        gridBag.setConstraints(nameTextField, cons);
        basicPanel.add(nameTextField);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Class
        JLabel classLbl = new JLabel("Class");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(classLbl, cons);
        basicPanel.add(classLbl);
        pos += cons.gridwidth;

        JComboBox classComboBox = new JComboBox();
        Collection<PcClass> pcClassesItr = config.getPcClasses().values();
        for (PcClass pcClass : pcClassesItr) {
            classComboBox.addItem(pcClass);
        }

        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 4;
        gridBag.setConstraints(classComboBox, cons);
        basicPanel.add(classComboBox);
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
        basicPanel.add(comLbl);
        pos += cons.gridwidth;

        JTextField comTextField = new JTextField();
        comTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(comTextField, cons);
        basicPanel.add(comTextField);
        pos += cons.gridwidth;

        // Mag
        JLabel magLbl = new JLabel("Mag");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(magLbl, cons);
        basicPanel.add(magLbl);
        pos += cons.gridwidth;

        JTextField magTextField = new JTextField();
        magTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(magTextField, cons);
        basicPanel.add(magTextField);
        pos += cons.gridwidth;

        // Ste
        JLabel steLbl = new JLabel("Ste");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(steLbl, cons);
        basicPanel.add(steLbl);
        pos += cons.gridwidth;

        JTextField steTextField = new JTextField();
        steTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(steTextField, cons);
        basicPanel.add(steTextField);
        pos += cons.gridwidth;

        // Gen
        JLabel genLbl = new JLabel("Gen");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genLbl, cons);
        basicPanel.add(genLbl);
        pos += cons.gridwidth;

        JTextField genTextField = new JTextField();
        genTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genTextField, cons);
        basicPanel.add(genTextField);
        pos += cons.gridwidth;

        // Row 2
        row = 2;
        pos = 0;
        // Race
        JLabel raceLbl = new JLabel("Race");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(raceLbl, cons);
        basicPanel.add(raceLbl);
        pos += cons.gridwidth;

        JComboBox raceComboBox = new JComboBox();
        Collection<String> races = config.getRaces();
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
        basicPanel.add(raceComboBox);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Size
        JLabel sizeLbl = new JLabel("Size");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(sizeLbl, cons);
        basicPanel.add(sizeLbl);
        pos += cons.gridwidth;

        // Size List
        JComboBox sizeComboBox = new JComboBox();
        Collection<String> sizes = config.getSizes();
        for (String aSize : sizes) {
            sizeComboBox.addItem(aSize);
        }
        sizeComboBox.setSelectedIndex(0);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 3;
        gridBag.setConstraints(sizeComboBox, cons);
        basicPanel.add(sizeComboBox);
        pos += cons.gridwidth;
        cons.gridwidth = 1;

        // Gender
        JLabel genderLbl = new JLabel("Gender");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(genderLbl, cons);
        basicPanel.add(genderLbl);
        pos += cons.gridwidth;

        // Gender List
        JComboBox genderComboBox = new JComboBox();
        Collection<String> genders = config.getGenders();
        for (String aGender : genders) {
            genderComboBox.addItem(aGender);
        }
        genderComboBox.setSelectedIndex(0);
        cons.gridx = pos;
        cons.gridy = row;
        cons.gridwidth = 3;
        gridBag.setConstraints(genderComboBox, cons);
        basicPanel.add(genderComboBox);
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
        basicPanel.add(levelLbl);
        pos += cons.gridwidth;

        // Level Spinner
        SpinnerModel levelModel = new SpinnerNumberModel(1, // initial
                // value
                0, // min
                999, // max
                1); // step

        JSpinner levelSpinner = new JSpinner(levelModel);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(levelSpinner, cons);
        basicPanel.add(levelSpinner);
        pos += cons.gridwidth;

        // Action Points
        JLabel apLbl = new JLabel("Action Points");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(apLbl, cons);
        basicPanel.add(apLbl);
        pos += cons.gridwidth;

        JTextField apTextField = new JTextField();
        apTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(apTextField, cons);
        basicPanel.add(apTextField);
        pos += cons.gridwidth;

        // Health
        JLabel healthLbl = new JLabel("Health");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(healthLbl, cons);
        basicPanel.add(healthLbl);
        pos += cons.gridwidth;

        JTextField healthTextField = new JTextField();
        healthTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(healthTextField, cons);
        basicPanel.add(healthTextField);
        pos += cons.gridwidth;

        // Mana
        JLabel manaLbl = new JLabel("Mana");
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(manaLbl, cons);
        basicPanel.add(manaLbl);
        pos += cons.gridwidth;

        JTextField manaTextField = new JTextField();
        manaTextField.setColumns(3);
        cons.gridx = pos;
        cons.gridy = row;
        gridBag.setConstraints(manaTextField, cons);
        basicPanel.add(manaTextField);
        pos += cons.gridwidth;

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

}