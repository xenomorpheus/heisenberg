package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;

public interface Animal {

  /**
   * Eat
   *
   * @param entity that which is doing the eating.
   * @param food that which is eaten.
   */
  public void eat(Item food);

  /**
   * Drink
   *
   * @param entity that which is doing the drinking.
   * @param drink that which is eaten.
   */
  public void drink(Item drink);
}
