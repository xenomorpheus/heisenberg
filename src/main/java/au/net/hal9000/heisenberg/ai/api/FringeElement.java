package au.net.hal9000.heisenberg.ai.api;

/**
 * A search algorithm will expand elements on the fringe of the area yet to be searched.
 *
 * @author bruins
 */
public interface FringeElement {

  /**
   * @return get model state.
   */
  ModelState getModelState();

  /**
   * @return get path so far.
   */
  Path getPathSoFar();

  /**
   * @return get cost so far.
   */
  double getCostSoFar();

  /**
   * @return get estimate of total cost from start to goal.
   */
  double getEstimatedTotalCost();
}
