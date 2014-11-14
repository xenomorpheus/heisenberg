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
        assertEquals(point.getClass().getSimpleName()+"=[1.00, 2.00, 3.00]", point.toString());
    }

    /**
     * Test the equals that compares to within a supplied tolerance.
     */
    @Test
    public void testEqualsPositionDouble() {
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
     * Test the equals that compares to within the default tolerance.
     */
    @Test
    public void testEquals() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position point2 = new Position(X_TEST, Y_TEST, Z_TEST);

        // x
        point.setX(X_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true x", point.equals(point2));
        point.setX(X_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false x", point.equals(point2));
        point.setX(X_TEST);
        // y
        point.setY(Y_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true y", point.equals(point2));
        point.setY(Y_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false y", point.equals(point2));
        point.setY(Y_TEST);
        // z
        point.setZ(Z_TEST + WITHIN_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true z", point.equals(point2));
        point.setZ(Z_TEST + OUTSIDE_TOLERANCE * Position.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false z", point.equals(point2));
        point.setX(Z_TEST);
    }

    @Test
    public void testPositionDoubleDouble() {
        Position point = new Position(X_TEST, Y_TEST);
        assertEquals("X", X_TEST, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", 0f, point.getZ(), TEST_TOLERANCE);
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
        double xOff = 1.1;
        double yOff = 2.2;
        double zOff = 3.3;
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position delta = new Position(xOff, yOff, zOff);
        point.applyDelta(delta);
        assertEquals("X", X_TEST + xOff, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST + yOff, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST + zOff, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testAdd() {
        double xOff = 1.1;
        double yOff = 2.2;
        double zOff = 3.3;
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position delta = new Position(xOff, yOff, zOff);
        Position total = point.add(delta);
        assertEquals("X", X_TEST + xOff, total.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST + yOff, total.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST + zOff, total.getZ(), TEST_TOLERANCE);
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
        double xOff = 1.1;
        double yOff = 2.2;
        double zOff = 3.3;
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position point2 = new Position(X_TEST + xOff, Y_TEST + yOff, Z_TEST
                + zOff);
        double distance = Math.sqrt(xOff * xOff + yOff * yOff + zOff * zOff);
        // distance to same point should be zero
        assertEquals(0f, point.distance(point), TEST_TOLERANCE);
        assertEquals(0f, point2.distance(point2), TEST_TOLERANCE);
        // distance between point and point2
        assertEquals(distance, point.distance(point2), TEST_TOLERANCE);
        assertEquals(distance, point2.distance(point), TEST_TOLERANCE);
    }

    @Test
    public void testSetVectorLength() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        double lengthOld = point.length();
        double factor = 1.23f; // Something strange
        point.vectorMul(factor);
        assertEquals(factor * lengthOld, point.length(), TEST_TOLERANCE);
    }

    @Test
    public void testNormalise() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        point.normalise();
        assertEquals(1, point.length(), TEST_TOLERANCE);
    }

    @Test
    public void testEqualsObject() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        Position point2 = new Position(X_TEST, Y_TEST, Z_TEST);
        assertTrue(point.equals(point2));
        assertFalse(point.equals("foo"));
        assertFalse(point.equals(null));
    }

    @Test
    public void testVectorMul() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        double factor = 3.3;
        point.vectorMul(factor);
        assertEquals("X", X_TEST * factor, point.getX(), TEST_TOLERANCE);
        assertEquals("Y", Y_TEST * factor, point.getY(), TEST_TOLERANCE);
        assertEquals("Z", Z_TEST * factor, point.getZ(), TEST_TOLERANCE);
    }

    @Test
    public void testRotateX() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        point.rotateX(Math.PI / 2);
        Position expect = new Position(X_TEST, Z_TEST, -Y_TEST);
        assertTrue(expect.equals(point));
    }

    @Test
    public void testRotateY() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        point.rotateY(Math.PI / 2);
        Position expect = new Position(-Z_TEST, Y_TEST, X_TEST);
        assertTrue(expect.equals(point));
    }

    @Test
    public void testRotateZ() {
        Position point = new Position(X_TEST, Y_TEST, Z_TEST);
        point.rotateZ(Math.PI / 2);
        Position expect = new Position(Y_TEST, -X_TEST, Z_TEST);
        assertTrue(expect.equals(point));
    }

}
