package au.net.hal9000.heisenberg.ai;

public class Successor {

    /** an action the agent can perform. */
    private Action action;
    /** the new model state if the actor performs this action. */
    private ModelState modelState;

    /**
     * Constructor.
     * 
     * @param action
     *            the possible action.
     * @param modelState
     *            the new state if the action is performed by the agent.
     */
    public Successor(Action action, ModelState modelState) {
        this.action = action;
        this.modelState = modelState;
    }

    /**
     * @return get the action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * @return get the new model state.
     */
    public ModelState getModelState() {
        return modelState;
    }

    @Override
    public String toString() {
        return "[action=" + action + ", modelStage=" + modelState + "]";
    }

}
