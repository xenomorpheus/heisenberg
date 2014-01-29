package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test ModelStateV1.
 * 
 * @author bruins
 * 
 */
public class ModelStateV1Test {

    /** test getAgentPosition. */
    @Test
    public void testGetAgentPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);
        Point3d gotAgentPosition = modelState.getAgentPosition();
        assertEquals(agentPosition, gotAgentPosition);
    }

    /** test setAgentPosition. */
    @Test
    public void testSetAgentPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);
        Point3d newAgentPosition = new Point3d();
        modelState.setAgentPosition(newAgentPosition);
        Point3d gotAgentPosition = modelState.getAgentPosition();
        assertEquals(newAgentPosition, gotAgentPosition);
        assertNotEquals(agentPosition, gotAgentPosition);
    }

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);
        Point3d gotGoalPosition = modelState.getGoalPosition();
        assertEquals(goalPosition, gotGoalPosition);
    }

    /** test setGoalPosition. */
    @Test
    public void testSetGoalPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);
        Point3d newGoalPosition = new Point3d();
        modelState.setGoalPosition(newGoalPosition);
        Point3d gotGoalPosition = modelState.getGoalPosition();
        assertEquals(newGoalPosition, gotGoalPosition);
        assertNotEquals(goalPosition, gotGoalPosition);
    }

    /**
     * test clone.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testClone() throws CloneNotSupportedException {
        Point3d agentPosition = new Point3d(0, 1, 2);
        Point3d goalPosition = new Point3d(2, 1, 0);
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);
        // ModelState
        ModelStateV1 newModelState = modelState.clone();
        assertFalse("ensure a new ModelStateV1 is created",
                modelState == newModelState);
        assertTrue("ensure new ModelStateV1 equals() old",
                modelState.equals(newModelState));
        // agent
        Point3d newAgentPosition = newModelState.getAgentPosition();
        assertFalse("ensure a new agentPosition is created",
                agentPosition == newAgentPosition);
        assertTrue("ensure a new agentPosition equals() old",
                agentPosition.equals(newAgentPosition));
        // gaol
        Point3d newGoalPosition = newModelState.getGoalPosition();
        assertFalse("ensure a new goalPosition is created",
                goalPosition == newGoalPosition);
        assertTrue("ensure a new goalPosition equals() old",
                goalPosition.equals(newGoalPosition));
    }

    /** test hashCode. */
    @Test
    public void testHashCode() {
        Point3d agentPosition = new Point3d(0, 1, 2);
        Point3d goalPosition = new Point3d();
        ModelStateV1 modelState = new ModelStateV1(agentPosition, goalPosition);

        Point3d agentPosition2 = new Point3d(0, 1, 2);
        Point3d goalPosition2 = new Point3d();
        ModelStateV1 modelState2 = new ModelStateV1(agentPosition2,
                goalPosition2);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
