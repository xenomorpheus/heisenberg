package au.net.hal9000.heisenberg.crafting;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.units.Skill;

/**
 * The Cooker takes the {@link Recipe} and ingredients and build the products. <br>
 * <br>
 * <h3>User process for Recipe:</h3><br>
 * <ol>
 * <li>Recipe requirements are represented as a list of {@link Requirement}
 * objects.
 * <li>For each requirement, load a matching Item into the corresponding
 * position in the Cooker.
 * <li>Item objects that don't meet the requirements will be rejected by the
 * cooker.
 * <li>Set the chef and ensure they have the required actionPoints and mana.
 * <li>Set the newItemLocation if any Item objects will be created.
 * <li>Call cook on the cooker to perform the recipe
 * <li>Any new Item objects will be at the newItemLocation.
 * </ol>
 * 
 * // Example: // Create a SmallGroundFire. <br>
 * PcRace pc = new Human("Fred the Fighter"); <br>
 * Cooker cooker = pc.getCooker("testFireGround1"); <br>
 * cooker.setItemsAvailable("Location", location); <br>
 * cooker.setItemsAvailable("FlintAndTinder", flintAndTinder); <br>
 * cooker.setItemsAvailable("Wood", wood); <br>
 * cooker.cook(); <br>
 * 
 * 
 * @author bruins
 * 
 */
public class Cooker extends ItemContainer {

    /**
     * serial version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * recipe describes the process to make the products.
     */
    private Recipe recipe = null;
    /**
     * The PcRace doing the cooking. Supplies any actionPoints and mana.
     */
    private Entity chef = null;

    /**
     * Ingredients we will cook with.
     */
    private List<Item> ingredients = new Vector<Item>();

    /** error message. **/
    public static final String ITEM_MAY_NOT_BE_NULL = "item must exist";
    /** error message. **/
    public static final String ALREADY_CONTAINS_THAT_ITEM = "already contains that item";
    /** error message. **/
    public static final String NO_SUCH_REQUIREMENT = "no such requirement";
    /** error message. **/
    public static final String ALREADY_OCCUPIED = "already occupied";
    /** error message. **/
    public static final String BAD_KEY = "bad key";

    /**
     * Constructor.
     * 
     * @param recipe
     *            the recipe we are cooking.
     */
    public Cooker(final Recipe recipe) {
        super("Cooker");
        this.recipe = recipe;
        /**
         * There may be an exploit in ability to hold items of unlimited weight
         * or volume. <br>
         * Mitigation: Sometimes Ingredients are be checked on entry, but not
         * all methods do. <br>
         * Mitigation: Location of Cooker still needs to be able to hold the
         * weight and volume. But currently there is an exploit when adding to a
         * bag inside a bag.<br>
         */
        this.setVolumeMax(-1f);
        this.setWeightMax(-1f);
    }

    // Setters and Getters

    /**
     * set the chef.
     * 
     * @param chef
     *            the person doing the cooking.
     */
    public void setChef(final Entity chef) {
        this.chef = chef;
    }

    /**
     * get the chef.
     * 
     * @return the person doing the cooking.
     */
    public final Entity getChef() {
        return chef;
    }

    // misc

    /**
     * Add an Item to a particular key in the list of itemsAvailable.
     * 
     * @param index
     *            where to add Item
     * @param item
     *            the Item we are making available.
     * @return error message or null if it worked
     */
    public final String setItemsAvailable(final int index, final Item item) {
        // item exists
        if (item == null) {
            return ITEM_MAY_NOT_BE_NULL;
        }

        // spot is free?
        if ((ingredients.size() > index) && (ingredients.get(index) != null)) {
            return ALREADY_OCCUPIED;
        }
        // already in this container?
        if (this.contains(item)) {
            return ALREADY_CONTAINS_THAT_ITEM;
        }
        // is there a requirement to fulfill ?
        RequirementItem requirementItem = (RequirementItem) recipe
                .getRequirement(index);
        if (requirementItem == null) {
            return NO_SUCH_REQUIREMENT;
        }

        // Does the Item fulfill the Requirement?
        String rejectionReason = requirementItem.meetsRequirements(item);
        if (rejectionReason != null) {
            return rejectionReason;
        }

        // success
        this.add(item);
        ingredients.add(index, item);
        return null;
    }

    // requirements
    /**
     * Return the Requirement at the specified index.
     * 
     * @param index
     *            the index of the Requirement requested
     * @return the Requirement at this index.
     */
    public final Requirement getRequirement(final int index) {
        return recipe.getRequirement(index);
    }

    /**
     * Return the number of requirements.
     * 
     * @return the number of requirements.
     */
    public final int getRequirementCount() {
        return recipe.getRequirementCount();
    }

    /**
     * Used the check the requirements are met.
     * 
     * @return undef if requirements met, otherwise the reason.
     */
    private final String requirementsMet() {
        StringBuilder string = new StringBuilder();

        // mana
        int manaRequired = recipe.getMana();
        if (manaRequired > 0) {
            if (chef == null) {
                string.append("No chef set to supply mana\n");
            } else if (manaRequired > chef.getMana()) {
                string.append("Not enough mana\n");
            }
        }

        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            if (chef == null) {
                string.append("No chef set to supply action points\n");
            } else if (actionPointsRequired > chef.getActionPoints()) {
                string.append("Not enough action points\n");
            }
        }

