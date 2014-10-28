package au.net.hal9000.heisenberg.scenario;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.BarrierLine;
import au.net.hal9000.heisenberg.ai.Memory;
import au.net.hal9000.heisenberg.ai.MemoryOfBarrier;
import au.net.hal9000.heisenberg.ai.SuccessorFunctionEntity;
import au.net.hal9000.heisenberg.ai.TransitionFunctionImpl;
import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class AiMovement {

    /**
     * Try to simulate as much as possible an Entity looking at 
     * a wall and trying to walk to avoid it.
     */
    @Test
    public void testAiMovement() {

        // Simulate the results after seeing a wall.
        Point2D point1 = new Point2D.Double(1,-2);
        Point2D point2 = new Point2D.Double(1,2);
        Line2D line = new Line2D.Double(point1, point2);
        Object blocker = "this is the wall";
        Barrier barrier = new BarrierLine(line, blocker);
        Memory memory = new MemoryOfBarrier(null, null, barrier);

        // Setup Transition and Successor Functions.
        TransitionFunction transitionFunction = new TransitionFunctionImpl();
        SuccessorFunction successorFunction = new SuccessorFunctionEntity(
                transitionFunction);

        // Setup Entity: position, successorFunction etc.
        
    }
}
