package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;
import java.text.DecimalFormat;

/**
 * The fringe elements hold the state of the search at that point.
 *
 * <p>In particular:<br>
 * The current model state.<br>
 * The path to get there.<br>
 * The cost so far.<br>
 */
class FringeElementImpl implements FringeElement, Comparable<FringeElementImpl> {
  /** the state of the model at this fringe element. */
  private ModelState modelState;

  /** the list of actions to reach this state of the model. */
  private Path pathSoFar;

  /** the total cost to reach this state of the model. */
  private double costSoFar;

  /** the estimated total cost of following the path then on to the goal. */
  private double estimatedTotalCost;

  /**
   * Constructor.
   *
   * @param modelState the state of the model at this fringe element.
   * @param pathSoFar the list of actions to reach this state of the model.
   * @param costSoFar the total cost to reach this state of the model.
   * @param estimatedTotalCost the estimated total cost of following the path then on to the goal.
   */
  FringeElementImpl(
      ModelState modelState, Path pathSoFar, double costSoFar, double estimatedTotalCost) {
    this.modelState = modelState;
    this.pathSoFar = pathSoFar;
    this.costSoFar = costSoFar;
    this.estimatedTotalCost = estimatedTotalCost;
  }

  @Override
  public ModelState getModelState() {
    return modelState;
  }

  @Override
  public Path getPathSoFar() {
    return pathSoFar;
  }

  @Override
  public double getCostSoFar() {
    return costSoFar;
  }

  @Override
  public double getEstimatedTotalCost() {
    return estimatedTotalCost;
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.###");
    StringBuilder string = new StringBuilder(11);
    return string
        .append(getClass().getSimpleName())
        .append("=[costSoFar=")
        .append(df.format(costSoFar))
        .append(", estimatedTotalCost=")
        .append(df.format(estimatedTotalCost))
        .append(", ")
        .append(modelState)
        .append(", ")
        .append(pathSoFar)
        .append(']')
        .toString();
  }

  @Override
  public int compareTo(FringeElementImpl other) {
    double oEstimatedTotalCost = other.getEstimatedTotalCost();
    return (estimatedTotalCost < oEstimatedTotalCost)
        ? -1
        : ((estimatedTotalCost > oEstimatedTotalCost) ? 1 : 0);
  }
}
