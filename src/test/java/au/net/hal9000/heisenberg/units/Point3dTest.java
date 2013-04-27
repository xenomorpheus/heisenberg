package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Point3dTest {
    static final double POINT3D_TOLERANCE = Point3d.getTolerance();
    static final double TEST_TOLERANCE = 0.01f;
    static final double ZERO = 0f;

    @Test
    public void testPoint3d() {
        Point3d point = new Point3d();
        assertEquals("X", ZERO, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", ZERO, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", ZERO, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testPoint3dDoubleDoubleDouble() {
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        assertEquals("X", x, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", y, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", z, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testDistancePoint3d() {
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        double x2 = 1;
        double y2 = 2;
        double z2 = 3;
        Point3d point2 = new Point3d(x2, y2, z2);
        assertEquals("same", ZERO, point.distance(point2), TEST_TOLERANCE);
        double x3 = 11; // 10
        double y3 = 22; // 20
        double z3 = 33; // 30
        Point3d point3 = new Point3d(x3, y3, z3);
        // 10*10 + 20*20 + 30*30 = 1400
        assertEquals("same", Math.sqrt(1400), point.distance(point3), TEST_TOLERANCE);

    }

    @Test
    public void testClonePoint3d() {
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        Point3d point2 = point.clone();
        assertEquals("X", x, point2.getX(), TEST_TOLERANCE);
        assertEquals("Y", y, point2.getY(), TEST_TOLERANCE);
        assertEquals("Z", z, point2.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testToString() {
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        assertEquals("[" + x + ", " + y + ", " + z + "]", point.toString());
    }

    @Test
    public void testEqualsFloat() {
        double tolerance = POINT3D_TOLERANCE;
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        double x2 = 1;
        double y2 = 2;
        double z2 = 3;
        Point3d point2 = new Point3d(x2, y2, z2);
        assertTrue("equals", point.equals(point2));
        // x
        point.setX(x+0.9*tolerance);
        assertTrue("true x", point.equals(point2));
        point.setX(x+1.1*tolerance);
        assertFalse("false x", point.equals(point2));
        point.setX(x);
        // y
        point.setY(y+0.9*tolerance);
        assertTrue("true y", point.equals(point2));
        point.setY(y+1.1*tolerance);
        assertFalse("false y", point.equals(point2));
        point.setY(y);
        // z
        point.setZ(z+0.9*tolerance);
        assertTrue("true z", point.equals(point2));
        point.setZ(z+1.1*tolerance);
        assertFalse("false z", point.equals(point2));
        point.setX(z);
    }

    @Test
    public void testEqualsDouble() {
        double tolerance = 10d;
        double x = 1;
        double y = 2;
        double z = 3;
        Point3d point = new Point3d(x, y, z);
        double x2 = 1;
        double y2 = 2;
        double z2 = 3;
        Point3d point2 = new Point3d(x2, y2, z2);
        assertTrue("equals", point.equals(point2, tolerance));
        // x
        point.setX(x+0.9*tolerance);
        assertTrue("true x", point.equals(point2, tolerance));
        point.setX(x+1.1*tolerance);
        assertFalse("false x", point.equals(point2, tolerance));
        point.setX(x);
        // y
        point.setY(y+0.9*tolerance);
        assertTrue("true y", point.equals(point2, tolerance));
        point.setY(y+1.1*tolerance);
        assertFalse("false y", point.equals(point2, tolerance));
        point.setY(y);
        // z
        point.setZ(z+0.9*tolerance);
        assertTrue("true z", point.equals(point2, tolerance));
        point.setZ(z+1.1*tolerance);
        assertFalse("false z", point.equals(point2, tolerance));
        point.setX(z);
    }
    
}
