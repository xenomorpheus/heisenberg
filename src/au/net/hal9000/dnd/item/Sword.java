package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Sharp;

public class Sword extends Item implements Sharp{
	public Sword(){
		super("Sword");
	}

	public Sword(String pString){
		super(pString);
	}

}
