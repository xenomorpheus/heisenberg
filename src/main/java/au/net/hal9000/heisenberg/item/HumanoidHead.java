package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class HumanoidHead extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HumanoidHead(String pName) {
		super(pName);
        ItemProperty.setLiving(this, true);
	}
	public HumanoidHead() {
		this("Head");
	}

}
