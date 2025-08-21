package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Elf;
import au.net.hal9000.heisenberg.item.being.Being;
import au.net.hal9000.heisenberg.item.being.Humanoid;

import org.junit.Test;

/** */
public class ElfTest {

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {

    Object elf = new Elf();
    assertTrue("is Elf", elf instanceof Elf);
    assertTrue("is Humanoid", elf instanceof Humanoid);
    assertTrue("is Being", elf instanceof Being);
    assertTrue("is item", elf instanceof Item);
  }
}
