package au.net.hal9000.heisenberg.pc;

import static org.junit.Assert.assertEquals;

import java.util.TreeMap;
import java.util.Iterator;

/**
 * 
 * TODO Refactor this into two classes 1) Holder of configuration of a PC Class
 * 2) The actual character sheet class.
 * 
 * Ideas: New class CharacterSheet will hold a PC's stats and maybe items.
 * CharacterSheet will hold the following for each ability score: 1) (no
 * extension) - The current value Equals: CharacterClass ability base + (level *
 * CharacterClass ability inc) + Mod 2) "Mod" - Any modifiers the character has
 * chosen.
 * 
 * @author bruins
 * 
 */

public class CharacterSheet {

	private String name;
	private int level = 0;
	private PcClass pcClass;
	private String race;
	private String gender;
	private String size;
	// dice
	private int combatDice;
	private int magicDice;
	private int stealthDice;
	private int generalDice;
	// misc
	private int actionPoints;
	private int mana;
	private int encumbrance;
	private int health;
	TreeMap<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();

	/**
	 * TODO Idea - Give me a PC that is a Warrior, then customise.
	 */
	public CharacterSheet(PcClass pPcClass) {
		pcClass = pPcClass;
		init();
	}

	/**
	 * Don't use. Only here for JPA.
	 * 
	 */
	@Deprecated
	public CharacterSheet() {
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the level
	 */
	public final int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public final void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the pcClass
	 */
	public final PcClass getPcClass() {
		return pcClass;
	}

	/**
	 * @param pcClass
	 *            the pcClass to set
	 */
	public final void setPcClass(PcClass pcClass) {
		this.pcClass = pcClass;
		if (pcClass == null) {
			clearClassBasedFields();
		} else {
			setBasicsFromPcClass();
		}
	}

	/**
	 * @return the race
	 */
	public final String getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public final void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the gender
	 */
	public final String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public final void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the size
	 */
	public final String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public final void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the combatDice
	 */
	public final int getCombatDice() {
		return combatDice;
	}

	/**
	 * @param combatDice
	 *            the combatDice to set
	 */
	public final void setCombatDice(int combatDice) {
		this.combatDice = combatDice;
	}

	/**
	 * @return the magicDice
	 */
	public final int getMagicDice() {
		return magicDice;
	}

	/**
	 * @param magicDice
	 *            the magicDice to set
	 */
	public final void setMagicDice(int magicDice) {
		this.magicDice = magicDice;
	}

	/**
	 * @return the stealthDice
	 */
	public final int getStealthDice() {
		return stealthDice;
	}

	/**
	 * @param stealthDice
	 *            the stealthDice to set
	 */
	public final void setStealthDice(int stealthDice) {
		this.stealthDice = stealthDice;
	}

	/**
	 * @return the generalDice
	 */
	public final int getGeneralDice() {
		return generalDice;
	}

	/**
	 * @param generalDice
	 *            the generalDice to set
	 */
	public final void setGeneralDice(int generalDice) {
		this.generalDice = generalDice;
	}

	/**
	 * @return the actionPoints
	 */
	public final int getActionPoints() {
		return actionPoints;
	}

	/**
	 * @param actionPoints
	 *            the actionPoints to set
	 */
	public final void setActionPoints(int actionPoint) {
		this.actionPoints = actionPoint;
	}

	/**
	 * @return the mana
	 */
	public final int getMana() {
		return mana;
	}

	/**
	 * @param mana
	 *            the mana to set
	 */
	public final void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * @return the encumbrance
	 */
	public final int getEncumbrance() {
		return encumbrance;
	}

	/**
	 * @param encumbrance
	 *            the encumbrance to set
	 */
	public final void setEncumbrance(int encumbrance) {
		this.encumbrance = encumbrance;
	}

	/**
	 * @return the health
	 */
	public final int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public final void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the abilityScores
	 */
	public final TreeMap<String, AbilityScore> getAbilityScores() {
		return abilityScores;
	}

	/**
	 * @param abilityScores
	 *            the abilityScores to set
	 */
	public final void setAbilityScores(
			TreeMap<String, AbilityScore> abilityScores) {
		this.abilityScores = abilityScores;
	}

	/**
	 * @param name
	 *            name of AbilityScore
	 * @return the AbilityScore object
	 */
	public AbilityScore getAbilityScore(String name) {
		return abilityScores.get(name);
	}

	/**
	 * @param AbilityScore
	 *            the AbilityScore to put.<br>
	 *            Any existing AbilityScore with this name will be removed.
	 */
	public void setAbilityScore(AbilityScore abilityScore) {
		abilityScores.put(abilityScore.getName(), abilityScore);
	}

	// misc

	private void init() {
		if (pcClass == null) {
			clearClassBasedFields();
		} else {
			setBasicsFromPcClass();
			abilityScoresRecalculate();
		}
	}

	private void setBasicsFromPcClass() {
		// dice
		combatDice = pcClass.getCombatDice();
		magicDice = pcClass.getMagicDice();
		stealthDice = pcClass.getStealthDice();
		generalDice = pcClass.getGeneralDice();
		// misc
		encumbrance = pcClass.getEncumbrance();
		actionPoints = pcClass.getActionPoints();
		health = pcClass.getHealth();
		mana = pcClass.getMana();
	}

	/**
	 * recalculate the AbilityScore objects. <br>
	 * e.g. when a PC levels.<br>
	 * Should be safe to call any time.
	 */
	private void abilityScoresRecalculate() {
		Iterator<String> itr = abilityScores.keySet().iterator();
		while (itr.hasNext()) {
			String name = itr.next();
			AbilityScore abilityScore = abilityScores.get(name);
			AbilityScore pcClassAbility = pcClass.getAbilityScore(name);
			abilityScore
					.setValue(pcClassAbility.getValue()
							+ (level * pcClassAbility.getMod())
							+ abilityScore.getMod());
		}
	}

	/**
	 * 
	 * CharacterSheet has a list of AbiltyScore objects. PcClass has a list of
	 * AbiltyScore objects.
	 * 
	 * 
	 * To set up a Character Sheet, just work through the list on the PcClass.
	 * 
	 * Create a new AbilityScore object on CS for each one on PC. Set the
	 * modifier to 0. Set the name = pcClassAbility.getName()
	 * 
	 * Call the reCalculateAllAbilityScores() function<br>
	 * Set the value = pcClassAbility.getValue() + (level *
	 * pcClassAbility.getModifier()) + modifier;
	 */

	private void clearClassBasedFields() {
		pcClass = null;
		race = null;
		gender = null;
		size = null;
		// dice
		combatDice = 0;
		magicDice = 0;
		stealthDice = 0;
		generalDice = 0;
		// misc
		actionPoints = 0;
		mana = 0;
		encumbrance = 0;
		health = 0;

	}

	/**
	 * @return Plain text description of the object
	 */

	public String toString() {
		String string = "Name: " + name + "\nLevel: " + level + "\nClass: "
				+ pcClass.getId() + "\nCombat Dice: D" + combatDice
				+ "\nMagic Dice: D" + magicDice + "\nStealth Dice: D"
				+ stealthDice + "\nGeneral Dice: D" + generalDice
				+ "\nAction Points: " + actionPoints + "\nhealth: " + health
				+ "\nmana: " + mana + "\nrace: " + race + "\ngender: " + gender
				+ "\nsize:" + size + "\nencumbrance: " + encumbrance + "\n";

		if (abilityScores != null) {
			string = string.concat("Abilities:\n");
			Iterator<AbilityScore> itr = abilityScores.values().iterator();
			while (itr.hasNext()) {
				string = string.concat("  " + itr.next().toString() + "\n");
			}
		}
		return string;
	}

}
