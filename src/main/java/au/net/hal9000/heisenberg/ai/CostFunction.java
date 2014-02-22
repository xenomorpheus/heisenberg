package au.net.hal9000.heisenberg.ai;

/**
 * Standard AI cost function for the transition from one state to another by an
 * action.
 */
public interface CostFunction {


    /**
     * Standard AI cost function fro the transition from state before to other
     * by performing the action.
     * 
     * @param before
     *            the ModelState before.
     * @param action
     *            the action to move between ModeleState objects.
     * @param after
     *            the ModelState after.
    
     * @return the cost of performing the move between ModelStates. */
    double calculateCost(ModelStateV1 before, ActionAgentMoveV1 action,
            ModelStateV1 after);

}