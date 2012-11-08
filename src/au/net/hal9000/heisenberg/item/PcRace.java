package au.net.hal9000.heisenberg.item;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.PcClass;

// TODO Don't extend Being.
public abstract class PcRace extends Being {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int level = 0;
	/**
	 * profession e.g. Soldier, Wizard etc.
	 */
	protected PcClass pcClass;
	private int combatDice;
	private int magicDice;
	private int stealthDice;
	private int generalDice;
	int encumbrance;
	int health;
	/**
	 * A measure of the amount of things the entity can do in the current round.
	 * This is the remaining, not the maximum.
	 */
	private int actionPoints;
	/**
	 * The size of the magic user's fuel tank. This is the remaining, not the
	 * maximum.
	 */
	private int mana;
	/**
	 * Being object has a list of AbiltyScore objects.<br>
	 * PcClass object has a list of AbiltyScore objects.
	 * 
	 * To set up a Being, just work through the list on the PcClass.
	 * 
	 * Create a new AbilityScore object on Being for each one on PC. Set the
	 * modifier to 0. Set the name = pcClassAbility.getName()
	 * 
	 * Call the reCalculateAllAbilityScores() function<br>
	 * Set the value = pcClassAbility.getValue() + (level *
	 * pcClassAbility.getModifier()) + modifier;
	 */
	TreeMap<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();
	/**
	 * The {@link Recipe} list that is known by this Being.
	 */
	private Vector<Recipe> recipes = new Vector<Recipe>();
	/**
	 * The {@link Skill} objects required.
	 */
	private Set<Skill> skills = new TreeSet<Skill>();

	/**
	 * Give me a PC that is a Warrior, then customise.
	 */
	public PcRace(String pName, PcClass pPcClass) {
		super(pName);
		pcClass = pPcClass;
		init();
	}

	public PcRace(String pName) {
		super(pName);
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
		abilityScoresRecalculate();
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

	public void actionPointsAdjust(int adjust) {
		actionPoints += adjust;
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

	public void manaAdjust(int adjust) {
		mana += adjust;
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

	public Vector<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Vector<Recipe> recipes) {
		this.recipes = recipes;
	}

	/**
	 * Get the Skill objects.
	 * 
	 * @return a set of Skill objects
	 */
	public final Set<Skill> getSkills() {
		return skills;
	}

	public final void setSkills(final Set<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Set the PowerWords
	 * 
	 * @param newSkills
	 *            list of powerWord IDs as Strings.
	 */
	public void setSkills(final String[] newSkills) {
		skills.clear();
		skillsAdd(newSkills);
	}

	/**
	 * Add extra Skills to the list of required ingredients.
	 * 
	 * @param skills
	 */
	public final void skillsAdd(final Set<Skill> skills) {
		skills.addAll(skills);
	}

	/**
	 * Add extra PowerWords to the list of required ingredients.
	 * 
	 * @param powerWords
	 *            a Set of PowerWord objects to add.
	 */
	public final void skillsAddAll(final Set<Skill> powerWords) {
		powerWords.addAll(powerWords);
	}

	/**
	 * Add extra Skills to the list of required ingredients.
	 * 
	 * @param newSkills
	 */
	public final void skillsAdd(final String[] newSkills) {
		for (int i = newSkills.length - 1; i >= 0; i--) {
			skills.add(new Skill(newSkills[i]));
		}
	}

	// Misc

	protected void init() {
		if (pcClass == null) {
			clearClassBasedFields();
		} else {
			setBasicsFromPcClass();
			abilityScoresRecalculate();
		}
	}

	/**
	 * @return short plan text string for this object.
	 */

	// public String toString() {
	// String string = getName();
	// if (pcClass != null) {
	// string = string.concat(" the " + pcClass.getId());
	// }
	// return string;
	// }

	/**
	 * Warning: will reset all abilityScore objects
	 */
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

		// reset abilityScore objects
		abilityScores.clear();
		// TODO don't reach inside object's data structures - keySet()
		Iterator<String> itr = pcClass.getAbilityScores().keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			AbilityScore abilityScore = new AbilityScore(key, 0, 0);
			abilityScores.put(key, abilityScore);
		}
		abilityScoresRecalculate();
	}

	/**
	 * recalculate the AbilityScore objects. <br>
	 * e.g. when a PC levels.<br>
	 * Should be safe to call any time.
	 */
	private void abilityScoresRecalculate() {
		// TODO should we be iterating over keys or values?
		Iterator<String> itr = abilityScores.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			AbilityScore abilityScore = abilityScores.get(key);
			AbilityScore pcClassAbility = pcClass.getAbilityScore(key);
			abilityScore
					.setValue(pcClassAbility.getValue()
							+ (level * pcClassAbility.getMod())
							+ abilityScore.getMod());
		}
	}

	private void clearClassBasedFields() {

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
	public String description() {
		String string = "Name: " + getName() + "\nLevel: " + level + "\nRace: "
				+ getRace() + "\nClass: " + pcClass.getId()
				+ "\nCombat Dice: D" + combatDice + "\nMagic Dice: D"
				+ magicDice + "\nStealth Dice: D" + stealthDice
				+ "\nGeneral Dice: D" + generalDice + "\nAction Points: "
				+ actionPoints + "\nHealth: " + health + "\nMana: " + mana
				+ "\nGender: " + getGender() + "\nSize:" + getSize()
				+ "\nEncumbrance: " + encumbrance + "\n";

		if (abilityScores != null) {
			string = string.concat("Abilities:\n");
			Iterator<AbilityScore> itr = abilityScores.values().iterator();
			while (itr.hasNext()) {
				string = string.concat("  " + itr.next().toString() + "\n");
			}
		}
		return string;
	}

	public abstract String getRace();

	/**
	 * Shallow copy properties from one object to another.
	 * 
	 * @param item
	 */
	public void setAllFrom(PcRace pc) {
		setAllFrom((Being) pc);
		setLevel(pc.getLevel());
		setPcClass(pc.getPcClass());
		setCombatDice(pc.getCombatDice());
		setMagicDice(pc.getMagicDice());
		setStealthDice(pc.getStealthDice());
		setGeneralDice(pc.getGeneralDice());
		setEncumbrance(pc.getEncumbrance());
		setHealth(pc.getHealth());
		setActionPoints(pc.getActionPoints());
		setMana(pc.getMana());
		setAbilityScores(pc.getAbilityScores());
		setRecipes(pc.getRecipes());
		setSkills(pc.getSkills());
	}

}