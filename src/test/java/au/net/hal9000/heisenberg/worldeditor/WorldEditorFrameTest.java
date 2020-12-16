package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import org.junit.Before;
import org.junit.Test;

/** */
public class WorldEditorFrameTest {

  @Before
  public void setupClass() {
    DemoEnvironment.setup();
  }

  @Test
  public void testWorldEditorFrame() throws ConfigurationError {
    WorldEditorFrame wef = new WorldEditorFrame();
  }

  @Test
  public void testGetMenus() {
    ActionListener actionListener =
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

          }
        };
    JMenuBar jMenuBar = WorldEditorFrame.getMenus(actionListener);
    assertNotNull("Not null", jMenuBar);
  }
}
