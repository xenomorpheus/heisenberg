package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import java.util.Queue;
import java.util.LinkedList;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test that the entire process of AI, AKA Computational Search.
 */
public class SearchTestV2 {

    /** desired x pos. */
    private static final int X_POS = 3;
    /** desired y pos. */
    private static final int Y_POS = 4;

    /**
     * Test that the entire process of AI, AKA Computational Search.
     */
    @Test
    public void test() {

        Path expectedPath = new Path();
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(X_POS, Y_POS, 0);
        ModelState modelState = new ModelStateV1(agentPosition, goalPosition);

        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorV1();
        TransitionFunction transitionFunction = new TransitionFunctionV1();
        SuccessorFunction successorFunction = new SuccessorFunctionV2(
                transitionFunction);

        Search search = new Search(successorFunction, modelStateEvaluator);

        // System.out.println("Goal is at position: " + goalPosition);
        // System.out.println("Agent is at position: " + agentPosition);
        Path gotPath = search.findPath(modelState);
        System.out.println("gotPath     : " + gotPath);
        System.out.println("expectedPath: " + expectedPath);
        assertTrue("path correct", expectedPath.equals(gotPath));

    }
}
