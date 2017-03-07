package au.net.hal9000.heisenberg.ai;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.junit.Test;

import au.net.hal9000.heisenberg.ai.action.ActionBase;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
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

	/** Graph details about a node. */
	private class MyNodeDataSet {

		/** estimated cost to goal. */
		float heuristic;

		/** possible actions AKA edges from this node */
		Map<String, Float> transitionMap;

		/** constructor */
		MyNodeDataSet(float heuristic, Map<String, Float> transitionMap) {
			this.heuristic = heuristic;
			this.transitionMap = transitionMap;
		}
	}

	private class MyAction extends ActionBase implements Action {
		String nodeName;

		// Constructor
		MyAction(String nodeName, Float cost) {
			super(cost);
			this.nodeName = nodeName;
		}

		// Misc
		@Override
		public void apply(ModelState modelState) {
			MyModelState myModelState = (MyModelState) modelState;
			myModelState.setAgentPosition(nodeName);
		}

	}

	private class MyTransitionMap {

		Map<String, MyNodeDataSet> getTransitionMap() {
			Map<String, Float> sTransition = new HashMap<>();
			sTransition.put("A", 2.f);
			sTransition.put("B", 1.f);

			Map<String, Float> aTransition = new HashMap<>();
			aTransition.put("D", 1.f);
			aTransition.put("C", 3.f);
			aTransition.put("B", 1.f);

			Map<String, Float> bTransition = new HashMap<>();
			bTransition.put("D", 5.f);
			bTransition.put(GOAL, 10.f);

			Map<String, Float> cTransition = new HashMap<>();
			cTransition.put(GOAL, 7.f);

			Map<String, Float> dTransition = new HashMap<>();
			dTransition.put(GOAL, 4.f);

			Map<String, MyNodeDataSet> transitionMap = new TreeMap<>();
			transitionMap.put(START, new MyNodeDataSet(0.0f, sTransition));
			transitionMap.put("A", new MyNodeDataSet(3.0f, aTransition));
			transitionMap.put("B", new MyNodeDataSet(3.0f, bTransition));
			transitionMap.put("C", new MyNodeDataSet(1.0f, cTransition));
			transitionMap.put("D", new MyNodeDataSet(2.0f, dTransition));
			return transitionMap;
		}
	}

	private class MyModelStateEvaluator implements ModelStateEvaluator {
		private Map<String, MyNodeDataSet> transitionMap = null;

		MyModelStateEvaluator(Map<String, MyNodeDataSet> transitionMap) {
			super();
			this.transitionMap = transitionMap;
		}

		@Override
		public double costToGoalEstimate(ModelState modelState) {
			MyModelState myModelState = (MyModelState) modelState;
			if (isAtGoal(modelState)) {
				return 0;
			}
			return transitionMap.get(myModelState.agentPosition).heuristic;
		}

		@Override
		public boolean isAtGoal(ModelState modelState) {
			MyModelState myModelState = (MyModelState) modelState;
			return GOAL.equals(myModelState.agentPosition);
		}

		@Override
		public boolean isModelStateInAdded(List<ModelState> addedModelStates, ModelState modelState) {
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

		void setAgentPosition(String agentPosition) {
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
			result = prime * result + ((agentPosition == null) ? 0 : agentPosition.hashCode());
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

	private class MyActionGenerator implements ActionGenerator {

		private Map<String, MyNodeDataSet> transitionMap;

		MyActionGenerator(Map<String, MyNodeDataSet> transitionMap) {
			super();
			this.transitionMap = transitionMap;
		}

		@Override
		public Path generateActions(ModelState modelState) {
			Path list = new PathImpl();
			MyModelState myModelState = (MyModelState) modelState;
			String agentPositionNow = myModelState.agentPosition;
			MyNodeDataSet nodeDataSet = transitionMap.get(agentPositionNow);
			for (String agentPositionNext : nodeDataSet.transitionMap.keySet()) {
				Float cost = nodeDataSet.transitionMap.get(agentPositionNext);
				list.add(new MyAction(agentPositionNext, cost));
			}
			return list;
		}

	}

	private class MyTransitionFunction implements TransitionFunction {

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

		Map<String, MyNodeDataSet> transitionMap = new MyTransitionMap().getTransitionMap();

		// Setup how to transition (move) to a new state.
		final TransitionFunction transitionFunction = new MyTransitionFunction();

		final ActionGenerator actionGenerator = new MyActionGenerator(transitionMap);

		// Setup how to generate new successor states.
		SuccessorFunction successorFunction = new SuccessorFunction() {

			@Override
			public Queue<Successor> generateSuccessors(ModelState modelState, Path actions) {
				Queue<Successor> successors = new LinkedList<>();

				for (Action action : actions) {
					ModelState nextModelState = transitionFunction.transition(modelState, action);
					successors.add(new SuccessorImpl(nextModelState, action, action.getCost()));
				}
				return successors;
			}

		};

		// Create an object to evaluate each Model State.
		final ModelStateEvaluator modelStateEvaluator = new MyModelStateEvaluator(
				new MyTransitionMap().getTransitionMap());

		SearchAStar searchAStar = new SearchAStar(successorFunction, modelStateEvaluator, actionGenerator);

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
			modelStateCurrent = transitionFunction.transition(modelStateCurrent, action);
		}

		// Check we reached a Goal.
		assertTrue("modelStateCurrent instanceof ModelStateAgentGoal", modelStateCurrent instanceof MyModelState);
		assertTrue("Agent at goal", modelStateEvaluator.isAtGoal(modelStateCurrent));
	}
}
