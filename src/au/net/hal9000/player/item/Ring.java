package au.net.hal9000.player.item;

import au.net.hal9000.player.units.Currency;

//A ring is an IItem except:
//* Default description is "small metalic ring".
//* Default value is 5gp ?
//* Default weight ?

public class Ring extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ring() {
		super("Ring");
		this.setDefaults(this);
	}
	
	public Ring(String pName) {
		super(pName);
		this.setDefaults(this);
	}
	
	private void setDefaults(Ring pRing){
		pRing.setDescription("small metalic ring");
		pRing.setValueBase(new Currency(0, 5, 0, 0));
		pRing.setWeightBase(0.02F);
	}

	/** {@inheritDoc} */
	@Override
	public Item clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
