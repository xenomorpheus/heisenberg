package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.units.Currency;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Purse extends Item {

    private static final long serialVersionUID = 1L;
    // TODO put in config
    /** conversion of coin count to weight units */
    public static final float COINS_TO_WEIGHT = 0.1f;
    // TODO give value and put in config
    /** conversion of coin count to volume units */
    public static final float COINS_TO_VOLUME = 0.01f;
    /** holder for currency */
    private Currency coins;

    public Purse(final String pString, Currency coins) {
        super(pString);
        this.coins = coins;
    }

    /**
     * constructor.
     */
    public Purse() {
        this("Purse", new Currency());
    }

    /**
     * Constructor.
     * @param coins
     */
    public Purse(Currency coins) {
        this("Purse", coins);
    }

    /**
     * Constructor.
     * @param pString
     */
    public Purse(final String pString) {
        this(pString, new Currency());
    }

    /**
     * Number of coins in purse.
     * 
     * @return number of coins
     */
    public int getCoinCount() {
        return coins.getCp() + coins.getSp() + coins.getGp() + coins.getPp();
    }

    /** {@inheritDoc} */
    @Override
    public float getWeight() {
        return this.getWeightBase() + this.getCoinCount() * COINS_TO_WEIGHT;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolume() {
        return this.getVolumeBase() + this.getCoinCount() * COINS_TO_VOLUME;
    }

    /** {@inheritDoc} */
    @Override
    public Currency getValue() {
        // We take a fresh currency so we don't alter the Purse's inner currency
        // object.
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
