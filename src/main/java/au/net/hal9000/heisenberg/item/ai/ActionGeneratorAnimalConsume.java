package au.net.hal9000.heisenberg.item.ai;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateHunterPrey;
import au.net.hal9000.heisenberg.units.Position;

/**
 * Generate movement Action objects in the shape of a wheel spoke, using current
 * position as centre.<br>
 * This class also knows about Barriers, that which blocks movement.
 */
public class ActionGeneratorAnimalConsume implements ActionGenerator {

    private double distanceMax;
	/**
     * 
     */
    public ActionGeneratorAnimalConsume(double distanceMax) {
    	this.distanceMax = distanceMax;
    }
    // TODO unit tests EatActionGenerator

    @Override
    public List<Action> generateActions(ModelState modelState) {
        List<Action> actions = new ArrayList<>();

        // Attempt to eat prey if close enough.
        if (modelState instanceof ModelStateHunterPrey) {
            ModelStateHunterPrey modelStateHunterPrey = (ModelStateHunterPrey) modelState;
            Position hunterPos = modelStateHunterPrey.getHunterPosition();
            Position preyPos = modelStateHunterPrey.getPreyPosition();
            Position delta = preyPos.subtract(hunterPos);
            double goalDist = delta.length();
            if (goalDist < distanceMax) {
                // TODO cost increase by effort to eat.
                actions.add(new ActionAnimalConsume(
                        modelStateHunterPrey.getHunter(),
                        modelStateHunterPrey.getPrey(), goalDist));
            }
        }
        return actions;
    }

}
