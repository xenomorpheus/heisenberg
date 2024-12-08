package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.api.ModelState;
import au.net.hal9000.heisenberg.ai.api.ModelStateConsumerConsumable;

/**
 * Action to eat something.<br>
 * Check super classes but this class is likely to be immutable.
 */
public final class ActionConsume extends ActionBase {

  /** Entity to eat */
  private Object consumer;

  /** Item to be eaten */
  private Object consumable;

  /**
   * Constructor.
   *
   * @param consumable That to be eaten or drunk.
   * @param cost The cost of performing this action.
   */
  public ActionConsume(Object entity, Object consumable, double cost) {
    super(cost);
    this.consumer = entity;
    this.consumable = consumable;
  }

  // Misc
  @Override
  public void apply(ModelState modelState) {
    if (modelState instanceof ModelStateConsumerConsumable) {
      ModelStateConsumerConsumable modelStateEntityConsumable =
          (ModelStateConsumerConsumable) modelState;
      modelStateEntityConsumable.setConsumed();
    } else {
      throw new RuntimeException("Bad type of model state " + modelState);
    }
  }

  // Misc
  /**
   * Method toString.
   *
   * @see au.net.hal9000.heisenberg.ai.api.Action#toString()
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(12);
    sb.append("ActionConsume=[consumer=")
        .append(consumer)
        .append(",consumable=")
        .append(consumable)
        .append(']');
    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((consumable == null) ? 0 : consumable.hashCode());
    result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ActionConsume other = (ActionConsume) obj;
    if (consumable == null) {
      if (other.consumable != null) {
        return false;
      }
    } else if (!consumable.equals(other.consumable)) {
      return false;
    }
    if (consumer == null) {
      if (other.consumer != null) {
        return false;
      }
    } else if (!consumer.equals(other.consumer)) {
      return false;
    }
    return true;
  }
}
