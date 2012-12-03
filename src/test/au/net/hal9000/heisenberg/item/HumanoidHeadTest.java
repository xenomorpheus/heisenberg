package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.HumanoidHead;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class HumanoidHeadTest {

    @Test
    public void testLiving() {
        HumanoidHead head = new HumanoidHead();
        assertTrue("living", ItemProperty.isLiving(head));
    }

}
