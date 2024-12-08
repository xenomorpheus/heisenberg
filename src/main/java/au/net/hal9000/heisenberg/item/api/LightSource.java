package au.net.hal9000.heisenberg.item.api;

/** Sources of light, e.g. torches, lanterns etc. */
public interface LightSource {

  /**
   * Start the light.
   *
   * @param ignighter possible source of ignition.
   * @return true if ignition occurred.
   */
  boolean lightWith(Object ignighter);

  /** Stop the light. */
  void extinguish();

  /**
   * Is this torch lit?
   *
   * @return true if lit
   */
  boolean isLit();
}
