package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.Being}.
 * 
 * <ul>
 * <li>carpentry</li>
 * <li>brick-laying</li>
 * </ul>
 * </P>
 *
 * <P>
 * Developer Notes:Lets try making Skills immutable and see how it goes
 * </P>
 * 
 * @author bruins
 */
public class SkillDetail extends KeywordDetail {
	/**
	 * The word that is invoked as part of a spell.
	 */
	public SkillDetail(String id, String description) {
		super(id, description);
	}

}
