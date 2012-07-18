package au.net.hal9000.player.units;

import java.io.Serializable;

/** PowerWords are the alphabet of a {@link Spell}.
 * 
 * <P>
 * Typically the more powerful words have a greater
 * number of syllables. </P>
 * <P>
 * The more powerful words are rarer than others. This is due
 * to their complexity they are harder for people to learn
 * so are not in common use.
 * </P>
 * 
 * <P>
 * The description of a power words may never be revealed to players.
 * This is part of game play, working out what it does.
 * </P>
 * 
 * <P>
 * As part of the fun of game play, player may try to:
 * <ul>
 * <li>Quest/discover hidden PowerWords.</li>
 * <li>Guess the hidden description of a PowerWord.</li>
 * <li>Quest/Experiment with the permutations to discover Spells.</li> 
 * <li>Work out how to protect themselves from the spell they are casting.</li>
 * </ul>
 * </P>
 * 
 * <P> Ideas for Power Words</P>
 * <ul>
 * <li>Some for each Fire, Ice, Electricity, etc</li>
 * <li>Multiple levels of each word, eg small fire, bigger fire. </li>
 * <li>Some for healing</li>
 * <li>Some for targeting e.g. Magic Missile style</li>
 * <li>Some for defence from other spells</li>
 * <li>Some for spell invocation shielding, otherwise spell causes
 * damage to the caster.  Part of the fun of trying to develop your
 * own spells is to learn to be careful.</li>
 * <li>Some for multiple e.g. two magic missiles at once</li>
 * <li>Some for repeat e.g. next round another the second missle fires</li>
 * <li>Some for duration</li>
 * 
 * 
 * <P>Developer Notes:Lets try making PowerWords mutable and 
 * see how it goes</P>
 * 
 * @author bruins
 */
public class PowerWord implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The word that is uttered as part of a spell.
	 */
	private String syllables;
	/**
	 * The game developer's hidden description of the word.
	 */
	private String description;

	PowerWord(String word, String description) {
		super();
		this.syllables = word;
		this.description = description;
	}

	public String getSyllables() {
		return syllables;
	}

	public String getDescription() {
		return description;
	}

}
