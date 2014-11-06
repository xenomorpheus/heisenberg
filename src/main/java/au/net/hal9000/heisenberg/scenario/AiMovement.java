package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.fail;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Queue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.BarrierLine;
import au.net.hal9000.heisenberg.ai.Memory;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.ModelStateGoal;
import au.net.hal9000.heisenberg.ai.ModelStateMemories;
import au.net.hal9000.heisenberg.ai.SuccessorFunctionEntity;
import au.net.hal9000.heisenberg.ai.TransitionFunctionImpl;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.ModelState;
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
     * Try to simulate as much as possible an Entity seeking the gaol.
     */
    @Test
    public void testAiMovementWithGoal() {

        // Setup Agent and Goal
        Position goalPosition = new Position(0,-2);
        Entity agent = new Cat();
        agent.setPosition(new Position(0,2));

        // Model state
        ModelState modelState = new ModelStateGoal(agent.getPosition(), goalPosition);

        // Setup Transition and Successor Functions.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();

        // Setup Entity: position, successorFunction etc.
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction);

        Queue<Successor> successors = successorFunction
                .generateSuccessors(modelState);
        fail("todo"); // TODO this

    }


    /**
     * Try to simulate as much as possible an Entity looking at a wall and
     * trying to walk to avoid it.
     */
    @Test
    public void testAiMovementWithMemories() {

        Entity agent = new Cat();
        agent.setPosition(new Position(0,2));
        
        // Simulate the results after seeing a wall.
        Object blocker = "this is the wall";
        Point2D point1 = new Point2D.Double(-2, 0);
        Point2D point2 = new Point2D.Double(2, 0);
        Line2D line = new Line2D.Double(point1, point2);
        Barrier barrier = new BarrierLine(line, blocker);
        Memory memory = new MemoryOfBarrier(null, null, barrier);
        agent.addMemory(memory);

        // Model state
        ModelStateMemories modelState = new ModelStateMemories(agent.getPosition(), agent.getMemories());

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
