package au.net.hal9000.player.item;

import java.util.*;
import au.net.hal9000.player.item.exception.*;
import au.net.hal9000.player.item.property.*;
import au.net.hal9000.player.units.*;
import au.net.hal9000.player.units.Currency;

/*

 http://www.dandwiki.com/wiki/Bag_of_holding

 Bag	    | Bag Weight | Contents Weight Limit | Contents Volume Limit | Market Price
 Type I	    | 15 lb.     | 250 lb.               | 30 cu. ft.            | 2,500 gp
 Type II	| 25 lb.     | 500 lb.               | 70 cu. ft.            | 5,000 gp
 Type III   | 35 lb.     | 1,000 lb.             | 150 cu. ft.           | 7,400 gp
 Type IV	| 60 lb.     | 1,500 lb.             | 250 cu. ft.           |10,000 gp

 */

public class BagOfHolding extends Bag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;

	public BagOfHolding(int type, String pName) {
		super(pName);
		this.setType(type);
	}

	public BagOfHolding(int type) {
		this(type, "Bag of Holding");
	}

	// Features
	@Override
	public boolean isExtraDimensional() {
		return true;
	}

	@Override
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

	// take the item out of the bag and place
	// at newLocation
	public Item getItem(Item item, Item newLocation){
        Vector <ItemImpl> items = this.getContents();
		if (! items.removeElement(item)) {
			throw new ExceptionCantRemove("remove failed");
		}
		item.setLocation(newLocation);
		return item;
	}

	// Magic
	@Override
	public Weight getWeight() {
		return this.getWeightBase();
	}

	// Magic
	@Override
	public Volume getVolume() {
		return this.getVolumeBase();
	}

    // BOH Type I,II,III or IV
	public int getType() {
		return type;
	}

	// A BOH rupturing is kind of special :-)
	public void rupture() {
		this.beNot();
	}

	public void add(ItemImpl item) throws ExceptionTooHeavy, ExceptionTooBig,
			ExceptionInvalidType {

		// Recursively check for ExtraDimensional items.
		ItemSearch search = new ItemSearchExtraDimensional();
		item.accept(search);
		if (search.getMatchingItemsCount() > 0) {
			this.rupture();
			throw new ExceptionInvalidType("ExtraDimensional");
		}
		// TODO Do sharp objects cause a ED rupture?

		// Super will check for sharp.
		super.add(item);
	}

}
