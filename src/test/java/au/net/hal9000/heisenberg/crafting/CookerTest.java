package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.*;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class CookerTest {
    static int REQUIRED_ACTION_POINTS = 42;
    static int REQUIRED_MANA = 3;
    static String[] REQUIRED_POWERWORDS = new String[] { "PowerWord0",
            "PowerWord", "PowerWord2" };
    static String[] REQUIRED_SKILLS = new String[] { "Skill0", "Skill1",
            "Skill2" };

    Configuration config = null;

    @Before
    public void initialize() throws ConfigurationError {
        config = new Configuration("src/test/resources/config.xml");
    }

    /**
     * Magically create a Cookie. e.g. using only magic and time.
     * An example of common use - create an Item.
     */

    @Test
    public void testItemCreation() {
        Recipe recipe = config.getRecipe("testItem1");

        // Setup the chef
        Location world = new Location("World");
        PcRace chef = new Human();
        chef.setLocation(world);
        chef.setMana(recipe.getMana() + 1);
        chef.setActionPoints(recipe.getActionPoints() + 2);
        chef.powerWordsAdd(new PowerWord("testPowerWord1"));
        chef.skillsAdd(new Skill("testSkill1"));
        chef.recipesAdd("testItem1");

        // Prepare to cast a spell
        Cooker cooker = chef.getCooker("testItem1");
        Location newItemLocation = new Location("New Item Location");
        assertEquals("set Location", null,
                cooker.setItemsAvailable("Location", newItemLocation));
        assertEquals("cook works", null, cooker.cook());

        assertEquals("location has new item", 1,
                newItemLocation.getChildCount());
        // Check the item produced
        Item item = newItemLocation.getChild(0);
        assertEquals("item type", "Cookie", item.getClass().getSimpleName());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", 1, chef.getMana());
        assertEquals("actionPoints", 2, chef.getActionPoints());
    }

    /**
     * An example of common use - A spell.
     */

    @Test
    public void testSpell() {

    	final int manaRemaining = 1;
    	final int actionPointsRemaining = 2;
        Recipe recipe = config.getRecipe("testSpell1");

        // Setup the chef
        Location world = new Location("World");
        PcRace chef = new Human();
        chef.setLocation(world);
        chef.setMana(recipe.getMana() + manaRemaining);
        chef.setActionPoints(recipe.getActionPoints() + actionPointsRemaining);
        chef.powerWordsAdd(new PowerWord("testPowerWord1"));
        chef.recipesAdd("testSpell1");

        // Prepare to cast a spell
        Cooker cooker = chef.getCooker("testSpell1");
        assertEquals("cook works", null, cooker.cook());

        // TODO - check the spell method was run.

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", manaRemaining, chef.getMana());
        assertEquals("actionPoints", actionPointsRemaining, chef.getActionPoints());
    }

    /**
     * Mixture of everything.<br>
     * This is a stand-along test. Doesn't require config.
     * 
     */
    @Test
    public void testFullProcess() {

        Location world = new Location("World");
        Cookie cookie = new Cookie();
        world.add(cookie);

        TreeMap<String, Requirement> requirements = new TreeMap<String, Requirement>();

        // RequirementItem(s)
        RequirementItem requirementItem = new RequirementItem("Cookie");
        requirements.put(requirementItem.getId(), requirementItem);
        RequirementItem requirementLocation = new RequirementItem(
                "ItemContainer");
        requirements.put("Location", requirementLocation);

        // Product(s)
        Vector<String> products = new Vector<String>();
        products.add("Cookie");

        // PowerWord(s)
        Set<PowerWord> powerWords = new TreeSet<PowerWord>();
        for (int i = REQUIRED_POWERWORDS.length - 1; i >= 0; i--) {
            powerWords.add(new PowerWord(REQUIRED_POWERWORDS[i]));
        }

        // Skill(s)
        Set<Skill> skills = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skills.add(new Skill(REQUIRED_SKILLS[i]));
        }

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("magicCookie1", "a magic cookie", null,
                REQUIRED_MANA, REQUIRED_ACTION_POINTS, requirements,
                powerWords, skills, products);

        // Set the chef
        PcRace chef = new Human();

        chef.setMana(REQUIRED_MANA + 1);
        chef.setActionPoints(REQUIRED_ACTION_POINTS + 2);
        chef.powerWordsAdd(REQUIRED_POWERWORDS);
        chef.skillsAdd(REQUIRED_SKILLS);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        // Set the
        Location newItemLocation = new Location("newItemLocation");
        assertEquals("set Location", null,
                cooker.setItemsAvailable("Location", newItemLocation));

        assertEquals("make item available", null,
                cooker.setItemsAvailable("Cookie", cookie));

        // Do the cooking
        assertEquals("cook works", null, cooker.cook());

        // Does the location now contain a Cookie?
        assertEquals("1 item", 1, newItemLocation.getChildCount());
        Item newItem = newItemLocation.getChild(0);
        assertEquals("item type", "Cookie", newItem.getClass().getSimpleName());
        assertEquals("new item location", newItemLocation,
                newItem.getLocation());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", 1, chef.getMana());
        assertEquals("actionPoints", 2, chef.getActionPoints());

    }

    /**
     * Testing RequirementItem objects.<br>
     * No actual cooking done (yet)
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testRecipeIngredients() {

        TreeMap<String, Requirement> requirements = new TreeMap<String, Requirement>();

        // RequirementItem
        // FlintAndTinder (not consumed)
        // Wood, min weight 3.
        RequirementItem ingredientFlintAndTinder = new RequirementItem(
                "FlintAndTinder");
        ingredientFlintAndTinder.setConsumed(false);
        requirements.put(ingredientFlintAndTinder.getId(),
                ingredientFlintAndTinder);

        RequirementItem ingredientWood = new RequirementItem("Wood");
        ingredientWood.setWeightMin(3);
        requirements.put(ingredientWood.getId(), ingredientWood);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0,
                requirements, null, null, null);
        Cooker cooker = recipe.getNewCooker();

        assertEquals("requirement count", requirements.size(),
                cooker.getRequirementCount());
        assertEquals("requirement flintAndTinder", ingredientFlintAndTinder,
                cooker.getRequirement(ingredientFlintAndTinder.getId()));
        assertEquals("requirement wood", ingredientWood,
                cooker.getRequirement(ingredientWood.getId()));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testRecipeItemsAvailable() {

        Location world = new Location("world");
        world.setVolumeMax(-1);
        world.setWeightMax(-1);
        TreeMap<String, Requirement> requirements = new TreeMap<String, Requirement>();

        // RequirementItem
        // FlintAndTinder, not consumed
        FlintAndTinder flintAndTinder = new FlintAndTinder();
        world.add(flintAndTinder);
        RequirementItem ingredientFlintAndTinder = new RequirementItem(
                "FlintAndTinder");
        ingredientFlintAndTinder.setConsumed(false);
        requirements.put(ingredientFlintAndTinder.getId(),
                ingredientFlintAndTinder);

        // Wood, min weight 3
        Wood wood = new Wood();
        wood.setWeightBase(3);
        world.add(wood);
        RequirementItem ingredientWood = new RequirementItem("Wood");
        ingredientWood.setWeightMin(3);
        requirements.put(ingredientWood.getId(), ingredientWood);

        // Wood2
        Wood wood2 = new Wood();
        world.add(wood2);
        RequirementItem ingredientWood2 = new RequirementItem("Wood");
        requirements.put("Wood2", ingredientWood2);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0,
                requirements, null, null, null);
        Cooker cooker = recipe.getNewCooker();

        // Item - FlintAndTinder
        assertEquals("item 0 - missing item and location",
                Cooker.itemMayNotBeNull,
                cooker.setItemsAvailable("FlintAndTinder", null));
        assertEquals("item 0 - wrong type", "item must be a FlintAndTinder",
                cooker.setItemsAvailable("FlintAndTinder", wood));
        assertEquals("item 0 - good", null,
                cooker.setItemsAvailable("FlintAndTinder", flintAndTinder));
        FlintAndTinder flintAndTinder2 = new FlintAndTinder();
        world.add(flintAndTinder2);
        assertEquals("item 0 - already occupied", Cooker.alreadyOccupied,
                cooker.setItemsAvailable("FlintAndTinder", flintAndTinder2));
        assertTrue("item 0 - put item back",
                cooker.clearItemsAvailable("FlintAndTinder", world));
        assertEquals("item 0 - good", null,
                cooker.setItemsAvailable("FlintAndTinder", flintAndTinder2));
        assertEquals("item 0 - item removed from Container", cooker,
                flintAndTinder2.getLocation());
        // Item - Wood
        assertEquals("item 1 - good", null,
                cooker.setItemsAvailable("Wood", wood));

        // Item - Wood2
        // test that an item can't be added to cooker more than once.
        assertEquals("item 2 - already in Cooker",
                Cooker.alreadyContainsThatItem,
                cooker.setItemsAvailable("Wood2", wood));
        assertEquals("item 2 - good", null,
                cooker.setItemsAvailable("Wood2", wood2));

        // misc
        assertEquals("item count", 3, cooker.getContentsCount());
        // Only the FlintAndTinder2
        assertEquals("world count before", 1, world.getChildCount());
        cooker.empty(world);
        assertEquals("item count", 0, cooker.getContentsCount());
        assertEquals("world count after", 4, world.getContentsCount());

    }

    /**
     * ID: test-general-null <BR>
     * Desc: Null case. No requirements.<br>
     * Require: none <BR>
     * Input: none <BR>
     * Result: <BR>
     * overall : pass <BR>
     * output: no change <BR>
     */
    @Test
    public void testCookGeneralNull() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-general-null",
                "Null case. No requirements", null, 0, 0, null, null, null,
                null);
        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        assertEquals("cook works", null, cooker.cook());
    }

    /**
     * Test setting the chef
     */
    @Test
    public void testSetChef() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-general-null",
                "Null case. No requirements", null, 0, 0, null, null, null,
                null);

        // Set the chef
        PcRace chef = new Human();

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);
        // Living is passing test.
    }

    /**
     * ID: test-mana-1 <BR>
     * Desc: Mana test, just enough. <BR>
     * Req: mana 3 <BR>
     * Input: mana 3 <BR>
     * Result: <BR>
     * overall : pass <BR>
     * output: mana=0 <BR>
     */
    @Test
    public void testCookGeneralMana1() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-mana-1", "Mana test, just enough.",
                null, 1, 0, null, null, null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setMana(1);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * Mini tests for matching: <BR>
     * <BR>
     * ID: test-mana-2 <BR>
     * Desc: Mana test, not enough. <BR>
     * As per test-mana-1 <BR>
     * Input: mana 2 <BR>
     * overall: fail <BR>
     * output: mana 2 <BR>
     */

    @Test
    public void testCookGeneralMana2() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-mana-2", "Mana test, not enough.",
                null, 3, 0, null, null, null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setMana(2);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", "Not enough mana", cooker.cook());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", 2, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-mana-3 <BR>
     * Desc: Mana test, more than enough. <BR>
     * As per mana-test1 <BR>
     * Input: mana 4 <BR>
     * overall: pass <BR>
     * output: mana 1 <BR>
     */

    @Test
    public void testCookGeneralMana3() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-mana-3",
                "Mana test, more than enough.", null, 3, 0, null, null, null,
                null);

        // Set the chef
        PcRace chef = new Human();
        chef.setMana(4);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", 1, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-actionPoints-1 <BR>
     * Desc: ActionPoints test, just enough. <BR>
     * Req: actionPoints 3 <BR>
     * Input: actionPoints 3 <BR>
     * Result: <BR>
     * overall : pass <BR>
     * output: actionPoints=0 <BR>
     */
    @Test
    public void testCookGeneralActionPoints1() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-actionPoints-1",
                "ActionPoints test, just enough.", null, 0, 1, null, null,
                null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setActionPoints(1);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        // Set the

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * Mini tests for matching: <BR>
     * <BR>
     * ID: test-actionPoints-2 <BR>
     * Desc: ActionPoints test, not enough. <BR>
     * As per test-actionPoints-1 <BR>
     * Input: actionPoints 2 <BR>
     * overall: fail <BR>
     * output: actionPoints 2 <BR>
     */

    @Test
    public void testCookGeneralActionPoints2() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-actionPoints-2",
                "ActionPoints test, not enough.", null, 0, 3, null, null, null,
                null);

        // Set the chef
        PcRace chef = new Human();
        chef.setActionPoints(2);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", "Not enough action points", cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 2, chef.getActionPoints());

    }

    /**
     * ID: test-actionPoints-3 <BR>
     * Desc: ActionPoints test, more than enough. <BR>
     * As per actionPoints-test1 <BR>
     * Input: actionPoints 4 <BR>
     * overall: pass <BR>
     * output: actionPoints 1 <BR>
     */

    @Test
    public void testCookGeneralActionPoints3() {

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-actionPoints-3",
                "ActionPoints test, more than enough.", null, 0, 3, null, null,
                null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setActionPoints(4);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 1, chef.getActionPoints());

    }

    /**
     * ID: test-powerWords-1 <BR>
     * Desc: PowerWord test, just enough <BR>
     * Equal powerWords to requirements <BR>
     * overall: true <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookPowerWords1() {

        // PowerWords
        Set<PowerWord> powerWordsRequired = new TreeSet<PowerWord>();
        Set<PowerWord> powerWordsGot = new TreeSet<PowerWord>();
        for (int i = REQUIRED_POWERWORDS.length - 1; i >= 0; i--) {
            powerWordsRequired.add(new PowerWord(REQUIRED_POWERWORDS[i]));
            powerWordsGot.add(new PowerWord(REQUIRED_POWERWORDS[i]));
        }

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-powerWords-1",
                "PowerWord test, just enough.", null, 0, 0, null,
                powerWordsRequired, null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setPowerWords(powerWordsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-powerWords-2 <BR>
     * Desc: Less powerWords than required <BR>
     * overall: fail <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookPowerWords2() {

        // PowerWords
        Set<PowerWord> powerWordsRequired = new TreeSet<PowerWord>();
        Set<PowerWord> powerWordsGot = new TreeSet<PowerWord>();
        for (int i = REQUIRED_POWERWORDS.length - 1; i >= 0; i--) {
            powerWordsRequired.add(new PowerWord(REQUIRED_POWERWORDS[i]));
            powerWordsGot.add(new PowerWord(REQUIRED_POWERWORDS[i]));
        }
        powerWordsRequired.add(new PowerWord("MissingPowerWord"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-powerWords-2",
                "Less powerWords than required", null, 0, 0, null,
                powerWordsRequired, null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setPowerWords(powerWordsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", "Missing Power Words", cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-powerWords-3 <BR>
     * Desc: More powerWords than required <BR>
     * overall: true <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookPowerWords3() {

        // PowerWords
        Set<PowerWord> powerWordsRequired = new TreeSet<PowerWord>();
        Set<PowerWord> powerWordsGot = new TreeSet<PowerWord>();
        for (int i = REQUIRED_POWERWORDS.length - 1; i >= 0; i--) {
            powerWordsRequired.add(new PowerWord(REQUIRED_POWERWORDS[i]));
            powerWordsGot.add(new PowerWord(REQUIRED_POWERWORDS[i]));
        }
        powerWordsGot.add(new PowerWord("MissingPowerWord"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-powerWords-3",
                "More powerWords than required", null, 0, 0, null,
                powerWordsRequired, null, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setPowerWords(powerWordsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-skills-1 <BR>
     * Desc: Skill test, just enough <BR>
     * Equal skills to requirements <BR>
     * overall: true <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookSkills1() {

        // Skills
        Set<Skill> skillsRequired = new TreeSet<Skill>();
        Set<Skill> skillsGot = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skillsRequired.add(new Skill(REQUIRED_SKILLS[i]));
            skillsGot.add(new Skill(REQUIRED_SKILLS[i]));
        }

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-skills-1", "Skill test, just enough.",
                null, 0, 0, null, null, skillsRequired, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setSkills(skillsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-skills-2 <BR>
     * Desc: Less skills than required <BR>
     * overall: fail <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookSkills2() {

        // Skills
        Set<Skill> skillsRequired = new TreeSet<Skill>();
        Set<Skill> skillsGot = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skillsRequired.add(new Skill(REQUIRED_SKILLS[i]));
            skillsGot.add(new Skill(REQUIRED_SKILLS[i]));
        }
        skillsRequired.add(new Skill("MissingSkill"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-skills-2",
                "Less skills than required", null, 0, 0, null, null,
                skillsRequired, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setSkills(skillsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", "Missing Skills", cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-skills-3 <BR>
     * Desc: More skills than required <BR>
     * overall: true <BR>
     * output: no other change <BR>
     */

    @Test
    public void testCookSkills3() {

        // Skills
        Set<Skill> skillsRequired = new TreeSet<Skill>();
        Set<Skill> skillsGot = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skillsRequired.add(new Skill(REQUIRED_SKILLS[i]));
            skillsGot.add(new Skill(REQUIRED_SKILLS[i]));
        }
        skillsGot.add(new Skill("MissingSkill"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-skills-3",
                "More skills than required", null, 0, 0, null, null,
                skillsRequired, null);

        // Set the chef
        PcRace chef = new Human();
        chef.setSkills(skillsGot);

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

    }

    /**
     * ID: test-item-1 <BR>
     * Desc: simplest test of one required item. <BR>
     * Require: Cookie <BR>
     * Input: Cookie <BR>
     * overall: true <BR>
     * output: The Cookie is consumed. <BR>
     */

    @Test
    public void TestCookItem1() {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        world.add(cookie);

        // RequirementItem
        TreeMap<String, Requirement> requirements = new TreeMap<String, Requirement>();
        RequirementItem requirementItem = new RequirementItem("Cookie");
        requirements.put(requirementItem.getId(), requirementItem);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-item-1",
                "simplest test of one required item", null, 0, 0, requirements,
                null, null, null);

        // Set the chef
        PcRace chef = new Human();

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);

        // Add an item.
        assertEquals("make item available", null,
                cooker.setItemsAvailable("Cookie", cookie));

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

        // Check the cooker has no items left.
        assertEquals("cooker item count", 0, cooker.getContentsCount());

        // TODO Assistance required - test for destroyed object
    }

    /**
     * <BR>
     * ID: test-item-1-catalyst <BR>
     * Desc: simplest test of one required item NOT consumed <BR>
     * Require: Cookie <BR>
     * Input: Cookie <BR>
     * overall: true <BR>
     * output: The Cookie is NOT consumed. <BR>
     */

    @SuppressWarnings("deprecation")
    @Test
    public void TestCookItem1Catalyst() {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        world.add(cookie);

        // RequirementItem
        TreeMap<String, Requirement> requirements = new TreeMap<String, Requirement>();
        RequirementItem requirementItem = new RequirementItem("Cookie");
        requirementItem.setConsumed(false);
        requirements.put(requirementItem.getId(), requirementItem);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("test-item-11-catalyst",
                "simplest test of one required item NOT consumed", null, 0, 0,
                requirements, null, null, null);

        // Set the chef
        PcRace chef = new Human();

        // Get a cooker
        Cooker cooker = recipe.getNewCooker();
        cooker.setChef(chef);
        assertEquals("make item available", null,
                cooker.setItemsAvailable("Cookie", cookie));

        assertEquals("cook works", null, cooker.cook());

        // Check the chef has paid in ActionPoints and ActionPoints
        assertEquals("mana", 0, chef.getMana());
        assertEquals("actionPoints", 0, chef.getActionPoints());

        // Check the cooker has one items left.
        assertEquals("cooker item count", 1, cooker.getContentsCount());
    }

    // TODO Implement Test
    /**
     * ID: test-item-2 <BR>
     * Desc: Multiple items of same type required but not enough present. <BR>
     * Require: <BR>
     * 3 x Cookie <BR>
     * Input: 2 x Cookie <BR>
     * overall: fail <BR>
     * output: No change. <BR>
     */
    // TODO Implement Test
    /**
     * ID: test-item-weightMin-1 <BR>
     * Require: Cookie weighing at least 2 units. <BR>
     * Input: Cookie weighing at least 2 units. <BR>
     * overall: true <BR>
     * output: Cookie is consumed. <BR>
     */
    // TODO Implement Test
    /**
     * ID: test-item-weightMin-2 <BR>
     * Desc: Simplest test for minimum required weight <BR>
     * Require: Cookie weighing at least 2.0001 units. <BR>
     * Input: Cookie weighing at least 2 units. <BR>
     * overall: fail <BR>
     * output: Cookie is untouched. <BR>
     */
    // TODO Implement Test
    /**
     * ID: test-item-weightMin-complex-1 <BR>
     * Require: <BR>
     * a) Cookie weighing at least 2 units. <BR>
     * b) Cookie weighing at least 3 units. <BR>
     * Input: <BR>
     * a) Cookie weighing 2 units. <BR>
     * b) Cookie weighing 3 units. <BR>
     * overall: true <BR>
     * output: Cookies consumed <BR>
     */

}
