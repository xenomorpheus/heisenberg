package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * The class <code>ModelStateImplTest</code> contains tests for the class
 * {@link <code>ModelStateImpl</code>}
 *
 * @pattern JUnit Test Case
 *
 * @author bruins
 *
 * @version $Revision$
 */
public class ModelStateImplTest {

    /** test getAgentPosition. */
    @Test
    public void testGetAgentPosition() {
        Position agentPosition = new Position();
        ModelState modelState = new ModelStateImpl(agentPosition);
        Position gotAgentPosition = modelState.getAgentPosition();
        assertEquals(agentPosition, gotAgentPosition);
    }

    /** test setAgentPosition. */
    @Test
    public void testSetAgentPosition() {
        Position agentPositionInitial = new Position();
        ModelState modelState = new ModelStateImpl(agentPositionInitial);
        Position agentPositionNew = new Position(0, 1);
        assertNotEquals("test integrity. Possitions must be different",
                agentPositionInitial, agentPositionNew);
        // Run test
        modelState.setAgentPosition(agentPositionNew);
        Position agentPositionGot = modelState.getAgentPosition();
        assertEquals("Position should be updated", agentPositionNew,
                agentPositionGot);
    }

    /**
     * test cloning Constructor.
     */
    @Test
    public void testClone() {
        Position agentPosition = new Position(0, 1, 2);
        ModelState modelState = new ModelStateImpl(agentPosition);
        // ModelState
        ModelState newModelState = new ModelStateImpl(modelState);
        assertFalse("ensure a new ModelStateImpl is created",
                modelState == newModelState);
        assertTrue("ensure new ModelStateImpl equals() old",
                modelState.equals(newModelState));
        // agent
        Position newAgentPosition = newModelState.getAgentPosition();
        assertFalse("ensure a new agentPosition is created",
                agentPosition == newAgentPosition);
        assertTrue("ensure a new agentPosition equals() old",
                agentPosition.equals(newAgentPosition));
    }

    /** test hashCode. */
    @Test
    public void testHashCode() {
        Position agentPosition = new Position(0, 1, 2);
        ModelState modelState = new ModelStateImpl(agentPosition);

        Position agentPosition2 = new Position(0, 1, 2);
        ModelState modelState2 = new ModelStateImpl(agentPosition2);
        assertEquals(modelState.hashCode(), modelState2.hashCode());

    }

}
