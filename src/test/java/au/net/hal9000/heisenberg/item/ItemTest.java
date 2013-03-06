package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

// Custom
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.units.*;

public class ItemTest {
    private static final float WITHIN_MARGIN = 0.00009F;

    @Test
    public void testItem() {
        Cookie i = new Cookie();
        assertEquals("IItem() name", "Cookie", i.getName());
        assertEquals("IItem() description", null, i.getDescription());
        // assertTrue("IItem() weightBase", i.getWeightBase().equals(0F));
        // assertTrue("IItem() weightMax", i.getWeightMax().equals(0F));
        // assertTrue("IItem() volumeBase", i.getVolumeBase().equals(0F));
        assertEquals("IItem() location", null, i.getLocation());
    }

    @Test
    public void testItemWithName() {
        Cookie i = new Cookie("The Name");
        assertEquals("IItem() name", "The Name", i.getName());
    }

    @Test
    public void testItemWithNameAndDescription() {
        Cookie i = new Cookie("The Name", "The Description");
        assertEquals("IItem() name", "The Name", i.getName());
        assertEquals("IItem() description", "The Description",
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

    public void testToString() {
        Cookie cookie = new Cookie();
        assertEquals("toString", "some text", cookie.toString());

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
    public void testFilePersistence() {
        File fileObj = new File(System.getProperty("java.io.tmpdir"),
                "cookie_persit_test.ser");
        String filename = fileObj.getAbsolutePath();
        Cookie old = new Cookie();
        // Store the object
        try {
            old.freezeToFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        }
        // Get the object back
        Cookie newObj = null;
        try {
            newObj = Cookie.thawFromFile(filename);
        } catch (IOException ex) {
            fail(ex.toString());
        } catch (ClassNotFoundException ex) {
            fail(ex.toString());
        }
        assertTrue("newObj not null", newObj != null);
        assertTrue("deserialized Cookie equals old cookie", old.equals(newObj));

    }

    @Test
    public void testProperties() {
        Cookie cookie = new Cookie();
        cookie.setProperty("myKey", "myVal");
        assertEquals("get property", "myVal", cookie.getProperty("myKey"));
        cookie.removeProperty("myKey");
    }



}
