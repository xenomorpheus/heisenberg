package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Rat;
import au.net.hal9000.heisenberg.units.Point3d;

public class ModelStateEvaluatorV1Test {
    static final double DIFF = 0.00001f;

    private double dist(Item agent, Item goal) {

        // state
        ModelStateV1 modelStateV1 = new ModelStateV1();
        modelStateV1.setAgent(agent);
        modelStateV1.setGoal(goal);
        ModelStateEvaluatorV1 modelStateEvaluatorV1 = new ModelStateEvaluatorV1();
        return modelStateEvaluatorV1.evaluate(modelStateV1);
    }

    /**
     * evaluate when two Item objects are on top of each other.
     */
    @Test
    public void basicTestZero() {
        // goal
        Item goal = new Rat();
        Point3d goalPosition = new Point3d();
        goal.setPosition(goalPosition);

        // agent
        Item agent = new Cat();
        Point3d agentPosition = new Point3d();
        agent.setPosition(agentPosition);

        Double valuation = dist(agent, goal);
        assertEquals(-1.0f * goalPosition.distance(agentPosition), valuation,
                DIFF);
    }

    /**
     * evaluate when two Item objects are off by one unit.
     */
    @Test
    public void basicTestXOne() {
        // goal
        Item goal = new Rat();
        Point3d goalPosition = new Point3d();
        goal.setPosition(goalPosition);

        // agent
        Item agent = new Cat();
        Point3d agentPosition = new Point3d();
        agentPosition.setX(1.0f);
        agent.setPosition(agentPosition);

        Double valuation = dist(agent, goal);
        assertEquals(-1.0f * goalPosition.distance(agentPosition), valuation,
                DIFF);
    }

    /**
     * evaluate when two Item objects are off by one unit.
     */
    @Test
    public void basicTestXTwo() {
        // goal
        Item goal = new Rat();
        Point3d goalPosition = new Point3d();
        goalPosition.setX(1.0f);
        goal.setPosition(goalPosition);

        // agent
        Item agent = new Cat();
        Point3d agentPosition = new Point3d();
        agentPosition.setX(3.0f);
        agent.setPosition(agentPosition);

        Double valuation = dist(agent, goal);
        assertEquals(-1.0f * goalPosition.distance(agentPosition), valuation,
                DIFF);
    }
}
