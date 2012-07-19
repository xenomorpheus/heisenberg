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
	private ItemContainer equipment = new Bag();
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
	public void setEquipment(ItemContainer equipment) {
		this.equipment = equipment;
	}

	public ItemContainer getEquipment() {
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
	public void equip(ItemImpl item) {
		equipment.add(item);
	}

	/**	 <P> Get weight of the Entity, including equipment etc. </P>
	 * 
	 * @return The total weight
	 */
	@Override
	public Weight getWeight() {
		Weight total = super.getWeight();
		total.add(equipment.getWeight());
		return total;
	}

	/**	 <P> Get volume of the Entity, including equipment etc. </P>
	 * 
	 * @return The total volume
	 */
	@Override
	public Volume getVolume() {
		Volume total = super.getVolume();
		total.add(equipment.getVolume());
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

}
