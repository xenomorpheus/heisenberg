package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Rat;
import au.net.hal9000.heisenberg.units.Point3d;

public class ModelStateEvaluatorV1Test {
    static final double DIFF = 0.00001f;

    /**
     * evaluate when two Item objects are on top of each other.
     */
    @Test
    public void testEvaluate() {
        // goal
        Item goal = new Rat();
        Point3d goalPosition = new Point3d();
        goal.setPosition(goalPosition);

        // agent
        Item agent = new Cat();
        Point3d agentPosition = new Point3d();
        agent.setPosition(agentPosition);

        // state
        ModelV1 modelStateV1 = new ModelV1();
        modelStateV1.setAgent(agent);
        modelStateV1.setGoal(goal);
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
        // agent
        Item agent = new Cat();
        Point3d agentPosition = new Point3d();
        agent.setPosition(agentPosition);

        // state
        ModelV1 modelStateV1 = new ModelV1();
        modelStateV1.setAgent(agent);
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorV1.GOAL_MAY_MAY_NOT_BE_NULL);
        // Missing goal, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);

    }

    @Test
    public void testEvaluateBadAgent() {
        // goal
        Item goal = new Rat();
        Point3d goalPosition = new Point3d();
        goal.setPosition(goalPosition);

        // state
        ModelV1 modelStateV1 = new ModelV1();
        modelStateV1.setGoal(goal);
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx
                .expectMessage(ModelStateEvaluatorV1.AGENT_MAY_MAY_NOT_BE_NULL);
        // Missing goal, throws RuntimeException
        modelStateEvaluatorV1.evaluate(modelStateV1);

    }

}
