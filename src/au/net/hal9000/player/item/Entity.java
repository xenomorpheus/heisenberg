package au.net.hal9000.player.item;

import java.util.ArrayList;
import au.net.hal9000.player.item.exception.ExceptionInvalidType;
import au.net.hal9000.player.units.*;

/**
 * Entity is the bases of thinking beings.
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public Entity(String pName) {
		super(pName);
	}

	// Fields
	/**
	 * A list of equipment items ({@link IItem}).
	 */
	private ItemContainer equipment = new Box("Equipment");
	/**
	 * A list of {@link Ingredient} list that is known by this Entity.
	 */
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	/**
	 * The {@link Recipe} list that is known by this Entity.
	 */
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	// Features
	// TODO Consider moving to a Marker Interface
	/** {@inheritDoc} */
	@Override
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

	// ingredients
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	// recipes
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
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

	/**
	 * <P>
	 * Get weight of the Entity, including equipment etc.
	 * </P>
	 * 
	 * @return The total weight
	 */
	@Override
	public Weight getWeight() {
		Weight total = super.getWeight();
		if (total == null)
			total = new Weight();
		total.add(equipment.getWeight());
		return total;
	}

	/**
	 * <P>
	 * Get volume of the Entity, including equipment etc.
	 * </P>
	 * 
	 * @return The total volume
	 */
	@Override
	public Volume getVolume() {
		Volume total = super.getVolume();
		if (total == null)
			total = new Volume();
		total.add(equipment.getVolume());
		return total;
	}

	/**
	 * <P>
	 * Get value of the Entity, including equipment etc.
	 * </P>
	 * 
	 * @return The total value
	 */
	@Override
	public Currency getValue() {
		Currency total = super.getValue();
		total.add(equipment.getValue());
		return total;
	}

	/**
	 * Eat an item
	 * 
	 * @param pFood
	 * @throws ExceptionInvalidType
	 */
	public void eat(IItem pFood) throws ExceptionInvalidType {
		if (!pFood.isHumanoidFood()) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	// Traverse Tree
	/** {@inheritDoc} */
	@Override
	public boolean isLeaf() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public int getChildCount() {
		// TODO consider Items in super
		int count = super.getChildCount();
		if (equipment != null) {
			count++;
		}
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public Item getChild(int index) {
		// Note: index is zero offset, count is not.
		int child_count = super.getChildCount();
		// Child is on super.
		if (index < child_count) {
			return (Item) super.getChild(index);
		}
		// Make index relative to this class;
		index -= child_count;

		// equipment
		if (equipment != null) {
			if (index == 0) {
				return equipment;
			}
			index--;
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(IItem child) {
		// TODO
		return -1;
	}

}
