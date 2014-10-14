package au.net.hal9000.heisenberg.ai.api;





public interface Path extends Iterable<Action>{

    /**
     * add action to path.
     * 
     * @param action
     *            action to add.
     */
    public abstract void add(Action action);

    /**
     * Shallow clone current path.
     * @throws CloneNotSupportedException
     * @return new path.
     */
    public abstract Path clone() throws CloneNotSupportedException;

}
