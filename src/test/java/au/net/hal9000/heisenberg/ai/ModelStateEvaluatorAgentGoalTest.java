package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.units.Position;

public class ModelStateEvaluatorAgentGoalTest {

    @Test
    public void testModelStateInAddedTrue() {
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
        List<ModelState> addedModelStates = new ArrayList<>();
        addedModelStates.add(new ModelStateAgentGoal(new Position(1, 1),
                new Position()));
        addedModelStates.add(new ModelStateAgentGoal(new Position(2, 2),
                new Position()));
        addedModelStates.add(new ModelStateAgentGoal(new Position(3, 3),
                new Position()));

        ModelState modelState = new ModelStateAgentGoal(new Position(2, 2),
                new Position());
        assertTrue(modelStateEvaluator.isModelStateInAdded(addedModelStates,
                modelState));
    }

    @Test
    public void testModelStateInAddedFalse() {
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
        List<ModelState> addedModelStates = new ArrayList<>();
        addedModelStates.add(new ModelStateAgentGoal(new Position(1, 1),
                new Position()));
        addedModelStates.add(new ModelStateAgentGoal(new Position(2, 2),
                new Position()));
        addedModelStates.add(new ModelStateAgentGoal(new Position(3, 3),
                new Position()));

        ModelState modelState = new ModelStateAgentGoal(new Position(4, 4),
                new Position());
        assertFalse(modelStateEvaluator.isModelStateInAdded(addedModelStates,
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
                modelStateEvaluatorAgentGoal.getPositionTolerance());
        assertEquals("At goal - cross-check",
                agentPosition.distance(goalPosition), valuationGoal,
                modelStateEvaluatorAgentGoal.getPositionTolerance());

        // Agent off by 1.0 in X
        agentPosition.setX(1.0f);
        double valuation1 = modelStateEvaluatorAgentGoal
                .costToGoalEstimate(modelState);
        assertEquals("Agent off by 1.0 in X", 1.0f, valuation1,
                modelStateEvaluatorAgentGoal.getPositionTolerance());
        assertEquals("Agent off by 1.0 in X - cross-check",
                agentPosition.distance(goalPosition), valuation1,
                modelStateEvaluatorAgentGoal.getPositionTolerance());

        // Agent off by 3.0 in X, 4.0 in Y
        agentPosition.setX(3.0f);
        agentPosition.setY(4.0f);
        double valuation5 = modelStateEvaluatorAgentGoal
                .costToGoalEstimate(modelState);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y", 5.0f, valuation5,
                modelStateEvaluatorAgentGoal.getPositionTolerance());
        assertEquals("Agent off by 3.0 in X, 4.0 in Y - cross-check",
                agentPosition.distance(goalPosition), valuation5,
                modelStateEvaluatorAgentGoal.getPositionTolerance());
    }

}
