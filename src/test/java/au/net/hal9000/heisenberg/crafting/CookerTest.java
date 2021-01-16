package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.Wood;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;

/** */
public class CookerTest {

  /** test amount of action points. */
  private static final int REQUIRED_ACTION_POINTS = 42;

  /** test amount of mana. */
  private static final int REQUIRED_MANA = 3;
  /** Field REQUIRED_SKILLS. */
  private static final String[] REQUIRED_SKILLS = new String[] {"Skill0", "Skill1", "Skill2"};

  /** Field config. */
  private Configuration config = null;

  /** Method setUp. */
  @Before
  public void setUp() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  /**
   * Magically create a Cookie. e.g. using only magic and time. An example of common use - create an
   * Item.
   */
  @Test
  public void testItemCreation() {
    Recipe recipe = config.getRecipe("testItem1");

    // Setup the chef
    Location world = new Location("World");
    Race chef = new Human();
    chef.setContainer(world);
    chef.setMana(recipe.getMana() + 1);
    chef.setActionPoints(recipe.getActionPoints() + 2);
    chef.skillsAdd(new Skill("testSkill1"));
    chef.recipeAdd("testItem1");

    // Prepare to cook
    Cooker cooker = chef.getCooker("testItem1");
    Location newItemLocation = new Location("New Item Location");
    cooker.setItemsAvailable("Location", newItemLocation);
    Water water = new Water();
    water.setWeightBase(3);
    cooker.setItemsAvailable("Water1", water);

    // Cook
    cooker.cook();

    // Check the item produced
    assertEquals("location has new item", 1, newItemLocation.size());
    Item item = newItemLocation.get(0);
    assertEquals("item type", "Cookie", item.getClass().getSimpleName());

    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", 1, chef.getMana());
    assertEquals("actionPoints", 2, chef.getActionPoints());
  }

  /** test findIngredientByName. */
  @Test
  public void testFindIngredientByName() {
    RequirementItem requirementItem = new RequirementItem("Location", "Location", false);
    Map<String, Requirement> requirements = new TreeMap<>();
    requirements.put("Location", requirementItem);
    Recipe recipe = new Recipe("someId", null, "someProcess", 0, 0, requirements, null, null);
    Cooker cooker = new Cooker(recipe);
    Location world = new Location("World");
    cooker.setItemsAvailable("Location", world);

    assertTrue("correct location", world.equals(cooker.findIngredientByName("Location")));
  }

  /** An example of common use - A spell. */
  @Test
  public void testSpell() {

    final int manaRemaining = 1;
    final int actionPointsRemaining = 2;
    Recipe recipe = config.getRecipe("testSpell1");

    // Setup the chef
    Location world = new Location("World");
    Race chef = new Human();
    chef.setContainer(world);
    chef.setMana(recipe.getMana() + manaRemaining);
    chef.setActionPoints(recipe.getActionPoints() + actionPointsRemaining);
    chef.recipeAdd("testSpell1");

    // Prepare to cast a spell
    Cooker cooker = chef.getCooker("testSpell1");
    cooker.cook();

    // TODO check the spell method was run.
    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", manaRemaining, chef.getMana());
    assertEquals("actionPoints", actionPointsRemaining, chef.getActionPoints());
  }

