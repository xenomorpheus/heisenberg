package au.net.hal9000.dnd.item;

//A ring is an Item except:
//* Default description is "small metalic ring".
//* Default value is 5gp ?
//* Default weight ?

public class Ring extends Item {

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

}
