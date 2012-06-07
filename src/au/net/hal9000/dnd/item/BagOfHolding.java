package au.net.hal9000.dnd.item;

import java.util.Stack;

import au.net.hal9000.dnd.item.exception.*;
import au.net.hal9000.dnd.item.property.*;

/*

 http://www.dandwiki.com/wiki/Bag_of_holding

 Bag	    | Bag Weight | Contents Weight Limit | Contents Volume Limit | Market Price
 Type I	    | 15 lb.     | 250 lb.               | 30 cu. ft.            | 2,500 gp
 Type II	| 25 lb.     | 500 lb.               | 70 cu. ft.            | 5,000 gp
 Type III   | 35 lb.     | 1,000 lb.             | 150 cu. ft.           | 7,400 gp
 Type IV	| 60 lb.     | 1,500 lb.             | 250 cu. ft.           |10,000 gp

 */

public class BagOfHolding extends Bag {

	private int type;

	public BagOfHolding(int type, String pName) {
		super(pName);
		setType(type);
	}

	public BagOfHolding(int type) {
		super("Bag of Holding");
		setType(type);
	}

	// Features
	public boolean isExtraDimensional() {
		return true;
	}

	public boolean isMagical() {
		return true;
	}

	// Other methods
	private void setType(int pType) {
		if ((pType < 1) || (pType > 4)) {
			throw new IllegalArgumentException("invalid type=" + pType);
		}
		type = pType;
		if (type == 1) {
			this.setWeightBase(15F);
			this.setWeightMax(250F);
			this.setVolumeMax(30F);
			this.setValueBase(new Currency(0, 2500, 0, 0));
		}
		if (type == 2) {
			this.setWeightBase(25F);
			this.setWeightMax(500F);
			this.setVolumeMax(70F);
			this.setValueBase(new Currency(0, 5000, 0, 0));
		}
		if (type == 3) {
			this.setWeightBase(35F);
			this.setWeightMax(1000F);
			this.setVolumeMax(150F);
			this.setValueBase(new Currency(0, 7400, 0, 0));
		}
		if (type == 4) {
			this.setWeightBase(60F);
			this.setWeightMax(1500F);
			this.setVolumeMax(150F);
			this.setValueBase(new Currency(0, 10000, 0, 0));
		}
		this.setVolumeBase(2F); // TODO

	}

	public Item removeElemement(Item item, Item newLocation)
			throws ExceptionCantRemove {

		if (!items.removeElement(item)) {
			throw new ExceptionCantRemove("remove failed");
		}
		item.setLocation(newLocation);
		return item;
	}

	// Magic
	public float getWeight() {
		return this.getWeightBase();
	}

	// Magic
	public float getVolume() {
		return this.getVolumeBase();
	}

	public int getType() {
		return type;
	}

	// TODO
	public void beNot() {
		System.out.println("TODO - beNot");
		// TODO call beNot() on every item declared directly in this class.
		// TODO call beNot() on super.
		this.beNot();
	}

	// A BOH rupturing is kind of special :-)
	public void rupture() {
		System.out.println("ExtraDimensional rupture");
		this.beNot();
	}

	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig,
			ExceptionInvalidType {

		// TODO Do sharp objects cause a ED rupture?
		if (item.isSharp()) {
			this.rupture();
			throw new ExceptionInvalidType("Sharp");
		}

		// Recursively check for ExtraDimensional items.
		ItemSearch search = new ItemSearchExtraDimensional();
		this.searchHelper(search);
		if (search.getMatchingItemsCount() > 0) {
			this.rupture();
			throw new ExceptionInvalidType("ExtraDimensional");
		}
		super.add(item);
	}

}
