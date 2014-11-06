package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Test the ModelStateEvaluatorImpl class.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ModelStateEvaluatorImplTest {
    /** tolerance for comparing floats. */
    static final double DIFF = 0.00001f;

    /**
     * evaluate when two Item objects are on top of each other.
     */
    @Test
    public void testEvaluate() {
        // goal
        Position goalPosition = new Position();

        // agent
        Position agentPosition = new Position();

        // state
        ModelState modelState = new ModelStateGoal(agentPosition,
                goalPosition);
        ModelStateEvaluatorImpl modelStateEvaluator = new ModelStateEvaluatorImpl();

        // Agent at goal - Return ZERO
        double valuationGoal = modelStateEvaluator.evaluate(modelState);
        assertEquals("At goal", 0.0f, valuationGoal, DIFF);
        assertEquals("At goal - cross-check",
                agentPosition.distance(goalPosition), valuationGoal, DIFF);

        // Agent off by 1.0 in X
        agentPosition.setX(1.0f);
        double valuation1 = modelStateEvaluator.evaluate(modelState);
        assertEquals("Agent off by 1.0 in X", 1.0f, valuation1, DIFF);
        assertEquals("Agent off by 1.0 in X - cross-check",
                agentPosition.distance(goalPosition), valuation1, DIFF);

        // Agent off by 3.0 in X, 4.0 in Y
        agentPosition.setX(3.0f);
        agentPosition.setY(4.0f);
        double valuation5 = modelStateEvaluator.evaluate(modelState);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y", 5.0f, valuation5, DIFF);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y - cross-check",
                agentPosition.distance(goalPosition), valuation5, DIFF);
    }


    /**
     * test if agent is at a goal.
     */
    @Test
    public void testIsAtGoal() {
        // goal
        Position goalPosition = new Position();

        // agent
        Position agentPosition = new Position();

        // state
        ModelState modelState = new ModelStateGoal(agentPosition,
                goalPosition);
        ModelStateEvaluatorImpl modelStateEvaluator = new ModelStateEvaluatorImpl();
        assertTrue("At goal", modelStateEvaluator.isAtGoal(modelState));
        agentPosition.setX(1.0f);
        assertFalse("Agent off by 1.0 in X",
                modelStateEvaluator.isAtGoal(modelState));
        agentPosition.setX(0.0f);
        agentPosition.setY(1.0f);
        assertFalse("Agent off by 1.0 in Y",
                modelStateEvaluator.isAtGoal(modelState));
    }

}
