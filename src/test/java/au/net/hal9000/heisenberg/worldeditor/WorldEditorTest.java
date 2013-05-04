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
        assertNotNull("Not null", jMenuBar);
    }

    @Test
    public void testWorldEditor() throws ConfigurationError{
        WorldEditor worldEditor = new WorldEditor();
        assertNotNull("Not null", worldEditor);
        
    }
    
    
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

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
