package au.net.hal9000.heisenberg.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Factory {

    static String packageName = Factory.class.getPackage().getName();

	static public Item createItem(String type) {
		return createItem(type, null);
	}

	static public Item createItem(String type, Object[] arguments) {
		try {
			Class<?> itemClass = Class.forName(packageName + "." + type);
			if (arguments == null) {
				return ((Item) itemClass.newInstance());
			} else {
				final Class<?> partypes[] = new Class[arguments.length];

				for (int count = 0; count < arguments.length; count++) {
					partypes[count] = arguments[count].getClass();
				}

				final Constructor<?> ct = itemClass.getConstructor(partypes);
				return (Item) ct.newInstance(arguments);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(ClassNotFoundException) bad type="+type);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(InstantiationException) bad type="+type);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(IllegalAccessException) bad type="+type);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(IllegalArgumentException) bad type="+type);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(InvocationTargetException) bad type="+type);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(SecurityException) bad type="+type);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new IllegalArgumentException("(NoSuchMethodException) bad type="+type);
		}
	}
}
