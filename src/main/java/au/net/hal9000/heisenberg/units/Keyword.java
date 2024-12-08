package au.net.hal9000.heisenberg.units;

/** */
class Keyword implements Comparable<Keyword> {
  /** Main Id. */
  private String id;

  /**
   * The word that is invoked as part of a spell.
   *
   * @param id the keyword id
   */
  Keyword(String id) {
    super();
    this.id = id;
  }

  /**
   * Method getId.
   *
   * @return String
   */
  public final String getId() {
    return id;
  }

  // TODO Will objects of different sub-classes with same String still be
  // different?
  /**
   * Method compareTo.
   *
   * @param other Keyword
   * @return int
   */
  public int compareTo(Keyword other) {
    return id.compareTo(other.getId());
  }

  /**
   * Method toString.
   *
   * @return String
   */
  public String toString() {
    return id;
  }
}
