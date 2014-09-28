package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Point3d;

public class TransitionFunctionImplTest {

    @Test
    public void testTransition() throws CloneNotSupportedException {
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        double tolerance = 0.01;
        Point3d agentPos = new Point3d(1, 2, 0);
        Point3d expectedAgentPos = agentPos.clone();
        Point3d goalPos = new Point3d(3, 4, 0);
        ModelState modelState = new ModelStateImpl(agentPos, goalPos);
        ActionMoveImpl action = ActionMoveImpl.NORTH;
        ModelState dest = transitionFunction.transition(modelState, action);
        expectedAgentPos.applyDelta(action.getDelta());
        assertTrue(expectedAgentPos.equals(dest.getAgentPosition(), tolerance));
    }
}
