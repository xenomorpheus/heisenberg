package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.units.Point3d;

public class ModelStateV1 implements ModelState {
    /*
     * A very simple model that only holds two Item objects, the agent and the
     * goal.
     */

    /* the agent moving to goal */
    private Point3d agentPosition;
    /* where the agent wants to be */
    private Point3d goalPosition;

    public Point3d getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(Point3d position3d) {
        this.agentPosition = position3d;
    }

    public Point3d getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Point3d position3d) {
        this.goalPosition = position3d;
    }

}
