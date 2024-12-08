package au.net.hal9000.heisenberg.ai.api;

import java.util.Date;

/** Holds a Memory for a period of until the memory fades. */
public interface Memory {

  /**
   * @return created date.
   */
  Date getCreatedDate();

  /**
   * The rate of decay of this Memory.
   *
   * @return the rate of decay of this Memory.
   */
  double getDecayRate();
}
