package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.being.Being}.
 *
 * <ul>
 * <li>carpentry
 * <li>brick-laying
 * </ul>
 *
 * <p>
 * Developer Notes:Lets try making Skills immutable and see how it goes
 */
public class SkillId extends Keyword {

  /**
   * Constructor for SkillId.
   *
   * @param name String
   */
  public SkillId(String name) {
    super(name);
  }
}
