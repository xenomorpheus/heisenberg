package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class EntityTest {

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

    @Test
    public void testGender() {
        final String expected = "some name";
        Cat cat = new Cat();
        cat.setGender(expected);
        assertEquals(expected, cat.getGender());
    }

    @Test
    public void testMana() {
        final int expected = 16;
        Cat cat = new Cat();
        assertEquals(0, cat.getMana());
        cat.setMana(expected);
        assertEquals(expected, cat.getMana());
    }

    @Test
    public void testRecipes() {
        Cat cat = new Cat();
        Set<String> recipes = new HashSet<String>();
        cat.setRecipes(recipes);
        cat.getRecipes();
        // assertNotNull("recipes not null", recipes);
        // TODO cat.setRecipes();
    }

    @Test
    public void testSize() {
        final String expected = "some name";
        Cat cat = new Cat();
        cat.setSize(expected);
        assertEquals(expected, cat.getSize());
    }

    @Test
    public void testGetCooker() {
        Cat cat = new Cat();
        String recipeId = "testItem1";
        Cooker cooker = cat.getCooker(recipeId);
        assertNotNull("cooker not null", cooker);
    }

    @Test
    public void testInstanceof() {
        Cat cat = new Cat();
        assertTrue("is Entity", cat instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(cat));
    }

    @Test
    public void testAdd() {

        // Cat
        Cat cat = new Cat("Cat"); // Close enough
        Item shield = new Shield();
        cat.add(shield);
    }

    // TODO testSetAllFrom - just test the fields at this level.

    // TODO clone - On an abstract class ?
    // TODO equals
    // TODO persistence
}
