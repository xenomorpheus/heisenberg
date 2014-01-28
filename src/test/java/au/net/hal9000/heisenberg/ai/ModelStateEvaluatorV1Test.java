package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Rat;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test the ModelStateEvaluatorV1 class.
 * 
 * @author bruins
 * 
 */
public class ModelStateEvaluatorV1Test {
    /** tolerance for comparing floats. */
    static final double DIFF = 0.00001f;

    /**
     * evaluate when two Item objects are on top of each other.
     */
    @Test
    public void testEvaluate() {
        // goal
        Point3d goalPosition = new Point3d();

        // agent
        Point3d agentPosition = new Point3d();

        // state
        ModelStateV1 modelStateV1 = new ModelStateV1();
        modelStateV1.setAgentPosition(goalPosition);
        modelStateV1.setGoalPosition(goalPosition);
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();

        // Agent at goal - Return ZERO
        double valuationGoal = modelStateEvaluatorV1.evaluate(modelStateV1);
        assertEquals("At goal", 0.0f, valuationGoal, DIFF);
        assertEquals("At goal - cross-check",
                agentPosition.distance(goalPosition), valuationGoal, DIFF);

        // Agent off by 1.0 in X
        agentPosition.setX(1.0f);
        double valuation1 = modelStateEvaluatorV1.evaluate(modelStateV1);
        assertEquals("Agent off by 1.0 in X", 1.0f, valuation1, DIFF);
        assertEquals("Agent off by 1.0 in X - cross-check",
                agentPosition.distance(goalPosition), valuation1, DIFF);

        // Agent off by 3.0 in X, 4.0 in Y
        agentPosition.setX(3.0f);
        agentPosition.setY(4.0f);
        double valuation5 = modelStateEvaluatorV1.evaluate(modelStateV1);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y", 5.0f, valuation5, DIFF);
        assertEquals("Agent off by 3.0 in X, 4.0 in Y - cross-check",
                agentPosition.distance(goalPosition), valuation5, DIFF);
    }

    /** describe the exception we are expecting. */
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testEvaluateBadGoal() {
        ModelStateV1 modelStateV1 = new ModelStateV1();
        modelStateV1.setAgentPosition(new Point3d());
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorV1.GOAL_MAY_MAY_NOT_BE_NULL);
        // Missing goalPosition, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);

    }

    @Test
    public void testEvaluateBadAgent() {
        ModelStateV1 modelStateV1 = new ModelStateV1();
        modelStateV1.setGoalPosition(new Point3d());
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorV1.AGENT_MAY_MAY_NOT_BE_NULL);
        // Missing agentPosition, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);

    }

}
