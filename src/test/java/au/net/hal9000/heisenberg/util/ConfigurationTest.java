package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Product;
import au.net.hal9000.heisenberg.crafting.ProductEntityProperty;
import au.net.hal9000.heisenberg.crafting.ProductItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;

/**
 */
public class ConfigurationTest {
    /** float comparison tolerance. */
    private static final float TOLERANCE = 0.00001f;
    /**
     * Field config.
     */
    private Configuration config = null;

    /**
     * Method setUp.
     */
    @Before
    public void setUp() {
        TestEnvironment.setup();
        config = Configuration.lastConfig();
    }

    /**
     * Method testCharacter.
     */
    @Test
    public void testCharacter() {
        // TODO testCharacter
    }

    /**
     * Method testItemClasses.
     */
    @Test
    public void testItemClasses() {
        final int minClasses = 8;
        Map<String,ItemClassConfiguration> itemClasses = config.getItemClasses();
        assertTrue("count", itemClasses.size() >= minClasses);
    }

    /**
     * Method testGetItemClassIds.
     */
    @Test
    public void testGetItemClassIds() {
        final int minClasses = 8;
        Set<String> ids = config.getItemClassIds();
        assertTrue("count", ids.size() >= minClasses);
    }

    /**
     * Method testSpriteSheets.
     */
    @Test
    public void testSpriteSheets() {
        Map<String, SpriteSheetConfiguration> spriteSheets = config
                .getSpriteSheets();
        assertTrue("count", spriteSheets.size() > 0);
    }

    /**
     * Method testSpriteSheetGet.
     */
    @Test
    public void testSpriteSheetGet() {
        SpriteSheetConfiguration details = config.getSpriteSheet("item");
        assertEquals("item", details.getId());
        assertEquals("images/icon/IconSet-01.png", details.getFilename());
        assertEquals(24, details.getWidth());
        assertEquals(24, details.getHeight());
        assertEquals(33, details.getRows());
        assertEquals(16, details.getColumns());
    }

    /**
     * Method testRaces.
     */
    @Test
    public void testRaces() {
        List<String> races = config.getRaces();
        assertEquals("count", 3, races.size());
    }

    /**
     * Method testSizes.
     */
    @Test
    public void testSizes() {
        List<String> sizes = config.getSizes();
        assertEquals("count", 3, sizes.size());
    }

    /**
     * Method testGenders.
     */
    @Test
    public void testGenders() {
        List<String> genders = config.getGenders();
        assertEquals("count", 4, genders.size());
    }

    /**
     * Method testSkills.
     */
    @Test
    public void testSkills() {
        Map<String, SkillDetail> skillDetails = config.getSkillDetails();
        SkillDetail fireLighting = skillDetails.get("testFireLighting");
        assertEquals("FireLighting-id", "testFireLighting",
                fireLighting.getId());
        assertEquals("FireLighting-Description",
                "lighting a fire, typically with flint and tinder",
                fireLighting.getDescription());
    }

    /**
     * Method testRecipeRequirementItem.
     */
    @Test
    public void testRecipeRequirementItem() {
        Recipe recipe1 = config.getRecipe("testItem1");
        assertEquals("requirement count", 2, recipe1.getRequirementCount());
        RequirementItem requirementItem = (RequirementItem) recipe1
                .getRequirement("Location");
        assertNotNull("requirement not null", requirementItem);
        assertEquals("requirement id", "Location", requirementItem.getId());
        assertEquals("requirement itemType", "Location",
                requirementItem.getItemType());
    }

    /**
     * Method testXmlToRecipeProductItems.
     */
    @Test
    public void testXmlToRecipeProductItems() {
        Recipe recipe1 = config.getRecipe("testItem1");
        assertEquals("product count", 1, recipe1.getProductCount());
        ProductItem product0 = (ProductItem) recipe1.getProduct(0);
        assertNotNull("product0 not null", product0);
        assertEquals("product0 id", "Cookie", product0.getId());
        assertEquals("product0 itemType", "Cookie", product0.getType());
        assertEquals("product0 getWeightBase", 1, product0.getWeightBase(),
                TOLERANCE);

    }

    /**
     * Method testXmlToRecipeProductProperty.
     */
    @Test
    public void testXmlToRecipeProductProperty() {
        Recipe recipe1 = config.getRecipe("testDrinkWater");
        assertEquals("product count", 1, recipe1.getProductCount());
        ProductEntityProperty product0 = (ProductEntityProperty) recipe1
                .getProduct(0);
        assertNotNull("product0 not null", product0);
        assertEquals("product0 id", "HydrationId", product0.getId());
        assertEquals("product0 propertyName", "hydration",
                product0.getPropertyName());
        assertEquals("product0 propertyDelta", 15.0f,
                product0.getPropertyDelta(), TOLERANCE);
    }

