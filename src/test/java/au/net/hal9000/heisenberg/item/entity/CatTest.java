package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Position;

/**
 */
public class CatTest {

    /**
     * Method CatMove.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testCatMove() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Location dungeon = new Location("Dungeon");
        Cat cat = new Cat("Fluffy", "Black cat");
        cat.move(dungeon, new Position());
        Position pos = new Position();
        assertTrue(pos.equals(cat.getPosition()));
        cat.moveToPoint2d(new Position(10, 10));
        pos = new Position(10, 10);
        assertTrue(pos.equals(cat.getPosition()));
    }

    /**
     * Method testDrinkRecipe.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testDrinkRecipe() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Location dungeon = new Location("Dungeon");
        dungeon.setWeightMax(1);
        Cat cat = new Cat("Fluffy");
        // Hydration before
        float hydrationBefore = ItemProperty.getHydration(cat);
        dungeon.add(cat);
        Water water = new Water();
        water.setWeightBase(1);
        dungeon.add(water);
        cat.setActionPoints(2);
        Cooker cooker = cat.getCooker("testDrinkWater");
        cooker.setItemsAvailable("Water", water);
        cooker.cook();
        // Hydration increases after drinking water
        float hydrationAfter = ItemProperty.getHydration(cat);
        assertTrue("Hydration increase", hydrationBefore < hydrationAfter);
    }

    /**
     * Method testDrink.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testDrink() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Location dungeon = new Location("Dungeon");
        dungeon.setWeightMax(1);
        Cat cat = new Cat("Fluffy");
        // Hydration before
        float hydrationBefore = ItemProperty.getHydration(cat);
        dungeon.add(cat);
        Water water = new Water();
        water.setWeightBase(1);
        dungeon.add(water);
        cat.setActionPoints(2);
        cat.drink(water);
        float hydrationAfter = ItemProperty.getHydration(cat);
        // Hydration increases after drinking water
        assertTrue("Hydration increase", hydrationBefore < hydrationAfter);
    }

}
