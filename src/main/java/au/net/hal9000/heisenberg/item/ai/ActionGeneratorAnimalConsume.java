package au.net.hal9000.heisenberg.item.ai;

import au.net.hal9000.heisenberg.ai.PathImpl;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateHunterPrey;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Generate movement Action objects in the shape of a wheel spoke, using current
 * position as centre.<br>
 * This class also knows about Barriers, that which blocks movement.
 */
public class ActionGeneratorAnimalConsume implements ActionGenerator {

    private double consumeDistanceMax;
	/**
     * 
     */
    public ActionGeneratorAnimalConsume(double distanceMax) {
    	this.consumeDistanceMax = distanceMax;
    }
    // TODO unit tests EatActionGenerator

    @Override
    public Path generateActions(ModelState modelState) {
        Path actions = new PathImpl();

        // Attempt to eat prey if close enough.
        if (modelState instanceof ModelStateHunterPrey) {
            ModelStateHunterPrey modelStateHunterPrey = (ModelStateHunterPrey) modelState;
            Position hunterPos = modelStateHunterPrey.getHunterPosition();
            Position preyPos = modelStateHunterPrey.getPreyPosition();
            Position delta = preyPos.subtract(hunterPos);
            double goalDist = delta.length();
            if (goalDist < consumeDistanceMax) {
                actions.add(new ActionEntityEat(
                        modelStateHunterPrey.getHunter(),
                        modelStateHunterPrey.getPrey(), goalDist));
            }
        }
        return actions;
    }

}
