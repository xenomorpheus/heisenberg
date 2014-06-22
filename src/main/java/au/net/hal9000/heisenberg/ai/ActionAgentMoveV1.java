package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ActionAgentMoveV1 implements Action {

    /** move agent North. */
    public static final ActionAgentMoveV1 NORTH = new ActionAgentMoveV1("North",
            new Point3d(0, 1, 0));
    /** move agent South. */
    public static final ActionAgentMoveV1 SOUTH = new ActionAgentMoveV1("South",
            new Point3d(0, -1, 0));
    /** move agent East. */
    public static final ActionAgentMoveV1 EAST = new ActionAgentMoveV1("East",
            new Point3d(1, 0, 0));
    /** move agent West. */
    public static final ActionAgentMoveV1 WEST = new ActionAgentMoveV1("West",
            new Point3d(-1, 0, 0));
    
    /** human understandable label. */
    private final String label;
    /** amount of movement. */
    private final Point3d delta;

    /**
     * Constructor.
     * 
     * @param label
     *            TODO
     * @param delta
     *            movement amount.
     */

    public ActionAgentMoveV1(String label, Point3d delta) {
        this.label = label;
        this.delta = delta;
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
        if (!(obj instanceof ActionAgentMoveV1)) {
            return false;
        }
        final ActionAgentMoveV1 other = (ActionAgentMoveV1) obj;
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
