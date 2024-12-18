package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.units.Currency;
import jakarta.persistence.Entity;

/** A common purse for holding coins. */
@Entity
public class Purse extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  // TODO put in config
  /** conversion of coin count to weight units. */
  public static final float COINS_TO_WEIGHT = 0.1f;

  // TODO give value and put in config
  /** conversion of coin count to volume units. */
  public static final float COINS_TO_VOLUME = 0.01f;

  /** holder for currency. */
  private Currency coins = null;

  /** constructor. */
  public Purse() {
    super();
  }

  /**
   * Number of coins in purse.
   *
   * @return number of coins
   */
  public int getCoinCount() {
    if (null == coins) {
      return 0;
    }
    return coins.getCp() + coins.getSp() + coins.getGp() + coins.getPp();
  }

  @Override
  public float getWeight() {
    return getWeightBase() + getCoinCount() * COINS_TO_WEIGHT;
  }

  @Override
  public float getVolume() {
    return getVolumeBase() + getCoinCount() * COINS_TO_VOLUME;
  }

  @Override
  public Currency getValue() {
    // We take a fresh currency so we don't alter the Purse's inner currency
    // object.
    Currency total = new Currency();
    var value = getValueBase();
    if (null != value) {
      total.add(value);
    }
    if (null != coins) {
      total.add(coins);
    }
    return total;
  }

  /**
   * The coins passed in are transferred to the purse.
   *
   * @param fromCoins coins passed in.
   */
  public void add(Currency fromCoins) {
    if (null == coins) {
      coins = new Currency();
    }
    coins.transfer(fromCoins);
  }
}
