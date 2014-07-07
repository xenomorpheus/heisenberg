package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Very simple actions - Agent movement only.
 * 
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ActionV1 implements Action {

    /** move agent North. */
    public static final ActionV1 NORTH = new ActionV1("North",
            new Point3d(0, 1, 0));
    /** move agent South. */
    public static final ActionV1 SOUTH = new ActionV1("South",
            new Point3d(0, -1, 0));
    /** move agent East. */
    public static final ActionV1 EAST = new ActionV1("East",
            new Point3d(1, 0, 0));
    /** move agent West. */
    public static final ActionV1 WEST = new ActionV1("West",
            new Point3d(-1, 0, 0));
    
    /** human understandable label. */
    private final String label;
    /** amount of movement. */
    private final Point3d delta;

    /**
     * Constructor.
     * 
     * @param label
     *            a nick name for this action.
     * @param delta
     *            movement amount.
     */

    public ActionV1(String label, Point3d delta) {
        super();
        this.label = label;
        this.delta = delta;
    }

    /**
     * Constructor.
     * 
     * @param delta
     *            movement amount.
     */

    public ActionV1(Point3d delta) {
        this("custom", delta);
    }
    
    /**
     * 
     * 
     * @return the amount of movement.
     */
    public Point3d getDelta() {
        return delta;
    }

    /**
     * Method toString.
     * 
     * @see au.net.hal9000.heisenberg.ai.Action#toString()
     * @return String
     */
    @Override
    public String toString() {
        if (null != label) {
            return label;
        }
        return delta.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    /**
     * Method hashCode.
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((null == delta) ? 0 : delta.hashCode());
        result = prime * result + ((null == label) ? 0 : label.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /**
     * Method equals.
     * 
     * @param obj
     *            Object
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof ActionV1)) {
            return false;
        }
        final ActionV1 other = (ActionV1) obj;
        if (null == delta) {
            if (null != other.delta) {
                return false;
            }
        } else if (!delta.equals(other.delta)) {
            return false;
        }
        if (null == label) {
            if (null != other.label) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        return true;
    }

}