    /**
     * Method testRecipes.
     */
    @Test
    public void testRecipes() {
        Map<String, Recipe> recipes = config.getRecipes();
        assertNotNull("ingredients !=null", recipes);
        assertTrue("requirement count", 2 <= recipes.size());
        for (Recipe recipe : recipes.values()) {
            assertTrue("id", recipe.getId().length() > 0);
            assertTrue("toDescription", recipe.getDescription().length() > 0);
        }
    }

    /**
     * Method testRecipeTestItem1.
     */
    @Test
    public void testRecipeTestItem1() {
        Recipe recipe = config.getRecipe("testItem1");
        assertEquals("Id", "testItem1", recipe.getId());
        assertEquals("Description", "item test 1", recipe.getDescription());
        assertEquals("Mana", 2, recipe.getMana());
        assertEquals("ActionPoints", 10, recipe.getActionPoints());
        assertEquals("Process", "createItem1", recipe.getProcess());
        assertEquals("Skill Count", 1, recipe.getSkillCount());
        Set<Skill> skills = recipe.getSkills();
        assertTrue("Skill 0 ID", skills.contains(new Skill("testSkill1")));
        assertEquals("Requirement count", 2, recipe.getRequirementCount());
        // Requirement 0
        Requirement requirement = recipe.getRequirement("Location");
        if (!(requirement instanceof RequirementItem)) {
            fail("Expecting 0 RequirementItem");
        }
        RequirementItem requirementItem = (RequirementItem) requirement;
        assertEquals("Requirement 0 Id", "Location", requirementItem.getId());
        assertEquals("Requirement 0 Type", "Location",
                requirementItem.getItemType());
        assertEquals("Requirement 0 Consumed", false,
                requirementItem.isConsumed());
        assertEquals("Requirement 0 weightMin", 0.0f,
                requirementItem.getWeightMin(), 0.0001);
        // Requirement 1
        Requirement requirement1 = recipe.getRequirement("Water1");
        if (!(requirement1 instanceof RequirementItem)) {
            fail("Expecting 1 RequirementItem");
        }
        RequirementItem requirementItem1 = (RequirementItem) requirement1;
        assertEquals("Requirement 1 Id", "Water1", requirementItem1.getId());
        assertEquals("Requirement 1 Type", "Water",
                requirementItem1.getItemType());
        assertEquals("Requirement 1 Consumed", true,
                requirementItem1.isConsumed());
        assertEquals("Requirement 1 weightMin", 3.0f,
                requirementItem1.getWeightMin(), 0.0001);

        // Products
        assertEquals("Product count", 1, recipe.getProductCount());
        // Product 0
        Product product = recipe.getProduct(0);
        if (!(product instanceof ProductItem)) {
            fail("Expecint ProcutItem");
        }
        ProductItem productItem = (ProductItem) product;
        assertEquals("Product 0 Id", "Cookie", productItem.getId());
        assertEquals("Product 0 Type", "Cookie", productItem.getType());
        assertEquals("Product 0 weightBase", 1, productItem.getWeightBase(),
                0.0001f);

    }

    /**
     * Method testFireGround1.
     */
    @Test
    public void testFireGround1() {
        Map<String, Recipe> recipes = config.getRecipes();
        Recipe recipe = recipes.get("testFireGround1");
        assertEquals("description", "small open ground fire",
                recipe.getDescription());
        assertEquals("process", "createItem1", recipe.getProcess());
        assertEquals("mana", 0, recipe.getMana());
        assertEquals("actionPoints", 42, recipe.getActionPoints());
        assertEquals("requirement count", 3, recipe.getRequirementCount());
        // TODO requirement
        assertEquals("skill count", 1, recipe.getSkillCount());
        assertTrue("skill 0",
                recipe.getSkills().contains(new Skill("testFireLighting")));
        assertEquals("product count", 1, recipe.getProductCount());
        Product product = recipe.getProduct(0);
        assertNotNull("product 0 not null", product);
        assertTrue("product 0 ProductItem", product instanceof ProductItem);
        ProductItem productItem = (ProductItem) product;
        assertEquals("product 0 id", "SmallGroundFire", productItem.getId());
        assertEquals("product 0 itemType", "SmallGroundFire",
                productItem.getType());
    }

    /**
     * Method testSpell1.
     */
    @Test
    public void testSpell1() {
        Map<String, Recipe> recipes = config.getRecipes();
        Recipe recipe = recipes.get("testSpell1");
        assertEquals("description", "spell test 1", recipe.getDescription());
        assertEquals("process", "testSpell1", recipe.getProcess());
        assertEquals("mana", 2, recipe.getMana());
        assertEquals("actionPoints", 10, recipe.getActionPoints());
        assertEquals("requirement count", 0, recipe.getRequirementCount());
        assertEquals("skill count", 0, recipe.getSkillCount());
        assertEquals("product count", 0, recipe.getProductCount());

    }

    /**
     * Method testValues.
     */
    @Test
    public void testValues() {
        PcClass warrior = config.getPcClass("testWarrior");

        assertEquals("testWarrior", warrior.getId());
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
