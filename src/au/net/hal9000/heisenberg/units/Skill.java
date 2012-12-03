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
 */
public class Skill extends Keyword {

	public Skill(String string) {
		super(string);
	}
}
