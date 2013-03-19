package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.IOException;
import nu.xom.ValidityException;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

public class PcRaceTest {

	/*
	 * Most tests will use a Human as a representative of the PcRace.
	 */

	@Test
	public void testLiving() {
		Human human = new Human();
		// By default PCs are living.
		assertTrue("living", ItemProperty.isLiving(human));
	}

	@Test
	public void testCombatDice() {
		final int expected = 12;
		Human human = new Human();
		// assertEquals(0, human.getCombatDice());
		human.setCombatDice(expected);
		assertEquals(expected, human.getCombatDice());
	}


	@Test
	public void testMagicDice() {
		final int expected = 13;
		Human human = new Human();
		// assertEquals(0, human.getMagicDice());
		human.setMagicDice(expected);
		assertEquals(expected, human.getMagicDice());
	}
	@Test
	public void testStealthDice() {
		final int expected = 14;
		Human human = new Human();
		// assertEquals(0, human.getStealthDice());
		human.setStealthDice(expected);
		assertEquals(expected, human.getStealthDice());
	}
	@Test
	public void testGeneralDice() {
		final int expected = 15;
		Human human = new Human();
		// assertEquals(0, human.getGeneralDice());
		human.setGeneralDice(expected);
		assertEquals(expected, human.getGeneralDice());
	}
	
	@Test
	public void testMana() {
		final int expected = 16;
		Human human = new Human();
		assertEquals(0, human.getMana());
		human.setMana(expected);
		assertEquals(expected, human.getMana());
	}

    @Test
    public void testEncumbrance() {
        final int expected = 16;
        Human human = new Human();
        assertEquals(0, human.getEncumbrance());
        human.setEncumbrance(expected);
        assertEquals(expected, human.getEncumbrance());
    }

    @Test
    public void testHealth() {
        final int expected = 16;
        Human human = new Human();
        assertEquals(0, human.getHealth());
        human.setHealth(expected);
        assertEquals(expected, human.getHealth());
    }

	@Test
	public void testActionPoints() {
		final int expected = 17;
		Human human = new Human();
		assertEquals(0, human.getActionPoints());
		human.setActionPoints(expected);
		assertEquals(expected, human.getActionPoints());
		human.actionPointsAdjust(2);
        assertEquals(expected+2, human.getActionPoints());
	}

	@Test
	public void testLevel() {
		final int expected = 18;
		Human human = new Human();
		assertEquals(0, human.getLevel());
		human.setLevel(expected);
		assertEquals(expected, human.getLevel());
	}

	@Test
	public void testValues() {

		Configuration config = null;
		try {
			config = new Configuration("test/config/config.xml");
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PcClass warrior = config.getPcClass("Warrior");
		Human pc = new Human("Mr Warrior");
		pc.setPcClass(warrior);

		// dice
		assertEquals(20, pc.getCombatDice());
		assertEquals(4, pc.getMagicDice());
		assertEquals(12, pc.getStealthDice());
		assertEquals(8, pc.getGeneralDice());
		// Attributes

		// misc
		assertEquals(100, pc.getEncumbrance());
		assertEquals(8, pc.getActionPoints());
		assertEquals(7, pc.getHealth());
		assertEquals(0, pc.getMana());

	}

}
