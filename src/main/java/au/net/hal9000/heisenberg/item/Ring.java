package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.*;

import au.net.hal9000.heisenberg.units.Currency;

//A ring is an IItem except:
//* Default description is "small metallic ring".
//* Default value is 5gp ?
//* Default weight ?

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class Ring extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ring() {
		super("Ring");
		this.setDefaults(this);
	}
	
	public Ring(final String pName) {
		super(pName);
		this.setDefaults(this);
	}
	
	private void setDefaults(Ring pRing){
		pRing.setDescription("small metalic ring");
		pRing.setValueBase(new Currency(0, 5, 0, 0));
		pRing.setWeightBase(0.02F);
	}

}
