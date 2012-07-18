package au.net.hal9000.player.item;

import java.util.ArrayList;
import au.net.hal9000.player.item.exception.ExceptionInvalidType;
import au.net.hal9000.player.units.*;

/**
 * Entity is the bases of thinking beings.
 * 
 * 
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends ItemImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A list of items ({@link Item}) carried.
	 */
	// TODO Convert to ItemCollection
	private ArrayList<Item> equipment = new ArrayList<Item>();
	/**
	 * A list of {@link PowerWord} list that is known by this Entity.
	 */
	private ArrayList<PowerWord> powerWords = new ArrayList<PowerWord>();
	/**
	 * The {@link Spell} list that is known by this Entity.
	 */
	private ArrayList<Spell> spells = new ArrayList<Spell>();

	public Entity(String pName) {
		super(pName);
	}

	// Features
	// TODO Consider moving to a Marker Interface
	public boolean isLiving() {
		return true;
	}

	// Methods

	// Getters and Setters
	// equipment
	public void setEquipment(ArrayList<Item> equipment) {
		this.equipment = equipment;
	}

	private ArrayList<Item> getEquipment() {
		return equipment;
	}

	// powerWords
	public ArrayList<PowerWord> getPowerWords() {
		return powerWords;
	}

	public void setPowerWords(ArrayList<PowerWord> powerWords) {
		this.powerWords = powerWords;
	}

	// spells
	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		this.spells = spells;
	}

	// Misc
	/**
	 * Add item to equipment
	 * 
	 * @param item
	 */
	public void equip(Item item) {
		equipment.add(item);
	}

	/**	 <P> Get weight of the Entity, including equipment etc. </P>
	 * 
	 * @return The total weight
	 */
	@Override
	public Weight getWeight() {
		Weight total = super.getWeight();
		total.add(this.getEquipmentWeight());
		return total;
	}

	/**
	 * Eat an item
	 * 
	 * @param pFood
	 * @throws ExceptionInvalidType
	 */
	public void eat(Item pFood) throws ExceptionInvalidType {
		if (pFood.isHumanoidFood()) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	/** <P>Get the weight of just the equipment.
	 * </P>
	 * 
	 * @return The weight of the equipment
	 */
	public Weight getEquipmentWeight() {
		Weight total = new Weight();
		for (Item item : this.getEquipment()) {
			total.add(item.getWeight());
		}
		return total;
	}

}
