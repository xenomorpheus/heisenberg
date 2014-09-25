package au.net.hal9000.heisenberg.ai;

/**
 * A Successor object contains three things:<br>
 * 1) The resultant ModelState when the Action is performed.<br>
 * 2) A valid Action that may be performed at the current ModelState.<br>
 * 3) The cost of performing the action.<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SuccessorImpl implements Successor {

    /** the new model state if the actor performs this action. */
    private ModelState modelState;
    /** the action that led to this ModelState */
    private Action action;
    /** the cost of performing this action */
    private double cost;

    /**
     * Constructor.
     * @param modelState
     *            the new state if the action is performed by the agent.
     * @param action
     *            the possible action.
     * @param cost the cost of performing this action
     */
    public SuccessorImpl(ModelState modelState, Action action, double cost) {
        this.modelState = modelState;
        this.action = action;
        this.cost = cost;
    }

    /* (non-Javadoc)
     * @see au.net.hal9000.heisenberg.ai.Successor#getAction()
     */
    @Override
    public Action getAction() {
        return action;
    }

    /* (non-Javadoc)
     * @see au.net.hal9000.heisenberg.ai.Successor#getModelState()
     */
    @Override
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
        return "[modelStage=" + modelState + ", action=" + action + ", cost=" + cost + "]";
    }

}
