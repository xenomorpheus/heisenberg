package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Humanoid;

public class Human extends Item implements Humanoid {
	public Human() {
		super("Human");
	}
	
	public Human(String string) {
		super(string);
	}
}
