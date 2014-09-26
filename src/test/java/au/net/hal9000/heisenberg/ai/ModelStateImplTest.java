package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test ModelStateV1.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ModelStateImplTest {

    /** test getAgentPosition. */
    @Test
    public void testGetAgentPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelState modelState = new ModelStateImpl(agentPosition, goalPosition);
        Point3d gotAgentPosition = modelState.getAgentPosition();
        assertEquals(agentPosition, gotAgentPosition);
    }

    /** test setAgentPosition. */
    @Test
    public void testSetAgentPosition() {
        Point3d agentPositionInitial = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelState modelState = new ModelStateImpl(agentPositionInitial, goalPosition);
        Point3d agentPositionNew = new Point3d(0, 0, 1);
        assertNotEquals("test integrity. Possitions must be different", agentPositionInitial, agentPositionNew);
        // Run test

        modelState.setAgentPosition(agentPositionNew);
        Point3d agentPositionGot = modelState.getAgentPosition();
        assertEquals("Position should be updated",agentPositionNew, agentPositionGot);
    }

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d();
        ModelState modelState = new ModelStateImpl(agentPosition, goalPosition);
        Point3d goalPositionGot = modelState.getGoalPosition();
        assertEquals(goalPosition, goalPositionGot);
    }

    /** test setGoalPosition. */
    @Test
    public void testSetGoalPosition() {
        Point3d agentPosition = new Point3d();
        Point3d initialGoalPosition = new Point3d();
        ModelState modelState = new ModelStateImpl(agentPosition, initialGoalPosition);
        Point3d newGoalPosition = new Point3d(0, 0, 1);
        assertNotEquals("test integrity. Possitions must be different", initialGoalPosition, newGoalPosition);
        // Run test
        modelState.setGoalPosition(newGoalPosition);
        Point3d gotGoalPosition = modelState.getGoalPosition();
        assertEquals("Position should be updated", newGoalPosition, gotGoalPosition);
    }

    /**
     * test clone.
     * 
     * @throws CloneNotSupportedException */
    @Test
    public void testClone() throws CloneNotSupportedException {
        Point3d agentPosition = new Point3d(0, 1, 2);
        Point3d goalPosition = new Point3d(2, 1, 0);
        ModelStateImpl modelState = new ModelStateImpl(agentPosition, goalPosition);
        // ModelState
        ModelStateImpl newModelState = modelState.clone();
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
        ModelStateImpl modelState = new ModelStateImpl(agentPosition, goalPosition);

        Point3d agentPosition2 = new Point3d(0, 1, 2);
        Point3d goalPosition2 = new Point3d();
        ModelStateImpl modelState2 = new ModelStateImpl(agentPosition2,
                goalPosition2);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
