package au.net.hal9000.heisenberg.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 3D point in space.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */

public class PositionTest {

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
     * Method testPosition.
     */
    @Test
    public void testPosition() {
        Position point = new Position();
        assertEquals("X", ZERO, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", ZERO, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", ZERO, point.getZ(), TEST_TOLERANCE);
    }

    /**
     * Method testPositionDoubleDoubleDouble.
     */
    @Test
    public void testPositionDoubleDoubleDouble() {

        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        assertEquals("X", X_TEST, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, point.getZ(), TEST_TOLERANCE);
    }

    /**
     * Method testDistancePosition.
     */
    @Test
    public void testDistancePosition() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position point2 = new Position(X_TEST, Y_TEST, Z_TEST);
        assertEquals("same", ZERO, point.distance(point2), TEST_TOLERANCE);
        final double x3 = 11; // 10
        final double y3 = 22; // 20
        final double z3 = 33; // 30
        Position point3 = new Position(x3, y3, z3);
        // 10*10 + 20*20 + 30*30 = 1400
        assertEquals("same", Math.sqrt(1400), point.distance(point3),
                TEST_TOLERANCE);

        final Position p1 = new Position(0, 3, 0);
        final Position p2 = new Position(4, 0, 0);
        assertEquals("3 4 5 triangle", 5f, p1.distance(p2), TEST_TOLERANCE);

    }

    /**
     * Method testToString.
     */
    @Test
    public void testToString() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        assertEquals("[1.00, 2.00, 3.00]", point.toString());
    }

    /**
     * Test the equals that compares to within a supplied margin.
     */
    @Test
    public void testEqualsDouble() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position point2 = new Position(X_TEST, Y_TEST, Z_TEST);
        assertTrue("equals", point.equals(point2));
        // x
        point.setX(X_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true x",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setX(X_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false x",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setX(X_TEST);
        // y
        point.setY(Y_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true y",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setY(Y_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false y",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setY(Y_TEST);
        // z
        point.setZ(Z_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true z",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setZ(Z_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false z",
                point.equals(point2, Position.DEFAULT_AXIS_TOLERANCE));
        point.setX(Z_TEST);
    }

    /**
     * test equals.
     */
    @Test
    public void testEquals() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position other = new Position(X_TEST, Y_TEST, Z_TEST);
        assertTrue(point.equals(other));
        assertTrue(other.equals(point));
    }

    @Test
    public void testPositionDoubleDouble() {
        fail("Not yet implemented");
    }

    @Test
    public void testPositionPosition() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position other = new Position(point);
        assertTrue("differnt object", point != other);
        assertTrue("equals returns true", point.equals(other));
        assertEquals("X", X_TEST, other.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, other.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST, other.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testApplyDelta() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testLength() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        assertEquals(
                Math.sqrt(X_TEST * X_TEST + Y_TEST * Y_TEST + Z_TEST * Z_TEST),
                point.length(), TEST_TOLERANCE);
    }

    @Test
    public void testDistance() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testSetVectorLength() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        double lengthOld = point.length();
        double factor = 1.23f; // Something strange
        point.vectorMul(factor);
        assertEquals(factor* lengthOld, point.length(), TEST_TOLERANCE);
    }

    @Test
    public void testEqualsPositionDouble() {
        fail("Not yet implemented");
    }

    @Test
    public void testNormalise() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        assertEquals(1, point.length(), TEST_TOLERANCE);
    }

    @Test
    public void testEqualsObject() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testVectorMul() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testRotateX() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testRotateY() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

    @Test
    public void testRotateZ() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        fail("Not yet implemented");
    }

}
