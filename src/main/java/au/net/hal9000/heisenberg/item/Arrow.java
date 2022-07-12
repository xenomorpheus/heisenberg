package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Sharp;
import jakarta.persistence.Entity;

/**
 * Arrow.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
// @PrimaryKeyJoinColumn(name="tableid", referencedColumnName="tableid")
public class Arrow extends ItemImpl implements Sharp {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;
  /** Field VOLUME_BASE. (value is 1.0) */
  private static final float VOLUME_BASE = 1; // TODO arrow volume default

  /** Constructor. */
  public Arrow() {
    super("Arrow");
    this.setVolumeBase(VOLUME_BASE);
  }
}
