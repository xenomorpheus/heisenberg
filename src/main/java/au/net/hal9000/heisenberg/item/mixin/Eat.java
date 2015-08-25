package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.entity.Animal;

public class Eat {

    /**
     * Eat an Animal.
     * 
     * @param animal
     *            food Item to eat.
     */
    public static void eat(Item animal, Item food) {
        if (food instanceof Animal) {
            Cooker cooker = animal.getCooker("ItemEatAnimal");
            cooker.setChef(animal);
            cooker.setItemsAvailable("Food", food);
            cooker.cook();
        } else  {
            throw new RuntimeException("Invalid type of item " + food);
        }
    }

}
