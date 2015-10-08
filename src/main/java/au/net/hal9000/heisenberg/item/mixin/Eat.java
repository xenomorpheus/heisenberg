package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;

public class Eat {

    /**
     * Eat an Animal.
     * 
     * @param entity
     *            food Item to eat.
     */
    public static void eat(Entity entity, Item food) {
        if (food instanceof Animal) {
            Cooker cooker = entity.getCooker("ItemEatAnimal");
            cooker.setChef(entity);
            cooker.setItemsAvailable("Food", food);
            cooker.cook();
        } else  {
            throw new RuntimeException("Invalid type of item " + food);
        }
    }

}
