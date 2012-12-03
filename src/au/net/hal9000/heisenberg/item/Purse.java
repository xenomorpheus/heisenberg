package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.units.Currency;

public class Purse extends Item {

    private static final long serialVersionUID = 1L;
    // TODO put in config
    public static final float coinsToWeight = 0.1f;
    // TODO give value and put in config
    public static final float coinsToVolume = 0.01f;
    private Currency coins;

    public Purse(final String pString, Currency coins) {
        super(pString);
        this.coins = coins;
    }

    public Purse() {
        this("Purse", new Currency());
    }

    public Purse(Currency coins) {
        this("Purse", coins);
    }

    public Purse(final String pString) {
        this(pString, new Currency());
    }

    /**
     * Number of coins
     * 
     * @return number of coins
     */
    public int getCoinCount() {
        return coins.getCp() + coins.getSp() + coins.getGp() + coins.getPp();
    }

    /** {@inheritDoc} */
    @Override
    public float getWeight() {
        return this.getWeightBase() + this.getCoinCount() * coinsToWeight;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolume() {
        return this.getVolumeBase() + this.getCoinCount() * coinsToVolume;
    }

    /** {@inheritDoc} */
    @Override
    public Currency getValue() {
        // We take a fresh currency so we don't alter the Purse's inner currency object.
        Currency total = new Currency(this.getValueBase());
        total.add(coins);
        return total;
    }

    /**
     * The coins passed in are transfered to the purse.
     * 
     * @param fromCoins
     *            coins passed in.
     */
    public void add(Currency fromCoins) {
        coins.transfer(fromCoins);
    }

}
