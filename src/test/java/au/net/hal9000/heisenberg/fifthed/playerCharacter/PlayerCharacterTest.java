package au.net.hal9000.heisenberg.fifthed.playercharacter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.fifthed.characterClass.CombatFeat;
import au.net.hal9000.heisenberg.fifthed.characterClass.Fighter;
import au.net.hal9000.heisenberg.fifthed.characterClass.Magus;
import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.ActionFree;
import au.net.hal9000.heisenberg.fifthed.combat.ActionFullRound;
import au.net.hal9000.heisenberg.fifthed.combat.ActionImmediate;
import au.net.hal9000.heisenberg.fifthed.combat.ActionMovement;
import au.net.hal9000.heisenberg.fifthed.combat.ActionMovementFiveFootStep;
import au.net.hal9000.heisenberg.fifthed.combat.ActionSpellCast;
import au.net.hal9000.heisenberg.fifthed.combat.ActionStandard;
import au.net.hal9000.heisenberg.fifthed.combat.ActionSwift;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.item.BowCrossLight;
import au.net.hal9000.heisenberg.fifthed.item.BowLong;
import au.net.hal9000.heisenberg.fifthed.item.Dagger;
import au.net.hal9000.heisenberg.fifthed.playercharacter.Human;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacterCondition;
import au.net.hal9000.heisenberg.fifthed.spell.BladeLash;
import au.net.hal9000.heisenberg.fifthed.spell.Fireball;
import au.net.hal9000.heisenberg.units.Position;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/** */
public class PlayerCharacterTest {

  /** Method setUp. */
  @Before
  public void setUp() {}

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
    assertTrue(details.contains("Name: Mr Magus"));
    assertTrue(details.contains("Race: Human"));
    assertTrue(details.contains("Level: 3"));
    assertTrue(details.contains("Condition: DEAFENED"));
    assertTrue(details.contains("Location (relative): "));
    assertTrue(details.contains("Equiped:"));
  }

  /** Test distance to other character. */
  @Test
  public void testPlayerCharacterDistance() {
    PlayerCharacter fred = new Human("Fred").setPosition(new Position(1, 0, 0));
    PlayerCharacter mary = new Human("Mary").setPosition(new Position(1, 1, 1));
    double distance = fred.distance(mary);
    double root2 = 1.41421356237f;
    assertEquals("distance ", root2, distance, 0.001f);
  }

  /** Test distance to other character. */
  @Test
  public void testGetActionsCombat() {
    PlayerCharacter self = _build_magus();
    CombatArena arena = new CombatArena(self);
    PlayerCharacter opponent = _build_fighter();
    opponent.setLocation(new Position(1, 1, 2));
    arena.opponentAdd(opponent);

    // Calculate legal actions
    Set<String> actions = new HashSet<>();
    for (Action a : self.getActionsCombat(arena)) {
      actions.add(a.getClass().getName());
    }
    assertTrue("contains ActionSpellCast", actions.contains(ActionSpellCast.class.getName()));
    assertTrue("contains ActionFullRound", actions.contains(ActionFullRound.class.getName()));
    assertTrue("contains ActionStandard", actions.contains(ActionStandard.class.getName()));
    assertTrue("contains ActionMovement", actions.contains(ActionMovement.class.getName()));
    assertTrue(
        "contains ActionMovementFiveFootStep",
        actions.contains(ActionMovementFiveFootStep.class.getName()));
    assertTrue("contains ActionFree", actions.contains(ActionFree.class.getName()));
    assertTrue("contains ActionSwift", actions.contains(ActionSwift.class.getName()));
    assertTrue("contains ActionImmediate", actions.contains(ActionImmediate.class.getName()));
  }
}