        // skills
        Set<Skill> required = recipe.getSkills();
        if ((required != null) && (required.size() > 0)) {
            if (chef == null) {
                string.append("No chef to supply skills\n");
            } else {
                Set<Skill> chefSkills = chef.getSkills();
                if ((chefSkills == null) || (!chefSkills.containsAll(required))) {
                    string.append("Missing Skills\n");
                }
            }
        }

        // Requirement
        String requiredItems = requirementsItemMet();
        if (requiredItems != null) {
            string.append(requiredItems);
        }

        // Product objects can also have requirements.
        if (recipe.getProductCount() > 0) {
            for (Product product : recipe.getProducts()) {
                String missingRequirement = product.meetsRequirements(this);
                if (missingRequirement != null) {
                    string.append(missingRequirement);
                }
            }

        }
        if (string.length() == 0) {
            return null;
        }
        return string.toString();
    }

    /**
     * Check that every required Requirement is matched by an Item.
     * 
     * @return null iff all requirements are met.
     */
    public final String requirementsItemMet() {
        StringBuilder errors = new StringBuilder();
        int requirementCount = getRequirementCount();
        // No requirements
        if (requirementCount == 0) {
            return null;
        }
        // Not enough Requirement Items.
        if (requirementCount > ingredients.size()) {
            errors.append("Too few ingredients " + requirementCount + " vs "
                    + ingredients.size() + "\n");
        }
        List<Requirement> requirements = recipe.getRequirements();
        for (int index = 0; index < requirements.size(); index++) {
            Requirement requirement = requirements.get(index);
            if (requirement instanceof RequirementItem) {
                RequirementItem requirementItem = (RequirementItem) requirement;
                if (ingredients.size() - 1 < index) {
                    errors.append("Missing ingredient index " + index);
                } else {
                    Item item = ingredients.get(index);
                    String reason = requirementItem.meetsRequirements(item);
                    if (reason != null) {
                        errors.append("Missing/bad ingredient index " + index
                                + " because " + reason + "\n");
                    }
                }
            }
        }
        if (errors.length() == 0) {
            return null;
        }
        return errors.toString();
    }

    /**
     * Remove the Ingredient objects, mana, action points etc. that are
     * consumed.
     */
    private final void requirementsConsume() {

        // mana
        int manaRequired = recipe.getMana();
        if (manaRequired > 0) {
            chef.manaAdjust(-1 * manaRequired);
        }
        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            chef.actionPointsAdjust(-1 * actionPointsRequired);
        }

        int requirementCount = recipe.getRequirementCount();

        if (requirementCount > 0) {
            List<Requirement> requirements = recipe.getRequirements();
            for (int i = 0; i < requirements.size(); i++) {
                Requirement requirement = requirements.get(i);
                if (requirement instanceof RequirementItem) {
                    RequirementItem requirementItem = (RequirementItem) requirement;
                    if (requirementItem.isConsumed()) {
                        // unlink from other objects
                        Item item = ingredients.get(i);
                        item.beNot();
                    }
                }
            }
        }
    }

    /**
     * Create all the products of the recipe.
     * 
     * @return null if good, or error string.
     */
    private String createProducts() {
        StringBuilder errors = new StringBuilder();
        int productCount = recipe.getProductCount();
        if (productCount > 0) {
            for (Product product : recipe.getProducts()) {
                String error = product.createProduct(this);
                if (error != null) {
                    errors.append(error);
                }
            }
        }
        if (errors.length() == 0) {
            return null;
        }
        return errors.toString();
    }

    /**
     * We will start with a restriction that all cooking is done at once. e.g.
     * we wan't allow cooking over multiple rounds.
     */
    public final String cook() {
        String requirementsMetError = requirementsMet();
        if (requirementsMetError != null) {
            return requirementsMetError;
        }
        requirementsConsume();
        String createProductsError = createProducts();
        if (createProductsError != null) {
            return createProductsError;
        }
        return null;
    }

    /**
     * A short description.
     */
    public final String toString() {
        return "Cooker for recipe:" + recipe.getId();
    }

    /**
     * Remove indexed Item and move to specified ItemContainer.
     * 
     * @param container
     *            destination ItemContainer.
     */
    public final void clearItemsAvailable(final int index,
            final ItemContainer container) {
        if (container == null) {
            throw new IllegalArgumentException("container may not be null");
        }
        Item item = ingredients.get(index);
        item.move(container);
        ingredients.remove(index);
    }

    public Item findIngredientByName(final String name) {
        for (int index = getRequirementCount() - 1; index >= 0; index--) {
            Requirement requirement = getRequirement(index);
            if (name.equals(requirement.getId())) {
                return ingredients.get(index);
            }
        }
        return null;
    }

}
