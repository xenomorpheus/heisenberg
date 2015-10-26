package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.MemorySet;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Test ModelStateGoalMemorySet.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ModelStateAgentGoalMemorySetTest {

    // TODO add tests related to memory set

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Position agentPosition = new Position();
        Position goalPosition = new Position();
        MemorySet memorySet = new MemorySetImpl();
        ModelStateAgentGoalMemorySetImpl modelState = new ModelStateAgentGoalMemorySetImpl(agentPosition,
                goalPosition,memorySet);
        Position goalPositionGot = modelState.getGoalPosition();
        assertEquals(goalPosition, goalPositionGot);
    }

    /**
     * test duplicate.
     */
    @Test
    public void testDuplicate() {
        Position agentPosition = new Position(0, 1, 2);
        Position goalPosition = new Position(2, 1, 0);
        MemorySet memorySet = new MemorySetImpl();
        ModelStateAgentGoalMemorySetImpl modelState = new ModelStateAgentGoalMemorySetImpl(agentPosition,
                goalPosition,memorySet);
        // ModelState
        ModelStateAgentGoalMemorySetImpl newModelState = (ModelStateAgentGoalMemorySetImpl)modelState.duplicate();
        assertFalse("ensure a new ModelStateGoalMemorySet is created",
                modelState == newModelState);
        assertTrue("ensure new ModelStateGoalMemorySet equals() old",
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
        MemorySet memorySet = new MemorySetImpl();
        ModelStateAgentGoalMemorySetImpl modelState = new ModelStateAgentGoalMemorySetImpl(agentPosition,
                goalPosition,memorySet);

        Position agentPosition2 = new Position(0, 1, 2);
        Position goalPosition2 = new Position();
        ModelStateAgentGoalMemorySetImpl modelState2 = new ModelStateAgentGoalMemorySetImpl(agentPosition2,
                goalPosition2,memorySet);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
