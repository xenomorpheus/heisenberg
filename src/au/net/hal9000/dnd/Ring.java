package au.net.hal9000.dnd;

//A ring is an Item except:
//* Default description is "small metalic ring".
//* Default value is 5gp ?
//* Default weight ?
		
public class Ring extends Item {
	public Ring(){
		super("Ring");
		setDescription("small metalic ring");
		setCost(new CoinCollection(0,5,0,0));
		setWeightBase(0.02F);
	}

}
