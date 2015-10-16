package au.net.hal9000.heisenberg.jbox2d.demo;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.action.MovementAbsoluteBarriers;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.action.ActionAnimalEat;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.units.Position;

public class HunterPreyActionGenerator implements ActionGenerator {

	/** Maximum radius of Entity. Used to avoid barriers */
	private float bodyRadiusMax;

	private ActionGenerator movementAbsoluteBarriers;

	/**
	 * Constructor.
	 * 
	 * @param stepSize
	 *            maximum distance to travel.
	 * @param directionCount
	 *            maximum directions to consider from any one state.
	 */
	public HunterPreyActionGenerator(double stepSize, int directionCount, float entityRadiusMax) {
		this.bodyRadiusMax = entityRadiusMax;
		movementAbsoluteBarriers = new MovementAbsoluteBarriers(stepSize, directionCount, bodyRadiusMax);
	}

	// Misc

	@Override
	public List<Action> generateActions(ModelState modelState) {
		List<Action> actions = new ArrayList<>();

		actions.addAll(movementAbsoluteBarriers.generateActions(modelState));

		// Attempt to eat prey if close enough.
		if (modelState instanceof HunterPreyModelState) {
			HunterPreyModelState modelStateHunterPrey = (HunterPreyModelState) modelState;
			Entity hunter = modelStateHunterPrey.getHunter();
			Position hunterPos = hunter.getPosition();
			Item prey = modelStateHunterPrey.getPrey();
			Position preyPos = prey.getPosition();
			Position delta = preyPos.subtract(hunterPos);
			double goalDist = delta.length();
			if (goalDist < 1.0f) {
				actions.add(new ActionAnimalEat(hunter, prey, 1f));
			}
		}

		// TODO add other actions, e.g. looking - updates memory of barriers.
		return actions;
	}

}
