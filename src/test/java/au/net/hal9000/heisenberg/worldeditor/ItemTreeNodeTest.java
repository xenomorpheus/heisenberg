package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.api.Item;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import junit.framework.TestCase;
import org.junit.Test;

public class ItemTreeNodeTest extends TestCase {

  private Item cookie;
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
    cookie = new Cookie();
    bag = new Bag();
  }

  @Test
  public void testItemTreeNode() {
    ItemTreeNode itn = new ItemTreeNode(cookie);
    assertNotNull(itn);
  }

  @Test
  public void testGetChildAt() {
    bag.add(cookie);
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    int childIndex = bag.indexOf(cookie);
    TreeNode childNode = bagNode.getChildAt(childIndex);
    if (childNode instanceof ItemTreeNode) {
      ItemTreeNode itn = (ItemTreeNode) childNode;
      assertEquals("Item", cookie, itn.getItem());
    } else {
      fail("not an instance of ItemTreeNode");
    }
  }

  @Test
  public void testGetChildCount() {
    bag.add(cookie);
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    assertEquals("childCount", bag.size(), bagNode.getChildCount());
  }

  @Test
  public void testGetParent() {
    // fail("Not yet implemented");
  }

  @Test
  public void testGetIndexIsContained() {

    // cookie in bag
    bag.add(cookie);
    int expectedResult = bag.indexOf(cookie);
    ItemTreeNode cookieTreeNode = new ItemTreeNode(cookie);
    ItemTreeNode bagTreeNode = new ItemTreeNode(bag);
    int gotResult = bagTreeNode.getIndex(cookieTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetIndexNotContained() {
    Item cookie = new Cookie();
    Bag bag = new Bag();
    int expectedResult = -1;
    ItemTreeNode cookieTreeNode = new ItemTreeNode(cookie);
    ItemTreeNode bagTreeNode = new ItemTreeNode(bag);
    int gotResult = cookieTreeNode.getIndex(bagTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetIndexNotTreeNode() {
    Item cookie = new Cookie();
    int expectedResult = -1;
    ItemTreeNode cookieTreeNode = new ItemTreeNode(cookie);

    int gotResult = cookieTreeNode.getIndex(notTreeNode);
    assertEquals("getIndex result", expectedResult, gotResult);
  }

  @Test
  public void testGetAllowsChildren() {
    ItemTreeNode cookieNode = new ItemTreeNode(cookie);
    assertFalse("cookie", cookieNode.getAllowsChildren());
    ItemTreeNode bagNode = new ItemTreeNode(bag);
    assertTrue("bag", bagNode.getAllowsChildren());
  }

  @Test
  public void testIsLeaf() {
    ItemTreeNode cookieNode = new ItemTreeNode(cookie);
    assertTrue("cookie", cookieNode.isLeaf());
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
