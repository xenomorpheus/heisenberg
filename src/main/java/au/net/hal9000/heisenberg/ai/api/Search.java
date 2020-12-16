package au.net.hal9000.heisenberg.ai.api;

public interface Search {

  /** @return the successor function. */
  SuccessorFunction getSuccessorFunction();

  /**
   * set the successor function
   *
   * @param successorFunction the successor function.
   */
  void setSuccessorFunction(SuccessorFunction successorFunction);

  /**
   * Return a list of actions to get from the current model state to reach the goal model state.
   *
   * @param modelState current model state.
   * @return list of actions.
   */
  Path findPathToGoal(ModelState modelState);

  /**
   * Get an object to evaluate cost from current ModelState to reach the Goal.
   *
   * @return model state evaluator object
   */
  ModelStateEvaluator getModelStateEvaluator();

  /**
   * Set an object to evaluate cost from current ModelState to reach the Goal.
   *
   * @param modelStateEvaluator
   */
  void setModelStateEvaluator(ModelStateEvaluator modelStateEvaluator);
}
