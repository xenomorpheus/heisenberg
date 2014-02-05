package au.net.hal9000.heisenberg.ai;

import java.util.Arrays;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * A very simple model state. It holds two positions, the agent and the goal.
 */
public class ModelStateV1 implements ModelState, Cloneable {

    /** The agent moving to goal. */
    private Point3d agentPosition;
    /** Where the agent wants to be. */
    private Point3d goalPosition;

    /**
     * Constructor.
     * 
     * @param agentPosition
     *            agent position.
     * @param goalPosition
     *            goal position.
     */
    public ModelStateV1(Point3d agentPosition, Point3d goalPosition) {
        this.agentPosition = agentPosition;
        this.goalPosition = goalPosition;
    }

    /**
     * @return get agent position.
     */
    public Point3d getAgentPosition() {
        return agentPosition;
    }

    /**
     * @param position3d
     *            set agent position.
     */
    public void setAgentPosition(Point3d position3d) {
        this.agentPosition = position3d;
    }

    /**
     * @return get goal position.
     */
    public Point3d getGoalPosition() {
        return goalPosition;
    }

    /**
     * @param position3d
     *            set goal position.
     */
    public void setGoalPosition(Point3d position3d) {
        this.goalPosition = position3d;
    }

    @Override
    public ModelStateV1 clone() throws CloneNotSupportedException {
        ModelStateV1 newModeState = (ModelStateV1) super.clone();
        newModeState.agentPosition = this.agentPosition.clone();
        newModeState.goalPosition = this.goalPosition.clone();
        return newModeState;
    }

    @Override
    public boolean equals(Object other) {
        boolean resultSoFar = false;
        if (other != null) {
            if (this == other) {
                resultSoFar = true; // Same object
            } else if (other instanceof ModelStateV1) {
                ModelStateV1 otherV1 = (ModelStateV1) other;
                // assume equal
                resultSoFar = true;
                // compare each field in turn. Delegate where possible.
                // agentPosition
                Point3d otherAgentPosition = otherV1.getAgentPosition();
                if (this.agentPosition != otherAgentPosition) {
                    if (this.agentPosition == null) {
                        resultSoFar = false;
                    } else {
                        resultSoFar = this.agentPosition
                                .equals(otherAgentPosition);
                    }
                }
                // goalPosition
                if (resultSoFar) {
                    Point3d otherGoalPosition = otherV1.getGoalPosition();
                    if (this.goalPosition != otherGoalPosition) {
                        if (this.goalPosition == null) {
                            resultSoFar = false;
                        } else {
                            resultSoFar = this.goalPosition
                                    .equals(otherGoalPosition);
                        }
                    }
                }
            }
        }
        return resultSoFar;
    }

    @Override
    public int hashCode() {
        Object[] hashList = new Object[2];
        if (agentPosition == null) {
            hashList[0] = null;
        } else {
            hashList[0] = agentPosition.hashCode();
        }
        if (goalPosition == null) {
            hashList[1] = null;
        } else {
            hashList[1] = goalPosition.hashCode();
        }
        return Arrays.hashCode(hashList);
    }

    @Override
    public String toString() {
        return "[agent=" + agentPosition + ", goal=" + goalPosition + "]";
    }

}
