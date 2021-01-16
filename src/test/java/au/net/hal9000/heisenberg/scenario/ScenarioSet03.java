package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Wood;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ScenarioSet03 {
  /** Field config. */
  private Configuration config = null;

  @Before
  public void initialize() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  // Human collects various items (move and pick up)
  // Builds a small ground fire (recipe)
  // Uses some kind of skill
  // Need groups of groups of skills.
  // Thought - Should a skill be a group of Recipes ?
  // E.g. Cooking skill can be a group of cooking recipes.
  // E.g. Woodland skill can be a group of hunting, tracking, etc recipes.

  /** Method testFireGround1. */
  @Test
  public void testFireGround1() {
    // The recipe we are going to test
    Recipe recipe = config.getRecipe("testFireGround1");

    // Set up the location
    Location location = new Location();

    // Wood
    Wood wood = new Wood();
    RequirementItem woodRequirement = (RequirementItem) recipe.getRequirement("Wood");
    wood.setWeightBase(woodRequirement.getWeightMin() + 2);

    // Flint and Tinder
    FlintAndTinder flintAndTinder = new FlintAndTinder();
    location.add(flintAndTinder);

    // Setup the PC
    Race pc = new Human();
    final int expectedMana = 1;
    final int expectedActionPoints = 2;
    pc.setMana(recipe.getMana() + expectedMana);
    pc.setActionPoints(recipe.getActionPoints() + expectedActionPoints);
    pc.skillsAdd(new Skill("testFireLighting"));
    pc.recipeAdd("testFireGround1");

    // TODO set XYZ location
    location.add(pc);

    // Prepare to create SmallGroundFire.
    Cooker cooker = pc.getCooker("testFireGround1");

    // <item id="Location" type="ItemContainer" consumed="no" />
    cooker.setItemsAvailable("Location", location);

    // <item id="Wood" consumed="yes" weightMin="3" />
    cooker.setItemsAvailable("Wood", wood);

    // TODO move pc to flintAndTinder
    cooker.setItemsAvailable("FlintAndTinder", flintAndTinder);

    // Cook!
    cooker.cook();

    // Check the item produced
    final int todo = 1; // TODO item number
    Item item = location.get(todo);
    assertEquals("item type", "SmallGroundFire", item.getClass().getSimpleName());

    // Check the chef has paid in Mana and ActionPoints
    assertEquals("mana", expectedMana, pc.getMana());
    assertEquals("actionPoints", expectedActionPoints, pc.getActionPoints());
  }
}
