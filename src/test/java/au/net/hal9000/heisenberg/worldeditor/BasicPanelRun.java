package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class BasicPanelRun {
    JFrame guiFrame;

    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("PC Editor");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        BasicPanel basicPanel = new BasicPanel();
        basicPanel.setItem(pc);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    BasicPanelRun basicPanelRun = new BasicPanelRun();
                    basicPanelRun.doTest();
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
