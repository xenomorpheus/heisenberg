package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ActionAgentMoveV1 implements Action {

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
     * @return the amount of movement.
     */
    public Point3d getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        if (label != null) {
            return label;
        }
        return delta.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((delta == null) ? 0 : delta.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActionAgentMoveV1)) {
            return false;
        }
        final ActionAgentMoveV1 other = (ActionAgentMoveV1) obj;
        if (delta == null) {
            if (other.delta != null) {
                return false;
            }
        } else if (!delta.equals(other.delta)) {
            return false;
        }
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        return true;
    }

}
