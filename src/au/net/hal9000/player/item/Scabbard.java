package au.net.hal9000.player.item;

import au.net.hal9000.player.item.exception.ExceptionCantRemove;
import au.net.hal9000.player.item.exception.ExceptionCantWear;
import au.net.hal9000.player.item.property.SwordSheath;
import au.net.hal9000.player.units.Currency;
import au.net.hal9000.player.units.Volume;
import au.net.hal9000.player.units.Weight;

public class Scabbard extends Item implements SwordSheath {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sword sword = null;

	public Scabbard() {
		this("Scabbard");
	}

	public Scabbard(String pString) {
		super(pString);
	}

	// Features
	@Override
	public boolean isClothing() {
		return true;
	}

	public void add(Sword pSword) throws ExceptionCantWear {
		if (sword != null) {
			throw new ExceptionCantWear("scabbard full");
		}
		sword = pSword;
		pSword.setLocation(this);
	}

	public Sword remove(IItem pLocation) throws ExceptionCantRemove {
		if (sword == null) {
			throw new ExceptionCantRemove("scabbard empty");
		}
		Sword swordReturn = sword;
		sword.setLocation(pLocation);
		this.sword = null;
		return swordReturn;
	}

	/** {@inheritDoc} */
	public boolean isLeaf() {
		return false;
	}

	/** {@inheritDoc} */
	public int getChildCount() {
		int count = super.getChildCount();
		if (sword != null) {
			count++;
		}
		return count;
	}

	/** {@inheritDoc} */
	public Item getChild(int index) {
		// TODO
		// int count = super.getChildCount();
		return sword;
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(IItem child) {
		// TODO
		return -1;
	}

	/**
	 * The total weight including the contents.
	 */
	@Override
	public Weight getWeight() {
		Weight total = this.getWeightBase();
		if (sword != null) {
			if (total == null)
				total = new Weight();
			total.add(sword.getWeight());
		}
		return total;
	}

	/**
	 * The total volume including the contents.
	 */
	@Override
	public Volume getVolume() {
		Volume total = this.getVolumeBase();
		if (sword != null) {
			if (total == null)
				total = new Volume();
			total.add(sword.getVolume());
		}
		return total;
	}

	/**
	 * The total value including the contents.
	 */
	@Override
	public Currency getValue() {
		Currency total = new Currency(this.getValueBase());
		if (sword != null) {
			total.add(sword.getValue());
		}
		return total;
	}

}
