package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.util.PcClass;

public class PcClassTest {

	@Test
	public final void testId() {
		PcClass pc = new PcClass();
		pc.setId("fred");
		assertEquals(pc.getId(), "fred");
	}

	@Test
	public final void testCombatDice() {
		PcClass pc = new PcClass();
		pc.setCombatDice(4);
		assertEquals(pc.getCombatDice(), 4);
	}

	@Test
	public final void testMagicDice() {
		PcClass pc = new PcClass();
		pc.setMagicDice(4);
		assertEquals(pc.getMagicDice(), 4);
	}

	@Test
	public final void testStealthDice() {
		PcClass pc = new PcClass();
		pc.setStealthDice(4);
		assertEquals(pc.getStealthDice(), 4);
	}

	@Test
	public final void testGeneralDice() {
		PcClass pc = new PcClass();
		pc.setGeneralDice(4);
		assertEquals(pc.getGeneralDice(), 4);
	}

	@Test
	public final void testActionPoints() {
		PcClass pc = new PcClass();
		pc.setActionPoints(4);
		assertEquals(pc.getActionPoints(), 4);
	}

	@Test
	public final void testHealth() {
		PcClass pc = new PcClass();
		pc.setHealth(4);
		assertEquals(pc.getHealth(), 4);
	}

	@Test
	public final void testMana() {
		PcClass pc = new PcClass();
		pc.setMana(4);
		assertEquals(pc.getMana(), 4);
	}

	@Test
	public final void testRaceAllow() {
		PcClass pc = new PcClass();
		pc.setRaceAllow("human");
		assertEquals(pc.getRaceAllow(), "human");
	}

	@Test
	public final void testGenderAllow() {
		PcClass pc = new PcClass();
		pc.setGenderAllow("any");
		assertEquals(pc.getGenderAllow(), "any");
	}

	@Test
	public final void testSizeAllow() {
		PcClass pc = new PcClass();
		pc.setSizeAllow("any");
		assertEquals(pc.getSizeAllow(), "any");
	}

	@Test
	public final void testEncumbrance() {
		PcClass pc = new PcClass();
		pc.setEncumbrance(100);
		assertEquals(pc.getEncumbrance(), 100);
	}

	// TODO testAbilityScores
	@Test
	public final void testAbilityScores() {
	}

	// TODO testAbilityScore
	@Test
	public final void testAbilityScore() {
	}

	// TODO PcClass testToString
	@Test
	public final void testToString() {
	}

	// TODO PcClass testDescription
	@Test
	public final void testDescription() {
	}

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

}
