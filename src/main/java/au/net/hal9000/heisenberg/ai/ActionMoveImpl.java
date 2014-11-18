package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Very simple actions - Agent movement only.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class ActionMoveImpl implements ActionMove {

    /** move agent North. */
    public static final ActionMoveImpl NORTH = new ActionMoveImpl(new Position(
            0, -1));
    /** move agent South. */
    public static final ActionMoveImpl SOUTH = new ActionMoveImpl(new Position(
            0, 1));
    /** move agent East. */
    public static final ActionMoveImpl EAST = new ActionMoveImpl(new Position(
            1, 0));
    /** move agent West. */
    public static final ActionMoveImpl WEST = new ActionMoveImpl(new Position(
            -1, 0));

    /** Amount of movement. */
    private Position delta;

    /**
     * Constructor.
     * 
     * @param delta
     *            movement amount.
     */

    public ActionMoveImpl(final Position delta) {
        super();
        this.delta = delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPositionDelta() {
        return delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionDelta(Position position) {
        delta = position;

    }

    /**
     * Method toString.
     * 
     * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(3);
        sb.append("ActionMove=[delta=");
        sb.append(delta);
        sb.append(']');
        return sb.toString();
    }

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
        return result;
    }

    /**
     * Method equals.
     * 
     * @param obj
     *            Object
     * 
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
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
        } else if (!delta.equals(other.delta, 0.0001)) {
            return false;
        }
        return true;
    }

}
