package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;

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
