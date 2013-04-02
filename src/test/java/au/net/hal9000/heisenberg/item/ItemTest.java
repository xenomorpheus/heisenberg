package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Vector;
import nu.xom.ValidityException;
import org.junit.Test;

// Custom
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.units.*;
import au.net.hal9000.heisenberg.util.Configuration;

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
        assertEquals("Item() location", null, i.getLocation());
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

    @Test
    public void testValueBase() {
        Cookie i = new Cookie();
        i.setValueBase(new Currency(1, 2, 4, 8));
        assertTrue("ValueBase",
                i.getValueBase().equals(new Currency(1, 2, 4, 8)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(2, 2, 4, 8)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(1, 3, 4, 8)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(1, 2, 5, 8)));
        assertFalse("ValueBase",
                i.getValueBase().equals(new Currency(1, 2, 4, 9)));
    }

    @Test
    public void testLocation() {
        Cookie cookie = new Cookie();
        Human human = new Human("human");
        cookie.setLocation(human);
        assertEquals("location", human, cookie.getLocation());
    }

    @Test
    public void testToString() throws ValidityException, IOException, Exception {

        Configuration config =  new Configuration("test/config/config.xml");
        Vector<String> itemClasses = config.getItemClasses();

        for (String itemClass : itemClasses) {
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

}
