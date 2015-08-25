package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.Item;

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
