package au.net.hal9000.heisenberg.fifthed.playercharacter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreatureSizeTest {

  @Test
  public void testGetNaturalReach() {
    assertEquals(5, CreatureSize.MEDIUM.getNaturalReach(), 0.01);
  }

  @Test
  public void testGetSizeModifier() {
    assertEquals(0, CreatureSize.MEDIUM.getSizeModifier());
  }

  @Test
  public void testGetSizeModifierToFly() {
    assertEquals(0, CreatureSize.MEDIUM.getSizeModifierToFly(), 0.01);
  }

  @Test
  public void testGetSizeModifierToStealth() {
    assertEquals(0, CreatureSize.MEDIUM.getSizeModifierToStealth(), 0.01);
  }

  @Test
  public void testGetSpace() {
    assertEquals(5, CreatureSize.MEDIUM.getSpace(), 0.01);
  }

  @Test
  public void testGetSpecialSizeModifier() {
    assertEquals(0, CreatureSize.MEDIUM.getSpecialSizeModifier(), 0.01);
  }

  @Test
  public void testGetTypicalHeightLength() {
    assertEquals("4′ to 8 ft.", CreatureSize.MEDIUM.getTypicalHeightLength());
  }

  @Test
  public void testGetTypicalWeight() {
    assertEquals("60-500 lbs.", CreatureSize.MEDIUM.getTypicalWeight());
  }
}
