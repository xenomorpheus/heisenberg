package au.net.hal9000.heisenberg.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory Design Pattern. Create different type of Item objects.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class Factory {

    /**
     * Constructor.
     */
    private Factory() {
        super();

    }

    /**
     * Create a new Item of the specified type.
     * 
     * @param type
     *            the type of Item to create.
     * 
     * @return the new Item.
     */
    public static Item createItem(String type) {
        return createItem(type, null);
    }

    /**
     * Create a new Item of the specified type.
     * 
     * @param type
     *            the type of Item to create.
     * @param arguments
     *            parameters to constructor.
     * 
     * @return the new Item.
     */
    public static Item createItem(String type, Object[] arguments) {
        try {
            Class<?> itemClass = Class.forName(Item.getClassForType(type));
            if (null == arguments) {
                return (Item) itemClass.newInstance();
            } else {
                final Class<?>[] partypes = new Class[arguments.length];

                for (int count = 0; count < arguments.length; count++) {
                    partypes[count] = arguments[count].getClass();
                }

                final Constructor<?> ct = itemClass.getConstructor(partypes);
                return (Item) ct.newInstance(arguments);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    "(ClassNotFoundException) bad type=" + type, e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(
                    "(InstantiationException) bad type=" + type, e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(
                    "(IllegalAccessException) bad type=" + type, e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "(IllegalArgumentException) bad type=" + type, e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(
                    "(InvocationTargetException) bad type=" + type, e);
        } catch (SecurityException e) {
            throw new IllegalArgumentException("(SecurityException) bad type="
                    + type, e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    "(NoSuchMethodException) bad type=" + type, e);
        }
    }

}
