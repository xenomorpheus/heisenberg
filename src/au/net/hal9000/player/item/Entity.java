package au.net.hal9000.player.item;

import java.util.ArrayList;
import au.net.hal9000.player.item.exception.ExceptionInvalidType;
import au.net.hal9000.player.units.*;
import au.net.hal9000.player.item.property.HumanoidFood;
import au.net.hal9000.player.item.property.Living;

/**
 * Entity is the bases of thinking beings.
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends Item implements Living{

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
	public float getWeight() {
		float total = super.getWeight();
		total += equipment.getWeight();
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
	public float getVolume() {
		float total = super.getVolume();
		total += equipment.getVolume();
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
		if (!( pFood instanceof HumanoidFood)) {
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
		// TODO getIndexOfChild
		return -1;
	}

	// Find items that match the criteria
	/** {@inheritDoc} */
	@Override
	public void accept(ItemVisitor visitor) {
		// TODO visit equipment then super
		visitor.visit(this);
	}

}
