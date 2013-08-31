package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Point3d;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class CatTest {

    Configuration config = null;

    @Before
    public void initialize() throws ConfigurationError {
        config = new Configuration("src/test/resources/config.xml");
    }

    @Test
    public void CatMove() {
        Location dungeon = new Location("Dungeon");
        Cat cat = new Cat("Fluffy", "Black cat");
        cat.move(dungeon, new Point3d(0, 0, 0));
        Point3d pos = new Point3d(0, 0, 0);
        assertTrue(pos.equals(cat.getPosition()));
        cat.move(new Point3d(10, 10, 0));
        pos = new Point3d(10, 10, 0);
        assertTrue(pos.equals(cat.getPosition()));
    }

    @Test
    public void CatDrink() {
        Location dungeon = new Location("Dungeon");
        Cat cat = new Cat("Fluffy", "Black cat");
        int hydrationBefore = ItemProperty.getHydration(cat);
        dungeon.add(cat);
        Water water = new Water();
        water.setWeightBase(1);
        dungeon.add(water);
        cat.setActionPoints(2);
        Cooker cooker = cat.getCooker("testDrinkWater");
        cooker.setChef(cat);
        assertEquals("setItemsAvaliable 0 water", null,cooker.setItemsAvailable(0, water));
        assertEquals("Cook", null, cooker.cook());
        int hydrationAfter = ItemProperty.getHydration(cat);
        assertTrue("Hytration increase", hydrationBefore < hydrationAfter);
    }
}
