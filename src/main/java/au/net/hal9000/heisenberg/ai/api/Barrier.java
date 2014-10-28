package au.net.hal9000.heisenberg.ai.api;

import java.awt.geom.Line2D;

import au.net.hal9000.heisenberg.ai.PathBlockDetails;

public interface Barrier {

    /**
     * Returns details of any barrier blocking the path.<br>
     * Returns null if not blockedwon't block.<br>
     * 
     * @param movement
     *            the path traveled between two points.
     * @return null or any details of a barrier.
     */
    PathBlockDetails getPathBlockDetailsDetails(Line2D movement);

}
