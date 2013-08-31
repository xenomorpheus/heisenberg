package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.crafting.*;

public class ScenarioSet03 {
    Configuration config = null;

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

    @Test
    public void testFireGround1() {
        // The recipe we are going to test
        Recipe recipe = config.getRecipe("testFireGround1");

        // Set up the location
        Location location = new Location();
        location.setWeightMax(20);

        Wood wood = new Wood();
        wood.setWeightBase(5);
        location.add(wood);

        FlintAndTinder flintAndTinder = new FlintAndTinder();
        location.add(flintAndTinder);

        // Setup the PC
        PcRace pc = new Human();
        final int expectedMana = 1;
        final int expectedActionPoints = 2;
        pc.setMana(recipe.getMana() + expectedMana);
        pc.setActionPoints(recipe.getActionPoints() + expectedActionPoints);
        // pc.powerWordsAdd(new PowerWord("testPowerWord1"));
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
        final int todo = 0; // TODO item number
        Item item = location.getChildAt(todo);
        assertEquals("item type", "SmallGroundFire", item.getClass()
                .getSimpleName());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", expectedMana, pc.getMana());
        assertEquals("actionPoints", expectedActionPoints, pc.getActionPoints());

    }
}
