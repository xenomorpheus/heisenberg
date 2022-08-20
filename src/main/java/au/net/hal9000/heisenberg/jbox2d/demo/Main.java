package au.net.hal9000.heisenberg.jbox2d.demo;

import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.jbox2d.testbed.framework.TestbedController.UpdateBehavior;
import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The HunterPreyTestBed is added to the JBox2D physics engine.
 *
 * @author Mike Bruins
 */
public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      log.warn(
          "Could not set the look and feel to nimbus.  "
              + "Hopefully you're on a mac so the window isn't ugly as crap.");
    }
    TestbedModel model = new TestbedModel();
    TestbedPanel panel = new TestPanelJ2D(model);
    model.addTest(new HunterPreyTestBed());
    JFrame testbed = new TestbedFrame(model, panel, UpdateBehavior.UPDATE_CALLED);
    testbed.pack();
    testbed.setLocationRelativeTo(null);
    testbed.setVisible(true);
    testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    DemoEnvironment.setup();
  }
}
