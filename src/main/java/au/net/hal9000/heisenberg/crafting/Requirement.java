package au.net.hal9000.heisenberg.crafting;

/**
 * This is an abstract class.<br>
 * Requirement objects hold details about a requirement for a Recipe objects.<br>
 * A Recipe may have any number of Requirement objects.
 *
 * <p>e.g.
 *
 * <ul>
 *   <li>Item - Wood with a minimum of 3 weight units.
 *   <li>Item - FlintAndTinder, not consumed.
 * </ul>
 */
public abstract class Requirement {

  /** Each requirement should have a unique ID, per recipe. */
  private String id;

  /**
   * Will the item be consumed as part of the cooking process.<br>
   * default is true.
   */
  private boolean isConsumed = true;

  /**
   * Constructor for Requirement.
   *
   * @param id String
   */
  private Requirement(String id) {
    super();
    this.id = id;
  }

  /**
   * Constructor for Requirement.
   *
   * @param id String
   * @param isConsumed boolean
   */
  protected Requirement(String id, boolean isConsumed) {
    this(id);
    this.isConsumed = isConsumed;
  }

  // setters and getters
  /**
   * Get the id.
   *
   * @return the id.
   */
  public String getId() {
    return id;
  }

  // isConsumed
  /**
   * Method isConsumed.
   *
   * @return boolean
   */
  public boolean isConsumed() {
    return isConsumed;
  }

  /**
   * Return a description of the object.
   *
   * @return Return a description of the object.
   */
  String getDescription() {
    String description = "Id: " + id;
    if (isConsumed) {
      description += ", consumed";
    } else {
      description += ", not consumed";
    }
    return description;
  }
}
