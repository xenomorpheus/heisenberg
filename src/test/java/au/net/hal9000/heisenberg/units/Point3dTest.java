package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 3D ponit in space.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class Point3dTest {
    /**
     * Field TEST_TOLERANCE. (value is 0.01)
     */
    private static final double TEST_TOLERANCE = 0.01f;
    /**
     * Field WITHIN_TOLERANCE. (value is 0.9)
     */
    private static final double WITHIN_TOLERANCE = 0.9;
    /**
     * Field OUTSIDE_TOLERANCE. (value is 1.1)
     */
    private static final double OUTSIDE_TOLERANCE = 1.1;
    /**
     * Field ZERO. (value is 0.0)
     */
    private static final double ZERO = 0f;
    /**
     * Field X_TEST. (value is 1.0)
     */
    private static final double X_TEST = 1;
    /**
     * Field Y_TEST. (value is 2.0)
     */
    private static final double Y_TEST = 2;
    /**
     * Field Z_TEST. (value is 3.0)
     */
    private static final double Z_TEST = 3;

    /**
     * Method testPoint3d.
     */
    @Test
    public void testPoint3d() {
        Point3d point = new Point3d();
        assertEquals("X", ZERO, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", ZERO, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", ZERO, point.getZ(), TEST_TOLERANCE);
    }

    /**
     * Method testPoint3dDoubleDoubleDouble.
     */
    @Test
    public void testPoint3dDoubleDoubleDouble() {

        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertEquals("X", X_TEST, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, point.getZ(), TEST_TOLERANCE);
    }

    /**
     * Method testDistancePoint3d.
     */
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

    /**
     * Method testClonePoint3d.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testClonePoint3d() throws CloneNotSupportedException {

        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d clone = point.clone();
        assertTrue("differnt object", point != clone);
        assertTrue("equals returns true", point.equals(clone));
        assertEquals("X", X_TEST, clone.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, clone.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, clone.getZ(), TEST_TOLERANCE);
    }

    /**
     * Method testToString.
     */
    @Test
    public void testToString() {
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertEquals("[1.00, 2.00, 3.00]", point.toString());
    }

    /**
     * Test the equals that compares to within a supplied margin.
     */
    @Test
    public void testEqualsDouble() {
        Point3d point = new Point3d(X_TEST, Y_TEST, Z_TEST);
        Point3d point2 = new Point3d(X_TEST, Y_TEST, Z_TEST);
        assertTrue("equals", point.equals(point2));
        // x
        point.setX(X_TEST + WITHIN_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true x", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setX(X_TEST + OUTSIDE_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false x", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setX(X_TEST);
        // y
        point.setY(Y_TEST + WITHIN_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true y", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setY(Y_TEST + OUTSIDE_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false y", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setY(Y_TEST);
        // z
        point.setZ(Z_TEST + WITHIN_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true z", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setZ(Z_TEST + OUTSIDE_TOLERANCE * Point3d.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false z", point.equals(point2, Point3d.DEFAULT_AXIS_TOLERANCE));
        point.setX(Z_TEST);
    }

    /**
     * test equals.
     */
    @Test
    public void testEquals() {
        Point3d point1 = new Point3d();
        Point3d point2 = new Point3d(0, 0, 0);
        Point3d point3 = new Point3d(0, 0, 1);
        assertTrue(point1.equals(point2));
        assertFalse(point1.equals(point3));
        assertFalse(point2.equals(point3));
    }

}
