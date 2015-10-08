package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.Water;

public class Drink {

    /**
     * Drink an Item.
     * 
     * @param entity
     *            the item consuming the liquid.
     * @param liquid
     *            liquid to drink.
     */
    public static void drink(Entity entity, Item liquid) {
        if (liquid instanceof Water) {
            Cooker cooker = entity.getCooker("ItemDrinkWater");
            cooker.setChef(entity);
            cooker.setItemsAvailable("Water", liquid);
            cooker.cook();
        } else {
            throw new RuntimeException("Invalid type of item " + liquid);
        }
    }

}
