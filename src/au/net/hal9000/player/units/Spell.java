package au.net.hal9000.player.units;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * Spells are recipes for magic.
 * 
 * <P>
 * Spells are comprised of a list of Power Words ({@link PowerWord}).
 * <P>
 * 
 * <P>
 * In order to perform a Spell, both the Spell and all the Power Words for it
 * must be known.
 * </P>
 * 
 * <P>
 * Typically the more complex the spell, the more power words that are required.
 * </P>
 * 
 * <P>
 * Developer Notes: Lets try making Spells mutable and see how it goes
 * </P>
 * 
 * <P>
 * 
 * @author bruins
 *         </P>
 */
public class Spell {
	private String name = new String();
	private String description = new String();
	private ArrayList<String> powerWordNames;

	// Constructors
	Spell() {
		super();
	}

	Spell(String name, String description, ArrayList<String> powerWordNames) {
		super();
		this.name = name;
		this.description = description;
		this.powerWordNames = powerWordNames;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getPowerWordNames() {
		return powerWordNames;
	}

	/** Read the Spell set from XML */
	public static ArrayList<Spell> FileReader(File file)
			throws ParsingException, IOException {
		Builder builder = new Builder();
		Document doc = builder.build(file);
		// get doc root element
		Element root = doc.getRootElement();

		Elements spellElementSet = root.getChildElements("spell");
		ArrayList<Spell> Spells = new ArrayList<Spell>();
		for (int spellCurrent = 0; spellCurrent < spellElementSet.size(); spellCurrent++) {
			// get current Spell
			Element spellElement = spellElementSet.get(spellCurrent);
			// get PowerWord set
			ArrayList<String> powerWordNames = new ArrayList<String>();
			Elements powerWordSet = spellElement.getChildElements("powerWordName");
			for (int powerWordCurrent = 0; powerWordCurrent < powerWordSet.size(); powerWordCurrent++){
				String powerWordName = powerWordSet.get(powerWordCurrent).getValue();
                powerWordNames.add(powerWordName);			
			}
			Spell spell = new Spell(
					spellElement.getAttributeValue("name"),
					spellElement.getAttributeValue("description"),
					powerWordNames
					);
			Spells.add(spell);
		}
		return Spells;
	}

	public void show() {
		System.out.println("\nSpell:");
		System.out.println("Name: " + this.getName());
		System.out.println("Description: " + this.getDescription());
		System.out.print("Syllables:");
		for (String name : this.getPowerWordNames()){
			System.out.print(" "+name);
		}
		System.out.println();
	}

	public static void main(String arguments[]) {
		try {
			File file = new File("config/Spell.xml");
			ArrayList<Spell> spells = Spell.FileReader(file);
			for (Spell spell : spells) {
				spell.show();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
