package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.api.Item;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import junit.framework.TestCase;
import org.junit.Test;

public class ItemTreeNodeTest extends TestCase {

  private Item biscuit;
  private Bag bag;
  private final TreeNode notTreeNode =
      new TreeNode() {

        @Override
        public TreeNode getChildAt(int childIndex) {
          return null;
        }

        @Override
        public int getChildCount() {
          return 0;
        }

        @Override
        public TreeNode getParent() {
          return null;
        }

        @Override
        public int getIndex(TreeNode node) {
          return 0;
        }

        @Override
        public boolean getAllowsChildren() {
          return false;
        }

        @Override
        public boolean isLeaf() {
          return false;
        }

        @Override
        public Enumeration<TreeNode> children() {
          return null;
        }
      };

  @Override
  protected void setUp() throws Exception {
    biscuit = new Biscuit();
    bag = new Bag();
  }

  @Test
  public void testItemTreeNode() {
    ItemTreeNode itn = new ItemTreeNode(biscuit);
    assertNotNull(itn);
  }

  @Test
  public void testGetChildAt() {
    bag.add(biscuit);
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    int childIndex = bag.indexOf(biscuit);
    TreeNode childNode = bagNode.getChildAt(childIndex);
    if (childNode instanceof ItemTreeNode) {
      ItemTreeNode itn = (ItemTreeNode) childNode;
      assertEquals("Item", biscuit, itn.getItem());
    } else {
      fail("not an instance of ItemTreeNode");
    }
  }

  @Test
  public void testGetChildCount() {
    bag.add(biscuit);
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    assertEquals("childCount", bag.size(), bagNode.getChildCount());
  }

  @Test
  public void testGetParent() {
    // fail("Not yet implemented");
  }

  @Test
  public void testGetIndexIsContained() {

    // biscuit in bag
    bag.add(biscuit);
    int expectedResult = bag.indexOf(biscuit);
    ItemTreeNode biscuitTreeNode = new ItemTreeNode(biscuit);
    ItemTreeNode bagTreeNode = new ItemTreeNode(bag);
    int gotResult = bagTreeNode.getIndex(biscuitTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetIndexNotContained() {
    Item biscuit = new Biscuit();
    Bag bag = new Bag();
    int expectedResult = -1;
    ItemTreeNode biscuitTreeNode = new ItemTreeNode(biscuit);
    ItemTreeNode bagTreeNode = new ItemTreeNode(bag);
    int gotResult = biscuitTreeNode.getIndex(bagTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetIndexNotTreeNode() {
    Item biscuit = new Biscuit();
    int expectedResult = -1;
    ItemTreeNode biscuitTreeNode = new ItemTreeNode(biscuit);

    int gotResult = biscuitTreeNode.getIndex(notTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetAllowsChildren() {
    ItemTreeNode biscuitNode = new ItemTreeNode(biscuit);
    assertFalse("biscuit", biscuitNode.getAllowsChildren());
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    assertTrue("bag", bagNode.getAllowsChildren());
  }

  @Test
  public void testIsLeaf() {
    ItemTreeNode biscuitNode = new ItemTreeNode(biscuit);
    assertTrue("biscuit", biscuitNode.isLeaf());
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    assertFalse("bag", bagNode.isLeaf());
  }

  @Test
  public void testChildren() {
    // fail("Not yet implemented");
  }

  @Test
  public void testToString() {
    // fail("Not yet implemented");
  }

  @Test
  public void testInsert() {
    // fail("Not yet implemented");
  }

  @Test
  public void testGetItem() {
    // fail("Not yet implemented");
  }

  @Test
  public void testRemoveInt() {
    // fail("Not yet implemented");
  }

  @Test
  public void testRemoveMutableTreeNode() {
    // fail("Not yet implemented");
  }

  @Test
  public void testSetUserObject() {
    // fail("Not yet implemented");
  }

  @Test
  public void testRemoveFromParent() {
    // fail("Not yet implemented");
  }

  @Test
  public void testSetParent() {
    // fail("Not yet implemented");
  }
}
