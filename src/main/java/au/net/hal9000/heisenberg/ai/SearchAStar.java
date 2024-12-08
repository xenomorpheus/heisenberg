package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateEvaluator;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.ai.api.Successor;
import au.net.hal9000.heisenberg.ai.api.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A-Star Search & Uniform Cost Search.<br>
 * Uniform Cost Search is just SearchAStar without an estimate of cost to goal.
 */
public class SearchAStar extends SearchBase {
  /** generates possible Action objects from current ModelState */
  private ActionGenerator actionGenerator;

  /** maximum fringe expansion. */
  private int fringeExpansionMax = 0;

  /** how many fringe expansions so far */
  private int fringeExpansionCount = 0;

  /** */
  private List<ModelState> fringeAdded;

  /**
   * constructor - A-Star search.
   *
   * @param successorFunction the successor function.
   * @param modelStateEvaluator the model state evaluator.
   * @param actionGenerator service to generate possible actions from a given ModelState object.
   */
  public SearchAStar(
      final SuccessorFunction successorFunction,
      final ModelStateEvaluator modelStateEvaluator,
      ActionGenerator actionGenerator) {
    super(successorFunction, modelStateEvaluator);
    this.actionGenerator = actionGenerator;
  }

  // Setters and Getters.
  /**
   * @return the number of Fringes elements that were expanded.
   */
  public int getFringeExpansionCount() {
    return fringeExpansionCount;
  }

  public void setFringeExpansionMax(int fringeExpansionMax) {
    this.fringeExpansionMax = fringeExpansionMax;
  }

  // Misc.
  /**
   * Return a list of actions to get from the current model state to reach the goal model state.
   *
   * @param initialModelState initial model state.
   * @return list of actions.
   */
  @Override
  public final Path findPathToGoal(final ModelState initialModelState) {

    Path resultPath = null;
    fringeExpansionCount = 0;
    /**
     * Remember the places we have already added to the fringe. <br>
     * This dramatically reduces the size of fringe.
     */
    fringeAdded = new ArrayList<>();

    /** fringe of states to expand */
    PriorityQueue<FringeElement> fringe = new PriorityQueue<>();

    fringe.add(new FringeElementImpl(initialModelState, new PathImpl(), 0f, 0));
    fringeAdded.add(initialModelState);

    while (!fringe.isEmpty()
        && ((fringeExpansionMax == 0) || (fringeExpansionCount < fringeExpansionMax))) {
      fringeExpansionCount++;

      FringeElement fringeElement = fringe.remove();
      ModelState currentModelState = fringeElement.getModelState();
      Path pathSoFar = fringeElement.getPathSoFar();
      double costSoFar = fringeElement.getCostSoFar();

      if (getModelStateEvaluator().isAtGoal(currentModelState)) {
        resultPath = pathSoFar;
        break;
      }
      // Action objects that may be performed at this ModelState
      Path actions = actionGenerator.generateActions(currentModelState);

      // ModelState Successor objects from the current ModelState.
      Queue<Successor> successors =
          getSuccessorFunction().generateSuccessors(currentModelState, actions);

      for (Successor successor : successors) {

        ModelState successorModelState = successor.getModelState();

        // Don't add state to the fringe more than once.
        if (getModelStateEvaluator().isModelStateInAdded(fringeAdded, successorModelState)) {
          // System.out.println("NOT Adding to Fringe: "+successorModelState);
          continue;
        }
        fringeAdded.add(successorModelState);

        // Add a new fringe element, with the extra action and cost.
        Path newPathSoFar = pathSoFar.duplicate();
        newPathSoFar.add(successor.getAction());
        double newCostSoFar = costSoFar + successor.getCost();
        double costToGoalEst = getModelStateEvaluator().costToGoalEstimate(successorModelState);
        FringeElement fringeElementNew =
            new FringeElementImpl(
                successorModelState, newPathSoFar, newCostSoFar, newCostSoFar + costToGoalEst);
        fringe.add(fringeElementNew);
        // System.out.println("Fringe add="+fringeElementNew);
      }
    }
    return resultPath;
  }

  /**
   * Used for debugging.
   *
   * @return the contents of FringeAdded.
   */
  public List<ModelState> getFringeAdded() {
    return fringeAdded;
  }
}
