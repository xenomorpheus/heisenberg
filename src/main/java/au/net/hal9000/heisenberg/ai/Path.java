package au.net.hal9000.heisenberg.ai;

import java.util.LinkedList;
import java.util.Queue;

import org.omg.CORBA.StringHolder;

/** AI path of Action objects. */
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
     * @return the queue
     */
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((queue == null) ? 0 : queue.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Path)) {
            return false;
        }
        Path other = (Path) obj;
        if (queue == null) {
            if (other.queue != null) {
                return false;
            }
        } else if (!queue.equals(other.queue)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Action action : queue) {
            string.append(action.toString());
        }
        return string.toString();
    }

}
