package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.Elf;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class PcRaceTest {
    /**
     * Field config.
     */
    private Configuration config = null;

    /*
     * Most tests will use a Human as a representative of the PcRace. Tests
     * should be in the same order as the fields or methods.
     */

    @Before
    public void initialize() {
        DemoEnvironment.setup();
        config = Configuration.lastConfig();
    }

    // Constructor
    /**
     * Method testPcRace.
     */
    @Test
    public void testPcRace() {
        Human human = new Human();
        checkBasicsAreZero(human);
        assertEquals("Name check", "Human", human.getName());
        assertEquals("Description check", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living", ItemProperty.isLiving(human));
    }

    /**
     * Method testPcRaceString.
     */
    @Test
    public void testPcRaceString() {
        Human human = new Human("Fred1");
        assertEquals("Name check1", "Fred1", human.getName());
        assertEquals("Description check1", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living1", ItemProperty.isLiving(human));
    }

    /**
     * Method testPcRaceStringString.
     */
    @Test
    public void testPcRaceStringString() {
        Human human = new Human("Fred2", "Desc2");
        assertEquals("Name check2", "Fred2", human.getName());
        assertEquals("Description check2", "Desc2", human.getDescription());
        // By default PCs are living.
        assertTrue("living2", ItemProperty.isLiving(human));
    }

    /**
     * Method testPcRaceStringPcClass.
     */
    @Test
    public void testPcRaceStringPcClass() {
        final PcClass expectedValue = new PcClass();
        Human human = new Human("Fred3", expectedValue);
        assertEquals("Name check3", "Fred3", human.getName());
        assertEquals("Description check3", "", human.getDescription());
        // By default PCs are living.
        assertTrue("living3", ItemProperty.isLiving(human));
        assertSame(expectedValue, human.getPcClass());
    }

    /**
     * Method testPcRaceStringStringPcClass.
     */
    @Test
    public void testPcRaceStringStringPcClass() {
        final PcClass expectedValue = new PcClass();
        Human human = new Human("Fred4", "Desc4", expectedValue);
        assertEquals("Name check3", "Fred4", human.getName());
        assertEquals("Description check4", "Desc4", human.getDescription());
        // By default PCs are living.
        assertTrue("living4", ItemProperty.isLiving(human));
        assertSame(expectedValue, human.getPcClass());
    }

    // Getters and Setters in field order.
    /**
     * Method testLevel.
     */
    @Test
    public void testLevel() {
        final int expectedValue = 18;
        Human human = new Human();
        assertEquals(0, human.getLevel());
        human.setLevel(expectedValue);
        assertEquals(expectedValue, human.getLevel());
    }

    /**
     * Method testPcClass.
     */
    @Test
    public void testPcClass() {
        final PcClass expectedValue = new PcClass();
        Human human = new Human();
        checkBasicsAreZero(human);
        // default
        assertNull("pcClass default of null", human.getPcClass());
        // set
        human.setPcClass(expectedValue);
        assertEquals("pcClass changed", expectedValue, human.getPcClass());
        // cleared
        human.setCombatDice(1);
        human.setMagicDice(1);
        human.setStealthDice(1);
        human.setGeneralDice(1);
        human.setActionPoints(1);
        human.setMana(1);
        human.setEncumbrance(1);
        human.setHealth(1);
        human.setPcClass(null);
        assertNull("pcClass nulled", human.getPcClass());
        checkBasicsAreZero(human);
    }

    /**
     * Method checkBasicsAreZero.
     * 
     * @param pc
     *            PcRace
     */
    public void checkBasicsAreZero(PcRace pc) {
        assertEquals("combatDice", 0, pc.getCombatDice());
        assertEquals("magicDice", 0, pc.getMagicDice());
        assertEquals("stealthDice", 0, pc.getStealthDice());
        assertEquals("generalDice", 0, pc.getGeneralDice());
        assertEquals("actionPoints", 0, pc.getActionPoints());
        assertEquals("mana", 0, pc.getMana());
        assertEquals("encumbrance", 0, pc.getEncumbrance());
        assertEquals("health", 0, pc.getHealth());
    }

    /**
     * Method testCombatDice.
     */
    @Test
    public void testCombatDice() {
        final int expectedValue = 12;
        Human human = new Human();
        // assertEquals(0, human.getCombatDice());
        human.setCombatDice(expectedValue);
        assertEquals("combatDice", expectedValue, human.getCombatDice());
    }

    /**
     * Method testMagicDice.
     */
    @Test
    public void testMagicDice() {
        final int expectedValue = 13;
        Human human = new Human();
        // assertEquals(0, human.getMagicDice());
        human.setMagicDice(expectedValue);
        assertEquals(expectedValue, human.getMagicDice());
    }

    /**
     * Method testStealthDice.
     */
    @Test
    public void testStealthDice() {
        final int expectedValue = 14;
        Human human = new Human();
        // assertEquals(0, human.getStealthDice());
        human.setStealthDice(expectedValue);
        assertEquals(expectedValue, human.getStealthDice());
    }

    /**
     * Method testGeneralDice.
     */
    @Test
    public void testGeneralDice() {
        final int expectedValue = 15;
        Human human = new Human();
        // assertEquals(0, human.getGeneralDice());
        human.setGeneralDice(expectedValue);
        assertEquals(expectedValue, human.getGeneralDice());
    }

    /**
     * Method testEncumbrance.
     */
    @Test
    public void testEncumbrance() {
        final int expectedValue = 16;
        Human human = new Human();
        assertEquals(0, human.getEncumbrance());
        human.setEncumbrance(expectedValue);
        assertEquals(expectedValue, human.getEncumbrance());
    }

    /**
     * Method testHealth.
     */
    @Test
    public void testHealth() {
        final int expectedValue = 16;
        Human human = new Human();
        assertEquals(0, human.getHealth());
        human.setHealth(expectedValue);
        assertEquals(expectedValue, human.getHealth());
    }

    /**
     * Method testValues.
     */
    @Test
    public void testValues() {
        PcClass warrior = config.getPcClass("testWarrior");
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

    /**
     * Method testAbilityScores.
     */
    @Test
    public void testAbilityScores() {
        // change level with ability scores : default, null and not null.
        // fail("todo");
    }

    /**
     * Method testGetDetailedDescription.
     */
    @Test
    public void testGetDetailedDescription() {
        // vanilla - no mods.
        Human human = new Human();
        assertNotNull("vanilla - detailed description not null",
                human.detailedDescription());

        // null
        human.setName(null);
        human.setDescription(null);
        // TODO human.setSkills(null);
        // TODO human.setPowerWords(null);
        // TODO human.setAbilityScores(null);
        assertNotNull("vanilla - detailed description not null",
                human.detailedDescription());

        // fully populated
        human.setName("The Name");
        human.setGender("Male");
        human.setSize("Large");
        human.setDescription("The Description");
        PcClass warrior = config.getPcClass("testWarrior");
        human.setPcClass(warrior);
        human.setLevel(3);
        human.skillsAddArray(new String[] { "testSkill1", "testSkill2",
                "testSkill3" });
        human.recipesAdd(new String[] { "testItem1", "testFireGround1",
                "testSpell1" });

        assertNotNull("vanilla - detailed description not null",
                human.detailedDescription());
    }

    /**
     * Method testSetAllFrom.
     */
    @Test
    public void testSetAllFrom() {
        // Only test the fields that are implemented at this level.
        Human human = new Human();
        Elf elf = new Elf();

        // Check that everything is in a known state before we start;
        checkBasicsAreZero(human);
        checkBasicsAreZero(elf);

        // Make some changes
        // TODO - add more fields.
        // pcClass - ensure results are not linked
        // abilityScores - ensure results are not linked
        // recipes - ensure results are not linked
        // skills - ensure results are not linked

        final int level = 11;
        final int combatDice = 12;
        final int magicDice = 13;
        final int stealthDice = 14;
        final int generalDice = 15;
        final int actionPoints = 16;
        final int mana = 17;
        final int encumberance = 18;
        final int health = 19;

        human.setLevel(level);
        human.setCombatDice(combatDice);
        human.setMagicDice(magicDice);
        human.setStealthDice(stealthDice);
        human.setGeneralDice(generalDice);
        human.setActionPoints(actionPoints);
        human.setMana(mana);
        human.setEncumbrance(encumberance);
        human.setHealth(health);

        // do the copy
        elf.setAllFrom(human);

        // check the results
        assertEquals("level", level, elf.getLevel());
        assertEquals("combatDice", combatDice, elf.getCombatDice());
        assertEquals("magicDice", magicDice, elf.getMagicDice());
        assertEquals("stealthDice", stealthDice, elf.getStealthDice());
        assertEquals("generalDice", generalDice, elf.getGeneralDice());
        assertEquals("actionPoints", actionPoints, elf.getActionPoints());
        assertEquals("mana", mana, elf.getMana());
        assertEquals("encumbrance", encumberance, elf.getEncumbrance());
        assertEquals("health", health, elf.getHealth());
    }

    /**
     * Method testSetBasicsFromClass.
     */
    @Test
    public void testSetBasicsFromClass() {
        // TODO fail("TEST not yet implemented");
    }

}
