package au.net.hal9000.heisenberg.ai.action;

import static org.junit.Assert.*;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Elf;
import au.net.hal9000.heisenberg.item.entity.EntityItem;
import org.junit.Before;
import org.junit.Test;

public class ActionConsumeTest {
  private static final EntityItem ENTITY = new Elf();
  private static final Item CONSUMABLE = new Cookie();
  private static final double COST = 1.67;

  // immutable so we can reuse.
  private ActionConsume actionEat = new ActionConsume(ENTITY, CONSUMABLE, COST);

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testApply() {
    // TODO fail("TEST not yet implemented");
  }

  @Test
  public void testToString() {
    String expect = "ActionConsume=[consumer=" + ENTITY + ",consumable=" + CONSUMABLE + ']';
    assertEquals(expect, actionEat.toString());
  }
}
