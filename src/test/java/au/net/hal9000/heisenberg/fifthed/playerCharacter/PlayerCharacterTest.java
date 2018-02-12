package au.net.hal9000.heisenberg.fifthed.playerCharacter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.fifthed.characterClass.CombatFeat;
import au.net.hal9000.heisenberg.fifthed.characterClass.Fighter;
import au.net.hal9000.heisenberg.fifthed.characterClass.Magus;
import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.item.BowCrossLight;
import au.net.hal9000.heisenberg.fifthed.item.BowLong;
import au.net.hal9000.heisenberg.fifthed.item.Dagger;
import au.net.hal9000.heisenberg.fifthed.spell.BladeLash;
import au.net.hal9000.heisenberg.fifthed.spell.Fireball;
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
		PlayerCharacter character = new Human("Mr Magus");

		Magus magus = new Magus(3, character);

		// Spells
		magus.spellsAdd(new Fireball());
		magus.spellsAdd(new BladeLash());

		// Combat Feats
		magus.combatFeatAdd(CombatFeat.ARTFULL_DODGE);

		// Conditions
		character.conditionAdd(PlayerCharacterCondition.DEAFENED);

		// Equipment
		character.equippedAdd(new Dagger());
		character.equippedAdd(new BowCrossLight());

		return character;
	}

	private PlayerCharacter _build_fighter() {
		PlayerCharacter character = new Human("Ms Fighter");

		// Classes
		Fighter fighter = new Fighter(2, character);

		// Combat Feats
		fighter.combatFeatAdd(CombatFeat.ARTFULL_DODGE);

		// Conditions
		character.conditionAdd(PlayerCharacterCondition.DEAFENED);


		// Equipment
		character.equippedAdd(new Dagger());
		character.equippedAdd(new BowLong());

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
		PlayerCharacter fred = new Human("Fred").setPosition(new Position(1, 0, 0));
		PlayerCharacter mary = new Human("Mary").setPosition(new Position(1, 1, 1));
		double distance = fred.distance(mary);
		double root2 = 1.41421356237f;
		assertEquals("distance ", root2, distance, 0.001f);
	}

	/**
	 * Test distance to other character.
	 */

	@Test
	public void testGetActionsCombat() {
		PlayerCharacter self = _build_magus();
		CombatArena arena = new CombatArena(self);
		PlayerCharacter opponent = _build_fighter();
		opponent.setLocation(new Position(1,1,2));
		arena.opponentAdd(opponent);

		// Calculate legal actions
		List<Action> actions = self.getActionsCombat(arena);

		// Display the results
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Combat Arena%n")).append(arena.toString());
		sb.append(String.format("Actions%n"));
		for (Action action : actions) {
			sb.append(String.format("  %s%n", action));
		}
		System.out.println(sb.toString());
	}

}
