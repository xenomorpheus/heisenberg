package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Living;


public abstract class Animal extends Item implements Living {

	public Animal(String pName) {
		super(pName);
	}
    public static boolean Living(){
    	return true;
    }
}
