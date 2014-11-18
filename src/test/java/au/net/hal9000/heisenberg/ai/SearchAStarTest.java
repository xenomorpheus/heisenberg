package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionMove;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.GoalEstFunction;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.Cat;
import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.units.Position;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchAStarTest {
    /* Max number of successors. */
    static final int successorCountMax = 8;

    /**
     * This sub is used by tests. Straight line movement from start to goal.<br>
     * Try to simulate as much as possible an Entity seeking the goal.
     */
    private void testAiMovementWithGoalBase(final int distanceStraightLine,
            final double stepSizeMax) {

        Entity agent = new Cat();
        final Position startPosition = new Position(0, 2);
        final Position goalPosition = new Position(0, startPosition.getY()
                - distanceStraightLine);
        agent.setPosition(new Position(startPosition));

        // Setup starting model state.
        final ModelState modelStateStart = new ModelStateGoal(new Position(
                agent.getPosition()), new Position(goalPosition));

        // Setup how to transition (move) to a new state.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup how to generate new successor states.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction, stepSizeMax, successorCountMax);

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        GoalEstFunction goalEstCostFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelStateGoal modelState) {
                return modelStateEvaluator.costToGoalEstimate(modelState);
            }
        };

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator, goalEstCostFunction);
        
        searchAStar.setFringeExpansionMax(15);

        // Generate path of actions.
        Path path = searchAStar.findPathToGoal(modelStateStart);

        // Tests
        assertNotNull("path defined", path);
        assertEquals("path length",
                (int) Math.ceil(distanceStraightLine / stepSizeMax), path.size());

        // Start at start model state.
        ModelState modelStateCurrent = modelStateStart.duplicate();

        // Apply all actions in path.
        for (Action action : path) {
            // No step greater than max step size.
            if (action instanceof ActionMove) {
                ActionMove actionMove = (ActionMove) action;
                assertTrue("assert each step size is within limits", actionMove
                        .getPositionDelta().length() <= stepSizeMax);
            }

            modelStateCurrent = transitionFunction.transition(
                    modelStateCurrent, action);
        }

        assertTrue("assert path leads to goal", goalPosition.equals(
                modelStateCurrent.getAgentPosition(),
                Position.DEFAULT_AXIS_TOLERANCE));

    }

    /**
     * Very simple test.<br>
     * Distance is 4 units, 4 steps of 1 unit each.<br>
     * Straight line movement from start to goal.<br>
     * Try to simulate as much as possible an Entity seeking the goal.
     */
    @Test
    public void testAiMovementWithGoalTest1() {
        final int distanceStraightLine = 4;
        final double stepSizeMax = 1; // Max entity step size.
        testAiMovementWithGoalBase(distanceStraightLine, stepSizeMax);
    }

    /**
     * Simple test.<br>
     * Distance is a 1 units. Max 0.3 units per step. Last step is 0.1 units.<br>
     * Straight line movement from start to goal.<br>
     * Try to simulate as much as possible an Entity seeking the goal.
     */
    @Test
    public void testAiMovementWithGoalTest2() {
        final int distanceStraightLine = 1;
        final double stepSizeMax = 0.3; // Max entity step size.
        testAiMovementWithGoalBase(distanceStraightLine, stepSizeMax);
    }


    /**
     * Try to simulate as much as possible an Entity looking at a wall and
     * trying to walk to avoid it.
     */
    @Test
    public void testAiMovementWithGoalMemorySet() {

        final double stepSizeMax = 1; // Max entity step size.
        Entity agent = new Cat();

        final int distanceStraightLine = 4;
        final Position startPosition = new Position(0, 2);
        final Position goalPosition = new Position(0, startPosition.getY()
                - distanceStraightLine);
        agent.setPosition(new Position(startPosition));

        // Simulate the results after seeing a wall.
        Object blocker = "this is the wall";
        Point2D point1 = new Point2D.Double(-2, 0);
        Point2D point2 = new Point2D.Double(2, 0);
        Line2D line = new Line2D.Double(point1, point2);
        Barrier barrier = new BarrierLine(line, blocker);
        MemoryImpl memory = new MemoryOfBarrier(null, 0, barrier);
        agent.addMemory(memory);

        // Setup starting model state.
        final ModelState modelStateStart = new ModelStateGoalMemorySet(
                new Position(agent.getPosition()), new Position(goalPosition),
                new MemorySetImpl(agent.getMemorySet()));

        // Setup how to transition (move) to a new state.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup how to generate new successor states.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction, stepSizeMax, successorCountMax);

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        GoalEstFunction goalEstCostFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelStateGoal modelState) {
                return modelStateEvaluator.costToGoalEstimate(modelState);
            }
        };

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator, goalEstCostFunction);
        searchAStar.setFringeExpansionMax(219);

        // Generate path of actions.
        Path path = searchAStar.findPathToGoal(modelStateStart);

        // Tests
        assertNotNull("path defined", path);
        // Start at start model state.
        ModelState modelStateCurrent = modelStateStart.duplicate();

        // Apply all actions in path.
        for (Action action : path) {
            // No step greater than max step size.
            if (action instanceof ActionMove) {
                ActionMove actionMove = (ActionMove) action;
                assertTrue("assert each step size is within limits", actionMove
                        .getPositionDelta().length() <= (stepSizeMax * 1.0001));
            }

            modelStateCurrent = transitionFunction.transition(
                    modelStateCurrent, action);
        }

        // Check that we ended at goal.
        assertTrue(goalPosition.equals(modelStateCurrent.getAgentPosition(),
                Position.DEFAULT_AXIS_TOLERANCE));
    }

}
