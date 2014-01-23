package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.units.Currency;

public class PurseTest {

    private static final float MARGIN_OF_ERROR = 0.0001f;

    @Test
    public void testPurse() {
        Purse purse = new Purse();
        assertEquals("getName", "Purse", purse.getName());
        assertEquals("getValue - empty purse", purse.getValueBase(),
                purse.getValue());
        assertEquals("getWeight - empty purse", purse.getWeightBase(),
                purse.getWeight(), MARGIN_OF_ERROR);
        assertEquals("getVolume - empty purse", purse.getVolumeBase(),
                purse.getVolume(), MARGIN_OF_ERROR);
        // TODO add more
    }

    @Test
    public void testPurseString() {
        Purse purse = new Purse("ThisName");
        assertEquals("Name", "ThisName", purse.getName());
    }

    @Test
    public void testGetCoinCount() {
        // empty
        Purse purse = new Purse();
        assertEquals("empty count", 0, purse.getCoinCount());

        // coins
        Purse purse2 = new Purse(new Currency(1, 2, 4, 8));
        assertEquals("coins count", 15, purse2.getCoinCount());

    }

    @Test
    public void testGetVolume() {
        // empty
        Purse purse = new Purse();
        assertEquals("getVolume - empty purse", purse.getVolumeBase(),
                purse.getVolume(), MARGIN_OF_ERROR);

        // coins
        Purse purse2 = new Purse(new Currency(1, 2, 4, 8));
        float expect2 = purse2.getVolumeBase() + (15 * Purse.COINS_TO_VOLUME);
        assertEquals("getVolume - coins", expect2, purse2.getVolume(),
                MARGIN_OF_ERROR);
    }

    @Test
    public void testGetValue() {
        // empty
        Purse purse = new Purse();
        assertEquals("getValue - empty purse", purse.getValueBase(),
                purse.getValue());

        // coins
        Purse purse2 = new Purse(new Currency(1, 2, 4, 8));
        Currency expect2 = new Currency(purse2.getValueBase());
        expect2.add(new Currency(1, 2, 4, 8));
        assertEquals("getValue - coins", expect2, purse2.getValue());
    }

    @Test
    public void testGetWeight() {
        // empty
        Purse purse = new Purse();
        assertEquals("getWeight - empty purse", purse.getWeightBase(),
                purse.getWeight(), MARGIN_OF_ERROR);

        // coins
        Purse purse2 = new Purse(new Currency(1, 2, 4, 8));
        float expect2 = purse2.getWeightBase() + (15 * Purse.COINS_TO_WEIGHT);
        assertEquals("getWeight - coins", expect2, purse2.getWeight(),
                MARGIN_OF_ERROR);
    }

}
