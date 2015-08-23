package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.entity.Entity}.
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
 * @version $Revision: 1.0 $
 */
public class SkillDetail extends KeywordDetail {
    /**
     * The word that is invoked as part of a spell.
     * @param id String
     * @param description String
     */
    public SkillDetail(String id, String description) {
        super(id, description);
    }

}
