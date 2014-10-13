package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.GoalEstFunction;
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
 * Depth First Search using a Successor Function that can only step one unit in
 * one of 4 directions at multiples of 90 degrees to goal.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchUniformCostSpokeTest {

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
    private void testHelper(GoalEstFunction gFunction) throws CloneNotSupportedException  {

        // The expected Actions to reach the Goal.
        PathImpl expectedPath = new PathImpl();
        
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
        Search search = new SearchAStar(successorFunction,
                modelStateEvaluator,gFunction);

        // Search
        Path gotPath = search.findPathToGoal(modelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
    }

    /**
     * Test that the entire process of AI, AKA Computational Search.
     * @throws CloneNotSupportedException 
     */
    @Test
    public void testWithSearchAStar() throws CloneNotSupportedException {
        GoalEstFunction gFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelState modelState) {
                return modelState.getAgentPosition().distance(
                        modelState.getGoalPosition());
            }

        };
        testHelper(gFunction);
    }

    /**
     * Test that the entire process of AI, AKA Computational Search.
     * @throws CloneNotSupportedException 
     */
    @Test
    public void testWithSearchUniformCost() throws CloneNotSupportedException {
        testHelper(null);
    }

}