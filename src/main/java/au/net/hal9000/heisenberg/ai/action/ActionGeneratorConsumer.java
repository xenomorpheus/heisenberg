package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.PathImpl;
import au.net.hal9000.heisenberg.ai.api.ActionGenerator;
import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateConsumerConsumable;
import au.net.hal9000.heisenberg.ai.api.Path;
import au.net.hal9000.heisenberg.units.Position;

/** Generate Action object if we are close enough to consume. */
public class ActionGeneratorConsumer implements ActionGenerator {

  private double consumeDistanceMax;
  private double consumeCost;

  /**
   * Sets the maximum distance for the action generator.
   *
   * @param distanceMax Maximum distance
   * @param consumeCost Cost to consume
   */
  public ActionGeneratorConsumer(double distanceMax, double consumeCost) {
    this.consumeDistanceMax = distanceMax;
    this.consumeCost = consumeCost;
  }

  @Override
  public Path generateActions(ModelState modelState) {
    Path actions = new PathImpl();

    // Attempt to consume if close enough.
    if (modelState instanceof ModelStateConsumerConsumable) {
      ModelStateConsumerConsumable modelStateConsumerConsumable =
          (ModelStateConsumerConsumable) modelState;
      Position consumerPos = modelStateConsumerConsumable.getEntityPosition();
      Position consumablePos = modelStateConsumerConsumable.getConsumablePosition();
      Position delta = consumablePos.subtract(consumerPos);
      double goalDist = delta.length();
      if (goalDist < consumeDistanceMax) {
        actions.add(
            new ActionConsume(
                modelStateConsumerConsumable.getConsumer(),
                modelStateConsumerConsumable.getConsumable(),
                goalDist + consumeCost));
      }
    }
    return actions;
  }
}
