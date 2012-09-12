package test.au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.pc.PcClass;
import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.util.Configuration;

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
	}

	@Test
	public void testTraits() {
	}

	@Test
	public void testSkills() {
		Vector<SkillDetail> skillDetails = config.getSkillDetails();
		assertTrue("some entries", skillDetails.size() >= 2);
		boolean foundChrstnahndrx = false;
		boolean foundSudo = false;
		for (SkillDetail skillDetail : skillDetails) {
			if (skillDetail.getId().equals("CHRSTNAHNDRX")
					& skillDetail.getDescription().equals(
							"The biggest ball of flame")) {
				foundChrstnahndrx = true;
			}
			if (skillDetail.getId().equals("SUDO")
					& skillDetail.getDescription().equals("Doppleganger god")) {
				foundSudo = true;
			}

		}
		assertTrue("CHRSTNAHNDRX", foundChrstnahndrx);
		assertTrue("SUDO", foundSudo);

	}

	@Test
	public void testRecipes() {

		Vector<Recipe> recipes = config.getRecipes();
		assertTrue("ingredients !=null", recipes != null);
		assertEquals("ingredient count", 2, recipes.size());
		for (Recipe recipe : recipes) {
			assertTrue("id", recipe.getId().length() > 0);
			assertTrue("toDescription", recipe.getDescription().length() > 0);
		}

		// recipe0
		Recipe recipe0 = recipes.get(0);
		assertEquals("actionPoints", 42, recipe0.getActionPoints());
		assertEquals("ingredient count", 2, recipe0.getIngredients().size());
		assertEquals("skill count", 1, recipe0.getSkills().size());
		// TODO assertEquals("product count", 1,
		// recipe0.getProducts().size());

		// recipe0
		Recipe recipe1 = recipes.get(1);
		assertEquals("mana", 2, recipe1.getMana());
		assertEquals("actionPoints", 10, recipe1.getActionPoints());
		assertEquals("ingredient count", 0, recipe1.getIngredients().size());
		assertEquals("skill count", 1, recipe1.getSkills().size());
		// TODO assertEquals("product count", 1,
		// recipe1.getProducts().size());
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
