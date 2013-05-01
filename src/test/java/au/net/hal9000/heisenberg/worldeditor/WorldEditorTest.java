package au.net.hal9000.heisenberg.worldeditor;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import org.junit.Test;

import au.net.hal9000.heisenberg.worldeditor.WorldEditor;

public class WorldEditorTest {

    @Test
    public void testGetDemoWorld() {
        /*
         * This unit test is here so:<br> 1) We know the test data will build
         * before running the UI. 2) Code coverage.
         */
        try {
            WorldEditor.getDemoWorld();
        } catch (Exception e) {
            fail(e.toString());
        }
    }

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

}
