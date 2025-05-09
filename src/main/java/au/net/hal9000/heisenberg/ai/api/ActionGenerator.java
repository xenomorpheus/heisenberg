package au.net.hal9000.heisenberg.ai.api;

/** Interface for generating actions in the AI system. */
public interface ActionGenerator {
  /**
   * Generate a list of possible actions from this model state.
   *
   * @param modelState current model state
   * @return a list of possible actions from this model state.
   */
  public Path generateActions(ModelState modelState);
}
