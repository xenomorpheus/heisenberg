package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class HorseTest {

    @Test
    public void testInstanceof() {
        Horse horse = new Horse();
        assertTrue("is humanoid mount", horse instanceof HumanoidMount);
        assertTrue("is Entity", horse instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(horse));
    }

}
