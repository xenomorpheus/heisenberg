package au.net.hal9000.heisenberg.fifthed.spell;

import java.util.HashSet;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.ActionDuration;

public abstract class Spell {
	private String name = null;
	private Set<SpellComponent> spellComponents = new HashSet<SpellComponent>();
	private boolean rangeTouch = false;
	private ActionDuration actionDuration = null;
	private SpellSavingThrows spellSavingThrow;
	private int effectDurationSeconds = 0;
	private EffectArea effectAreaType = null;
	private int effectAreaFeet = 0;
	private int effectRangeBase = 0;
	private int effectRangeLevelMultiplier = 0;
	private String description;
	private String Url;

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

	public Spell setEffectSavingThrowsAdd(SpellSavingThrows spellSavingThrow) {
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
	public SpellSavingThrows getSpellSavingThrow() {
		return spellSavingThrow;
	}

	/**
	 * @param spellSavingThrow
	 *            the spellSavingThrow to set
	 */
	public Spell setSpellSavingThrow(SpellSavingThrows spellSavingThrow) {
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
		if (rangeTouch) {
			sb.append(String.format("%sRangeTouch: Yes%n", prefix));
		}
		return sb.toString();
	}

}
