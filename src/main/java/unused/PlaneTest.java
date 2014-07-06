package unused;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * 3D ponit in space.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class PlaneTest {
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
     * Field A_TEST. (value is 1.0)
     */
    private static final double A_TEST = 1;
    /**
     * Field B_TEST. (value is 2.0)
     */
    private static final double B_TEST = 2;
    /**
     * Field C_TEST. (value is 3.0)
     */
    private static final double C_TEST = 3;
    /**
     * Field D_TEST. (value is 3.0)
     */
    private static final double D_TEST = 4;

    /**
     * Method testPoint3d.
     */
    @Test
    public void testPoint3d() {
        Plane plane = new Plane();
        assertEquals("A", ZERO, plane.getA(), TEST_TOLERANCE);
        assertEquals("B", ZERO, plane.getB(), TEST_TOLERANCE);
        assertEquals("C", ZERO, plane.getC(), TEST_TOLERANCE);
        assertEquals("D", ZERO, plane.getD(), TEST_TOLERANCE);
    }

    /**
     * Method testPoint3dDoubleDoubleDouble.
     */
    @Test
    public void testPoint3dDoubleDoubleDouble() {

        Plane plane = new Plane(A_TEST, B_TEST, C_TEST, D_TEST);
        assertEquals("A", A_TEST, plane.getA(), TEST_TOLERANCE);
        assertEquals("B", B_TEST, plane.getB(), TEST_TOLERANCE);
        assertEquals("C", C_TEST, plane.getC(), TEST_TOLERANCE);
        assertEquals("D", D_TEST, plane.getD(), TEST_TOLERANCE);
    }

    /**
     * Method testClonePoint3d.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testClonePoint3d() throws CloneNotSupportedException {

        Plane plane = new Plane(A_TEST, B_TEST, C_TEST, D_TEST);
        Plane clone = plane.clone();
        assertTrue("differnt object", plane != clone);
        assertTrue("equals returns true", plane.equals(clone));
        assertEquals("A", A_TEST, clone.getA(), TEST_TOLERANCE);
        assertEquals("B", B_TEST, clone.getB(), TEST_TOLERANCE);
        assertEquals("C", C_TEST, clone.getC(), TEST_TOLERANCE);
        assertEquals("D", D_TEST, clone.getD(), TEST_TOLERANCE);
}

    /**
     * Method testToString.
     */
    @Test
    public void testToString() {
        Plane plane = new Plane(A_TEST, B_TEST, C_TEST, D_TEST);
        assertEquals("[1.00, 2.00, 3.00, 4.00]", plane.toString());
    }

    /**
     * Test the equals that compares to within a supplied margin.
     */
    @Test
    public void testEqualsDouble() {
        Plane plane = new Plane(A_TEST, B_TEST, C_TEST, D_TEST);
        Plane plane2 = new Plane(A_TEST, B_TEST, C_TEST, D_TEST);
        assertTrue("equals", plane.equals(plane2));
        // a
        plane.setA(A_TEST + WITHIN_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true a", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setA(A_TEST + OUTSIDE_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false a", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setA(A_TEST);
        // b
        plane.setB(B_TEST + WITHIN_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true b", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setB(B_TEST + OUTSIDE_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false b", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setB(B_TEST);
        // c
        plane.setC(C_TEST + WITHIN_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true c", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setC(C_TEST + OUTSIDE_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false c", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setC(C_TEST);
        // d
        plane.setD(D_TEST + WITHIN_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertTrue("true d", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setD(D_TEST + OUTSIDE_TOLERANCE * Plane.DEFAULT_AXIS_TOLERANCE);
        assertFalse("false d", plane.equals(plane2, Plane.DEFAULT_AXIS_TOLERANCE));
        plane.setD(D_TEST);
    }

    /**
     * test equals.
     */
    @Test
    public void testEquals() {
        Plane plane1 = new Plane();
        Plane plane2 = new Plane(0, 0, 0, 0);
        Plane plane3 = new Plane(0, 0, 1, 0);
        Plane plane4 = new Plane(0, 0, 0, 1);
        assertTrue(plane1.equals(plane2));
        assertFalse(plane1.equals(plane3));
        assertFalse(plane2.equals(plane3));
        assertFalse(plane4.equals(plane3));
    }

    
    /**
     * test distance.
     */
    @Test
    public void testDistance() {

        Plane plane = new Plane(1, 2, 2, -11);
        Point3d point = new Point3d(2, 1, 1);
        double d = plane.distance(point);
        assertEquals(5.00/3.00, d, TEST_TOLERANCE);
    }
    
}
