package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import org.junit.Test;

import au.net.hal9000.heisenberg.util.ConfigurationError;

public class WorldEditorTest {

    @Test
    public void testGetMenus() {
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }

        };
        JMenuBar jMenuBar = WorldEditor.getMenus(actionListener);
        assertNotNull("getMenus not null", jMenuBar);
    }

    public void doTest() throws ConfigurationError {

        JFrame guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("WorldEditor");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        WorldEditor worldEditor = new WorldEditor();

        // add to JFrame
        guiFrame.add(worldEditor);
        guiFrame.setVisible(true);

    }

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    WorldEditorTest worldEditorTest = new WorldEditorTest();
                    worldEditorTest.doTest();
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
