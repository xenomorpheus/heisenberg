package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.TestHelper;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.util.Configuration;
import java.awt.Component;
import javax.swing.JTree;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>ItemTreeCellRendererTest</code> contains tests for the class {@link
 * <code>ItemTreeCellRenderer</code>}
 */
public class ItemTreeCellRendererTest {

  @Before
  public void setupClass() {
    TestHelper.setup();
  }

  /**
   * Run the Component getTreeCellRendererComponent(JTree, Object, boolean, boolean, boolean, int,
   * boolean) method test
   */
  @Test
  public void testGetTreeCellRendererComponent() {
    Configuration config = Configuration.lastConfig();
    ItemTreeCellRenderer fixture = new ItemTreeCellRenderer(config);
    JTree tree = new JTree();
    Object value = null;
    boolean sel = false;
    boolean exp = false;
    boolean leaf = false;
    int row = 0;
    boolean hasFocus = false;
    Component result =
        fixture.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
    assertNotNull(result);
  }

  /**
   * Run the Component getTreeCellRendererComponent(JTree, Object, boolean, boolean, boolean, int,
   * boolean) method test
   */
  @Test
  public void testGetTreeCellRendererComponentItem() {
    Configuration config = Configuration.lastConfig();
    ItemTreeCellRenderer fixture = new ItemTreeCellRenderer(config);
    JTree tree = new JTree();
    Item value = new Biscuit();
    boolean sel = false;
    boolean exp = false;
    boolean leaf = false;
    int row = 0;
    boolean hasFocus = false;
    Component result =
        fixture.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
    assertNotNull(result);
  }
}
