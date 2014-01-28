package au.net.hal9000.heisenberg.ai;

public class CostFunctionV1 implements CostFunction {

    public CostFunctionV1() {
    }

    // TODO - this is just a fake version while testing.
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
     * @return the cost of performing the move between ModelStates.
     */
    public double calculateCost(ModelStateV1 before, ActionAgentMove action,
            ModelStateV1 after) {
        return action.getDelta().size();
    }

}
