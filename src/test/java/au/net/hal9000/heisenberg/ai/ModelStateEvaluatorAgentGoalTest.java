package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.units.Position;

class MyModelState implements ModelState {

    private int id;

    MyModelState(int id) {
        super();
        this.id = id;
    }

    @Override
    public ModelState duplicate() {
        return new MyModelState(id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyModelState other = (MyModelState) obj;
        if (id != other.id)
            return false;
        return true;
    }

}

public class ModelStateEvaluatorAgentGoalTest {

    @Test
    public void testModelStateInAddedTrue() {
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
        List<ModelState> addedModelStates = new ArrayList<>();
        addedModelStates.add(new MyModelState(1));
        addedModelStates.add(new MyModelState(2));
        addedModelStates.add(new MyModelState(3));

        ModelState modelState = new MyModelState(2);
        assertTrue(modelStateEvaluator.modelStateInAdded(addedModelStates,
                modelState));
    }

    @Test
    public void testModelStateInAddedFalse() {
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
        List<ModelState> addedModelStates = new ArrayList<>();
        addedModelStates.add(new MyModelState(1));
        addedModelStates.add(new MyModelState(2));
        addedModelStates.add(new MyModelState(3));

        ModelState modelState = new MyModelState(4);
        assertFalse(modelStateEvaluator.modelStateInAdded(addedModelStates,
                modelState));
    }

    /**
     * test if agent is at a goal.
     */
    @Test
    public void testIsAtGoal() {

        ModelStateEvaluatorAgentGoal modelStateEvaluatorAgentGoal = new ModelStateEvaluatorAgentGoal();
        // goal
        Position goalPosition = new Position();

        // agent
        Position agentPosition = new Position();

        // state
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                goalPosition);
        assertTrue("At goal", modelStateEvaluatorAgentGoal.isAtGoal(modelState));
        agentPosition.setX(1.0f);
        assertFalse("Agent off by 1.0 in X",
                modelStateEvaluatorAgentGoal.isAtGoal(modelState));
        agentPosition.setX(0.0f);
        agentPosition.setY(1.0f);
        assertFalse("Agent off by 1.0 in Y",
                modelStateEvaluatorAgentGoal.isAtGoal(modelState));
    }

    /**
     * evaluate when two Item objects are on top of each other.
     */
    @Test
    public void testCostToGoalEstimate() {
        ModelStateEvaluatorAgentGoal modelStateEvaluatorAgentGoal = new ModelStateEvaluatorAgentGoal();
        // goal
        Position goalPosition = new Position();

        // agent
        Position agentPosition = new Position();

        // state
        ModelStateAgentGoal modelState = new ModelStateAgentGoal(agentPosition,
                goalPosition);

        // Agent at goal - Return ZERO
        double valuationGoal = modelStateEvaluatorAgentGoal
                .costToGoalEstimate(modelState);
        assertEquals("At goal", 0.0f, valuationGoal,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);
        assertEquals("At goal - cross-check",
                agentPosition.distance(goalPosition), valuationGoal,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);

        // Agent off by 1.0 in X
        agentPosition.setX(1.0f);
        double valuation1 = modelStateEvaluatorAgentGoal
                .costToGoalEstimate(modelState);
        assertEquals("Agent off by 1.0 in X", 1.0f, valuation1,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);
        assertEquals("Agent off by 1.0 in X - cross-check",
                agentPosition.distance(goalPosition), valuation1,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);

        // Agent off by 3.0 in X, 4.0 in Y
        agentPosition.setX(3.0f);
        agentPosition.setY(4.0f);
        double valuation5 = modelStateEvaluatorAgentGoal
                .costToGoalEstimate(modelState);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y", 5.0f, valuation5,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y - cross-check",
                agentPosition.distance(goalPosition), valuation5,
                ModelStateEvaluatorAgentGoal.GOAL_TOLERANCE);
    }

}
