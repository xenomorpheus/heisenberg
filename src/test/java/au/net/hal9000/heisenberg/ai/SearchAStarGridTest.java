package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.ai.action.ActionGeneratorMovementAbsoluteBarriers;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionAgentMoveRelative;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.units.Position;
import java.awt.geom.Line2D;
import java.util.Arrays;
import org.junit.Test;

/**
 * Testing A* Search in a grid based system using Position objects.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchAStarGridTest {
  /* Max number of successors. */
  private static final int successorCountMax = 8;

  /**
   * This sub is used by tests. Straight line movement from start to goal.<br>
   * Try to simulate as much as possible an Entity seeking the goal.
   */
  private void testAiMovementWithGoalBase(
      final int distanceStraightLine, final double stepSizeMax) {

    Entity agent = new Cat();
    final Position startPosition = new Position(0, 2);
    final Position goalPosition = new Position(0, startPosition.getY() - distanceStraightLine);
    agent.setPosition(new Position(startPosition));

    // Setup starting model state. No barriers
    final ModelStateAgentGoalImpl modelStateStart =
        new ModelStateAgentGoalImpl(new Position(agent.getPosition()), new Position(goalPosition));

    // Setup how to transition (move) to a new state.
    final TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

    // Setup how to generate new successor states.
    final SuccessorFunction successorFunction = new SuccessorFunctionImpl(transitionFunction);

    // Service to provide movement Action objects.
    final ActionGenerator movementAbsoluteBarriers =
        new ActionGeneratorMovementAbsoluteBarriers(stepSizeMax, successorCountMax, 0f);

    // Setup how we evaluate the worth of a new model state.
    ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
    SearchAStar searchAStar =
        new SearchAStar(successorFunction, modelStateEvaluator, movementAbsoluteBarriers);

    searchAStar.setFringeExpansionMax(17);

    // Generate path of actions.
    Path path = searchAStar.findPathToGoal(modelStateStart);

    // Tests
    assertNotNull("path defined", path);
    assertEquals("path length", (int) Math.ceil(distanceStraightLine / stepSizeMax), path.size());

    // Start at start model state.
    ModelState modelStateCurrent = modelStateStart.duplicate();

    // Apply all actions in path.
    for (Action action : path) {
      // No step greater than max step size.
      if (action instanceof ActionAgentMoveRelative) {
        ActionAgentMoveRelative actionMove = (ActionAgentMoveRelative) action;
        assertTrue(
            "assert each step size is within limits",
            actionMove.getPositionDelta().length() <= stepSizeMax);
      }

      modelStateCurrent = transitionFunction.transition(modelStateCurrent, action);
    }
    assertTrue(
        "modelStateCurrent instanceof ModelStateAgentGoal",
        modelStateCurrent instanceof ModelStateAgentGoalImpl);
    assertTrue(
        "assert path leads to goal",
        goalPosition.equals(
            ((ModelStateAgentGoalImpl) modelStateCurrent).getAgentPosition(),
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
   * Distance is a 1 units. Max 0.3 units per step. Last step is 0.1 units. <br>
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
   * Try to simulate as much as possible an Entity looking at a wall and trying to walk to avoid it.
   */
  @Test
  public void testAiMovementWithGoalMemorySet() {

    final double stepSizeMax = 1; // Max entity step size.
    Entity agent = new Cat();

    final int distanceStraightLine = 4;
    final Position startPosition = new Position(0, 2);
    final Position goalPosition = new Position(0, startPosition.getY() - distanceStraightLine);
    agent.setPosition(new Position(startPosition));

    // Simulate the results after seeing a wall.
    // There are Barrier objects.
    Object blocker = "this is the wall";
    Line2D.Double line = new Line2D.Double(-2, 0.5, 2, 0.5);
    Barrier barrier = new BarrierShape(Arrays.asList(line), blocker);
    MemoryAbstract memory = new MemoryOfBarrier(null, 0, barrier);
    agent.memoryAdd(memory);

    // Setup starting model state.
    final ModelStateAgentGoalImpl modelStateStart =
        new ModelStateAgentGoalMemorySetImpl(
            new Position(agent.getPosition()),
            new Position(goalPosition),
            new MemorySetImpl(agent.getMemorySet()));

    // Service to transition (move) to a new ModelState.
    TransitionFunction transitionFunction = new TransitionFunctionAgentGoalImpl();

    // Setup how to generate new successor states.
    SuccessorFunction successorFunction = new SuccessorFunctionImpl(transitionFunction);

    // Service to provide movement Action objects.
    final ActionGenerator movementAbsoluteBarriers =
        new ActionGeneratorMovementAbsoluteBarriers(stepSizeMax, successorCountMax, 0f);

    // Setup how we evaluate the worth of a new model state.
    ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorAgentGoal();
    SearchAStar searchAStar =
        new SearchAStar(successorFunction, modelStateEvaluator, movementAbsoluteBarriers);
    searchAStar.setFringeExpansionMax(519);

    // Generate path of actions.
    Path path = searchAStar.findPathToGoal(modelStateStart);
    // System.out.println("FingeExpansionCount="+searchAStar.getFringeExpansionCount());

    // Tests
    assertNotNull("path defined", path);
    // Start at start model state.
    ModelState modelStateCurrent = modelStateStart.duplicate();

    // Apply all actions in path.
    for (Action action : path) {
      // No step greater than max step size.
      if (action instanceof ActionAgentMoveRelative) {
        ActionAgentMoveRelative actionMove = (ActionAgentMoveRelative) action;
        assertTrue(
            "assert each step size is within limits",
            actionMove.getPositionDelta().length() <= (stepSizeMax * 1.0001));

        // TODO Did we walk through the barrier?
      }

      modelStateCurrent = transitionFunction.transition(modelStateCurrent, action);
    }

    // Check that we ended at goal.
    assertTrue(
        "modelStateCurrent instanceof ModelStateAgentGoal",
        modelStateCurrent instanceof ModelStateAgentGoalImpl);
    assertTrue(
        goalPosition.equals(
            ((ModelStateAgentGoalImpl) modelStateCurrent).getAgentPosition(),
            Position.DEFAULT_AXIS_TOLERANCE));
  }
}
