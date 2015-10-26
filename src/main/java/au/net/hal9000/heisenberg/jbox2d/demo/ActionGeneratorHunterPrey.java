package au.net.hal9000.heisenberg.jbox2d.demo;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.ai.action.ActionGeneratorMovementAbsoluteBarriers;
import au.net.hal9000.heisenberg.ai.api.Action;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.item.ai.ActionGeneratorAnimalConsume;

public class ActionGeneratorHunterPrey implements ActionGenerator {

    private List<ActionGenerator> actionGenerators;

    /**
     * Constructor.
     *
     * @param stepSize
     *            maximum distance to travel.
     * @param directionCount
     *            maximum directions to consider from any one state.
     */
    public ActionGeneratorHunterPrey(double stepSize, int directionCount,
            float bodyRadiusMax) {
        actionGenerators = new ArrayList<>();
        actionGenerators.add(new ActionGeneratorMovementAbsoluteBarriers(
                stepSize, directionCount, bodyRadiusMax));
        // As a rough metric, a body can attack/eat prey at a distance
        // equal to the radius of the attacker.
        actionGenerators.add(new ActionGeneratorAnimalConsume(bodyRadiusMax));

        // TODO add other action generators, e.g. looking - updates memory of barriers.
    }

    // Misc

    @Override
    public List<Action> generateActions(ModelState modelState) {
        List<Action> actions = new ArrayList<>();
        for (ActionGenerator actionGenerator : actionGenerators) {
            actions.addAll(actionGenerator.generateActions(modelState));
        }
        return actions;
    }

}
