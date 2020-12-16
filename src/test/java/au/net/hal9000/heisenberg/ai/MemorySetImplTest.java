package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MemorySetImplTest {

  MemorySetImpl msi = null;

  @Before
  public void setUp() throws Exception {
    msi = new MemorySetImpl();
  }

  @Test
  public void testGetMemorySet() {
    // TODO fail("TEST not yet implemented");
  }

  @Test
  public void testSetMemorySet() {
    // TODO fail("TEST not yet implemented");
  }

  @Test
  public void testAdd() {
    // TODO fail("TEST not yet implemented");
  }

  @Test
  public void testIsEmpty() {
    // TODO fail("TEST not yet implemented");
  }

  @Test
  public void testToString() {
    String expect = msi.getClass().getSimpleName() + "=[]";
    assertEquals(expect, msi.toString());
  }

  @Test
  public void testDuplicate() {
    // TODO fail("TEST not yet implemented");
  }
}
