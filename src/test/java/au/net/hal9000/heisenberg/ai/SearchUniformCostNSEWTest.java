package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Search;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Point3d;

/**
 * Test that the entire process of AI, AKA Computational Search.
 * 
 * Depth First Search using a Successor Function that can only step North,
 * South, East or West.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchUniformCostNSEWTest {

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
        PathImpl expectedPath = new PathImpl();
        expectedPath.add(ActionMoveImpl.NORTH);
        expectedPath.add(ActionMoveImpl.NORTH);
        expectedPath.add(ActionMoveImpl.EAST);
        expectedPath.add(ActionMoveImpl.NORTH);
        expectedPath.add(ActionMoveImpl.EAST);
        expectedPath.add(ActionMoveImpl.NORTH);
        expectedPath.add(ActionMoveImpl.EAST);

        // Initial ModelState
        Point3d agentPosition = new Point3d();
        Point3d desiredGoalPosition = new Point3d(X_POS, Y_POS, 0);
        ModelState initialModelState = new ModelStateImpl(agentPosition,
                desiredGoalPosition);

        // Methods to evaluate, move, etc.
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        TransitionFunction transitionFunction = new TransitionFunctionImpl();
        SuccessorFunction successorFunction = new SuccessorFunctionNSEW(
                transitionFunction);
        Search search = new SearchUniformCost(successorFunction,
                modelStateEvaluator);

        // Search
        Path gotPath = search.findPathToGoal(initialModelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
    }
}
