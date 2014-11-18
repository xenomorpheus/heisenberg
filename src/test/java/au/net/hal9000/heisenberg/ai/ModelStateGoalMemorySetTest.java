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
public class ModelStateGoalMemorySetTest {

    // TODO add tests related to memory set

    /** test getGoalPosition. */
    @Test
    public void testGetGoalPosition() {
        Position agentPosition = new Position();
        Position goalPosition = new Position();
        MemorySet memorySet = new MemorySetImpl();
        ModelStateGoalMemorySet modelState = new ModelStateGoalMemorySet(agentPosition,
                goalPosition,memorySet);
        Position goalPositionGot = modelState.getGoalPosition();
        assertEquals(goalPosition, goalPositionGot);
    }

    /** test setGoalPosition. */
    @Test
    public void testSetGoalPosition() {
        Position agentPosition = new Position();
        Position initialGoalPosition = new Position();
        MemorySet memorySet = new MemorySetImpl();
        ModelStateGoalMemorySet modelState = new ModelStateGoalMemorySet(agentPosition,
                initialGoalPosition,memorySet);
        Position newGoalPosition = new Position(0, 1);
        assertFalse("test integrity. Possitions must be different",
                initialGoalPosition.equals(newGoalPosition));
        // Run test
        modelState.setGoalPosition(newGoalPosition);
        Position gotGoalPosition = modelState.getGoalPosition();
        assertEquals("Position should be updated", newGoalPosition,
                gotGoalPosition);
        
    }

    /**
     * test duplicate.
     */
    @Test
    public void testDuplicate() {
        Position agentPosition = new Position(0, 1, 2);
        Position goalPosition = new Position(2, 1, 0);
        MemorySet memorySet = new MemorySetImpl();
        ModelStateGoalMemorySet modelState = new ModelStateGoalMemorySet(agentPosition,
                goalPosition,memorySet);
        // ModelState
        ModelStateGoalMemorySet newModelState = (ModelStateGoalMemorySet)modelState.duplicate();
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
        ModelStateGoalMemorySet modelState = new ModelStateGoalMemorySet(agentPosition,
                goalPosition,memorySet);

        Position agentPosition2 = new Position(0, 1, 2);
        Position goalPosition2 = new Position();
        ModelStateGoalMemorySet modelState2 = new ModelStateGoalMemorySet(agentPosition2,
                goalPosition2,memorySet);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
