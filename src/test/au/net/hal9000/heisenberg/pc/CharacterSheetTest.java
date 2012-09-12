package test.au.net.hal9000.heisenberg.pc;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import nu.xom.ValidityException;


import au.net.hal9000.heisenberg.pc.CharacterSheet;
import au.net.hal9000.heisenberg.pc.PcClass;
import au.net.hal9000.heisenberg.util.Configuration;

public class CharacterSheetTest {

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
		CharacterSheet pc = new CharacterSheet(warrior);

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
		// TODO assertEquals("~", pc.getGender());
		// TODO assertEquals("~", pc.getSize());
		// TODO assertEquals("~", pc.getRace());
	}
}
