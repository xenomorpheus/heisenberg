package au.net.hal9000.player.item;

import java.util.ArrayList;
import au.net.hal9000.player.item.exception.ExceptionInvalidType;
import au.net.hal9000.player.units.*;

/** Entity is the bases of thinking beings.
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

	// Getters and Setters
	private ArrayList<Item> getEquipment() {
		return equipment;
	}

	// Methods
	public void equip(Item item) {
		equipment.add(item);
	}

	public void eat(Item pFood) throws ExceptionInvalidType {
		if (pFood.isHumanoidFood()) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	// Warning
	// Use getWeight() to get total including contents.
	// Magic containers will override getWeight().
	public Weight getEquipmentWeight() {
		Weight total = new Weight();
		for (Item item : this.getEquipment()) {
			total.add(item.getWeight());
		}
		return total;
	}

	public Weight getWeight() {
		Weight total = super.getWeight();
		total.add(this.getEquipmentWeight());
		return total;
	}

	public ArrayList<PowerWord> getPowerWords() {
		return powerWords;
	}

	public void setPowerWords(ArrayList<PowerWord> powerWords) {
		this.powerWords = powerWords;
	}

}
