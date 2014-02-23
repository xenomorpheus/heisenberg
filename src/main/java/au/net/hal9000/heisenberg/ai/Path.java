package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

/** AI path of Action objects. * @author bruins
 * @version $Revision: 1.0 $
 */
public class Path {

    /** path. */
    private Queue<Action> queue = new LinkedList<Action>();

    /**
     * Constructor.
     * 
     */
    public Path() {
        super();
    }

    /**
    
     * @return the queue */
    public Queue<Action> getQueue() {
        return queue;
    }

    /**
     * add action to path.
     * 
     * @param action
     *            action to add.
     */
    void add(Action action) {
        queue.add(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    /**
     * Method hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((null == queue) ? 0 : queue.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /**
     * Method equals.
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof Path)) {
            return false;
        }
        final Path other = (Path) obj;
        if (null == queue) {
            if (null != other.queue) {
                return false;
            }
        } else if (!queue.equals(other.queue)) {
            return false;
        }
        return true;
    }

    /**
     * Method toString.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Action action : queue) {
            string.append(action.toString());
        }
        return string.toString();
    }

}
