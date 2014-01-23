package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class MagicRingTest {

    @Test
    public void testImplementsMagic() {
        MagicRing ring = new MagicRing();
        assertTrue("Implements Magical", ItemProperty.isMagical(ring));
    }

}
