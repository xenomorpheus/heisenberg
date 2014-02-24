package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 */
public class CatTest {

    /**
     * Method CatMove.
     * 
     * @throws CantWearException
     * @throws InvalidTypeException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testCatMove() throws InvalidTypeException, CantWearException, TooHeavyException, TooLargeException {
        Location dungeon = new Location("Dungeon");
        Cat cat = new Cat("Fluffy", "Black cat");
        cat.move(dungeon, new Point3d(0, 0, 0));
        Point3d pos = new Point3d(0, 0, 0);
        assertTrue(pos.equals(cat.getPosition()));
        cat.moveToPoint3d(new Point3d(10, 10, 0));
        pos = new Point3d(10, 10, 0);
        assertTrue(pos.equals(cat.getPosition()));
    }

    /**
     * Method testDrinkRecipe.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testDrinkRecipe() throws InvalidTypeException,
            CantWearException, TooHeavyException, TooLargeException {
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
        assertEquals("setItemsAvaliable 0 water", null,
                cooker.setItemsAvailable(0, water));
        assertEquals("Cook", null, cooker.cook());
        // Hydration increases after drinking water
        float hydrationAfter = ItemProperty.getHydration(cat);
        assertTrue("Hydration increase", hydrationBefore < hydrationAfter);
    }

    /**
     * Method testDrink.
     * 
     * @throws InvalidTypeException
     * @throws CantWearException
     * @throws TooLargeException 
     * @throws TooHeavyException 
     */
    @Test
    public void testDrink() throws InvalidTypeException, CantWearException, TooHeavyException, TooLargeException {
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
        assertEquals("Drink should return null", null, cat.drink(water));
        float hydrationAfter = ItemProperty.getHydration(cat);
        // Hydration increases after drinking water
        assertTrue("Hydration increase", hydrationBefore < hydrationAfter);
    }

}
