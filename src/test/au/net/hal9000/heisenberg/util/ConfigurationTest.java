package test.au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.units.PowerWordDetail;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class ConfigurationTest {
    Configuration config = null;

    @Before
    public void setUp() {
        try {
            config = new Configuration("test/config/config.xml");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void testCharacter() {
        // TODO testCharacter
    }

    @Test
    public void testTraits() {
        // TODO testTraits
    }

    @Test
    public void testItemClasses() {
        Vector<String> itemClasses = config.getItemClasses();
        assertTrue("count", itemClasses.size() > 7);
    }

    @Test
    public void testRaces() {
        Vector<String> races = config.getRaces();
        assertEquals("count", 3, races.size());
    }

    @Test
    public void testGenders() {
        Vector<String> genders = config.getGenders();
        assertEquals("count", 7, genders.size());
    }

    @Test
    public void testPowerWords() {
        TreeMap<String, PowerWordDetail> skillDetails = config
                .getPowerWordDetails();
        PowerWordDetail chrstnahndrx = skillDetails.get("CHRSTNAHNDRX");
        assertEquals("CHRSTNAHNDRX-id", "CHRSTNAHNDRX", chrstnahndrx.getId());
        assertEquals("CHRSTNAHNDRX-Description", "The biggest ball of flame",
                chrstnahndrx.getDescription());
        PowerWordDetail sudo = skillDetails.get("SUDO");
        assertEquals("SUDO-id", "SUDO", sudo.getId());
        assertEquals("SUDO-Description", "Doppleganger god",
                sudo.getDescription());
    }

    @Test
    public void testSkills() {
        TreeMap<String, SkillDetail> skillDetails = config.getSkillDetails();
        SkillDetail fireLighting = skillDetails.get("FireLighting");
        assertEquals("FireLighting-id", "FireLighting", fireLighting.getId());
        assertEquals("FireLighting-Description",
                "lighting a fire, typically with flint and tinder",
                fireLighting.getDescription());
    }

    @Test
    public void testRecipes() {

        TreeMap<String, Recipe> recipes = config.getRecipes();
        assertTrue("ingredients !=null", recipes != null);
        assertTrue("ingredient count", 2 <= recipes.size());
        for (Recipe recipe : recipes.values()) {
            assertTrue("id", recipe.getId().length() > 0);
            assertTrue("toDescription", recipe.getDescription().length() > 0);
        }

        // recipe1
        Recipe recipe1 = recipes.get("testItem1");

        assertEquals("description", "item test 1", recipe1.getDescription());
        assertEquals("process", "createItem1", recipe1.getProcess());
        assertEquals("actionPoints", 10, recipe1.getActionPoints());
        assertEquals("ingredient count", 0, recipe1.getIngredients().size());
        assertEquals("powerWord count", 1, recipe1.getPowerWords().size());
        assertEquals("skill count", 1, recipe1.getSkills().size());
        assertEquals("product count", 1, recipe1.getProducts().size());
        assertEquals("product type", "Cookie", recipe1.getProducts().get(0));

        // recipe2
        Recipe recipe2 = recipes.get("testSpell1");
        assertEquals("description", "spell test 1", recipe2.getDescription());
        assertEquals("process", "spellTest1", recipe2.getProcess());
        assertEquals("mana", 2, recipe2.getMana());
        assertEquals("actionPoints", 10, recipe2.getActionPoints());
        assertEquals("ingredient count", 0, recipe2.getIngredients().size());
        assertEquals("powerWord count", 1, recipe2.getPowerWords().size());
        assertTrue(
                "powerWord 0",
                recipe2.getPowerWords().contains(
                        new PowerWord("testPowerWord1")));
        assertEquals("skill count", 0, recipe2.getSkills().size());
        assertEquals("product count", 0, recipe2.getProducts().size());
    }

    @Test
    public void testValues() {
        PcClass warrior = config.getPcClass("Warrior");
        // System.out.print(warrior);

        assertEquals("Warrior", warrior.getId());
        // dice
        assertEquals(20, warrior.getCombatDice());
        assertEquals(4, warrior.getMagicDice());
        assertEquals(12, warrior.getStealthDice());
        assertEquals(8, warrior.getGeneralDice());
        // misc
        assertEquals(8, warrior.getActionPoints());
        assertEquals(7, warrior.getHealth());
        assertEquals(0, warrior.getMana());
        assertEquals(100, warrior.getEncumbrance());
        // Attributes
        assertEquals(0, warrior.getAbilityScore("Dweomer Lore").getValue());
        assertEquals(0, warrior.getAbilityScore("Dweomer Lore").getMod());
        assertEquals(0, warrior.getAbilityScore("Mechanisms").getValue());
        assertEquals(0, warrior.getAbilityScore("Mechanisms").getMod());
        assertEquals(0, warrior.getAbilityScore("Magical Attack").getValue());
        assertEquals(0, warrior.getAbilityScore("Magical Attack").getMod());
        assertEquals(2, warrior.getAbilityScore("Crafting").getValue());
        assertEquals(0, warrior.getAbilityScore("Crafting").getMod());
        assertEquals(4, warrior.getAbilityScore("Perception").getValue());
        assertEquals(0, warrior.getAbilityScore("Perception").getMod());
        assertEquals(1, warrior.getAbilityScore("Trickery").getValue());
        assertEquals(1, warrior.getAbilityScore("Trickery").getMod());
        assertEquals(3, warrior.getAbilityScore("Ranged Attack").getValue());
        assertEquals(1, warrior.getAbilityScore("Ranged Attack").getMod());
        assertEquals(0, warrior.getAbilityScore("Enchantment").getValue());
        assertEquals(0, warrior.getAbilityScore("Enchantment").getMod());
        assertEquals(2, warrior.getAbilityScore("Advantage Attack").getValue());
        assertEquals(0, warrior.getAbilityScore("Advantage Attack").getMod());
        assertEquals(3, warrior.getAbilityScore("Resistance").getValue());
        assertEquals(0, warrior.getAbilityScore("Resistance").getMod());
        assertEquals(2, warrior.getAbilityScore("Appraisal").getValue());
        assertEquals(0, warrior.getAbilityScore("Appraisal").getMod());
        assertEquals(2, warrior.getAbilityScore("Magic Defence").getValue());
        assertEquals(0, warrior.getAbilityScore("Magic Defence").getMod());
        assertEquals(4, warrior.getAbilityScore("Melee Attack").getValue());
        assertEquals(1, warrior.getAbilityScore("Melee Attack").getMod());
        assertEquals(3, warrior.getAbilityScore("Combat Defence").getValue());
        assertEquals(1, warrior.getAbilityScore("Combat Defence").getMod());
        assertEquals(3, warrior.getAbilityScore("Sneak").getValue());
        assertEquals(1, warrior.getAbilityScore("Sneak").getMod());
        // TODO required?
        // assertEquals("~", warrior.getGenderAllow());
        // assertEquals("~", warrior.getSizeAllow());
        // assertEquals("~", warrior.getRaceAllow());
    }

}
