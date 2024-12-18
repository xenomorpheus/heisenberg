package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.units.Currency;
import org.junit.Test;

/** */
public class PurseTest {

  /** Field MARGIN_OF_ERROR. (value is 1.0E-4) */
  private static final float MARGIN_OF_ERROR = 0.0001f;

  /** test Purse. */
  @Test
  public void testPurse() {
    Purse purse = new Purse();
    assertEquals("getValue - empty purse", new Currency(), purse.getValue());
    assertEquals(
        "getWeight - empty purse", purse.getWeightBase(), purse.getWeight(), MARGIN_OF_ERROR);
    assertEquals(
        "getVolume - empty purse", purse.getVolumeBase(), purse.getVolume(), MARGIN_OF_ERROR);
    // TODO add more tests
  }

  /** test getCoinCount. */
  @Test
  public void testGetCoinCount() {
    // empty
    Purse purse = new Purse();
    assertEquals("empty count", 0, purse.getCoinCount());

    // coins
    Purse purse2 = new Purse();
    purse2.add(new Currency(1, 2, 4, 8));
    assertEquals("coins count", 15, purse2.getCoinCount());
  }

  /** test getVolume. */
  @Test
  public void testGetVolume() {
    // empty
    Purse purse = new Purse();
    assertEquals(
        "getVolume - empty purse", purse.getVolumeBase(), purse.getVolume(), MARGIN_OF_ERROR);

    // coins
    Purse purse2 = new Purse();
    purse2.add(new Currency(1, 2, 4, 8));
    float expect2 = purse2.getVolumeBase() + (15 * Purse.COINS_TO_VOLUME);
    assertEquals("getVolume - coins", expect2, purse2.getVolume(), MARGIN_OF_ERROR);
  }

  /** test getValue. */
  @Test
  public void testGetValue() {
    // empty
    Purse purse = new Purse();
    assertEquals("getValue - empty purse", new Currency(), purse.getValue());

    // coins
    Purse purse2 = new Purse();
    purse2.add(new Currency(1, 2, 4, 8));
    assertEquals("getValue - coins", new Currency(1, 2, 4, 8), purse2.getValue());
  }

  /** test getWeight. */
  @Test
  public void testGetWeight() {
    // empty
    Purse purse = new Purse();
    assertEquals(
        "getWeight - empty purse", purse.getWeightBase(), purse.getWeight(), MARGIN_OF_ERROR);

    // coins
    Purse purse2 = new Purse();
    purse2.add(new Currency(1, 2, 4, 8));
    float expect2 = purse2.getWeightBase() + (15 * Purse.COINS_TO_WEIGHT);
    assertEquals("getWeight - coins", expect2, purse2.getWeight(), MARGIN_OF_ERROR);
  }
}
