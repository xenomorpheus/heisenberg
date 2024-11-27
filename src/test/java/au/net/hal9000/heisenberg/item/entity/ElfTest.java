package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import org.junit.Test;

/** */
public class ElfTest {

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {

    Object elf = new Elf();
    assertTrue("is Elf", elf instanceof Elf);
    assertTrue("is Humanoid", elf instanceof Humanoid);
    assertTrue("is EntityItem", elf instanceof EntityItem);
    assertTrue("is item", elf instanceof Item);
  }

  /** Method testGetName. */
  @Test
  public void testGetName() {
    Elf elf = new Elf();
    assertEquals("Elf", elf.getName());
  }
}
