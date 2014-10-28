package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Test the ModelStateEvaluatorV1 class.
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
        ModelState modelStateV1 = new ModelStateImpl(agentPosition,
                goalPosition);
        ModelStateEvaluatorImpl modelStateEvaluatorV1 = new ModelStateEvaluatorImpl();

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

    /**
     * Method testEvaluateBadGoal.
     */
    @Test
    public void testEvaluateBadGoal() {
        ModelState modelStateV1 = new ModelStateImpl(null, null);
        modelStateV1.setAgentPosition(new Position());
        ModelStateEvaluatorImpl modelStateEvaluatorV1 = new ModelStateEvaluatorImpl();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorImpl.GOAL_MAY_MAY_NOT_BE_NULL);
        // Missing goalPosition, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);
        fail("should not get here");
    }

    /**
     * Method testEvaluateBadAgent.
     */
    @Test
    public void testEvaluateBadAgent() {
        ModelState modelStateV1 = new ModelStateImpl(null, null);
        modelStateV1.setGoalPosition(new Position());
        ModelStateEvaluatorImpl modelStateEvaluatorV1 = new ModelStateEvaluatorImpl();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorImpl.AGENT_MAY_MAY_NOT_BE_NULL);
        // Missing agentPosition, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);
        fail("should not get here");
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
        ModelState modelStateV1 = new ModelStateImpl(agentPosition,
                goalPosition);
        ModelStateEvaluatorImpl modelStateEvaluatorV1 = new ModelStateEvaluatorImpl();
        assertTrue("At goal", modelStateEvaluatorV1.isAtGoal(modelStateV1));
        agentPosition.setX(1.0f);
        assertFalse("Agent off by 1.0 in X",
                modelStateEvaluatorV1.isAtGoal(modelStateV1));
        agentPosition.setX(0.0f);
        agentPosition.setY(1.0f);
        assertFalse("Agent off by 1.0 in Y",
                modelStateEvaluatorV1.isAtGoal(modelStateV1));
    }

}
