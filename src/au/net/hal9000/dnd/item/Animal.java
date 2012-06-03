package au.net.hal9000.dnd.item;

public abstract class Animal extends Item{

	public Animal(String pName) {
		super(pName);
	}
    public static boolean Living(){
    	return true;
    }
    
    // Features
    public static boolean isLiving(){
    	return true;
    }
}
