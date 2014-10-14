package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.units.Point3d;

public interface ModelState {

    /**
     * 
     * @return get agent position.
     */
    Point3d getAgentPosition();

    /**
     * @param position3d
     *            set agent position.
     */
    void setAgentPosition(Point3d position3d);

    /**
     * 
     * @return get goal position.
     */
    Point3d getGoalPosition();

    /**
     * @param position3d
     *            set goal position.
     */
    void setGoalPosition(Point3d position3d);

    /**
     * Method clone.
     * 
     * @return ModelStateV1
     * @throws CloneNotSupportedException
     */
    ModelState clone() throws CloneNotSupportedException;

}
