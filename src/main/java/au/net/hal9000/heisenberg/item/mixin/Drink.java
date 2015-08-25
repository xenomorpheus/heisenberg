package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Water;

public class Drink {

    /**
     * Drink an Item.
     * 
     * @param consumer
     *            the item consuming the liquid.
     * @param liquid
     *            liquid to drink.
     */
    public static void drink(Item consumer, Item liquid) {
        if (liquid instanceof Water) {
            Cooker cooker = consumer.getCooker("ItemDrinkWater");
            cooker.setChef(consumer);
            cooker.setItemsAvailable("Water", liquid);
            cooker.cook();
        } else {
            throw new RuntimeException("Invalid type of item " + liquid);
        }
    }

}
