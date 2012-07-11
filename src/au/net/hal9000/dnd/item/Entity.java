package au.net.hal9000.dnd.item;

import java.util.Stack;
import au.net.hal9000.dnd.item.exception.ExceptionInvalidType;
import au.net.hal9000.dnd.units.*;

public abstract class Entity extends ItemImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Stack<Item> equipment = new Stack<Item>();

	public Entity(String pName) {
		super(pName);
	}

	// public boolean Living() {
	// return true;
	// }

	// Features
	public boolean isLiving() {
		return true;
	}

	// Getters and Setters
	private Stack<Item> getEquipment() {
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
			total.add( item.getWeight());
		}
		return total;
	}

	public Weight getWeight() {
		Weight total = super.getWeight();
		total.add( this.getEquipmentWeight());
		return total;
	}

}
