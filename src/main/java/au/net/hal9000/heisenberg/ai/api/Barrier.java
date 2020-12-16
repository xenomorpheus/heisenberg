package au.net.hal9000.heisenberg.ai.api;

import au.net.hal9000.heisenberg.ai.PathBlockDetails;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Holds details about a barrier blocking the path of movement.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public interface Barrier {

  /**
   * Returns details of any barrier blocking the path.<br>
   * Returns null if not blocked.<br>
   *
   * @param from the start position.
   * @param to the end position.
   * @return null or any details of a barrier.
   */
  PathBlockDetails getPathBlockDetailsDetails(Position from, Position to);
}
