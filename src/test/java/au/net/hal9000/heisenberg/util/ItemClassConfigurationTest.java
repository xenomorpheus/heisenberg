package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/** */
public class ItemClassConfigurationTest {

  private ItemClassConfiguration itemClassConf = null;

  @Before
  public void before() {
    itemClassConf = new ItemClassConfiguration();
  }

  @Test
  public void testGetIconClosedId() {
    itemClassConf.setIconClosedId(2323);
    assertEquals("getIconClosedId", 2323, itemClassConf.getIconClosedId());
  }

  @Test
  public void testGetIconLeafId() {
    int expected = 123;
    itemClassConf.setIconLeafId(expected);
    assertEquals("getIconLeafId", expected, itemClassConf.getIconLeafId());
  }

  @Test
  public void testGetIconOpenId() {
    int expected = 23;
    itemClassConf.setIconOpenId(expected);
    assertEquals("getIconOpenId", expected, itemClassConf.getIconOpenId());
  }

  @Test
  public void testGetId() {
    String expected = "theId";
    itemClassConf.setId(expected);
    assertEquals("id", expected, itemClassConf.getId());
  }

  @Test
  public void testGetJavaClass() {
    String expected = "entity.Elf";
    itemClassConf.setJavaClass(expected);
    assertEquals("getJavaClass", expected, itemClassConf.getJavaClass());
  }

  @Test
  public void testSetIconClosedId() {
    int expected = 2323;
    itemClassConf.setIconClosedId(expected);
    assertEquals("getIconClosedId", expected, itemClassConf.getIconClosedId());
  }

  @Test
  public void testSetIconLeafId() {
    itemClassConf.setIconLeafId(123);
  }

  @Test
  public void testSetIconOpenId() {
    itemClassConf.setIconOpenId(23);
  }

  @Test
  public void testSetId() {
    itemClassConf.setId("theId");
  }

  @Test
  public void testSetJavaClass() {
    itemClassConf.setJavaClass("entity.Elf");
  }
}
