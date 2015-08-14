package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;

public class Eat {

    /**
     * Eat an Animal.
     * 
     * @param animal
     *            food Item to eat.
     */
    public static void eat(Item animal, Animal food) {
        Cooker cooker = animal.getCooker("ItemEatAnimal");
        cooker.setItemsAvailable("Food", food);
        cooker.cook();
    }

    
    /**
     * Eat an Animal.
     * 
     * @param animal
     *            food Item to eat.
     */
    public static void eat(Item animal, Cookie food) {
        Cooker cooker = animal.getCooker("ItemEatCookie");
        cooker.setItemsAvailable("Food", food);
        cooker.cook();
    }
    
    
}
