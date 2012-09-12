package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.units.Currency;

public class Purse extends Item {

	private static final long serialVersionUID = 1L;
	// TODO put in config
	private static final float coinsToWeight = 0.1f;
	// TODO give value and put in config
	private static final float coinsToVolume = 0.01f;
	private Currency coins = new Currency();

	public Purse() {
		this("Purse");
	}

	public Purse(final String pString) {
		super(pString);
	}

	/** {@inheritDoc} */
	@Override
	public float getWeight() {
		return this.getWeightBase()
				+ (coins.getCp() + coins.getSp() + coins.getGp() + coins
						.getPp()) * coinsToWeight;
	}

	/** {@inheritDoc} */
	@Override
	public float getVolume() {
		return this.getVolumeBase()
				+ (coins.getCp() + coins.getSp() + coins.getGp() + coins
						.getPp()) * coinsToVolume;
	}

	/** {@inheritDoc} */
	@Override
	public Currency getValue() {
		Currency total = this.getValueBase();
		total.add(coins);
		return total;
	}

}
