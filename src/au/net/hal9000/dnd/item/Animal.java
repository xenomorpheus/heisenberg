package au.net.hal9000.dnd.item;

public abstract class Animal extends Item {

	public Animal(String pName) {
		super(pName);
	}

	public boolean Living() {
		return true;
	}

	// Features
	public boolean isLiving() {
		return true;
	}
}
