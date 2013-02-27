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

	@Test
	public void testMana() {

		// Human
		Human human = new Human("Human");
        assertEquals(0, human.getMana());
        human.setMana(5);
        assertEquals(5, human.getMana());
	}
	
	@Test
	public void testActionPoints() {

		// Human
		Human human = new Human("Human");
        assertEquals(0, human.getActionPoints());
        human.setActionPoints(43);
        assertEquals(43, human.getActionPoints());
	}
	
    @Test
    public void testLiving() {
        Human human = new Human(); // Close enough
        // By default PCs are living.
        assertTrue("living", ItemProperty.isLiving(human));
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
