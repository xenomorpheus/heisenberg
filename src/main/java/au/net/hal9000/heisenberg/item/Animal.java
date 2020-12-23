package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;

public interface Animal {

  /**
   * Eat
   *
   * @param food that which is eaten.
   */
  public void eat(Item food);

  /**
   * Drink
   *
   * @param drink that which is eaten.
   */
  public void drink(Item drink);
}
