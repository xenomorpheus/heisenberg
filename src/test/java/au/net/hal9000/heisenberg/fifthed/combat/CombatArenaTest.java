package au.net.hal9000.heisenberg.fifthed.combat;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.TestHelper;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.units.Position;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/** */
public class CombatArenaTest {
  @Test
  public void testGetActionsCombat() {
    PlayerCharacter pc = TestHelper.getMagus();
    CombatArena arena = new CombatArena(pc);
    PlayerCharacter opponent = TestHelper.getFighter();
    // TODO set opponent location relative to pc
    opponent.setLocation(new Position(1, 1, 2));
    arena.opponentAdd(opponent);

    // Calculate legal actions
    Set<String> actions = new HashSet<>();
    for (Action a : arena.getActionsCombat()) {
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