  /**
   * Mixture of everything.<br>
   * This is a stand-along test. Doesn't require config.
   */
  @Test
  public void testFullProcess() {

    Location world = new Location("World");
    Cookie cookie = new Cookie();
    world.add(cookie);

    Map<String, Requirement> requirements = new TreeMap<>();
    requirements.put("Location", new RequirementItem("Location", "Location", false, 0));
    requirements.put("Cookie", new RequirementItem("Cookie"));

    List<Product> products = new ArrayList<Product>();
    products.add(new ProductItem("Cookie"));

    // Skill(s)
    Set<Skill> skills = new TreeSet<Skill>();
    for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
      skills.add(new Skill(REQUIRED_SKILLS[i]));
    }

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "magicCookie1",
            "a magic cookie",
            null,
            REQUIRED_MANA,
            REQUIRED_ACTION_POINTS,
            requirements,
            skills,
            products);

    // Set the chef
    Race chef = new Human();

    chef.setMana(REQUIRED_MANA + 1);
    chef.setActionPoints(REQUIRED_ACTION_POINTS + 2);
    chef.skillsAddArray(REQUIRED_SKILLS);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    // Set the
    Location newItemLocation = new Location("newItemLocation");
    cooker.setItemsAvailable("Location", newItemLocation);
    cooker.setItemsAvailable("Cookie", cookie);

    // Do the cooking
    cooker.cook();

    // Does the location now contain a Cookie?
    assertEquals("1 item", 1, newItemLocation.size());
    Item newItem = newItemLocation.get(0);
    assertEquals("item type", "Cookie", newItem.getClass().getSimpleName());
    assertEquals("new item location", newItemLocation, newItem.getContainer());

    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", 1, chef.getMana());
    assertEquals("actionPoints", 2, chef.getActionPoints());
  }

  /**
   * Testing RequirementItem objects.<br>
   * No actual cooking done (yet)
   */
  @Test
  public void testRecipeIngredients() {

    Map<String, Requirement> requirements = new TreeMap<>();

    // RequirementItem
    // FlintAndTinder (not consumed)
    // Wood, min weight 3.
    RequirementItem ingredientFlintAndTinder =
        new RequirementItem("FlintAndTinder", "FlintAndTinder", false, 0);
    requirements.put("FlintAndTinder", ingredientFlintAndTinder);

    RequirementItem ingredientWood = new RequirementItem("Wood", "Wood", true, 3);
    requirements.put("Wood", ingredientWood);

    // Build a recipe with the list of required ingredients
    Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0, requirements, null, null);
    Cooker cooker = recipe.getNewCooker(null);

    assertEquals("requirement count", requirements.size(), cooker.getRequirementCount());
    assertEquals(
        "requirement flintAndTinder",
        ingredientFlintAndTinder,
        cooker.getRequirement("FlintAndTinder"));
    assertEquals("requirement wood", ingredientWood, cooker.getRequirement("Wood"));
  }

  /** Method testRecipeItemsAvailable. */
  @Test
  public void testRecipeItemsAvailable() {

    Location world = new Location("world");
    world.setVolumeMax(-1);
    world.setWeightMax(-1);
    Map<String, Requirement> requirements = new TreeMap<>();

    // RequirementItem
    // FlintAndTinder, not consumed
    FlintAndTinder flintAndTinder = new FlintAndTinder();
    world.add(flintAndTinder);
    RequirementItem ingredientFlintAndTinder =
        new RequirementItem("FlintAndTinder", "FlintAndTinder", false, 0);
    requirements.put("FlintAndTinder", ingredientFlintAndTinder);

    // Wood, min weight 3
    Wood wood = new Wood();
    wood.setWeightBase(3);
    world.add(wood);
    RequirementItem ingredientWood = new RequirementItem("Wood", "Wood", true, 3);
    requirements.put("Wood", ingredientWood);

    // Wood2
    Wood wood2 = new Wood();
    world.add(wood2);
    RequirementItem ingredientWood2 = new RequirementItem("Wood2", "Wood", true);
    requirements.put("Wood2", ingredientWood2);

    // Build a recipe with the list of required ingredients
    Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0, requirements, null, null);
    Cooker cooker = recipe.getNewCooker(null);

    // Item - FlintAndTinder
    try {
      cooker.setItemsAvailable("FlintAndTinder", null);
    } catch (RuntimeException e) {
      assertEquals(
          "item 0 - missing item and location", Cooker.ITEM_MAY_NOT_BE_NULL, e.getMessage());
    }

    try {
      cooker.setItemsAvailable("FlintAndTinder", wood);
    } catch (RuntimeException e) {
      assertEquals("item 0 - wrong type", "item must be a FlintAndTinder", e.getMessage());
    }

    cooker.setItemsAvailable("FlintAndTinder", flintAndTinder);

    FlintAndTinder flintAndTinder2 = new FlintAndTinder();
    world.add(flintAndTinder2);
    try {
      cooker.setItemsAvailable("FlintAndTinder", flintAndTinder2);
    } catch (RuntimeException e) {
      assertEquals("item 0 - already occupied", Cooker.ALREADY_OCCUPIED, e.getMessage());
    }

    cooker.clearItemsAvailable("FlintAndTinder", world);

    cooker.setItemsAvailable("FlintAndTinder", flintAndTinder2);

    assertEquals("item 0 - item removed from Container", cooker, flintAndTinder2.getContainer());
    // Item - Wood
    cooker.setItemsAvailable("Wood", wood);

    // Item - Wood2
    // test that an item can't be added to cooker more than once.
    try {
      cooker.setItemsAvailable("Wood2", wood);
    } catch (RuntimeException e) {
      assertEquals(
          "item 2 - already in Cooker",
          Cooker.ALREADY_CONTAINS_THAT_ITEM + ": " + wood,
          e.getMessage());
    }

    cooker.setItemsAvailable("Wood2", wood2);

    // misc
    assertEquals("item count", 3, cooker.size());
    // Only the FlintAndTinder2
    assertEquals("world count before", 1, world.size());
    cooker.empty(world);
    assertEquals("item count", 0, cooker.size());
    assertEquals("world count after", 4, world.size());
  }

  /**
   * ID: test-general-null <br>
   * Desc: Null case. No requirements.<br>
   * Require: none <br>
   * Input: none <br>
   * Result: <br>
   * overall : pass <br>
   * output: no change <br>
   */
  @Test
  public void testCookGeneralNull() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe("test-general-null", "Null case. No requirements", null, 0, 0, null, null, null);
    // Get a cooker
    Cooker cooker = recipe.getNewCooker(null);
    cooker.cook();
  }

  /** Test setting the chef. */
  @Test
  public void testSetChef() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe("test-general-null", "Null case. No requirements", null, 0, 0, null, null, null);

    // Set the chef
    Race chef = new Human();

    // Get a cooker
    recipe.getNewCooker(chef);
    // non-exception is passing test.
  }

  /**
   * ID: test-mana-1 <br>
   * Desc: Mana test, just enough. <br>
   * Req: mana 3 <br>
   * Input: mana 3 <br>
   * Result: <br>
   * overall : pass <br>
   * output: mana=0 <br>
   */
  @Test
  public void testCookGeneralMana1() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe("test-mana-1", "Mana test, just enough.", null, 1, 0, null, null, null);

    // Set the chef
    Race chef = new Human();
    chef.setMana(1);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    cooker.cook();

    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * Mini tests for matching: <br>
   * <br>
   * ID: test-mana-2 <br>
   * Desc: Mana test, not enough. <br>
   * As per test-mana-1 <br>
   * Input: mana 2 <br>
   * overall: fail <br>
   * output: mana 2 <br>
   */
  @Test
  public void testCookGeneralMana2() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe("test-mana-2", "Mana test, not enough.", null, 3, 0, null, null, null);

    // Set the chef
    Race chef = new Human();
    chef.setMana(2);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    try {
      cooker.cook();
    } catch (RuntimeException e) {
      assertEquals("Not enough mana" + System.lineSeparator(), e.getMessage());
    }
    ;

    // Check the chef hasn't lost Mana or ActionPoints
    assertEquals("mana", 2, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * ID: test-mana-3 <br>
   * Desc: Mana test, more than enough. <br>
   * As per mana-test1 <br>
   * Input: mana 4 <br>
   * overall: pass <br>
   * output: mana 1 <br>
   */
  @Test
  public void testCookGeneralMana3() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe("test-mana-3", "Mana test, more than enough.", null, 3, 0, null, null, null);

    // Set the chef
    Race chef = new Human();
    chef.setMana(4);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    // Cook
    cooker.cook();

    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", 1, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * ID: test-actionPoints-1 <br>
   * Desc: ActionPoints test, just enough. <br>
   * Req: actionPoints 3 <br>
   * Input: actionPoints 3 <br>
   * Result: <br>
   * overall : pass <br>
   * output: actionPoints=0 <br>
   */
  @Test
  public void testCookGeneralActionPoints1() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "test-actionPoints-1", "ActionPoints test, just enough.", null, 0, 1, null, null, null);

    // Set the chef
    Race chef = new Human();
    chef.setActionPoints(1);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    // Cook
    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * Mini tests for matching: <br>
   * <br>
   * ID: test-actionPoints-2 <br>
   * Desc: ActionPoints test, not enough. <br>
   * As per test-actionPoints-1 <br>
   * Input: actionPoints 2 <br>
   * overall: fail <br>
   * output: actionPoints 2 <br>
   */
  @Test
  public void testCookGeneralActionPoints2() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "test-actionPoints-2", "ActionPoints test, not enough.", null, 0, 3, null, null, null);

    // Set the chef
    Race chef = new Human();
    chef.setActionPoints(2);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    try {
      cooker.cook();
    } catch (RuntimeException e) {
      assertEquals("Not enough action points" + System.lineSeparator(), e.getMessage());
    }

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 2, chef.getActionPoints());
  }

  /**
   * ID: test-actionPoints-3 <br>
   * Desc: ActionPoints test, more than enough. <br>
   * As per actionPoints-test1 <br>
   * Input: actionPoints 4 <br>
   * overall: pass <br>
   * output: actionPoints 1 <br>
   */
  @Test
  public void testCookGeneralActionPoints3() {

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "test-actionPoints-3",
            "ActionPoints test, more than enough.",
            null,
            0,
            3,
            null,
            null,
            null);

    // Set the chef
    Race chef = new Human();
    chef.setActionPoints(4);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 1, chef.getActionPoints());
  }

  /**
   * ID: test-skills-1. <br>
   * Desc: Skill test, just enough <br>
   * Equal skills to requirements <br>
   * overall: true <br>
   * output: no other change <br>
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
    Recipe recipe =
        new Recipe(
            "test-skills-1", "Skill test, just enough.", null, 0, 0, null, skillsRequired, null);

    // Set the chef
    Race chef = new Human();
    chef.setSkills(skillsGot);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * ID: test-skills-2. <br>
   * Desc: Less skills than required <br>
   * overall: fail <br>
   * output: no other change <br>
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
    Recipe recipe =
        new Recipe(
            "test-skills-2", "Less skills than required", null, 0, 0, null, skillsRequired, null);

    // Set the chef
    Race chef = new Human();
    chef.setSkills(skillsGot);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    try {
      cooker.cook();
    } catch (RuntimeException e) {

      assertEquals("Missing Skills" + System.lineSeparator(), e.getMessage());
    }

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * ID: test-skills-3. <br>
   * Desc: More skills than required <br>
   * overall: true <br>
   * output: no other change <br>
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
    Recipe recipe =
        new Recipe(
            "test-skills-3", "More skills than required", null, 0, 0, null, skillsRequired, null);

    // Set the chef
    Race chef = new Human();
    chef.setSkills(skillsGot);

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());
  }

  /**
   * ID: test-item-1 <br>
   * Desc: simplest test of one required item. <br>
   * Require: Cookie <br>
   * Input: Cookie <br>
   * overall: true <br>
   * output: The Cookie is consumed. <br>
   */
  @Test
  public void testCookItem1() {
    Location world = new Location("World");
    Cookie cookie = new Cookie();
    world.add(cookie);

    // RequirementItem
    Map<String, Requirement> requirements = new TreeMap<>();

    requirements.put("Cookie", new RequirementItem("Cookie"));

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "test-item-1",
            "simplest test of one required item",
            null,
            0,
            0,
            requirements,
            null,
            null);

    // Set the chef
    Race chef = new Human();

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);

    // Add an item.
    cooker.setItemsAvailable("Cookie", cookie);

    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());

    // Check the cooker has no items left.
    assertEquals("cooker item count", 0, cooker.size());

    // TODO Assistance required - test for destroyed object
  }

  /**
   * <br>
   * ID: test-item-1-catalyst <br>
   * Desc: simplest test of one required item NOT consumed <br>
   * Require: Cookie <br>
   * Input: Cookie <br>
   * overall: true <br>
   * output: The Cookie is NOT consumed. <br>
   */
  @Test
  public void testCookItem1Catalyst() {
    Location world = new Location("World");
    Cookie cookie = new Cookie();
    world.add(cookie);

    // RequirementItem
    Map<String, Requirement> requirements = new TreeMap<>();

    requirements.put("Cookie", new RequirementItem("Cookie", "Cookie", false, 0));

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "test-item-11-catalyst",
            "simplest test of one required item NOT consumed",
            null,
            0,
            0,
            requirements,
            null,
            null);

    // Set the chef
    Race chef = new Human();

    // Get a cooker
    Cooker cooker = recipe.getNewCooker(chef);
    cooker.setItemsAvailable("Cookie", cookie);

    cooker.cook();

    // Check the chef has paid in ActionPoints and ActionPoints
    assertEquals("mana", 0, chef.getMana());
    assertEquals("actionPoints", 0, chef.getActionPoints());

    // Check the cooker has one items left.
    assertEquals("cooker item count", 1, cooker.size());
  }
  // TODO Implement Test
  /**
   * ID: test-item-2 <br>
   * Desc: Multiple items of same type required but not enough present. <br>
   * Require: <br>
   * 3 x Cookie <br>
   * Input: 2 x Cookie <br>
   * overall: fail <br>
   * output: No change. <br>
   */
  // TODO Implement Test
  /**
   * ID: test-item-weightMin-1 <br>
   * Require: Cookie weighing at least 2 units. <br>
   * Input: Cookie weighing at least 2 units. <br>
   * overall: true <br>
   * output: Cookie is consumed. <br>
   */
  // TODO Implement Test
  /**
   * ID: test-item-weightMin-2 <br>
   * Desc: Simplest test for minimum required weight <br>
   * Require: Cookie weighing at least 2.0001 units. <br>
   * Input: Cookie weighing at least 2 units. <br>
   * overall: fail <br>
   * output: Cookie is untouched. <br>
   */
  // TODO Implement Test
  /**
   * ID: test-item-weightMin-complex-1 <br>
   * Require: <br>
   * a) Cookie weighing at least 2 units. <br>
   * b) Cookie weighing at least 3 units. <br>
   * Input: <br>
   * a) Cookie weighing 2 units. <br>
   * b) Cookie weighing 3 units. <br>
   * overall: true <br>
   * output: Cookies consumed <br>
   */
}
