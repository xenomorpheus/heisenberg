package au.net.hal9000.heisenberg.item;

import jakarta.persistence.Entity;

/**
 * A common torch. e.g. A rod with oil soaked rags on the end.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Torch extends Candle {

  /** A stick with some kind of combustable material on the end e.g. oiled rag. */
  private static final long serialVersionUID = 1L;

  /** Constructor for Torch. */
  public Torch() {
    super();
    setDescription("a short wooden rod tipped with cloth soaked in oil");
  }

  /**
   * Method setType.
   *
   * @param type int
   */
  public void setType(final int type) {
    if (1 == type) {
      setVolumeBase(1.0f); // TODO what about litres vs. gallons
      setWeightBase(1.0f); // TODO what about kilos vs. pounds ?
    }
  }
}
