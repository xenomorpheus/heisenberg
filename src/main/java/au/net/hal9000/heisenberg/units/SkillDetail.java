package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.being.Being}.
 *
 * <ul>
 *   <li>carpentry
 *   <li>brick-laying
 * </ul>
 *
 * <p>Developer Notes:Lets try making Skills immutable and see how it goes
 */
public class SkillDetail extends KeywordDetail {
  /**
   * The word that is invoked as part of a spell.
   *
   * @param id String
   * @param description String
   */
  public SkillDetail(String id, String description) {
    super(id, description);
  }
}
