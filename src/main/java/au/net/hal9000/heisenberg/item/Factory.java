package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import java.lang.reflect.Constructor;

/**
 * Factory Design Pattern. Create different type of Item objects.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class Factory {

  /**
   * Create a new Item of the specified type.
   *
   * @param type the type of Item to create.
   * @return the new Item.
   */
  public static Item createItem(String type) {
    return createItem(type, null);
  }

  /**
   * Create a new Item of the specified type.
   *
   * @param type the type of Item to create.
   * @param arguments parameters to constructor.
   * @return the new Item.
   */
  public static Item createItem(String type, Object[] arguments) {
    try {
      Class<?> itemClass = Class.forName(Item.getClassForType(type));
      if (null == arguments) {
        return (Item) itemClass.getDeclaredConstructor().newInstance();
      } else {
        final Class<?>[] partypes = new Class[arguments.length];

        for (int count = 0; count < arguments.length; count++) {
          partypes[count] = arguments[count].getClass();
        }

        final Constructor<?> ct = itemClass.getConstructor(partypes);
        return (Item) ct.newInstance(arguments);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Bad Item type=" + type, e);
    }
  }
}
