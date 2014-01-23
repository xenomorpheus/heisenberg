package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class OrbOfLightTest {

    // Name and Description
    @Test
    public void testName() {
        private static final String defaultName = "Orb Of Light";
        private static final String defaultDescription = "orb of light";
        OrbOfLight ool = new OrbOfLight();
        assertEquals(defaultName, ool.getName());
        assertEquals(defaultDescription, ool.getDescription());
        // custom
        private static final String expectedName = "my ool";
        private static final String expectedDescription = "the description";
        OrbOfLight custom = new OrbOfLight(expectedName, expectedDescription);
        assertEquals(expectedName, custom.getName());
        assertEquals(expectedDescription, custom.getDescription());
    }

    @Test
    public void testMagical() {
        OrbOfLight ool = new OrbOfLight();
        assertTrue("Implements Magical", ItemProperty.isMagical(ool));
    }
}
