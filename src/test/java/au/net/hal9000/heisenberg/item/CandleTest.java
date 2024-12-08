package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** */
public class CandleTest {
  /** Field MARGIN. (value is 1.0E-5) */
  private static final float MARGIN = 0.00001f;

  /** Test Constructor. */
  @Test
  public void testConstructor() {
    // defaults
    final String defaultDescription = "a simple tallow candle";
    Candle candle = new Candle();
    assertEquals(defaultDescription, candle.getDescription());
  }

  /** Method testSetType. */
  @Test
  public void testSetType() {
    int type = 1;
    Candle candle = new Candle();
    candle.setType(type);
    if (1 == type) {
      assertEquals("volumeBase", Candle.VOLUME_DEFAULT, candle.getVolumeBase(), MARGIN);
      assertEquals("weightBase", Candle.WEIGHT_DEFAULT, candle.getWeightBase(), MARGIN);
    }
  }

  /** Method testLit. */
  @Test
  public void testLit() {
    Candle candle = new Candle();
    assertFalse("candle created unlit", candle.isLit());
    candle.setLit(true);
    assertTrue(candle.isLit());
    candle.extinguish();
    assertFalse(candle.isLit());
  }

  /** Method testLightWith. */
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

    // test light from Torch
    candle2.extinguish();
    Torch torch = new Torch();
    Torch unlitTorch = new Torch();
    torch.setLit(true);
    candle2.lightWith(unlitTorch);
    assertFalse("candle2 not lit from unlit Torch", candle2.isLit());
    candle2.lightWith(torch);
    assertTrue("candle2 lit from lit Torch", candle2.isLit());

    // TODO test NOT lit from OrbOfLight
    candle2.extinguish();
    OrbOfLight ool = new OrbOfLight();
    OrbOfLight unlitOrbOfLight = new OrbOfLight();
    ool.setLit(true);
    candle2.lightWith(unlitOrbOfLight);
    assertFalse("candle2 not lit from unlit OrbOfLight", candle2.isLit());
    candle2.lightWith(ool);
    assertFalse("candle2 NOT lit from lit OrbOfLight", candle2.isLit());
  }

  /** Method testExtinquish. */
  @Test
  public void testExtinquish() {
    Candle candle = new Candle();
    assertFalse("isLit=false", candle.isLit());
    candle.setLit(true);
    assertTrue("isLit=true", candle.isLit());
    candle.extinguish();
    assertFalse("extinguish isLit=false", candle.isLit());
  }

  /** Method testGetDetailedDescription. */
  @Test
  public void testGetDetailedDescription() {
    Candle candle = new Candle();
    // lit
    candle.setLit(true);
    String description = candle.detailedDescription();
    assertNotEquals(-1, description.indexOf("The Candle is lit."));
    // unlit
    candle.extinguish();
    description = candle.detailedDescription();
    assertNotEquals(-1, description.indexOf("Candle is extinguished."));
    // unlit
    candle.setLit(false);
    description = candle.detailedDescription();
    assertNotEquals(-1, description.indexOf("Candle is extinguished."));
  }

  /**
   * Method assertNotEquals.
   *
   * @param i int
   * @param indexOf int
   */
  private void assertNotEquals(int i, int indexOf) {
    // TODO Auto-generated method stub

  }
}
