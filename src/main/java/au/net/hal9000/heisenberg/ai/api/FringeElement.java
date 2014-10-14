package au.net.hal9000.heisenberg.ai.api;

public interface FringeElement {

    /**
     * get model state.
     * 
     * @return model state.
     */
    ModelState getModelState();

    /**
     * get the path so far.
     * 
     * @return path so far.
     */
    Path getPathSoFar();

    /**
     * get cost so far.
     * 
     * @return cost so far.
     */
    double getCostSoFar();

    /**
     * get order.
     * 
     * @return order.
     */
    double estimatedTotalCost();

}
