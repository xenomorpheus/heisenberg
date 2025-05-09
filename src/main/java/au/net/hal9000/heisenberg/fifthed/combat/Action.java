package au.net.hal9000.heisenberg.fifthed.combat;

/** Base class for all Actions */
public abstract class Action {

  /** Default constructor for Action. */
  public Action() {
    super();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
