package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ActionAgentMove implements Action {

    /** amount of movement. */
    private Point3d delta;

    /**
     * Constructor.
     * 
     * @param delta
     *            movement amount.
     */
    ActionAgentMove(Point3d delta) {
        this.delta = delta;
    }

    /**
     * 
     * @return the amount of movement.
     */
    public Point3d getDelta() {
        return delta;
    }

}
