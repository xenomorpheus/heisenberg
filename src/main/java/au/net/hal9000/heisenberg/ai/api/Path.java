package au.net.hal9000.heisenberg.ai.api;

public interface Path extends Iterable<Action> {

    /**
     * add action to path.
     * 
     * @param action
     *            action to add.
     */
    void add(Action action);

    /**
     * Shallow clone current path.
     * 
     * @return new path.
     */
    Path duplicate();

    /**
     * @return number of actions in the path.
     */
    int size();

}
