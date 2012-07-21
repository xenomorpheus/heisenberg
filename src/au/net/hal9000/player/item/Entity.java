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
	 * A list of {@link Ingredient} list that is known by this Entity.
	 */
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	/**
	 * The {@link Recipe} list that is known by this Entity.
	 */
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

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
	public void equip(ItemImpl item) {
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
	public void eat(Item pFood) throws ExceptionInvalidType {
		if (pFood.isHumanoidFood()) {
			throw new ExceptionInvalidType(this.getName() + " can't eat "
					+ pFood.getName());
		}
		pFood.beNot();
	}

	// Traverse Tree
	/** {@inheritDoc} */
	public boolean isLeaf() {
		return (equipment == null) || (this.equipment.getChildCount() == 0);
	}

	/** {@inheritDoc} */
	public int getChildCount() {
		if (equipment != null) {
			return equipment.getChildCount();
		}
		return 0;
	}

	/** {@inheritDoc} */
	public ItemImpl getChild(int index) {
		return equipment.getChild(index);
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(Item child) {
		// TODO
		return -1;
	}

}
