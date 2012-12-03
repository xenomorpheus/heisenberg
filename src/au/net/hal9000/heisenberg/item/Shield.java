package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;


/*
 * Shield  
 * 
 * 1) Reduces the chance that a attack will land.
 * 2) Reduces the effect of an attack.
 * 3) How can a shield get damaged?
 * 4) Will there be armour types ?  Brace, shield, body, magical ...
 *
 *  TODO perhaps create a protection Interface  
 */

public class Shield extends Item{

//	ToHitModifier toHitModifier = new ToHitModifier();
//	DamageModifier damageModifier = new DamageModifier();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor(s)
	public Shield() {
		super("Shield");
        ItemProperty.setClothing(this, true);
	}

}
