package au.net.hal9000.heisenberg.ai.api;

/**
 * A Successor object contains three things:<br>
 * 1) The resultant ModelState when the Action is performed.<br>
 * 2) A valid Action that may be performed at the current ModelState.<br>
 * 3) The cost of performing the action.<br>
 */
public interface Successor {

  /**
   * @see au.net.hal9000.heisenberg.ai.api.ModelState
   * @return get the new model state.
   */
  ModelState getModelState();

  /**
   * @see au.net.hal9000.heisenberg.ai.api.Action
   * @return get the action.
   */
  Action getAction();

  /**
   * @return get the cost.
   */
  double getCost();
}
