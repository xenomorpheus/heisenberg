package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test that the entire process of AI, AKA Computational Search.
 * @author bruins
 * @version $Revision: 1.0 $
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

        // The expected Actions to reach the Goal.
        Path expectedPath = new Path();
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));
        expectedPath.add(new ActionAgentMoveV1(null, new Point3d(0.6, 0.8, 0)));

        // Initial ModelState        
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(X_POS, Y_POS, 0);
        ModelState modelState = new ModelStateV1(agentPosition, goalPosition);

        // Methods to evaluate, move, etc.
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorV1();
        TransitionFunction transitionFunction = new TransitionFunctionV1();
        SuccessorFunction successorFunction = new SuccessorFunctionV2(
                transitionFunction);
        Search search = new Search(successorFunction, modelStateEvaluator);

        // Search
        Path gotPath = search.findPath(modelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
    }
}
