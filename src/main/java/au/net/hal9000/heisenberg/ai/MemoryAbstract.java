package au.net.hal9000.heisenberg.ai;

import au.net.hal9000.heisenberg.ai.api.Memory;
import java.util.Date;

/** Holds a memory for a period of time. */
public abstract class MemoryAbstract implements Memory {
  /** When the memory was created. */
  private Date createdDate;

  /**
   * Memories fade over time. Walls fade slowly. Memory objects of transient objects fade quickly.
   */
  private double decayRate;

  /**
   * Constructor.
   *
   * @param createdDate date memory created.
   */
  private MemoryAbstract(Date createdDate) {
    super();
    this.createdDate = createdDate;
  }

  /**
   * Constructor.
   *
   * @param createdDate date memory created.
   * @param decayRate date memory expires.
   */
  public MemoryAbstract(Date createdDate, double decayRate) {
    this(createdDate);
    this.decayRate = decayRate;
  }

  @Override
  public Date getCreatedDate() {
    return createdDate;
  }

  @Override
  public double getDecayRate() {
    return decayRate;
  }

  /**
   * Method toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder(7);
    return string
        .append(getClass().getSimpleName())
        .append("=[")
        .append(createdDate)
        .append(",")
        .append(decayRate)
        .append("]")
        .toString();
  }
}
