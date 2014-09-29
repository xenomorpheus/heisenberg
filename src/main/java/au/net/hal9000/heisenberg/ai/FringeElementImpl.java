package au.net.hal9000.heisenberg.ai;

/** The search fringe elements hold the state of the search at that point.
 * 
 *  The current model state, the path to get there and the cost so far.
 *  
 */
import au.net.hal9000.heisenberg.ai.api.FringeElement;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;

public class FringeElementImpl implements FringeElement,
        Comparable<FringeElementImpl> {
    private ModelState modelState;
    private Path pathSoFar;
    private double costSoFar;
    private double order;

    public FringeElementImpl(ModelState modelState, Path pathSoFar,
            double costSoFar, double order) {
        this.modelState = modelState;
        this.pathSoFar = pathSoFar;
        this.costSoFar = costSoFar;
        this.order = order;
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

    public double getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "[ModelState=" + modelState + ", pathSoFar=" + pathSoFar
                + ", costSoFar=" + costSoFar + ", order="+order+"]";
    }

    @Override
    public int compareTo(FringeElementImpl o) {
        double oOrder = o.getOrder();
        return (order < oOrder) ? -1 : ((order > oOrder) ? 1 : 0);
    }
}
