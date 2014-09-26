package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.CostFunction;
import au.net.hal9000.heisenberg.ai.api.ModelState;

/**
 * Standard AI cost function for the transition from one state to another by an
 * action.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class CostFunctionImpl implements CostFunction {

    /**
     * Constructor for CostFunctionV1.
     */
    public CostFunctionImpl() {
        super();
    }

    // TODO - this is just a fake version while testing.
    /**
     * Standard AI cost function for the transition from state before to other
     * by performing the action.
     * 
     * @param before
     *            the ModelState before.
     * @param action
     *            the action to move between ModeleState objects.
     * @param after
     *            the ModelState after.
     * 
     * @return the cost of performing the move between ModelStates.
     * @see au.net.hal9000.heisenberg.ai.api.CostFunction#calculateCost(ModelStateImpl,
     *      ActionAgentMoveV1, ModelStateImpl)
     */
    public double calculateCost(ModelState before, ActionMoveImpl action,
            ModelState after) {
        return action.getDelta().length();
    }

}
