package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.*;

public class Horse extends Item implements Mount,Animal {
	public Horse() {
		super("Horse");
	}

	public Horse(String string) {
		super(string);
	}

}
