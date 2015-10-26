package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;

public class EntityConsumeSustenance {

    /**
     * Eat or Drink
     * 
     * @param entity that which is doing the eating or drinking
     * @param sustenance that which is eat or drank
     */
    public static void consume(Entity entity, Item sustenance) {
        Cooker cooker = entity.getCooker("ItemConsumeSustenance");
        cooker.setItemsAvailable("Sustenance", sustenance);
        cooker.cook();
    }

}
