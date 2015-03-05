package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Position;

/**
 * Test ModelStateAgentGoal.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ModelStateAgentGoalTest {
    /** tolerance for comparing floats. */
    static final double DIFF = 0.00001f;

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Position agentPosition = new Position();
        Position goalPosition = new Position();
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                goalPosition);
        Position goalPositionGot = modelState.getGoalPosition();
        assertEquals(goalPosition, goalPositionGot);
    }

    /** test setGoalPosition. */
    @Test
    public void testSetGoalPosition() {
        Position agentPosition = new Position();
        Position initialGoalPosition = new Position();
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                initialGoalPosition);
        Position newGoalPosition = new Position(0, 1);
        assertFalse("test integrity. Possitions must be different",
                initialGoalPosition.equals(newGoalPosition));
        // Run test
        modelState.setGoalPosition(newGoalPosition);
        Position gotGoalPosition = modelState.getGoalPosition();
        assertEquals("Position should be updated", newGoalPosition,
                gotGoalPosition);
    }

    /** test setAgentPosition. */
    @Test
    public void testSetAgentPosition() {
        Position agentPositionInitial = new Position();
        Position goalPosition = new Position();
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(
                agentPositionInitial, goalPosition);
        Position agentPositionNew = new Position(0, 1);
        assertFalse("test integrity. Possitions must be different",
                agentPositionInitial.equals(agentPositionNew));
        // Run test
        modelState.setAgentPosition(agentPositionNew);
        Position agentPositionGot = modelState.getAgentPosition();
        assertEquals("Position should be updated", agentPositionNew,
                agentPositionGot);
    }

    /**
     * test duplicate.
     */
    @Test
    public void testDuplicate() {
        Position agentPosition = new Position(0, 1, 2);
        Position goalPosition = new Position(2, 1, 0);
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                goalPosition);
        // ModelState
        ModelStateAgentGoal newModelState = (ModelStateAgentGoal) modelState
                .duplicate();
        assertFalse("ensure a new ModelStateAgentGoal is created",
                modelState == newModelState);
        assertTrue("ensure new ModelStateAgentGoal equals() old",
                modelState.equals(newModelState));

        // goal
        Position newGoalPosition = newModelState.getGoalPosition();
        assertFalse("ensure a new goalPosition is created",
                goalPosition == newGoalPosition);
        assertTrue("ensure a new goalPosition equals() old",
                goalPosition.equals(newGoalPosition));
    }

    /** test hashCode. */
    @Test
    public void testHashCode() {
        Position agentPosition = new Position(0, 1, 2);
        Position goalPosition = new Position();
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                goalPosition);

        Position agentPosition2 = new Position(0, 1, 2);
        Position goalPosition2 = new Position();
        ModelStateAgentGoal modelState2 = new ModelStateAgentGoal(
                agentPosition2, goalPosition2);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }


}
