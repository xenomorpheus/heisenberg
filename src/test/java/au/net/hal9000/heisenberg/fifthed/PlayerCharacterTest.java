package au.net.hal9000.heisenberg.fifthed;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.fifthed.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.CharacterClass.CharacterClass;
import au.net.hal9000.heisenberg.fifthed.CharacterClass.Magus;
import au.net.hal9000.heisenberg.fifthed.item.BowLong;
import au.net.hal9000.heisenberg.fifthed.item.Dagger;
import au.net.hal9000.heisenberg.fifthed.item.Item;
import au.net.hal9000.heisenberg.fifthed.race.Human;
import au.net.hal9000.heisenberg.fifthed.spell.BladeLash;
import au.net.hal9000.heisenberg.fifthed.spell.Fireball;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;
import au.net.hal9000.heisenberg.units.Position;

/**
 */
public class PlayerCharacterTest {

	/**
	 * Method setUp.
	 */
	@Before
	public void setUp() {

	}
	@Test
	public void testPlayerCharacter() {
		PlayerCharacter character = new PlayerCharacter().setName("Test Character Name").setRace(new Human());
		// Classes
		List<CharacterClass> characterClasses = new ArrayList<CharacterClass>();
		Magus magus = new Magus();
		magus.setLevel(2);
		Set<Spell> spells = new HashSet<Spell>();
		spells.add(new Fireball());
		spells.add(new BladeLash());
		magus.setSpells(spells);
		characterClasses.add(magus);
		character.setCharacterClasses(characterClasses);
		// Conditions
		Set<PlayerCharacterCondition> conditions = new HashSet<PlayerCharacterCondition>();
		conditions.add(PlayerCharacterCondition.MUTE);
		character.setConditions(conditions);
		// Equipment
		Set<Item> equipped = new HashSet<Item>();
		equipped.add(new Dagger());
		equipped.add(new BowLong());
		character.setEquipped(equipped);
		// Misc
		System.out.println(character.details());
	}
	/**
	 * Test distance to other character.
	 */

	@Test
	public void testPlayerCharacterDistance() {
		PlayerCharacter character1 = new PlayerCharacter().setPosition(new Position(1, 0, 0));
		PlayerCharacter character2 = new PlayerCharacter().setPosition(new Position(1, 1, 1));
		double distance = character1.distance(character2);
		double root2 = 1.41421356237f;
		assertEquals("distance ", root2, distance, 0.001f);
	}

}
