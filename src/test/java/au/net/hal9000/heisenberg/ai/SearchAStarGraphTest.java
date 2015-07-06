package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import au.net.hal9000.heisenberg.ai.api.TransitionFunction;

/**
 * This test is performed within a graph. The AStar search is required to find
 * the minimum cost from Start node (S) to Goal node (G). A node has edges that
 * lead to other nodes. Following an edge incurs a cost.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SearchAStarGraphTest {

    static final String START = "S";
    static final String GOAL = "G";

    /** an edge leading away from a node */
    private class NodeTransition {
        /** the next node */
        String nextNodeName;
        /* the cost to reach the next node. */
        float cost;

        /** constructor */
        NodeTransition(String nextNodeName, float cost) {
            this.nextNodeName = nextNodeName;
            this.cost = cost;
        }
    }

    /** Graph details about a node. */
    private class NodeDataSet {

        /** estimated cost to goal. */
        float heuristic;

        /** possible actions AKA edges from this node */
        List<NodeTransition> transitionSet;

        /** constructor */
        NodeDataSet(float heuristic, List<NodeTransition> transitionSet) {
            this.heuristic = heuristic;
            this.transitionSet = transitionSet;
        }
    }

    private class MyAction implements Action {
        String nodeName;

        MyAction(String nodeName) {
            this.nodeName = nodeName;
        }
    }

    private class MyTransitionSet {
        private Map<String, NodeDataSet> transitionSet = null;

        MyTransitionSet() {
            super();
        }

        Map<String, NodeDataSet> getTransitionSet() {
            if (transitionSet == null) {
                List<NodeTransition> sTransition = new ArrayList<>();
                sTransition.add(new NodeTransition("A", 2.f));
                sTransition.add(new NodeTransition("B", 1.f));

                List<NodeTransition> aTransition = new ArrayList<>();
                aTransition.add(new NodeTransition("D", 1.f));
                aTransition.add(new NodeTransition("C", 3.f));
                aTransition.add(new NodeTransition("B", 1.f));

                List<NodeTransition> bTransition = new ArrayList<>();
                bTransition.add(new NodeTransition("D", 5.f));
                bTransition.add(new NodeTransition(GOAL, 10.f));

                List<NodeTransition> cTransition = new ArrayList<>();
                cTransition.add(new NodeTransition(GOAL, 7.f));

                List<NodeTransition> dTransition = new ArrayList<>();
                dTransition.add(new NodeTransition(GOAL, 4.f));

                transitionSet = new TreeMap<>();
                transitionSet.put(START, new NodeDataSet(0.0f, sTransition));
                transitionSet.put("A", new NodeDataSet(3.0f, aTransition));
                transitionSet.put("B", new NodeDataSet(3.0f, bTransition));
                transitionSet.put("C", new NodeDataSet(1.0f, cTransition));
                transitionSet.put("D", new NodeDataSet(2.0f, dTransition));
            }
            return transitionSet;
        }
    }

    private class MyModelStateEvaluator implements ModelStateEvaluator {
        private Map<String, NodeDataSet> transitionSet = null;

        MyModelStateEvaluator(Map<String, NodeDataSet> transitionSet) {
            super();
            this.transitionSet = transitionSet;
        }

        @Override
        public double costToGoalEstimate(ModelState modelState) {
            MyModelState myModelState = (MyModelState) modelState;
            if (isAtGoal(modelState)){
                return 0;
            }
            return transitionSet.get(myModelState.agentPosition).heuristic;
        }

        @Override
        public boolean isAtGoal(ModelState modelState) {
            MyModelState myModelState = (MyModelState) modelState;
            return GOAL.equals(myModelState.agentPosition);
        }

        @Override
        public boolean isModelStateInAdded(List<ModelState> addedModelStates,
                ModelState modelState) {
            for (ModelState modelStateOther : addedModelStates) {
                if (modelState.equals(modelStateOther)) {
                    return true;
                }
            }
            return false;
        }
    };

    private class MyModelState implements ModelState {
        String agentPosition;

        MyModelState(String agentPosition) {
            super();
            this.agentPosition = agentPosition;
        }

        @Override
        public MyModelState duplicate() {
            return new MyModelState(agentPosition);
        }

        private SearchAStarGraphTest getOuterType() {
            return SearchAStarGraphTest.this;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result
                    + ((agentPosition == null) ? 0 : agentPosition.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MyModelState other = (MyModelState) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (agentPosition == null) {
                if (other.agentPosition != null)
                    return false;
            } else if (!agentPosition.equals(other.agentPosition))
                return false;
            return true;
        }

    }

    // Setup how to transition (move) to a new state.
    TransitionFunction transitionFunction = new TransitionFunction() {

        @Override
        public ModelState transition(ModelState modelState, Action action) {
            MyAction myAction = (MyAction) action;
            return new MyModelState(myAction.nodeName);
        }
    };

    /**
     * Test moving within a graph set of notes.
     */
    @Test
    public void testAiMovementWithGoalBase() {

        // Setup starting model state.
        final ModelState modelStateStart = new MyModelState(START);

        // Setup how to generate new successor states.
        SuccessorFunction successorFunction = new SuccessorFunction() {

            Map<String, NodeDataSet> transitionSet = new MyTransitionSet()
                    .getTransitionSet();

            @Override
            public Queue<Successor> generateSuccessors(ModelState modelState) {
                Queue<Successor> successors = new LinkedList<>();

                MyModelState myModelState = (MyModelState) modelState;
                String agentPostion = myModelState.agentPosition;
                List<NodeTransition> transitionList = transitionSet
                        .get(agentPostion).transitionSet;
                for (NodeTransition nodeTransition : transitionList) {
                    MyAction myAction = new MyAction(
                            nodeTransition.nextNodeName);
                    ModelState nextModelState = transitionFunction.transition(
                            modelState, myAction);
                    successors.add(new SuccessorImpl(nextModelState, myAction,
                            nodeTransition.cost));
                }
                return successors;
            }
        };

        // Create an object to evaluate each Model State.
        final ModelStateEvaluator modelStateEvaluator = new MyModelStateEvaluator(
                new MyTransitionSet().getTransitionSet());

        SearchAStar searchAStar = new SearchAStar(successorFunction,
                modelStateEvaluator);

        searchAStar.setFringeExpansionMax(15);

        // Generate path of actions.
        Path path = searchAStar.findPathToGoal(modelStateStart);

        // Tests
        assertNotNull("path defined", path);

        // Verify the Start ... Goal by following the path.
        // Begin at the Start model state.
        ModelState modelStateCurrent = modelStateStart.duplicate();

        // Apply each Action in turn from the Path.
        for (Action action : path) {
            modelStateCurrent = transitionFunction.transition(
                    modelStateCurrent, action);
        }

        // Check we reached a Goal.
        assertTrue("modelStateCurrent instanceof ModelStateAgentGoal",
                modelStateCurrent instanceof MyModelState);
        assertTrue("Agent at goal",
                modelStateEvaluator.isAtGoal(modelStateCurrent));
    }
}
