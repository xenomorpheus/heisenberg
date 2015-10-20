package au.net.hal9000.heisenberg.jbox2d.demo;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.action.EatActionGenerator;
import au.net.hal9000.heisenberg.ai.action.MovementAbsoluteBarriers;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;

public class HunterPreyActionGenerator implements ActionGenerator {

	/** Maximum radius of Entity. Used to avoid barriers */
	private float bodyRadiusMax;

	private ActionGenerator movementAbsoluteBarriers;
	private ActionGenerator eat;

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
		eat = new EatActionGenerator();
	}

	// Misc

	@Override
	public List<Action> generateActions(ModelState modelState) {
		List<Action> actions = new ArrayList<>();

		actions.addAll(movementAbsoluteBarriers.generateActions(modelState));
		actions.addAll(eat.generateActions(modelState));

		// TODO add other actions, e.g. looking - updates memory of barriers.
		return actions;
	}

}
