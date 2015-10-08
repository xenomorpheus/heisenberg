package au.net.hal9000.heisenberg.item.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Elf;
import au.net.hal9000.heisenberg.item.entity.Entity;

public class ActionAnimalEatTest {
    private static final Entity CONSUMER = new Elf();
    private static final Item FOOD = new Cookie();
    private static final double COST = 1.67;
    private ActionAnimalEat actionEat = null;

    @Before
    public void setUp() throws Exception {
        actionEat = new ActionAnimalEat(CONSUMER, FOOD, COST);
    }

    @Test
    public void testEqualsObject() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testActionEat() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testGetFood() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testSetFood() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testApply() {
        // TODO fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        String expect = "ActionAnimalEat=[entity=" + CONSUMER + ",food=" + FOOD + ']';
        assertEquals(expect, actionEat.toString());
    }

}
