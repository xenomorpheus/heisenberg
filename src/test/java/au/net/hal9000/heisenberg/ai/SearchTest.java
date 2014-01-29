package au.net.hal9000.heisenberg.ai;

import java.util.Queue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

public class SearchTest {

    @Test
    public void test() {
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(3, 4, 0);
        ModelState modelState = new ModelStateV1(agentPosition, goalPosition);

        ModelStateEvaluator modelStateEvaluator = new ModelStateEvaluatorV1();
        TransitionFunction transitionFunction = new TransitionFunctionV1();
        SuccessorFunction successorFunction = new SuccessorFunctionV1(
                transitionFunction);

        Search search = new Search(successorFunction, modelStateEvaluator);

        Queue<Action> path = search.findPath(modelState);
        for (Action direction : path) {
            System.out.println(direction + "\n");
        }

    }

}
