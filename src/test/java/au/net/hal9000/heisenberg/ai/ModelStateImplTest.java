package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

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
        Position agentPosition = new Position();
        Position goalPosition = new Position();
        ModelState modelState = new ModelStateImpl(agentPosition, goalPosition);
        Position gotAgentPosition = modelState.getAgentPosition();
        assertEquals(agentPosition, gotAgentPosition);
    }

    /** test setAgentPosition. */
    @Test
    public void testSetAgentPosition() {
        Position agentPositionInitial = new Position();
        Position goalPosition = new Position();
        ModelState modelState = new ModelStateImpl(agentPositionInitial,
                goalPosition);
        Position agentPositionNew = new Position(0, 1);
        assertNotEquals("test integrity. Possitions must be different",
                agentPositionInitial, agentPositionNew);
        // Run test

        modelState.setAgentPosition(agentPositionNew);
        Position agentPositionGot = modelState.getAgentPosition();
        assertEquals("Position should be updated", agentPositionNew,
                agentPositionGot);
    }

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Position agentPosition = new Position();
        Position goalPosition = new Position();
        ModelState modelState = new ModelStateImpl(agentPosition, goalPosition);
        Position goalPositionGot = modelState.getGoalPosition();
        assertEquals(goalPosition, goalPositionGot);
    }

    /** test setGoalPosition. */
    @Test
    public void testSetGoalPosition() {
        Position agentPosition = new Position();
        Position initialGoalPosition = new Position();
        ModelState modelState = new ModelStateImpl(agentPosition,
                initialGoalPosition);
        Position newGoalPosition = new Position(0, 1);
        assertNotEquals("test integrity. Possitions must be different",
                initialGoalPosition, newGoalPosition);
        // Run test
        modelState.setGoalPosition(newGoalPosition);
        Position gotGoalPosition = modelState.getGoalPosition();
        assertEquals("Position should be updated", newGoalPosition,
                gotGoalPosition);
    }

    /**
     * test cloning Constructor.
     */
    @Test
    public void testClone() {
        Position agentPosition = new Position(0, 1, 2);
        Position goalPosition = new Position(2, 1, 0);
        ModelStateImpl modelState = new ModelStateImpl(agentPosition,
                goalPosition);
        // ModelState
        ModelStateImpl newModelState = new ModelStateImpl(modelState);
        assertFalse("ensure a new ModelStateV1 is created",
                modelState == newModelState);
        assertTrue("ensure new ModelStateV1 equals() old",
                modelState.equals(newModelState));
        // agent
        Position newAgentPosition = newModelState.getAgentPosition();
        assertFalse("ensure a new agentPosition is created",
                agentPosition == newAgentPosition);
        assertTrue("ensure a new agentPosition equals() old",
                agentPosition.equals(newAgentPosition));
        // gaol
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
        ModelStateImpl modelState = new ModelStateImpl(agentPosition,
                goalPosition);

        Position agentPosition2 = new Position(0, 1, 2);
        Position goalPosition2 = new Position();
        ModelStateImpl modelState2 = new ModelStateImpl(agentPosition2,
                goalPosition2);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
