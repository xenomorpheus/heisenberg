package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.api.Item;
import java.awt.Component;
import javax.swing.JTree;
import junit.framework.TestCase;

/**
 * The class <code>ItemTreeCellRendererTest</code> contains tests for the class {@link
 * <code>ItemTreeCellRenderer</code>}
 *
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 6/03/15 9:06 AM
 * @author bruins
 * @version $Revision$
 */
public class ItemTreeCellRendererTest extends TestCase {

  /**
   * Construct new test instance
   *
   * @param name the test name
   */
  public ItemTreeCellRendererTest(String name) {
    super(name);
  }

  /**
   * Run the Component getTreeCellRendererComponent(JTree, Object, boolean, boolean, boolean, int,
   * boolean) method test
   */
  public void testGetTreeCellRendererComponent() {
    // add test code here
    ItemTreeCellRenderer fixture = new ItemTreeCellRenderer();
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
  public void testGetTreeCellRendererComponentItem() {
    // add test code here
    ItemTreeCellRenderer fixture = new ItemTreeCellRenderer();
    JTree tree = new JTree();
    Item value = new Cookie();
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
