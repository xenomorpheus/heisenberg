package au.net.hal9000.heisenberg.item.mixin;

/** Represents an entity that consumes sustenance. */
import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Being;

public class AnimalConsumeSustenance {

  /** Default constructor for AnimalConsumeSustenance. */
  public AnimalConsumeSustenance() {
    // Default constructor
  }

  /**
   * Eat.
   *
   * @param animal that which is doing the eating.
   * @param food that which is eaten.
   */
  public static void eat(Animal animal, Item food) {
    Cooker cooker = ((Being) animal).getCooker("AnimalEat");
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
    Cooker cooker = ((Being) animal).getCooker("AnimalDrink");
    cooker.setItemsAvailable("Drink", drink);
    cooker.cook();
  }
}
