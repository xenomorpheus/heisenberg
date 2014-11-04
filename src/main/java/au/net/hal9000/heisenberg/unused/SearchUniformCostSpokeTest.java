package au.net.hal9000.heisenberg.unused;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.ActionMoveImpl;
import au.net.hal9000.heisenberg.ai.ModelStateEvaluatorImpl;
import au.net.hal9000.heisenberg.ai.ModelStateGoal;
import au.net.hal9000.heisenberg.ai.PathImpl;
import au.net.hal9000.heisenberg.ai.SearchAStar;
import au.net.hal9000.heisenberg.ai.TransitionFunctionImpl;
import au.net.hal9000.heisenberg.ai.api.GoalEstFunction;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Search;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;
import au.net.hal9000.heisenberg.units.Position;

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
     */
    private void testHelper(GoalEstFunction gFunction) {

        // The expected Actions to reach the Goal.
        PathImpl expectedPath = new PathImpl();

        // length is one unit.
        Position point = new Position(X_POS / HYPOT, Y_POS / HYPOT);
        expectedPath.add(new ActionMoveImpl(new Position(point)));
        expectedPath.add(new ActionMoveImpl(new Position(point)));
        expectedPath.add(new ActionMoveImpl(new Position(point)));
        expectedPath.add(new ActionMoveImpl(new Position(point)));
        expectedPath.add(new ActionMoveImpl(new Position(point)));

        // Initial ModelState
        Position agentPosition = new Position();
        Position goalPosition = new Position(X_POS, Y_POS);
        ModelState modelState = new ModelStateGoal(agentPosition, goalPosition);

        // Methods to evaluate, move, etc.
        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorImpl();
        TransitionFunction transitionFunction = new TransitionFunctionImpl();
        SuccessorFunction successorFunction = new SuccessorFunctionGoalSpoke(
                transitionFunction);
        Search search = new SearchAStar(successorFunction, modelStateEvaluator,
                gFunction);

        // Search
        Path gotPath = search.findPathToGoal(modelState);
        assertTrue("path correct", expectedPath.equals(gotPath));
    }

    /**
     * Test that the entire process of AI, AKA Computational Search.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testWithSearchAStar() throws CloneNotSupportedException {
        GoalEstFunction gFunction = new GoalEstFunction() {

            @Override
            public double estimatedCostToGoal(ModelState modelState) {
                if (!(modelState instanceof ModelStateGoal)){
                    throw new IllegalArgumentException("modelState must implement ModelStateGoal");
                }
                ModelStateGoal modelStateGoal = (ModelStateGoal)modelState;
                return modelState.getAgentPosition().distance(
                        modelStateGoal.getGoalPosition());
            }

        };
        testHelper(gFunction);
    }

    /**
     * Test that the entire process of AI, AKA Computational Search.
     * 
     * @throws CloneNotSupportedException
     */
    @Test
    public void testWithSearchUniformCost() throws CloneNotSupportedException {
        testHelper(null);
    }

}
