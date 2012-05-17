//A ring is an Item except:
//* Default description is "small metalic ring".
//* Default value is 5gp ?
//* Default weight ?
		
public class Ring extends Item {
	Ring(){
		setDescription("small metalic ring");
		setValue(new CoinCollection(0,5,0,0));
		setBaseWeight(0);
	}

}
