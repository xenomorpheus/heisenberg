package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Position;

public class TransitionFunctionImplTest {

    @Test
    public void testTransition() {
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        double tolerance = 0.01;
        Position agentPos = new Position(1, 2);
        Position expectedAgentPos = new Position(agentPos);
        Position goalPos = new Position(3, 4);
        ModelState modelState = new ModelStateGoal(agentPos, goalPos);
        ActionMoveImpl action = ActionMoveImpl.NORTH;
        ModelState dest = transitionFunction.transition(modelState, action);
        expectedAgentPos.applyDelta(action.getDelta());
        assertTrue(expectedAgentPos.equals(dest.getAgentPosition(), tolerance));
    }
}
