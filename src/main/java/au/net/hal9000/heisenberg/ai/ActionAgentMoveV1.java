package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ActionAgentMoveV1 implements Action {

    /** human understandable label. */
    private String label;
    /** amount of movement. */
    private Point3d delta;

    /**
     * Constructor.
     * 
     * @param label
     *            TODO
     * @param delta
     *            movement amount.
     */
    ActionAgentMoveV1(String label, Point3d delta) {
        this.delta = delta;
        this.label = label;
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

}
