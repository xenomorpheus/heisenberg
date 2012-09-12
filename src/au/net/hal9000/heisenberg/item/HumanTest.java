package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class HumanTest {

    @Test
    public void testInstanceof() {

        Human human = new Human();
        Item humanItem = (Item) human;
        assertTrue("is Human", human instanceof Human);
        assertTrue("is Humanoid", human instanceof Human);
        assertTrue("is Entity", human instanceof Entity);
        assertTrue("is item", human instanceof Item);
        assertFalse("is Cookie", humanItem instanceof Cookie);
    }
}
