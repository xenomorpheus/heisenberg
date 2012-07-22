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

public abstract class Entity extends ItemImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A list of items ({@link Item}) stowed.
	 */
	private ItemContainer worn = new Box("Worn");
	/**
	 * A list of {@link Ingredient} list that is known by this Entity.
	 */
	private ItemContainer equipment = new Box("Equipment");
	/**
	 * A list of items ({@link Item}) worn.
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

	public void setWorn(ItemContainer worn) {
		this.worn = worn;
	}

	public ItemContainer getWorn() {
		return worn;
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
	 * Wear an item
	 * 
	 * @param item
	 */
	public void wear(ItemImpl item) {
		// TODO check the item is wearable.
		worn.add(item);
	}
	// TODO add unwear

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
		if (total == null)
			total = new Weight();
		total.add(worn.getWeight());
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
		total.add(worn.getVolume());
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
		total.add(worn.getValue());
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
	@Override
	public boolean isLeaf() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public int getChildCount() {
		// TODO consider Items in super
		int count = super.getChildCount();
		if (worn != null) {
			count++;
		}
		if (equipment != null) {
			count++;
		}
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public ItemImpl getChild(int index) {
		// Note: index is zero offset, count is not.
		int child_count = super.getChildCount();		
		// Child is on super.
        if (index < child_count){
        	return (ItemImpl)super.getChild(index);
        }
        // Make index relative to this class;
        index -= child_count;

        // worn
		if (worn != null){
			if (index == 0) {
				return worn;
			}
			index--;
		}

		// equipment
		if (equipment != null){
			if (index == 0) {
				return equipment;
			}
			index--;
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(Item child) {
		// TODO
		return -1;
	}

}
