package au.net.hal9000.heisenberg.fifthed.spell;

import java.util.HashSet;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.combat.ActionDuration;

public abstract class Spell {
	private String name = null;
	private Set<SpellComponent> spellComponents = new HashSet<SpellComponent>();
	private boolean rangeTouch = false;
	private ActionDuration actionDuration = null;
	private SpellSavingThrow spellSavingThrow;
	private int effectDurationSeconds = 0;
	private EffectArea effectAreaType = null;
	private int effectAreaFeet = 0;
	private int effectRangeBase = 0;
	private int effectRangeLevelMultiplier = 0;
	private boolean effectSpellResistance = false;
	private String description = null;
	private String Url = null;

	// Setters and Getters

	/**
	 * @param name
	 *            the name to set
	 */
	public Spell setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	protected Spell setRangeTouch(boolean rangeTouch) {
		this.rangeTouch = rangeTouch;
		return this;
	}

	/**
	 * @return the rangeTouch
	 */
	public boolean isRangeTouch() {
		return rangeTouch;
	}

	/**
	 * @return the actionDuration
	 */
	public ActionDuration getActionDuration() {
		return actionDuration;
	}

	/**
	 * @param actionDuration
	 *            the actionDuration to set
	 */
	public Spell setCastingTime(ActionDuration actionDuration) {
		this.actionDuration = actionDuration;
		return this;
	}

	public Spell setEffectSavingThrowsAdd(SpellSavingThrow spellSavingThrow) {
		this.spellSavingThrow = spellSavingThrow;
		return this;
	}

	public Spell setEffectDuration(int effectDurationSeconds) {
		this.effectDurationSeconds = effectDurationSeconds;
		return this;
	}

	public Spell setEffectAreaType(EffectArea effectAreaType) {
		this.effectAreaType = effectAreaType;
		return this;
	}

	public Spell setEffectAreaFeet(int effectAreaFeet) {
		this.effectAreaFeet = effectAreaFeet;
		return this;
	}

	public Spell setEffectRangeBase(int effectRangeBase) {
		this.effectRangeBase = effectRangeBase;
		return this;
	}

	public Spell setEffectRangeLevelMultiplier(int effectRangeLevelMultiplier) {
		this.effectRangeLevelMultiplier = effectRangeLevelMultiplier;
		return this;
	}

	/**
	 * @return the spellComponents
	 */
	public Set<SpellComponent> getSpellComponents() {
		return spellComponents;
	}

	/**
	 * @param spellComponents
	 *            the spellComponents to set
	 */
	public Spell setSpellComponents(Set<SpellComponent> spellComponents) {
		this.spellComponents = spellComponents;
		return this;
	}

	/**
	 * @return the spellSavingThrow
	 */
	public SpellSavingThrow getSpellSavingThrow() {
		return spellSavingThrow;
	}

	/**
	 * @param spellSavingThrow
	 *            the spellSavingThrow to set
	 */
	public Spell setSpellSavingThrow(SpellSavingThrow spellSavingThrow) {
		this.spellSavingThrow = spellSavingThrow;
		return this;
	}

	/**
	 * @return the effectDurationSeconds
	 */
	public int getEffectDurationSeconds() {
		return effectDurationSeconds;
	}

	/**
	 * @param effectDurationSeconds
	 *            the effectDurationSeconds to set
	 */
	public Spell setEffectDurationSeconds(int effectDurationSeconds) {
		this.effectDurationSeconds = effectDurationSeconds;
		return this;
	}

	/**
	 * @return the effectRangeLevelMultiplier
	 */
	public int getEffectRangeLevelMultiplier() {
		return effectRangeLevelMultiplier;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public Spell setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public Spell setUrl(String url) {
		Url = url;
		return this;
	}

	/**
	 * @return the effectAreaType
	 */
	public EffectArea getEffectAreaType() {
		return effectAreaType;
	}

	/**
	 * @return the effectAreaFeet
	 */
	public int getEffectAreaFeet() {
		return effectAreaFeet;
	}

	/**
	 * @return the effectRangeBase
	 */
	public int getEffectRangeBase() {
		return effectRangeBase;
	}

	/**
	 * @param actionDuration
	 *            the actionDuration to set
	 */
	public Spell setActionDuration(ActionDuration actionDuration) {
		this.actionDuration = actionDuration;
		return this;
	}

	// Misc

	/**
	 * @return the effectSpellResistance
	 */
	public boolean isEffectSpellResistance() {
		return effectSpellResistance;
	}

	/**
	 * @param effectSpellResistance
	 *            the effectSpellResistance to set
	 */
	public void setEffectSpellResistance(boolean effectSpellResistance) {
		this.effectSpellResistance = effectSpellResistance;
	}

	public Spell componentsAdd(SpellComponent spellComponent) {
		this.spellComponents.add(spellComponent);
		return this;
	}

	public String toString() {
		return getName();
	}

	public int getEffectRange(int casterLevel) {
		return effectRangeBase + effectRangeLevelMultiplier * casterLevel;
	}

	/**
	 * A detailed description.
	 * 
	 * @return
	 */
	public String details() {
		return details("");
	}

	/**
	 * A detailed description.
	 * 
	 * @return
	 */
	public String details(String prefix) {
		StringBuilder sb = new StringBuilder(10);
		sb.append(String.format("%sName: %s%n", prefix, name));
		sb.append(String.format("%s  Components: %s%n", prefix, spellComponents));
		if (spellSavingThrow != null) {
			sb.append(String.format("%s  SavingThrow: %s%n", prefix, spellSavingThrow));
		}
		if (actionDuration != null) {
			sb.append(String.format("%s  ActionDuration: %s%n", prefix, actionDuration));
		}

		if (rangeTouch) {
			sb.append(String.format("%s  RangeTouch: Yes%n", prefix));
		}
		if (Url != null) {
			sb.append(String.format("%s  URL: %s%n", prefix, Url));
		}
		if (description != null) {
			sb.append(String.format("%s  Description: %s%n", prefix, description));
		}
		// Effect
		sb.append(String.format("%s  Effect: %n", prefix));
		if (effectDurationSeconds == 0) {
			sb.append(String.format("%s      Duration: Instantaneous%n", prefix));
		} else {
			sb.append(String.format("%s      Duration: %d seconds%n", prefix, effectDurationSeconds));
		}
		if (effectAreaType != null) {
			sb.append(String.format("%s      Area Type: %s%n", prefix, effectAreaType));
		}
		sb.append(String.format("%s      Area: %d ft%n", prefix, effectAreaFeet));
		sb.append(String.format("%s      Range Base: %d ft%n", prefix, effectRangeBase));
		sb.append(String.format("%s      Range Level Mult: %d ft%n", prefix, effectRangeLevelMultiplier));
		sb.append(String.format("%s      Spell Resistance: %s%n", prefix, effectSpellResistance));

		return sb.toString();
	}


}
