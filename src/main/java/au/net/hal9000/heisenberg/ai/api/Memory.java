package au.net.hal9000.heisenberg.ai.api;

import java.util.Date;

/**
 * Holds a Memory for a period of until the memory fades.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface Memory {

  /** @return created date. */
  Date getCreatedDate();

  /**
   * The rate of decay of this Memory.
   *
   * @return the rate of decay of this Memory.
   */
  double getDecayRate();
}
