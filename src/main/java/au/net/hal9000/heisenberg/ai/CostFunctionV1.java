package au.net.hal9000.heisenberg.ai;

/**
 * Standard AI cost function for the transition from one state to another by an
 * action.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class CostFunctionV1 implements CostFunction {

    /**
     * Constructor for CostFunctionV1.
     */
    public CostFunctionV1() {
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
     * @see au.net.hal9000.heisenberg.ai.CostFunction#calculateCost(ModelStateV1,
     *      ActionAgentMoveV1, ModelStateV1)
     */
    public double calculateCost(ModelStateV1 before, ActionV1 action,
            ModelStateV1 after) {
        return action.getDelta().length();
    }

}
