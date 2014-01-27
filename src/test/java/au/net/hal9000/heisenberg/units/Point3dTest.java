package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** 3D ponit in space.
 * 
 * @author bruins
 *
 */
public class Point3dTest {
    private static final double POINT3D_TOLERANCE = Point3d.getTolerance();
    private static final double TEST_TOLERANCE = 0.01f;
    private static final double ZERO = 0f;
    private static final double X_TEST = 1;
    private static final double Y_TEST = 2;
    private static final double Z_TEST = 3;

    @Test
    public void testPoint3d() {
        Point3d point = new Point3d();
        assertEquals("X", ZERO, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", ZERO, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", ZERO, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testPoint3dDoubleDoubleDouble() {

        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertEquals("X", X_TEST, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testDistancePoint3d() {
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d point2 = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertEquals("same", ZERO, point.distance(point2), TEST_TOLERANCE);
        double x3 = 11; // 10
        double y3 = 22; // 20
        double z3 = 33; // 30
        Point3d point3 = new Point3d(x3, y3, z3);
        // 10*10 + 20*20 + 30*30 = 1400
        assertEquals("same", Math.sqrt(1400), point.distance(point3),
                TEST_TOLERANCE);

    }

    @Test
    public void testClonePoint3d() {

        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d point2 = point.clone();
        assertEquals("X", X_TEST, point2.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, point2.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, point2.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testToString() {
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertEquals("[" + X_TEST + ", " + Y_TEST + ", " + Z_TEST + "]",
                point.toString());
    }

    @Test
    public void testEqualsFloat() {
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d point2 = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertTrue("equals", point.equals(point2));
        // x
        point.setX(X_TEST + 0.9 * POINT3D_TOLERANCE);
        assertTrue("true x", point.equals(point2));
        point.setX(X_TEST + 1.1 * POINT3D_TOLERANCE);
        assertFalse("false x", point.equals(point2));
        point.setX(X_TEST);
        // y
        point.setY(Y_TEST + 0.9 * POINT3D_TOLERANCE);
        assertTrue("true y", point.equals(point2));
        point.setY(Y_TEST + 1.1 * POINT3D_TOLERANCE);
        assertFalse("false y", point.equals(point2));
        point.setY(Y_TEST);
        // z
        point.setZ(Z_TEST + 0.9 * POINT3D_TOLERANCE);
        assertTrue("true z", point.equals(point2));
        point.setZ(Z_TEST + 1.1 * POINT3D_TOLERANCE);
        assertFalse("false z", point.equals(point2));
        point.setX(Z_TEST);
    }

    @Test
    public void testEqualsDouble() {
        double tolerance = 10d;
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d point2 = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertTrue("equals", point.equals(point2, tolerance));
        // x
        point.setX(X_TEST + 0.9 * tolerance);
        assertTrue("true x", point.equals(point2, tolerance));
        point.setX(X_TEST + 1.1 * tolerance);
        assertFalse("false x", point.equals(point2, tolerance));
        point.setX(X_TEST);
        // y
        point.setY(Y_TEST + 0.9 * tolerance);
        assertTrue("true y", point.equals(point2, tolerance));
        point.setY(Y_TEST + 1.1 * tolerance);
        assertFalse("false y", point.equals(point2, tolerance));
        point.setY(Y_TEST);
        // z
        point.setZ(Z_TEST + 0.9 * tolerance);
        assertTrue("true z", point.equals(point2, tolerance));
        point.setZ(Z_TEST + 1.1 * tolerance);
        assertFalse("false z", point.equals(point2, tolerance));
        point.setX(Z_TEST);
    }

}
