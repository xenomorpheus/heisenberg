package au.net.hal9000.heisenberg.item;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

/**
 */
public class EntityTest {

    @BeforeClass
    public static void setupConfig() throws ConfigurationError {
        new Configuration("src/test/resources/config.xml");
    }

    /**
     * Method testActionPoints.
     */
    @Test
    public void testActionPoints() {
        final int expected = 17;
        Cat cat = new Cat();
        assertEquals(0, cat.getActionPoints());
        cat.setActionPoints(expected);
        assertEquals(expected, cat.getActionPoints());
        cat.actionPointsAdjust(2);
        assertEquals(expected + 2, cat.getActionPoints());
    }

    /**
     * Method testGender.
     */
    @Test
    public void testGender() {
        final String expected = "some name";
        Cat cat = new Cat();
        cat.setGender(expected);
        assertEquals(expected, cat.getGender());
    }

    /**
     * Method testMana.
     */
    @Test
    public void testMana() {
        final int expected = 16;
        Cat cat = new Cat();
        assertEquals(0, cat.getMana());
        cat.setMana(expected);
        assertEquals(expected, cat.getMana());
    }

    /**
     * Method testRecipes.
     */
    @Test
    public void testRecipes() {
        Cat cat = new Cat();
        Set<String> recipes = new HashSet<String>();
        cat.setRecipes(recipes);
        cat.getRecipes();
        // assertNotNull("recipes not null", recipes);
        // TODO cat.setRecipes();
    }

    /**
     * Method testSize.
     */
    @Test
    public void testSize() {
        final String expected = "some name";
        Cat cat = new Cat();
        cat.setSize(expected);
        assertEquals(expected, cat.getSize());
    }

    /**
     * Method testGetCooker.
     */
    @Test
    public void testGetCooker() {
        Cat cat = new Cat();
        String recipeId = "testItem1";
        Cooker cooker = cat.getCooker(recipeId);
        assertNotNull("cooker not null", cooker);
    }

    /**
     * Method testGetCooker.
     */
    @Test
    public void testEat() {
        Cat cat = new Cat();
        String recipeId = "testItem1";
        Cooker cooker = cat.getCooker(recipeId);
        assertNotNull("cooker not null", cooker);
    }

    /**
     * Method testInstanceof.
     */
    @Test
    public void testInstanceof() {
        Item cat = new Cat();
        assertTrue("is Entity", cat instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(cat));
    }

    // TODO testSetAllFrom - just test the fields at this level.

    // TODO clone - On an abstract class ?
    // TODO equals
    // TODO persistence
}
