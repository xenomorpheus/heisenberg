package au.net.hal9000.heisenberg.ai;

import java.util.Queue;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Point3d;

public class SearchTest {

    @Test
    public void test() {
        ModelStateV1 modelState = new ModelStateV1();
        Point3d agentPosition = new Point3d();
        Point3d goalPosition = new Point3d(3, 4, 0);
        modelState.setAgentPosition(agentPosition);
        modelState.setGoalPosition(goalPosition);

        TransitionFunction transitionFunction = new TransitionFunctionV1();
        SuccessorFunction successorFunction = new SuccessorFunctionV1(transitionFunction);
        Search search = new Search();
        Queue<Point3d> path = search.findPath(modelState, successorFunction,
                transitionFunction);
        for (Point3d direction : path) {
            System.out.println(direction + "\n");
        }

    }

}
