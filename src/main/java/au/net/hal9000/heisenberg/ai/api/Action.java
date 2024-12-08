package au.net.hal9000.heisenberg.ai.api;

/** Apply an Action object to a ModelState to produce a new ModelState. */
public interface Action {

  /**
   * Get the cost of this action.
   *
   * @return
   */
  double getCost();

  /**
   * Apply the action to the modelState.
   *
   * @param modelState the modelState that will be mutated by the action.
   */
  void apply(ModelState modelState);

  /**
   * {@inheritDoc}
   *
   * @return String
   */
  @Override
  String toString();
}
