package au.net.hal9000.heisenberg.ai.api;

import java.awt.geom.Line2D;

import au.net.hal9000.heisenberg.ai.PathBlockDetails;

/**
 * Holds details about a barrier blocking the path of movement.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 *
 */

public interface Barrier {

    /**
     * Returns details of any barrier blocking the path.<br>
     * Returns null if not blocked.<br>
     * 
     * @param movement
     *            the desired path between two points.
     * @return null or any details of a barrier.
     */
    PathBlockDetails getPathBlockDetailsDetails(Line2D movement);

}
