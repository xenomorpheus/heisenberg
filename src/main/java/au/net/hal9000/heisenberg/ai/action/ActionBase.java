package au.net.hal9000.heisenberg.ai.action;

import au.net.hal9000.heisenberg.ai.api.Action;

/**
 * That base class for Action classes.<br>
 * Note the base class is immutable.
 */
public abstract class ActionBase implements Action {

  /** The cost of the Action */
  private double cost;

  public ActionBase(double cost) {
    super();
    this.cost = cost;
  }

  @Override
  public double getCost() {
    return cost;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(cost);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ActionBase other = (ActionBase) obj;
    if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost)) {
      return false;
    }
    return true;
  }
}
