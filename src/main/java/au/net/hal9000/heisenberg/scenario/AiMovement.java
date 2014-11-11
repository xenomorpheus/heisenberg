package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertTrue;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.BarrierLine;
import au.net.hal9000.heisenberg.ai.MemoryImpl;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.MemorySetImpl;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorImpl;
import au.net.hal9000.heisenberg.ai.ModelStateGoal;
import au.net.hal9000.heisenberg.ai.ModelStateGoalMemorySet;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.SuccessorFunctionEntity;
import au.net.hal9000.heisenberg.ai.TransitionFunctionImpl;
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
public class AiMovement {
    static final double stepSizeMax = 0.3; // Max entity step size.
    static final int successorCountMax = 4; // Max number of successors.

    /**
     * Try to simulate as much as possible an Entity seeking the goal.
     */
    @Test
    public void testAiMovementWithGoal() {

        Entity agent = new Cat();
        final Position startPosition = new Position(0, 2);
        final Position goalPosition = new Position(0, -2);
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
        
        // TODO Should we change to total cost to goal, not remaining distance?
        // We need to stop graph loops by deprioritising wasted effort to revisit states.
        GoalEstFunction goalEstCostFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelStateGoal modelState) {
                return modelStateEvaluator.evaluate(modelState);
            }
        };

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator, goalEstCostFunction);

        // Generate path of actions.
        Path path = searchAStar.findPathToGoal(modelStateStart);

        // Tests
        // Start at start model state.
        ModelState modelStateCurrent = modelStateStart.duplicate();

        // Apply all actions in path.
        for (Action action : path) {
            // No step greater than max step size.
            if (action instanceof ActionMove) {
                ActionMove actionMove = (ActionMove) action;
                assertTrue("valid distance", actionMove.getPositionDelta()
                        .length() <= stepSizeMax);
            }

            modelStateCurrent = transitionFunction.transition(
                    modelStateCurrent, action);
        }

        // Check that we ended at goal.
        assertTrue(goalPosition.equals(modelStateCurrent.getAgentPosition(),
                Position.DEFAULT_AXIS_TOLERANCE));

    }

    /**
     * Try to simulate as much as possible an Entity looking at a wall and
     * trying to walk to avoid it.
     */
    @Test
    public void testAiMovementWithGoalMemorySet() {

        Entity agent = new Cat();
        final Position startPosition = new Position(0, 2);
        final Position goalPosition = new Position(0, -2);
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
        final ModelState modelStateStart = new ModelStateGoalMemorySet(new Position(
                agent.getPosition()), new Position(goalPosition), new MemorySetImpl(agent.getMemorySet()));

        // Setup how to transition (move) to a new state.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup how to generate new successor states.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction, stepSizeMax, successorCountMax);

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();

        // TODO Should we change to total cost to goal, not remaining distance?
        // We need to stop graph loops by deprioritising wasted effort to revisit states.
        
        GoalEstFunction goalEstCostFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelStateGoal modelState) {
                return modelStateEvaluator.evaluate(modelState);
            }
        };

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator, goalEstCostFunction);

        // Generate path of actions.
        Path path = searchAStar.findPathToGoal(modelStateStart);

        // Tests
        // Start at start model state.
        ModelState modelStateCurrent = modelStateStart.duplicate();

        // Apply all actions in path.
        for (Action action : path) {
            // No step greater than max step size.
            if (action instanceof ActionMove) {
                ActionMove actionMove = (ActionMove) action;
                assertTrue("valid distance", actionMove.getPositionDelta()
                        .length() <= (stepSizeMax * 1.01));
            }

            modelStateCurrent = transitionFunction.transition(
                    modelStateCurrent, action);
        }

        // Check that we ended at goal.
        assertTrue(goalPosition.equals(modelStateCurrent.getAgentPosition(),
                Position.DEFAULT_AXIS_TOLERANCE));        
    }

}