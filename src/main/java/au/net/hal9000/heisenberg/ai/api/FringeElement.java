package au.net.hal9000.heisenberg.ai.api;

public interface FringeElement {

    /**
     * get model state.
     * 
     * @return model state.
     */
    public abstract ModelState getModelState();

    /**
     * get the path so far.
     * 
     * @return path so far.
     */
    public abstract Path getPathSoFar();

    /**
     * get cost so far.
     * 
     * @return cost so far.
     */
    public abstract double getCostSoFar();

    /**
     * get order.
     * 
     * @return order.
     */
    public abstract double estimatedTotalCost();
    
    
}
