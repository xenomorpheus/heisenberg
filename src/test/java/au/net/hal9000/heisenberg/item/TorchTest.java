package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** */
public class TorchTest {
  /** Field MARGIN. (value is 1.0E-5) */
  private static final float MARGIN = 0.00001f;

  /** Test Constructor. */
  @Test
  public void testConstructor() {
    final String defaultDescription = "a short wooden rod tipped with cloth soaked in oil";
    Torch torch = new Torch();
    assertEquals(defaultDescription, torch.getDescription());
  }

  // TODO test light from Torch / Candle
  // TODO test NOT lit from OrbOfLight

  /** Method testSetType. */
  @Test
  public void testSetType() {
    int type = 1;
    Torch torch = new Torch();
    torch.setType(type);
    if (1 == type) {
      assertEquals("volumeBase", 1.0f, torch.getVolumeBase(), MARGIN);
      assertEquals("weightBase", 1.0f, torch.getWeightBase(), MARGIN);
    }
  }
}
