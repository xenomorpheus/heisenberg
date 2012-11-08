package test.au.net.hal9000.heisenberg.pc;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.util.AbilityScore;

public class AbilityScoreTest {

	@Test
	public void testAbilityScore() {
		AbilityScore abilityScore = new AbilityScore("test", 3, 5);
		assertEquals("test", abilityScore.getName());
		assertEquals(3, abilityScore.getValue());
		assertEquals(5, abilityScore.getMod());
	}

	@Test
	public void testAbilityScoreStringString1() {

		AbilityScore abilityScore = new AbilityScore("test", "123");
		assertEquals("test", abilityScore.getName());
		assertEquals(123, abilityScore.getValue());
		assertEquals(0, abilityScore.getMod());
	}

	@Test
	public void testAbilityScoreStringString2() {
		AbilityScore abilityScore = new AbilityScore("test", "2/23");
		assertEquals("test", abilityScore.getName());
		assertEquals(2, abilityScore.getValue());
		assertEquals(23, abilityScore.getMod());
	}

	@Test
	public void testAbilityScoreStringString3() {
		AbilityScore abilityScore = new AbilityScore("test", "2/+3");
		assertEquals("test", abilityScore.getName());
		assertEquals(2, abilityScore.getValue());
		assertEquals(3, abilityScore.getMod());
	}

	@Test
	public void testAbilityScoreStringString4() {
		AbilityScore abilityScore = new AbilityScore("test", "+2/+3");
		assertEquals("test", abilityScore.getName());
		assertEquals(2, abilityScore.getValue());
		assertEquals(3, abilityScore.getMod());
	}

	@Test
	public void testAbilityScoreStringString5() {
		AbilityScore abilityScore = new AbilityScore("test asdf", "10/-3");
		assertEquals("test asdf", abilityScore.getName());
		assertEquals(10, abilityScore.getValue());
		assertEquals(-3, abilityScore.getMod());
	}

	@Test
	public void testToString() {
		AbilityScore abilityScore = new AbilityScore("test", 2, 3);
		assertEquals("test: 2/+3", abilityScore.toString());
		abilityScore.setMod(0);
		assertEquals("test: 2", abilityScore.toString());
	}

}
