package au.net.hal9000.player.units;

import java.io.*;
import nu.xom.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * PowerWords are the alphabet of a {@link Spell}.
 * 
 * <P>
 * Typically the more powerful words have more complex names.
 * </P>
 * <P>
 * The more powerful words are rarer than others. This is due to their
 * complexity they are harder for people to learn so are not in common use.
 * </P>
 * 
 * <P>
 * The description of a power words may never be revealed to players. This is
 * part of game play, working out what it does.
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
 * <P>
 * Ideas for Power Words
 * </P>
 * <ul>
 * <li>Some for each Fire, Ice, Electricity, etc</li>
 * <li>Multiple levels of each word, eg small fire, bigger fire.</li>
 * <li>Some for healing</li>
 * <li>Some for targeting e.g. Magic Missile style</li>
 * <li>Some for defence from other spells</li>
 * <li>Some for spell invocation shielding, otherwise spell causes damage to the
 * caster. Part of the fun of trying to develop your own spells is to learn to
 * be careful.</li>
 * <li>Some for multiple e.g. two magic missiles at once</li>
 * <li>Some for repeat e.g. next round another the second missle fires</li>
 * <li>Some for duration</li>
 * 
 * 
 * <P>
 * Developer Notes:Lets try making PowerWords mutable and see how it goes
 * </P>
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
	private String name;
	/**
	 * The game developer's hidden description of the word.
	 */
	private String description;

	PowerWord(String word, String description) {
		super();
		this.name = word;
		this.description = description;
	}

	public String getSyllables() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	/** Read the PowerWord set from XML */
	public static ArrayList<PowerWord> FileReader(File file) throws ParsingException,
			IOException {
		Builder builder = new Builder();
		Document doc = builder.build(file);
		// get doc root element, <properties>
		Element root = doc.getRootElement();
		// <powerWord name="ASDFASDFA1"
		// description="description 1"></powerWord>
		Elements entries = root.getChildElements("powerWord");
		ArrayList<PowerWord> powerWords = new ArrayList<PowerWord>();
		for (int current = 0; current < entries.size(); current++) {
			// get current powerWord
			Element entry = entries.get(current);
			PowerWord pw = new PowerWord(entry.getAttributeValue("name"),
					entry.getAttributeValue("description"));
			powerWords.add(pw);
		}
		return powerWords;
	}

	public void show() {
		System.out.println("\nPowerWord:");
		System.out.println("Syllables: " + this.getSyllables());
		System.out.println("Description: " + this.getDescription());
	}

	public static void main(String arguments[]) {
		try {
			File file = new File("config/PowerWord.xml");
			ArrayList<PowerWord> powerWords = PowerWord.FileReader(file);
			for (PowerWord pw : powerWords ){
				pw.show();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
