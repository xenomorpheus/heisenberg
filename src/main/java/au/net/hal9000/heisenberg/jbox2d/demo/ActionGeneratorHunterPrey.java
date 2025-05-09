package au.net.hal9000.heisenberg.jbox2d.demo;

import au.net.hal9000.heisenberg.ai.PathImpl;
import au.net.hal9000.heisenberg.ai.action.ActionGeneratorConsumer;
import au.net.hal9000.heisenberg.ai.action.ActionGeneratorMovementAbsoluteBarriers;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.Path;
import java.util.ArrayList;
import java.util.List;

/** Represents a hunter-prey action generator for AI systems. */
public class ActionGeneratorHunterPrey implements ActionGenerator {

  private List<ActionGenerator> actionGenerators;

  /**
   * Constructor.
   *
   * @param stepSize maximum distance to travel.
   * @param directionCount maximum directions to consider from any one state.
   * @param hunterRadius hunter's body radius
   * @param preyRadius prey's body radius
   */
  public ActionGeneratorHunterPrey(
      double stepSize, int directionCount, float hunterRadius, float preyRadius) {
    actionGenerators = new ArrayList<>();
    // As a rough metric, a body can attack/eat prey at a distance.
    // TODO calculate worth of eating. currently just a big number.
    actionGenerators.add(new ActionGeneratorConsumer(preyRadius + 3 * hunterRadius, -500f));

    actionGenerators.add(
        new ActionGeneratorMovementAbsoluteBarriers(stepSize, directionCount, hunterRadius));

    // TODO add other action generators, e.g. looking - updates memory of barriers.
  }

  // Misc

  @Override
  public Path generateActions(ModelState modelState) {
    Path actions = new PathImpl();
    for (ActionGenerator actionGenerator : actionGenerators) {
      actions.addAll(actionGenerator.generateActions(modelState));
    }
    return actions;
  }
}
