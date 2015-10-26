package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.action.ActionAgentMoveRelativeImpl;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Position;

public class TransitionFunctionAgentGoalImplTest {

    @Test
    public void testTransition() {
        TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

        double tolerance = 0.01;
        Position agentPos = new Position(1, 2);
        Position expectedAgentPos = new Position(agentPos);
        Position goalPos = new Position(3, 4);
        ModelStateAgentGoalImpl modelState = new ModelStateAgentGoalImpl(agentPos,
                goalPos);
        ActionAgentMoveRelativeImpl action = new ActionAgentMoveRelativeImpl(
                Position.NORTH, Position.NORTH.length());
        ModelStateAgentGoalImpl dest = (ModelStateAgentGoalImpl) transitionFunction
                .transition(modelState, action);
        expectedAgentPos.applyDelta(action.getPositionDelta());
        assertTrue(expectedAgentPos.equals(dest.getAgentPosition(), tolerance));
    }
}
