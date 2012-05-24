/*

 http://www.dandwiki.com/wiki/Bag_of_holding

 Bag	    | Bag Weight | Contents Weight Limit | Contents Volume Limit | Market Price
 Type I	    | 15 lb.     | 250 lb.               | 30 cu. ft.            | 2,500 gp
 Type II	| 25 lb.     | 500 lb.               | 70 cu. ft.            | 5,000 gp
 Type III   | 35 lb.     | 1,000 lb.             | 150 cu. ft.           | 7,400 gp
 Type IV	| 60 lb.     | 1,500 lb.             | 250 cu. ft.           |10,000 gp

 */

public class BagOfHolding extends Bag implements Magical {

	int type;

	BagOfHolding(int type, String pName) {
		super(pName);
		setType(type);
		volumeBase = 2F; // TODO
	}

	BagOfHolding(int type) {
		super("Bag of Holding");
		setType(type);
		volumeBase = 2F; // TODO
	}

	public Item removeElemement(Item item, Location newLocation)
			throws ExceptionCantRemove {

		if (!items.removeElement(item)) {
			throw new ExceptionCantRemove("remove failed");
		}
		item.setLocation(newLocation);
		return item;
	}

	public float getWeight() {
		return this.weightBase;
	}

	public float getVolume() {
		return this.volumeBase;
	}

	public int getType() {
		return type;
	}

	private void setType(int pType) {
		if ((pType < 1) || (pType > 4)) {
			throw new IllegalArgumentException("invalid type=" + pType);
		}
		type = pType;
		if (type == 1) {
			weightBase = 15F;
			weightMax = 250F;
			volumeMax = 30F;
			cost = new CoinCollection(0, 2500, 0, 0);
		}
		if (type == 2) {
			weightBase = 25F;
			weightMax = 500F;
			volumeMax = 70F;
			cost = new CoinCollection(0, 5000, 0, 0);
		}
		if (type == 3) {
			weightBase = 35F;
			weightMax = 1000F;
			volumeMax = 150F;
			cost = new CoinCollection(0, 7400, 0, 0);
		}
		if (type == 4) {
			weightBase = 60F;
			weightMax = 1500F;
			volumeMax = 150F;
			cost = new CoinCollection(0, 10000, 0, 0);
		}

	}

	// TODO 
	public void rupture(){
		// 
	}
	
	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig {
		// Look for items that implement Sharp, ExtraDimensional
		// if so rupture.
		if (item.implementsInterface(Sharp.class)){
			this.rupture();
		}
	// need to recursively check items, go into bags etc.
	// item.implementsInterface(ExtraDimensional.class)) {
		super.add(item);
	}

}
