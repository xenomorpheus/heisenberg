package au.net.hal9000.heisenberg.ai.api;

import java.util.Iterator;

public interface Path {

    /**
     * Method iterator
     * @return Iterator of Actions
     */
    public abstract Iterator<Action> iterator();

    /**
     * add action to path.
     * 
     * @param action
     *            action to add.
     */
    public abstract void add(Action action);

    /**
     * Shallow clone current path.
     * @return new path.
     */
    public abstract Path clone() throws CloneNotSupportedException;

}
