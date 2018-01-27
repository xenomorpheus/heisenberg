package au.net.hal9000.heisenberg.fifthed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.fifthed.CharacterClass.CharacterClass;
import au.net.hal9000.heisenberg.fifthed.race.Race;
import au.net.hal9000.heisenberg.fifthed.item.Item;

public class PlayerCharacter {
	private String name = null;
	private Race race = null;
	private List<CharacterClass> characterClasses = new ArrayList<CharacterClass>();
	private Set<PlayerCharacterCondition> conditions = new HashSet<PlayerCharacterCondition>();
	// TODO Position reference object from which the location is measured. Eg. Room
	// or, Combat area.
	private Position location = new Position();

	// TODO assign specific slots for Item instances.
	// For now we'll assume an Item is in a valid slot.
	private Set<Item> equipped = new HashSet<Item>();

	// Inventory other than equipped Item instances.
	// private List<Item> unEquipped = new ArrayList<Item>();

	public PlayerCharacter() {
		super();
	}

	// Getters and Setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public PlayerCharacter setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public PlayerCharacter setRace(Race race) {
		this.race = race;
		return this;
	}

	/**
	 * @return the characterClasses
	 */
	public List<CharacterClass> getCharacterClasses() {
		return characterClasses;
	}

	/**
	 * @param characterClasses
	 *            the characterClasses to set
	 */
	public PlayerCharacter setCharacterClasses(List<CharacterClass> characterClasses) {
		this.characterClasses = characterClasses;
		return this;
	}

	/**
	 * @return the location
	 */
	public Position getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public PlayerCharacter setLocation(Position location) {
		this.location = location;
		return this;
	}

	/**
	 * @return the conditions
	 */
	public Set<PlayerCharacterCondition> getConditions() {
		return conditions;
	}

	/** set the Condition */
	public PlayerCharacter setConditions(Set<PlayerCharacterCondition> conditions_new) {
		conditions.clear();
		conditions.addAll(conditions_new);
		return this;
	}

	/** set the Position */
	public PlayerCharacter setPosition(Position location_new) {
		location.set(location_new);
		return this;
	}

	/**
	 * @return the equipped
	 */
	public Set<Item> getEquipped() {
		return equipped;
	}

	/**
	 * @param equipped
	 *            the equipped to set
	 */
	public PlayerCharacter setEquipped(Set<Item> equipped) {
		this.equipped = equipped;
		return this;
	}

	// Misc
	public String toString() {
		return getName();
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
		sb.append(String.format("%sRace: %s%n", prefix, race.getName()));
		sb.append(String.format("%sLevel: %d%n", prefix, getLevel()));
		if ((characterClasses != null) && (!characterClasses.isEmpty())) {
			for (CharacterClass characterClass : characterClasses) {
				sb.append(characterClass.details(prefix + "  "));
			}
		}
		if ((conditions != null) && (!conditions.isEmpty())) {
			for (PlayerCharacterCondition playerCondition : conditions) {
				sb.append(String.format("%sCondition: %s%n", prefix, playerCondition.toString()));
			}
		}
		sb.append(String.format("%sLocation (relative): %s%n", prefix, location.toString()));
		if ((equipped != null) && (!equipped.isEmpty())) {
			sb.append(String.format("%sEquipment:%n", prefix));
			for (Item item : equipped) {
				sb.append(String.format("%s  %s%n", prefix, item.getSummary()));
			}
		}
		return sb.toString();
	}

	public double distance(PlayerCharacter other) {
		return location.distance(other.location);
	}

	public Integer getLevel() {
		Integer level = 0;
		if (characterClasses != null) {
			for (CharacterClass characterClass : characterClasses) {
				level += characterClass.getLevel();
			}
		}
		return level;
	}

	/**
	 * get a list of actions the character may perform. Only one action may be chosen.
	 * @param timer
	 * @return
	 */
	public List<Action> getActions(CombatArena arena, TimerRound timer){
		List<Action> actions = new ArrayList<Action>();
		actions.addAll( race.getActions(arena, timer));
		if ((characterClasses != null) && (!characterClasses.isEmpty())) {
			for (CharacterClass characterClass : characterClasses) {
				actions.addAll( characterClass.getActions(arena, timer));
			}
		}
		return actions;
	}
}
