package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.item.FlintAndTinder;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.item.Wood;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

/**
 */
public class ScenarioSet03 {
    /**
     * Field config.
     */
    private Configuration config = null;

    /**
     * Method initialize.
     * @throws ConfigurationError
     */
    @Before
    public void initialize() throws ConfigurationError {
        config = new Configuration("src/test/resources/config.xml");
    }

    // Human collects various items (move and pick up)
    // Builds a small ground fire (recipe)
    // Uses some kind of skill
    // Need groups of groups of skills.
    // Thought - Should a skill be a group of Recipes ?
    // E.g. Cooking skill can be a group of cooking recipes.
    // E.g. Woodland skill can be a group of hunting, tracking, etc recipes.

    /**
     * Method testFireGround1.
     * @throws InvalidTypeException 
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testFireGround1() throws InvalidTypeException, TooHeavyException, TooLargeException {
        // The recipe we are going to test
        Recipe recipe = config.getRecipe("testFireGround1");

        // Set up the location
        Location location = new Location();
        
        // Wood
        Wood wood = new Wood();
        RequirementItem woodRequirement = (RequirementItem) recipe
                .getRequirement(1);
        wood.setWeightBase(woodRequirement.getWeightMin() + 2);

        // Flint and Tinder
        FlintAndTinder flintAndTinder = new FlintAndTinder();
        location.add(flintAndTinder);

        // Setup the PC
        PcRace pc = new Human();
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
        assertEquals("set Location", null,
                cooker.setItemsAvailable(0, location));

        // <item id="Wood" consumed="yes" weightMin="3" />
        assertEquals("make Wood available", null,
                cooker.setItemsAvailable(1, wood));

        // TODO move pc to flintAndTinder
        assertEquals("make FlintAndTinder available", null,
                cooker.setItemsAvailable(2, flintAndTinder));

        // Cook!
        assertEquals("cook works", null, cooker.cook());

        // Check the item produced
        final int todo = 1; // TODO item number
        Item item = location.getChildAt(todo);
        assertEquals("item type", "SmallGroundFire", item.getClass()
                .getSimpleName());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", expectedMana, pc.getMana());
        assertEquals("actionPoints", expectedActionPoints, pc.getActionPoints());

    }
}
