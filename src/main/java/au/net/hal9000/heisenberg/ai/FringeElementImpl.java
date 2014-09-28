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
    ModelState modelState;
    Path pathSoFar;
    double costSoFar;

    public FringeElementImpl(ModelState modelState, Path pathSoFar,
            double costSoFar) {
        this.modelState = modelState;
        this.pathSoFar = pathSoFar;
        this.costSoFar = costSoFar;
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
    public String toString() {
        return "[ModelState=" + modelState + ", pathSoFar=" + pathSoFar
                + ", costSoFar=" + costSoFar + "]";
    }

    @Override
    public int compareTo(FringeElementImpl o) {
        double oCost = o.getCostSoFar();
        return (costSoFar < oCost) ? -1 : ((costSoFar > oCost) ? 1 : 0);
    }
}
