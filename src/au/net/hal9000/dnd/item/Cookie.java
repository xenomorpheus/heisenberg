package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.HumanoidFood;

public class Cookie extends Item implements HumanoidFood{
	public Cookie() {
		super("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}
}
