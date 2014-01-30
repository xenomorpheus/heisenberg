package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ActionAgentMove implements Action {

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
    ActionAgentMove(String label, Point3d delta) {
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
        return label;
    }

}
