package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import org.junit.Test;

import au.net.hal9000.heisenberg.util.ConfigurationError;

/**
 */
public class WorldEditorFrameTest {

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
        JMenuBar jMenuBar = WorldEditorFrame.getMenus(actionListener);
        assertNotNull("Not null", jMenuBar);
    }

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {
        WorldEditorFrame worldEditor = new WorldEditorFrame();
        assertNotNull("Not null", worldEditor);

    }

}
