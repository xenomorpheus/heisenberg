package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;

/**
 * The fringe elements hold the state of the search at that point.
 * 
 * In particular:<br>
 * The current model state<br>
 * The path to get there<br>
 * The cost so far.<br>
 * 
 */

public class FringeElementImpl implements FringeElement,
        Comparable<FringeElementImpl> {
    /** the state of the model at this fringe element */
    private ModelState modelState;
    /** the list of actions to reach this state of the model */
    private Path pathSoFar;
    /** the total cost to reach this state of the model */
    private double costSoFar;
    /** the estimated total cost of following the path then on to the goal */
    private double estimatedTotalCost;

    /**
     * Constructor
     * 
     * @param modelState
     *            the state of the model at this fringe element.
     * @param pathSoFar
     *            the list of actions to reach this state of the model.
     * @param costSoFar
     *            the total cost to reach this state of the model.
     * @param estimatedTotalCost
     *            the estimated total cost of following the path then on to the
     *            goal.
     */
    public FringeElementImpl(ModelState modelState, Path pathSoFar,
            double costSoFar, double estimatedTotalCost) {
        this.modelState = modelState;
        this.pathSoFar = pathSoFar;
        this.costSoFar = costSoFar;
        this.estimatedTotalCost = estimatedTotalCost;
    }

    /** {@inheritDoc} */
    @Override
    public ModelState getModelState() {
        return modelState;
    }

    /** {@inheritDoc} */
    @Override
    public Path getPathSoFar() {
        return pathSoFar;
    }

    /** {@inheritDoc} */
    @Override
    public double getCostSoFar() {
        return costSoFar;
    }

    public double estimatedTotalCost() {
        return estimatedTotalCost;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "[ModelState=" + modelState + ", pathSoFar=" + pathSoFar
                + ", costSoFar=" + costSoFar + ", estimatedTotalCost="
                + estimatedTotalCost + "]";
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(FringeElementImpl o) {
        double oEstimatedTotalCost = o.estimatedTotalCost();
        return (estimatedTotalCost < oEstimatedTotalCost) ? -1
                : ((estimatedTotalCost > oEstimatedTotalCost) ? 1 : 0);
    }
}
