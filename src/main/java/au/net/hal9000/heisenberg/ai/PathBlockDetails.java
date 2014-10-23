package au.net.hal9000.heisenberg.ai;

import java.awt.geom.Point2D;


/**
 * Details about the point in our path that we are blocked by an object.
 * 
 * @author bruins
 *
 */

public class PathBlockDetails {
    /** the point in our intended path at which we are blocked. */
    private Point2D blockingPoint;
    /** the object doing the blocking. */
    private Object blocker;

    /**
     * Constructor.
     * @param blockingPoint the point where the path is blocked.
     * @param blocker the object blocking the path.
     */
    PathBlockDetails(Point2D blockingPoint, Object blocker) {
        super();
        this.blockingPoint = blockingPoint;
        this.blocker = blocker;
    }

    /**
     * @return get the point where the path is blocked.
     */
    public Point2D getBlockingPoint() {
        return blockingPoint;
    }

    /**
     * @return get the object blocking the path.
     */
    public Object getBlocker() {
        return blocker;
    }

}
