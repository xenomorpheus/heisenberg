package au.net.hal9000.heisenberg.ai.api;

/**
 * The TransitionFunction takes a ModelState and applies the Action which results in a new
 * ModelState object.<br>
 */
public interface TransitionFunction {

  /**
   * Method transition.
   *
   * @param modelState ModelState
   * @param action Action
   * @return ModelState
   */
  ModelState transition(ModelState modelState, Action action);
}
