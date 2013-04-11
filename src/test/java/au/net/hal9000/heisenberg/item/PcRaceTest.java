package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import nu.xom.ValidityException;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class PcRaceTest {

    /*
     * Most tests will use a Human as a representative of the PcRace. Tests
     * should be in the same order as the fields or methods.
     */

    // Constructor
    @Test
    public void testPcRace() {
        Human human = new Human();
        assertEquals("Name check", "Human", human.getName());
        assertEquals("Description check", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living", ItemProperty.isLiving(human));
    }

    @Test
    public void testPcRaceString() {
        Human human = new Human("Fred1");
        assertEquals("Name check1", "Fred1", human.getName());
        assertEquals("Description check1", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living1", ItemProperty.isLiving(human));
    }

    @Test
    public void testPcRaceStringString() {
        Human human = new Human("Fred2", "Desc2");
        assertEquals("Name check2", "Fred2", human.getName());
        assertEquals("Description check2", "Desc2", human.getDescription());
        // By default PCs are living.
        assertTrue("living2", ItemProperty.isLiving(human));
    }

    @Test
    public void testPcRaceStringPcClass() {
        final PcClass expected = new PcClass();
        Human human = new Human("Fred3", expected);
        assertEquals("Name check3", "Fred3", human.getName());
        assertEquals("Description check3", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living3", ItemProperty.isLiving(human));
        assertSame(expected, human.getPcClass());
    }

    @Test
    public void testPcRaceStringStringPcClass() {
        final PcClass expected = new PcClass();
        Human human = new Human("Fred4", "Desc4", expected);
        assertEquals("Name check3", "Fred4", human.getName());
        assertEquals("Description check4", "Desc4", human.getDescription());
        // By default PCs are living.
        assertTrue("living4", ItemProperty.isLiving(human));
        assertSame(expected, human.getPcClass());
    }

    // Getters and Setters in field order.
    @Test
    public void testLevel() {
        final int expected = 18;
        Human human = new Human();
        assertEquals(0, human.getLevel());
        human.setLevel(expected);
        assertEquals(expected, human.getLevel());
    }

    @Test
    public void testPcClass() {
        final PcClass expected = new PcClass();
        Human human = new Human();
        assertEquals(null, human.getPcClass());
        human.setPcClass(expected);
        assertEquals(expected, human.getPcClass());
    }

    @Test
    public void testCombatDice() {
        final int expected = 12;
        Human human = new Human();
        // assertEquals(0, human.getCombatDice());
        human.setCombatDice(expected);
        assertEquals(expected, human.getCombatDice());
    }

    @Test
    public void testMagicDice() {
        final int expected = 13;
        Human human = new Human();
        // assertEquals(0, human.getMagicDice());
        human.setMagicDice(expected);
        assertEquals(expected, human.getMagicDice());
    }

    @Test
    public void testStealthDice() {
        final int expected = 14;
        Human human = new Human();
        // assertEquals(0, human.getStealthDice());
        human.setStealthDice(expected);
        assertEquals(expected, human.getStealthDice());
    }

    @Test
    public void testGeneralDice() {
        final int expected = 15;
        Human human = new Human();
        // assertEquals(0, human.getGeneralDice());
        human.setGeneralDice(expected);
        assertEquals(expected, human.getGeneralDice());
    }

    @Test
    public void testMana() {
        final int expected = 16;
        Human human = new Human();
        assertEquals(0, human.getMana());
        human.setMana(expected);
        assertEquals(expected, human.getMana());
    }

    @Test
    public void testEncumbrance() {
        final int expected = 16;
        Human human = new Human();
        assertEquals(0, human.getEncumbrance());
        human.setEncumbrance(expected);
        assertEquals(expected, human.getEncumbrance());
    }

    @Test
    public void testHealth() {
        final int expected = 16;
        Human human = new Human();
        assertEquals(0, human.getHealth());
        human.setHealth(expected);
        assertEquals(expected, human.getHealth());
    }

    @Test
    public void testActionPoints() {
        final int expected = 17;
        Human human = new Human();
        assertEquals(0, human.getActionPoints());
        human.setActionPoints(expected);
        assertEquals(expected, human.getActionPoints());
        human.actionPointsAdjust(2);
        assertEquals(expected + 2, human.getActionPoints());
    }

    @Test
    public void testValues() throws ValidityException, IOException, Exception {

        Configuration config = new Configuration("src/test/resources/config.xml");
        PcClass warrior = config.getPcClass("Warrior");
        Human pc = new Human("Mr Warrior");
        pc.setPcClass(warrior);

        // dice
        assertEquals(20, pc.getCombatDice());
        assertEquals(4, pc.getMagicDice());
        assertEquals(12, pc.getStealthDice());
        assertEquals(8, pc.getGeneralDice());
        // Attributes

        // misc
        assertEquals(100, pc.getEncumbrance());
        assertEquals(8, pc.getActionPoints());
        assertEquals(7, pc.getHealth());
        assertEquals(0, pc.getMana());

    }

    @Test
    public void testRecipes() {
        Human human = new Human();
        // TODO
        Set<String> recipes = human.getRecipes();
        assertNotNull("recipes not null", recipes);
        // TODO human.setRecipes();
    }

    @Test 
    public void testGetDetailedDescription() {
        Human human = new Human();
        String detailedDescription = human.getDetailedDescription();
        assertNotNull("detailed description not null", detailedDescription);
    }
    
    
}
