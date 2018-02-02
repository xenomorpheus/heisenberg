package au.net.hal9000.heisenberg.fifthed.race;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.ActionMovementFiveFootStep;
import au.net.hal9000.heisenberg.fifthed.ActionMovementStandard;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;

public abstract class Humanoid extends Race {

	public Humanoid() {
		super();
	}

	@Override
	/** work out what actions may be performed in this amount of time */
	public List<Action> getActionsCombat(CombatArena arena, TimerRound timer) {
		List<Action> actions = new ArrayList<Action>();
		if (timer.isActionDurationAvailable(ActionDuration.FIVEFOOTSTEP)) {
			actions.add(new ActionMovementFiveFootStep());
		}
		if (timer.isActionDurationAvailable(ActionDuration.MOVE)) {
			actions.add(new ActionMovementStandard());
		}
		if (timer.isActionDurationAvailable(ActionDuration.FREE)) {
			// TODO actions.add(new ActionFree());
		}
		// TODO untrained weapon attacks
		// TODO Free Actions, etc.
		return actions;
	}

}
