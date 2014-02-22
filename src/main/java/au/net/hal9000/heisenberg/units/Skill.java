package au.net.hal9000.heisenberg.units;

/**
 * The skills learnt by an {@link au.net.hal9000.heisenberg.item.Entity}.
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
public class Skill extends Keyword {

    /**
     * Constructor for Skill.
     * @param string String
     */
    public Skill(String string) {
        super(string);
    }
}
