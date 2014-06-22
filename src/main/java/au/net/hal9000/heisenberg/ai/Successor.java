package au.net.hal9000.heisenberg.ai;

/**
 * A Successor object contains two things:<br>
 * 1) A valid Action that may be performed at the current ModelState.<br>
 * 2) The resultant ModelState when the Action is performed.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
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
     * 
     * @return get the action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * 
     * @return get the new model state.
     */
    public ModelState getModelState() {
        return modelState;
    }

    /**
     * {@inheritDoc}
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[action=" + action + ", modelStage=" + modelState + "]";
    }

}
