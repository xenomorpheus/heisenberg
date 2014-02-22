package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import org.junit.Test;

import au.net.hal9000.heisenberg.util.ConfigurationError;

/**
 */
public class WorldEditorTest {

    /**
     * Method testGetMenus.
     */
    @Test
    public void testGetMenus() {
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }

        };
        JMenuBar jMenuBar = WorldEditor.getMenus(actionListener);
        assertNotNull("Not null", jMenuBar);
    }

    /**
     * Method testWorldEditor.
     * @throws ConfigurationError
     */
    @Test
    public void testWorldEditor() throws ConfigurationError {
        WorldEditor worldEditor = new WorldEditor();
        assertNotNull("Not null", worldEditor);

    }

    /**
     * app to test the world editor.
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    WorldEditor worldEditor = new WorldEditor();
                    // Center
                    worldEditor.setLocationRelativeTo(null);
                    // Kill app
                    worldEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    worldEditor.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
