package au.net.hal9000.heisenberg.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import au.net.hal9000.heisenberg.util.Configuration;

/**
 * Factory Design Pattern. Create different type of Item objects.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public final class Factory {

    /** The name of this package. */
    private static final String PACKAGE_NAME = Factory.class.getPackage()
            .getName();

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
        String javaClassSuffix = Configuration.lastConfig()
                .getItemClassConfiguration(type).getJavaClass();
        // Assume no subclass.
        if (javaClassSuffix == null) {
            javaClassSuffix = type;
        }
        try {
            Class<?> itemClass = Class.forName(PACKAGE_NAME + "."
                    + javaClassSuffix);
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
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(ClassNotFoundException) bad type=" + type);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(InstantiationException) bad type=" + type);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(IllegalAccessException) bad type=" + type);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(IllegalArgumentException) bad type=" + type);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(InvocationTargetException) bad type=" + type);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException("(SecurityException) bad type="
                    + type);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new IllegalArgumentException(
                    "(NoSuchMethodException) bad type=" + type);
        }
    }

}
