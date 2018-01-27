package au.net.hal9000.heisenberg.fifthed.race;

import java.util.List;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;

public class Human extends Humanoid {

	public Human() {
		super();
		setName("Human");
	}

	@Override
	/** work out what actions may be performed in this amount of time */
	public List<Action> getActions(CombatArena arena, TimerRound timer){
		// TODO Auto-generated method stub
		return null;
	}

}
