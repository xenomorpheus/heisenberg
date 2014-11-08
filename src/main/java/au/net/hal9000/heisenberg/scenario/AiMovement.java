package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.fail;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Queue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.BarrierLine;
import au.net.hal9000.heisenberg.ai.FringeElementImpl;
import au.net.hal9000.heisenberg.ai.MemoryImpl;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorImpl;
import au.net.hal9000.heisenberg.ai.ModelStateGoal;
import au.net.hal9000.heisenberg.ai.ModelStateMemories;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.SuccessorFunctionEntity;
import au.net.hal9000.heisenberg.ai.TransitionFunctionImpl;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.GoalEstFunction;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Successor;
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

    /**
     * Try to simulate as much as possible an Entity seeking the goal.
     */
    @Test
    public void testAiMovementWithGoal() {

        // Setup current model state.
        Position goalPosition = new Position(0, -2);
        Entity agent = new Cat();
        agent.setPosition(new Position(0, 2));
        ModelState modelState = new ModelStateGoal(agent.getPosition(),
                goalPosition);

        // Setup how to transition (move) to a new state.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup how to generate new successor states.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction);

        // Setup how we evaluate the worth of a new model state.
        final ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        GoalEstFunction goalEstCostFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelStateGoal modelState) {
                return modelStateEvaluator.evaluate(modelState);
            }
        };

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator, goalEstCostFunction);

        final Path path = searchAStar.findPathToGoal(modelState);
        System.out.println("path to goal: " + path);

        fail("todo"); // TODO this

        // end loop here
    }

    /**
     * Try to simulate as much as possible an Entity looking at a wall and
     * trying to walk to avoid it.
     */
    @Test
    public void testAiMovementWithMemories() {

        Entity agent = new Cat();
        agent.setPosition(new Position(0, 2));

        // Simulate the results after seeing a wall.
        Object blocker = "this is the wall";
        Point2D point1 = new Point2D.Double(-2, 0);
        Point2D point2 = new Point2D.Double(2, 0);
        Line2D line = new Line2D.Double(point1, point2);
        Barrier barrier = new BarrierLine(line, blocker);
        MemoryImpl memory = new MemoryOfBarrier(null, null, barrier);
        agent.addMemory(memory);

        // Model state
        ModelStateMemories modelState = new ModelStateMemories(
                agent.getPosition(), agent.getMemories());

        // Setup Transition and Successor Functions.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup Entity: position, successorFunction etc.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction);

        Queue<Successor> successors = successorFunction
                .generateSuccessors(modelState);
        fail("todo"); // TODO this
    }

}
