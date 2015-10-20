package au.net.hal9000.heisenberg.ai.action;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.action.ActionAnimalEat;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.jbox2d.demo.HunterPreyModelState;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Generate movement Action objects in the shape of a wheel spoke, using current
 * position as centre.<br>
 * This class also knows about Barriers, that which blocks movement.
 */
public class EatActionGenerator implements ActionGenerator {

	/**
	 * 
	 */
	public EatActionGenerator() {
	}
	// TODO unit tests EatActionGenerator

	@Override
	public List<Action> generateActions(ModelState modelState) {
		List<Action> actions = new ArrayList<>();

		// Attempt to eat prey if close enough.
		if (modelState instanceof HunterPreyModelState) {
			HunterPreyModelState modelStateHunterPrey = (HunterPreyModelState) modelState;
			Entity hunter = modelStateHunterPrey.getHunter();
			Position hunterPos = hunter.getPosition();
			Item prey = modelStateHunterPrey.getPrey();
			Position preyPos = prey.getPosition();
			Position delta = preyPos.subtract(hunterPos);
			double goalDist = delta.length();
			if (goalDist < Position.DEFAULT_AXIS_TOLERANCE) {
				// TODO cost increase by effort to eat.
				actions.add(new ActionAnimalEat(hunter, prey, goalDist));
			}
		}
		return actions;
	}

}
