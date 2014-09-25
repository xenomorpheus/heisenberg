package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test that the entire process of AI, AKA Computational Search.
 * 
 * Depth First Search using a Successor Function that can only step one unit in
 * one of 4 directions at multiples of 90 degrees to goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchDepthFirstSuccessorSpokeTest {

    /** desired x pos. */
    private static final double X_POS = 3;
    /** desired y pos. */
    private static final double Y_POS = 4;
    /** hypot length */
    private static final double HYPOT = Math.hypot(X_POS, Y_POS);

    /**
     * Test that the entire process of AI, AKA Computational Search.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void test() throws CloneNotSupportedException {

        // The expected Actions to reach the Goal.
        Path expectedPath = new Path();
        
        // length is one unit.
        Point3d point = new Point3d(X_POS / HYPOT, Y_POS / HYPOT, 0);
        expectedPath.add(new ActionMoveImpl(point.clone()));
        expectedPath.add(new ActionMoveImpl(point.clone()));
        expectedPath.add(new ActionMoveImpl(point.clone()));
        expectedPath.add(new ActionMoveImpl(point.clone()));
        expectedPath.add(new ActionMoveImpl(point.clone()));

        // Initial ModelState
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(X_POS, Y_POS, 0);
        ModelState modelState = new ModelStateImpl(agentPosition, goalPosition);

        // Methods to evaluate, move, etc.
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        TransitionFunction transitionFunction = new TransitionFunctionImpl();
        SuccessorFunction successorFunction = new SuccessorFunctionSpoke(
                transitionFunction);
        Search search = new SearchDepthFirst(successorFunction,
                modelStateEvaluator);

        // Search
        Path gotPath = search.findPathToGoal(modelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
    }
}
