package au.net.hal9000.heisenberg.ai;

import java.awt.geom.Line2D;
import java.text.DecimalFormat;

import au.net.hal9000.heisenberg.ai.api.Barrier;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Geometry;

/**
 * Used by MemoryOfBarrier class to remember barriers seen in physics engine.
 * Proof of concept. A very simple Barrier. Just a straight line.
 * 
 * @author bruins
 *
 */

public class BarrierLine implements Barrier {

    /** A line that forms a barrier. */
    private Line2D barrierLine;

    /** The object doing the blocking. */
    private Object blockerObject;

    /**
     * Constructor.
     * 
     * @param barrier
     *            line segment.
     * @param blocker
     *            the Object doing the blocking.
     */
    public BarrierLine(Line2D barrier, Object blocker) {
        super();
        this.barrierLine = barrier;
        this.blockerObject = blocker;
    }

    /**
     * Returns details of any barrier blocking the path.<br>
     * Returns null if not blockedwon't block.<br>
     * 
     * @param movement
     *            the path traveled between two points.
     * @return null or any details of a barrier.
     */
    @Override
    public PathBlockDetails getPathBlockDetailsDetails(Line2D movement) {

        // TODO Currently movement is a point mass so this approach may fail if
        // barrier has a gap which is too small for entity to fit through.
        // In future consider movement of shapes through.
        // Perhaps best left to the physics engine.

        PathBlockDetails blocker = null;
        Position point = Geometry.getLineIntersection(movement, barrierLine);
        if (null != point) {
            blocker = new PathBlockDetails(point, blockerObject);
        }

        return blocker;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(11);
        DecimalFormat df = new DecimalFormat("#.###");
        return string.append(getClass().getSimpleName()).append("=[ (")
                .append(df.format(barrierLine.getX1())).append(",")
                .append(df.format(barrierLine.getY1())).append(")=>(")
                .append(df.format(barrierLine.getX2())).append(",")
                .append(df.format(barrierLine.getY2())).append("), blocker ")
                .append(blockerObject).append("]").toString();
    }
}
