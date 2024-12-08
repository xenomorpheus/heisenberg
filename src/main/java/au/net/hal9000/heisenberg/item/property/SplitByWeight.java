package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.Item;

/**
 * Can this item be split into smaller items? e.g water, flour, sand, pile of wood can. By default
 * items can't be split eg. cookie.
 *
 * <p>This allows the system to divide an item into two if only some of the item is required. e.g.
 * as ingredient for a recipe.
 */
public interface SplitByWeight {

  /**
   * Split the current Item into two.
   *
   * @param newItemWeight the amount to carve off the original Item.
   * @return the carved off new Item.
   */
  Item splitByWeight(float newItemWeight);
}
