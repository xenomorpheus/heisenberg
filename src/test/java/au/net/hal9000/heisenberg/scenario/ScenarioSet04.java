package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.units.Point3d;

public class ScenarioSet04 {

    @Test
    public void Cat1() {
        Location dungeon = new Location("Dungeon");
        Cat cat = new Cat("Fluffy","Black cat");
        cat.move( dungeon, new Point3d(0,0,0));
        Point3d pos = new Point3d(0,0,0);
        assertTrue(pos.equals( cat.getPosition()));
        cat.move( new Point3d(10,10,0));
        pos = new Point3d(10,10,0);
        assertTrue(pos.equals( cat.getPosition()));
    }
}
