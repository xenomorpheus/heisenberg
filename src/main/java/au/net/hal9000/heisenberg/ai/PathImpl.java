package au.net.hal9000.heisenberg.ai;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.Path;

/**
 * AI path of Action objects. <br>
 * 
 * @version $Revision: 1.0 $
 * @author bruins
 */
public class PathImpl implements Iterable<Action>, Path, Cloneable{

    /** a list of actions. */
    private Queue<Action> queue = new LinkedList<Action>();

    /**
     * Constructor.
     * 
     */
    public PathImpl() {
        super();
    }

    /**
     * add action to path.
     * 
     * @param action
     *            action to add.
     */
    @Override
    public void add(Action action) {
        queue.add(action);
    }

    /**
     * Method hashCode.
     * 
     * @see java.lang.Object#hashCode()
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((null == queue) ? 0 : queue.hashCode());
        return result;
    }

    /**
     * Method equals.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * @param obj
     *            Object
     * 
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
        if (!(obj instanceof PathImpl)) {
            return false;
        }
        final PathImpl other = (PathImpl) obj;
        if ( size() != other.size()){
            return false;
        }
        // Compare each element
        Iterator<Action> otherIterator = other.iterator();
        for (Action action : this){
            Action otherAction = otherIterator.next();
            if (! action.equals(otherAction)){
                return false;
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see au.net.hal9000.heisenberg.ai.Path#size()
     */
    private int size(){
        return queue.size();
    }

    /* (non-Javadoc)
     * @see au.net.hal9000.heisenberg.ai.Path#iterator()
     */
    @Override
    public Iterator<Action> iterator(){
        return queue.iterator();
    }
    
    
    /**
     * Method toString.
     * 
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

    /** Clone 
     * @throws CloneNotSupportedException */
    @Override
    public PathImpl clone() throws CloneNotSupportedException{
        PathImpl path = (PathImpl) super.clone();
        // TODO is this correct?
        path.queue = new LinkedList<Action>();
        for(Action action: queue){
            path.add(action);
        }
        return path;
    }
    
}
