package au.net.hal9000.heisenberg.item.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.ai.ActionEntityEat;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Elf;
import au.net.hal9000.heisenberg.item.entity.Entity;

public class ActionAnimalEatTest {
    private static final Entity CONSUMER = new Elf();
    private static final Item FOOD = new Cookie();
    private static final double COST = 1.67;

    // immutable so we can reuse.
    private ActionEntityEat actionEat = new ActionEntityEat(CONSUMER, FOOD, COST);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testApply() {
        // TODO fail("TEST not yet implemented");
    }

    @Test
    public void testToString() {
        String expect = "ActionAnimalEat=[entity=" + CONSUMER + ",food=" + FOOD + ']';
        assertEquals(expect, actionEat.toString());
    }

}
