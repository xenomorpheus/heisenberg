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
import au.net.hal9000.heisenberg.fifthed.CharacterClass.CombatFeat;
import au.net.hal9000.heisenberg.fifthed.CharacterClass.Fighter;
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

	private PlayerCharacter _build_magus() {
		PlayerCharacter character = new PlayerCharacter().setName("Test Character Magus").setRace(new Human());

		// Classes
		List<CharacterClass> characterClasses = new ArrayList<CharacterClass>();
		Magus magus = new Magus();
		magus.setLevel(2);

		// Spells
		Set<Spell> spells = new HashSet<Spell>();
		spells.add(new Fireball());
		spells.add(new BladeLash());
		magus.setSpells(spells);

		// Combat Feats
		Set<CombatFeat> combatFeats = new HashSet<CombatFeat>();
		combatFeats.add(CombatFeat.ARTFULL_DODGE);
		magus.setCombatFeats(combatFeats);

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
		return character;
	}

	private PlayerCharacter _build_fighter() {
		PlayerCharacter character = new PlayerCharacter().setName("Test Character Fighter").setRace(new Human());

		// Classes
		List<CharacterClass> characterClasses = new ArrayList<CharacterClass>();
		Fighter magus = new Fighter();
		magus.setLevel(2);

		// Combat Feats
		Set<CombatFeat> combatFeats = new HashSet<CombatFeat>();
		combatFeats.add(CombatFeat.ARTFULL_DODGE);
		magus.setCombatFeats(combatFeats);

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
		return character;
	}

	@Test
	public void testPlayerCharacter() {

		// Misc
		PlayerCharacter character = _build_magus();
		String details = character.details();
		System.out.println(details);
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

	/**
	 * Test distance to other character.
	 */

	@Test
	public void testGetActionsCombat() {
		TimerRound timer = new TimerRound();
		CombatArena arena = new CombatArena();
		PlayerCharacter magus = _build_magus();
		PlayerCharacter fighter = _build_fighter();
		arena.setSelf(magus);
		List<PlayerCharacter> enemies = new ArrayList<PlayerCharacter>();
		enemies.add(fighter);
		arena.setEnemies(enemies);
		List<Action> actions = magus.getActionsCombat(arena,timer);
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Actions%n"));
		for(Action action: actions) {
			sb.append(String.format("  %s%n", action));
		}
		System.out.println(sb.toString());
	}

}
