package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Very simple actions - Agent movement only.
 * 
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionMoveImpl implements ActionMove {

    /** move agent North. */
    public static final ActionMoveImpl NORTH = new ActionMoveImpl("North",
            new Point3d(0, -1, 0));
    /** move agent South. */
    public static final ActionMoveImpl SOUTH = new ActionMoveImpl("South",
            new Point3d(0, 1, 0));
    /** move agent East. */
    public static final ActionMoveImpl EAST = new ActionMoveImpl("East",
            new Point3d(1, 0, 0));
    /** move agent West. */
    public static final ActionMoveImpl WEST = new ActionMoveImpl("West",
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

    public ActionMoveImpl(String label, Point3d delta) {
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

    public ActionMoveImpl(Point3d delta) {
        this("custom", delta);
    }
    
    /**
     * {@inheritDoc}
     * @return the amount of movement.
     */
    @Override
    public Point3d getDelta() {
        return delta;
    }

    /**
     * Method toString.
     * 
     * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
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
        if (!(obj instanceof ActionMoveImpl)) {
            return false;
        }
        final ActionMoveImpl other = (ActionMoveImpl) obj;
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
