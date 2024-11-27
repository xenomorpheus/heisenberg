package au.net.hal9000.heisenberg.item.mixin;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.EntityItem;

public class AnimalConsumeSustenance {

  /**
   * Eat.
   *
   * @param animal that which is doing the eating.
   * @param food that which is eaten.
   */
  public static void eat(Animal animal, Item food) {
    Cooker cooker = ((EntityItem) animal).getCooker("AnimalEat");
    cooker.setItemsAvailable("Food", food);
    cooker.cook();
  }

  /**
   * Drink.
   *
   * @param animal Animal which is doing the drinking.
   * @param drink Item which is drunk.
   */
  public static void drink(Animal animal, Item drink) {
    Cooker cooker = ((EntityItem) animal).getCooker("AnimalDrink");
    cooker.setItemsAvailable("Drink", drink);
    cooker.cook();
  }
}
