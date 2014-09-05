package au.net.hal9000.heisenberg.ui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.TestbedController.UpdateBehavior;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // log.warn("Could not set the look and feel to nimbus.  "
            // + "Hopefully you're on a mac so the window isn't ugly as crap.");
        }
        TestbedModel model = new TestbedModel();
        TestbedPanel panel = new JoglPanel(model);
        model.addTest(new MazeCat());
        JFrame testbed = new TestbedFrame(model, panel,
                UpdateBehavior.UPDATE_IGNORED);
        testbed.setVisible(true);
        testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}