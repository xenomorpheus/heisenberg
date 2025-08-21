package au.net.hal9000.heisenberg.ai.action;

import static org.junit.Assert.*;

import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Elf;
import au.net.hal9000.heisenberg.item.being.Being;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ActionConsumeTest {
  private static final Being ENTITY = new Elf();
  private static final Item CONSUMABLE = new Biscuit();
  private static final double COST = 1.67;

  // immutable so we can reuse.
  private ActionConsume actionEat = new ActionConsume(ENTITY, CONSUMABLE, COST);

  @Before
  public void setUp() throws Exception {}

  @Test
  @Ignore
  public void testApply() {
    fail("TEST not yet implemented");
  }

  @Test
  public void testToString() {
    String expect = "ActionConsume=[consumer=" + ENTITY + ",consumable=" + CONSUMABLE + ']';
    assertEquals(expect, actionEat.toString());
  }

  @Test
  public void testEquals() {
    ActionConsume actionEat2 = new ActionConsume(ENTITY, CONSUMABLE, COST);
    assertTrue(actionEat.equals(actionEat2));
  }
}
