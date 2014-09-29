package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Action;
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
    private static final int Y_POS = -4;

    private void testHelper(GoalEstFunction gFunction) {

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
        Search search = new SearchAStar(successorFunction, modelStateEvaluator,
                gFunction);

        // Search
        Path gotPath = search.findPathToGoal(initialModelState);
        int actionCount = 0;
        int actionNorthCount = 0;
        int actionEastCount = 0;
        for (Action action : gotPath) {
            ActionMoveImpl actionMove = (ActionMoveImpl) action;
            actionCount++;
            if (ActionMoveImpl.NORTH.equals(actionMove)) {
                actionNorthCount++;
            } else if (ActionMoveImpl.EAST.equals(actionMove)) {
                actionEastCount++;
            }
        }
        assertEquals("action count", Math.abs(X_POS) + Math.abs(Y_POS),
                actionCount);
        assertEquals("action North count", -Y_POS, actionNorthCount);
        assertEquals("action East count", X_POS, actionEastCount);

    }

    /**
     * Test that the entire process of AI, AKA Computational Search.
     */
    @Test
    public void testWithSearchAStar() {
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
     */
    @Test
    public void testWithSearchUniformCost() {
        testHelper(null);
    }
}
