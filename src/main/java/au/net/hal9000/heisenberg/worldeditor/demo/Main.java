package au.net.hal9000.heisenberg.worldeditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.WorldEditor;

public class Main {
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
                    worldEditor.pack();
                    worldEditor.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
