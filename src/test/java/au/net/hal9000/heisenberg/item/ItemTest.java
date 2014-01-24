package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Test;

// Custom
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.units.*;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;

public class ItemTest {
    private static final float WITHIN_MARGIN = 0.00009F;

    @Test
    public void testItem() {
        Cookie i = new Cookie();
        assertEquals("Item() name", "Cookie", i.getName());
        assertEquals("Item() description", null, i.getDescription());
        // assertTrue("Item() weightBase", i.getWeightBase().equals(0F));
        // assertTrue("Item() weightMax", i.getWeightMax().equals(0F));
        // assertTrue("Item() volumeBase", i.getVolumeBase().equals(0F));
        assertEquals("Item() location", null, i.getContainer());
    }

    @Test
    public void testItemWithName() {
        Cookie i = new Cookie("The Name");
        assertEquals("Item() name", "The Name", i.getName());
    }

    @Test
    public void testItemWithNameAndDescription() {
        Cookie i = new Cookie("The Name", "The Description");
        assertEquals("Item() name", "The Name", i.getName());
        assertEquals("Item() description", "The Description",
                i.getDescription());
    }

    @Test
    public void testName() {
        Cookie i = new Cookie("A Name");
        assertEquals("name", "A Name", i.getName());

        i.setName("fred");
        assertEquals("setname & getname", "fred", i.getName());
    }

    @Test
    public void testDescription() {
        Cookie i = new Cookie();
        i.setDescription("A Description");
        assertEquals("description", "A Description", i.getDescription());
    }

    @Test
    public void testBaseWeight() {
        Cookie i = new Cookie();
        i.setWeightBase(0.123F);
        assertEquals("weightBase", i.getWeightBase(), 0.123F, WITHIN_MARGIN);
    }

    @Test
    public void testWeight() {
        Cookie i = new Cookie();
        i.setWeightBase(0.123F);
        assertEquals("weight", i.getWeight(), 0.123F, WITHIN_MARGIN);
    }

    /** test value base. */
    @Test
    public void testValueBase() {
        Cookie i = new Cookie();
        final int pp = 1;
        final int gp = 2;
        final int sp = 4;
        final int cp = 8;
        i.setValueBase(new Currency(pp, gp, sp, cp));
        assertTrue("ValueBase",
                i.getValueBase().equals(new Currency(pp, gp, sp, cp)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(pp + 1, gp, sp, cp)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(pp, gp + 1, sp, cp)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(pp, gp, sp + 1, cp)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(pp, gp, sp, cp + 1)));
    }

    @Test
    public void testLocation() {
        Cookie cookie = new Cookie();
        Human human = new Human("human");
        cookie.setContainer(human);
        assertEquals("location", human, cookie.getContainer());
    }

    @Test
    public void testToString() throws ConfigurationError {

        Configuration config = new Configuration(
                "src/test/resources/config.xml");
        Vector<ItemClassConfiguration> itemClasses = config.getItemClasses();

        for (ItemClassConfiguration itemClassConfiguration : itemClasses) {
            String itemClass = itemClassConfiguration.getId();
            String string = Factory.createItem(itemClass).toString();
            assertTrue(itemClass + ".toString not null", string != null);
            assertTrue(itemClass + ".toString length", string.length() > 0);
        }
    }

    @Test
    public void testSetVolumeMax() {
        float volumeBase = 20F;
        Cookie cookie = new Cookie();
        cookie.setVolumeBase(volumeBase);
        float v = cookie.getVolumeBase();
        assertEquals("getVolumeBase=", volumeBase, v, 0.0001F);
    }

    @Test
    public void testEquals() {
        Cookie first = new Cookie();
        Cookie second = new Cookie();
        assertTrue("equals true for self", first.equals(first));
        assertFalse("equals false for other", first.equals(second));
    }

    @Test
    public void testProperties() {
        Cookie cookie = new Cookie();
        cookie.setProperty("myKey", "myVal");
        assertEquals("get property", "myVal", cookie.getProperty("myKey"));
        cookie.removeProperty("myKey");
    }

    /** Move without a container. */
    @Test
    public void testMoveNoContainer() {
        Cookie cookie = new Cookie();

        // No container - No Movement
        Point3d expectedPosition = new Point3d(10, 20, 30);
        // Before test we place in a known position.
        cookie.setPosition(expectedPosition);
        // Try to move, but will fail as not in an ItemContainer.
        boolean threwException = false;
        try {
            cookie.move(new Point3d(1, 2, 3));
        } catch (RuntimeException e) {
            threwException = true;
        }
        assertTrue("Exeption thrown", threwException);
        Point3d actualPosition = cookie.getPosition();
        assertTrue("No ItemContainer - final pos",
                expectedPosition.equals(actualPosition));
    }

    /** Move within a container. */
    @Test
    public void testMoveContainer() {
        Cookie cookie = new Cookie();

        Location container = new Location();
        cookie.setContainer(container);
        Point3d expectedPosition = new Point3d(2, 4, 8);
        cookie.move(expectedPosition);
        Point3d actualPosition = cookie.getPosition();
        assertTrue("Has ItemContainer - final pos",
                expectedPosition.equals(actualPosition));

    }

    /** check the instanceof results. */
    @Test
    public void testInstanceOf() {
        Item cookie = new Cookie();
        assertTrue("cookie", cookie.instanceOf("Cookie"));
        assertFalse("Sword", cookie.instanceOf("Sword"));
        assertFalse("Unknown", cookie.instanceOf("Unknown"));
    }

}
