package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.crafting.*;

public class ScenarioSet03 {
    Configuration config = null;

    @Before
    public void initialize() throws ValidityException, IOException, Exception {
        config = new Configuration("test/config/config.xml");
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
        location.setWeightMax(20); // TODO from config ?

        Wood wood = new Wood();
        wood.setWeightBase(5); // TODO from recipe config
        // TODO set XYZ location
        location.add(wood);

        FlintAndTinder flintAndTinder = new FlintAndTinder();
        // TODO set XYZ location
        location.add(flintAndTinder);

        // Setup the PC
        PcRace pc = new Human();
        final int expectedMana = 1;
        final int expectedActionPoints = 2;
        pc.setMana(recipe.getMana() + expectedMana);
        pc.setActionPoints(recipe.getActionPoints() + expectedActionPoints);
        // pc.powerWordsAdd(new PowerWord("testPowerWord1"));
        pc.skillsAdd(new Skill("testFireLighting"));
        pc.recipesAdd("testFireGround1");

        // TODO set XYZ location
        location.add(pc);

        // Prepare to create SmallGroundFire.
        Cooker cooker = pc.getCooker("testFireGround1");

        // <item id="Location" type="ItemContainer" consumed="no" />
        assertEquals("set Location", null,
                cooker.setItemsAvailable("Location", location));

        // TODO move pc to flintAndTinder
        assertEquals("make FlintAndTinder available", null,
                cooker.setItemsAvailable("FlintAndTinder", flintAndTinder));

        // <item id="Wood" consumed="yes" weightMin="3" />
        assertEquals("make Wood available", null,
                cooker.setItemsAvailable("Wood", wood));

        // Cook!
        assertEquals("cook works", null, cooker.cook());

        // Check the item produced
        final int todo = 0; // TODO
        Item item = location.getChild(todo);
        assertEquals("item type", "SmallGroundFire", item.getClass().getSimpleName());

        // Check the chef has paid in Mana and ActionPoints
        assertEquals("mana", expectedMana, pc.getMana());
        assertEquals("actionPoints", expectedActionPoints, pc.getActionPoints());

    }
}
