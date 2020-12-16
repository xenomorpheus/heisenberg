package au.net.hal9000.heisenberg.ai.api;

import java.util.Queue;

/**
 * Generate all possible ModelState objects from current ModelState object.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface SuccessorFunction {

  /**
   * Generate a list of possible Successor objects from current ModelState.
   *
   * @param modelState current ModelState
   * @param actions a list of actions to generate new ModelState objects for.
   * @return A list of successors from current model state.
   */
  Queue<Successor> generateSuccessors(ModelState modelState, Path actions);
}
