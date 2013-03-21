package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Candle;

public class CandleTest {
    private static final float MARGIN = 0.00001f;

    // Name and Description
    @Test
    public void testName() {
        // defaults
        final String defaultName = "Candle";
        final String defaultDescription = "a simple tallow candle";
        Candle candle = new Candle();
        assertEquals(defaultName, candle.getName());
        assertEquals(defaultDescription, candle.getDescription());
        // custom
        final String expectedName = "my candle";
        final String expectedDescription = "the description";
        Candle custom = new Candle(expectedName, expectedDescription);
        assertEquals(expectedName, custom.getName());
        assertEquals(expectedDescription, custom.getDescription());
    }

    @Test
    public void testSetType() {
        int type = 1;
        Candle candle = new Candle();
        candle.setType(type);
        if (type == 1) {
            assertEquals("volumeBase", 0.5f, candle.getVolumeBase(), MARGIN);
            assertEquals("weightBase", 0.5f, candle.getWeightBase(), MARGIN);
        }
    }

    @Test
    public void testLit() {
        Candle candle = new Candle();
        assertFalse("candle created unlit", candle.isLit());
        candle.setLit(true);
        assertTrue(candle.isLit());
        candle.extinguish();
        assertFalse(candle.isLit());
    }
    
    @Test
    public void testLightWith() {
        Candle candle = new Candle();
        
        // lit with nothing
        candle.lightWith(null);
        assertFalse("lit with nothing", candle.isLit());
        // lit with cookie
        candle.lightWith(new Cookie());
        assertFalse("lit with Cookie", candle.isLit());
        // lit with FlintAndTinder
        candle.lightWith(new FlintAndTinder());
        assertTrue("lit with FlintAndTinder", candle.isLit());
        // light with another *lit* candle
        Candle unlitCandle = new Candle();
        Candle candle2 = new Candle();
        assertFalse("candle2 created unlit", candle2.isLit());
        candle2.lightWith(unlitCandle);
        assertFalse("candle2 not lit from unlit candle", candle2.isLit());
        candle2.lightWith(candle);
        assertTrue("candle2 lit from lit candle", candle2.isLit());
        // TODO test light from Torch
        // TODO test *NOT* lit from OrbOfLight

    }

    @Test
    public void testExtinquish() {
        Candle candle = new Candle("TheCandle");
        assertFalse("isLit=false", candle.isLit());
        candle.setLit(true);
        assertTrue("isLit=true", candle.isLit());
        candle.extinguish();
        assertFalse("extinguish isLit=false", candle.isLit());
    }

    @Test
    public void testGetDetailedDescription() {
        // Name and description
        Candle candle = new Candle("My Name", "My Description");
        assertEquals("My Description. Not lit", candle.getDetailedDescription());
        candle.setLit(true);
        assertEquals("My Description. Is lit", candle.getDetailedDescription());
        // Name only
        candle.setDescription("");
        candle.extinguish();
        assertEquals("My Name. Not lit", candle.getDetailedDescription());
        candle.setLit(true);
        assertEquals("My Name. Is lit", candle.getDetailedDescription());
        // Neither name nor description
        candle.setName("");
        candle.extinguish();
        assertEquals("Candle. Not lit", candle.getDetailedDescription());
        candle.setLit(true);
        assertEquals("Candle. Is lit", candle.getDetailedDescription());
    }

}
