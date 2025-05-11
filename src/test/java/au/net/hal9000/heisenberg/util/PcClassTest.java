package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.junit.Test;

/** */
public class PcClassTest {

  /** Method testId. */
  @Test
  public final void testId() {
    PcClass pc = new PcClass();
    pc.setId("fred");
    assertEquals(pc.getId(), "fred");
  }

  /** Method testCombatDice. */
  @Test
  public final void testCombatDice() {
    PcClass pc = new PcClass();
    pc.setCombatDice(4);
    assertEquals(pc.getCombatDice(), 4);
  }

  /** Method testMagicDice. */
  @Test
  public final void testMagicDice() {
    PcClass pc = new PcClass();
    pc.setMagicDice(4);
    assertEquals(pc.getMagicDice(), 4);
  }

  /** Method testStealthDice. */
  @Test
  public final void testStealthDice() {
    PcClass pc = new PcClass();
    pc.setStealthDice(4);
    assertEquals(pc.getStealthDice(), 4);
  }

  /** Method testGeneralDice. */
  @Test
  public final void testGeneralDice() {
    PcClass pc = new PcClass();
    pc.setGeneralDice(4);
    assertEquals(pc.getGeneralDice(), 4);
  }

  /** Method testActionPoints. */
  @Test
  public final void testActionPoints() {
    PcClass pc = new PcClass();
    pc.setActionPoints(4);
    assertEquals(pc.getActionPoints(), 4);
  }

  /** Method testHealth. */
  @Test
  public final void testHealth() {
    PcClass pc = new PcClass();
    pc.setHealth(4);
    assertEquals(pc.getHealth(), 4);
  }

  /** Method testMana. */
  @Test
  public final void testMana() {
    PcClass pc = new PcClass();
    pc.setMana(4);
    assertEquals(pc.getMana(), 4);
  }

  /** Method testRaceAllow. */
  @Test
  public final void testRaceAllow() {
    PcClass pc = new PcClass();
    pc.setSpeciesAllow("human");
    assertEquals(pc.getSpeciesAllow(), "human");
  }

  /** Method testGenderAllow. */
  @Test
  public final void testGenderAllow() {
    PcClass pc = new PcClass();
    pc.setGenderAllow("any");
    assertEquals(pc.getGenderAllow(), "any");
  }

  /** Method testSizeAllow. */
  @Test
  public final void testSizeAllow() {
    PcClass pc = new PcClass();
    pc.setSizeAllow("any");
    assertEquals(pc.getSizeAllow(), "any");
  }

  /** Method testEncumbrance. */
  @Test
  public final void testEncumbrance() {
    PcClass pc = new PcClass();
    pc.setEncumbrance(100);
    assertEquals(pc.getEncumbrance(), 100);
  }

  // TODO testAbilityScores
  /** Method testAbilityScores. */
  @Test
  public final void testAbilityScores() {}

  // TODO testAbilityScore
  /** Method testAbilityScore. */
  @Test
  public final void testAbilityScore() {}

  // TODO PcClass testToString
  /** Method testToString. */
  @Test
  public final void testToString() {}

  // TODO PcClass testDescription
  /** Method testDescription. */
  @Test
  public final void testDescription() {}

  /** Method testCompareTo. */
  @Test
  public final void testCompareTo() {
    PcClass pc1 = new PcClass();
    pc1.setId("BBB");
    PcClass pc2 = new PcClass();
    pc2.setId("CCC");
    assertEquals(pc2.compareTo(pc1), 1);
    PcClass pc3 = new PcClass();
    pc3.setId("CCC");
    assertEquals(pc3.compareTo(pc2), 0);
  }

  /** Test serialisation. */
  @Test
  public void serialisation() {
    PcClass pcc = new PcClass();
    try (FileOutputStream fos = new FileOutputStream("/tmp/heisenberg-PcClass.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(pcc);
      System.out.println("CharacterSheet object serialized successfully.");

    } catch (IOException e) {
      fail(e.toString());
    }
  }
}
