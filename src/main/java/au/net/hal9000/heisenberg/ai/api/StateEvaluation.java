package au.net.hal9000.heisenberg.ai.api;

/** Objects that can evaluate their own state. */
public interface StateEvaluation {

  /**
   * @return return a numeric valuation of this object's state. Higher the better.
   */
  double getStateEvaluation();
}
