package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.entity.EntityItem}.
 *
 * <ul>
 *   <li>carpentry
 *   <li>brick-laying
 * </ul>
 *
 * <p>Developer Notes:Lets try making Skills immutable and see how it goes
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class Skill extends Keyword {

  /**
   * Constructor for Skill.
   *
   * @param name String
   */
  public Skill(String name) {
    super(name);
  }
}
