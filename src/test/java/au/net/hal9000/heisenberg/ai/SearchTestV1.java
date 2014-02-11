package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test that the entire process of AI, AKA Computational Search.
 */
public class SearchTestV1 {

    /** desirect x pos. */
    private static final int X_POS = 3;
    /** desirect y pos. */
    private static final int Y_POS = 4;

    /**
     * Test that the entire process of AI, AKA Computational Search.
     */
    @Test
    public void test() {

        Path expectedPath = new Path();
        expectedPath.add(SuccessorFunctionV1.NORTH);
        expectedPath.add(SuccessorFunctionV1.NORTH);
        expectedPath.add(SuccessorFunctionV1.EAST);
        expectedPath.add(SuccessorFunctionV1.NORTH);
        expectedPath.add(SuccessorFunctionV1.EAST);
        expectedPath.add(SuccessorFunctionV1.NORTH);
        expectedPath.add(SuccessorFunctionV1.EAST);
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(X_POS, Y_POS, 0);
        ModelState modelState = new ModelStateV1(agentPosition, goalPosition);

        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorV1();
        TransitionFunction transitionFunction = new TransitionFunctionV1();
        SuccessorFunction successorFunction = new SuccessorFunctionV1(
                transitionFunction);

        Search search = new Search(successorFunction, modelStateEvaluator);

        // System.out.println("Goal is at position: " + goalPosition);
        // System.out.println("Agent is at position: " + agentPosition);
        Path gotPath = search.findPath(modelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
        System.out.println(gotPath);

    }
}
