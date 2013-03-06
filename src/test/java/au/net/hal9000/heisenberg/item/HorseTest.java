package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class HorseTest {

    @Test
    public void testInstanceof() {
        Horse horse = new Horse();
        Item item = (Item) horse;
        assertTrue("is humanoid mount", item instanceof HumanoidMount);
        assertTrue("is Entity", item instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(item));
        assertFalse("is Cookie", item instanceof Cookie);
        assertFalse("is Humanoid", item instanceof Humanoid);
    }

}