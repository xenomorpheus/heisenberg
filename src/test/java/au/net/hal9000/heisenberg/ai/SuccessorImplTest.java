package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.ai.action.ActionAgentMoveAbsoluteImpl;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.units.Position;

public class SuccessorImplTest {


    static final ModelState MODEL_STATE = new ModelStateAgentGoalImpl(new Position(),new Position());
    static final Action ACTION = new ActionAgentMoveAbsoluteImpl(null,0.0);
    static final double COST = 1.45f;
    Successor successor = new SuccessorImpl(null, null, 0.0);


    @Before
    public void testSuccessorImpl() {
        successor = new SuccessorImpl(MODEL_STATE, ACTION, COST);
    }

    @Test
    public void testGetAction() {
        assertEquals(ACTION, successor.getAction());
    }

    @Test
    public void testGetModelState() {
        assertEquals(MODEL_STATE, successor.getModelState());
    }

    @Test
    public void testGetCost() {
        assertEquals(COST, successor.getCost(),0.001);
    }

    @Test
    public void testToString() {
        String expect = successor.getClass().getSimpleName()+"=[" + MODEL_STATE + ", " + ACTION + ", cost="
                + COST + "]";
        assertEquals(expect, successor.toString());
    }

}
