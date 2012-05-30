package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Clothing;

public class Cloak extends Item implements Clothing {
		public Cloak(){
			super("Cloak");
		}

		public Cloak(String pString){
			super(pString);
		}
}
