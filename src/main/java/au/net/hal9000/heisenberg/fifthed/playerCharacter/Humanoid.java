package au.net.hal9000.heisenberg.fifthed.playercharacter;

import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.combat.ActionFree;
import au.net.hal9000.heisenberg.fifthed.combat.ActionFullRound;
import au.net.hal9000.heisenberg.fifthed.combat.ActionImmediate;
import au.net.hal9000.heisenberg.fifthed.combat.ActionMovement;
import au.net.hal9000.heisenberg.fifthed.combat.ActionMovementFiveFootStep;
import au.net.hal9000.heisenberg.fifthed.combat.ActionStandard;
import au.net.hal9000.heisenberg.fifthed.combat.ActionSwift;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.combat.TimerRound;
import java.util.HashSet;
import java.util.Set;

/*
 A Humanoid shaped PlayerCharacter.
*/
public abstract class Humanoid extends PlayerCharacter {

  public Humanoid(String name) {
    super(name);
  }

  @Override
  /**
   *  Work out what actions may be performed in this amount of time.
   */
  public Set<Action> getActionsCombat(CombatArena arena) {
    Set<Action> actions = new HashSet<Action>();
    actions.addAll(super.getActionsCombat(arena));
    TimerRound timer = arena.getTimerRound();
    if (timer.isActionDurationAvailable(ActionDuration.FULLROUND)) {
      actions.add(new ActionFullRound());
    }
    if (timer.isActionDurationAvailable(ActionDuration.STANDARD)) {
      actions.add(new ActionStandard());
    }
    if (timer.isActionDurationAvailable(ActionDuration.MOVE)) {
      actions.add(new ActionMovement());
    }
    if (timer.isActionDurationAvailable(ActionDuration.FIVEFOOTSTEP)) {
      actions.add(new ActionMovementFiveFootStep());
    }
    if (timer.isActionDurationAvailable(ActionDuration.FREE)) {
      actions.add(new ActionFree());
    }
    if (timer.isActionDurationAvailable(ActionDuration.SWIFT)) {
      actions.add(new ActionSwift());
    }
    if (timer.isActionDurationAvailable(ActionDuration.IMMEDIATE)) {
      actions.add(new ActionImmediate());
    }
    // TODO untrained weapon attacks
    return actions;
  }
}
