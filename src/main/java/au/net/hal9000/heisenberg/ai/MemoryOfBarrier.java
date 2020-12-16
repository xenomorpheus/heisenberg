package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Barrier;
import java.util.Date;

/**
 * Holds a memory of a Barrier.
 *
 * @author bruins
 */
public class MemoryOfBarrier extends MemoryAbstract {
  /** the barrier this memory is about. */
  private Barrier barrier;

  /**
   * Constructor.
   *
   * @param createdDate date memory created.
   * @param decayRate rate memory decays.
   * @param barrier the barrier this memory is about.
   */
  public MemoryOfBarrier(Date createdDate, double decayRate, Barrier barrier) {
    super(createdDate, decayRate);
    this.barrier = barrier;
  }

  /** @return get the barrier. */
  public Barrier getBarrier() {
    return barrier;
  }

  /**
   * Method toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(11);
    sb.append(super.toString());
    if (barrier != null) {
      sb.append(", ").append(barrier);
    }
    return sb.append("]").toString();
  }
}
