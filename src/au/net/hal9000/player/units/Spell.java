package au.net.hal9000.player.units;

import java.util.ArrayList;
/** Spells are recipes for magic.
 * 
 * <P> Spells are comprised of a list of Power
 * Words ({@link PowerWord}). <P>
 * 
 * <P> In order to perform a Spell, both the Spell and all the
 * Power Words for it must be known.</P>
 * 
 * <P>
 * Typically the more complex the spell, the more power words that 
 * are required.
 * </P>
 *
 * <P>Developer Notes: Lets try making Spells mutable and see how it goes</P>
 * 
 * <P>
 * 
 * @author bruins
 *         </P>
 */
public class Spell {
	private ArrayList <PowerWord> powerWords;
	
	Spell(){
		super();
	}

	public ArrayList <PowerWord> getPowerWords() {
		return powerWords;
	}

}
