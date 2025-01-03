package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import javax.swing.tree.TreePath;
import org.junit.Before;
import org.junit.Test;

/** Test the ItemPanel. */
public class ItemTreePanelTest {

  @Before
  public void setupClass() {
    DemoEnvironment.setup();
  }

  @Test
  public void testItemTreePanel() throws ConfigurationError {
    Configuration config = Configuration.lastConfig();
    Location location = DemoEnvironment.getDemoWorld();
    ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
    assertNotNull("Not Null", itemTreePanel);
  }

  @Test
  public void testGetPathToNode() {
    Location location = new Location();
    Bag bag = new Bag();
    Biscuit biscuit = new Biscuit();
    location.add(bag);
    bag.add(biscuit);
    Item itemArray[] = new Item[] {location, bag, biscuit};

    TreePath actual = ItemTreePanel.getPathToNode(new ItemTreeNode(biscuit));
    assertEquals("path size", itemArray.length, actual.getPathCount());
    int expectedIndex = 0;
    for (Object object : actual.getPath()) {
      assertTrue("type", object instanceof ItemTreeNode);
      ItemTreeNode node = (ItemTreeNode) object;
      assertEquals("node", itemArray[expectedIndex++], node.getItem());
    }
  }
}
