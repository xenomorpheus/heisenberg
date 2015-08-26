package au.net.hal9000.heisenberg.item;


public interface Animal {

    /**
     * eat.
     * 
     * @param food
     *            food to eat.<br>
     */
    public void eat(Item food);

    /**
     * Drink.
     * 
     * @param liquid
     *            liquid to drink.<br>
     */
    public void drink(Item water);
}
