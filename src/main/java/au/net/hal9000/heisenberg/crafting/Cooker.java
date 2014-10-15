package au.net.hal9000.heisenberg.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
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
 * @version $Revision: 1.0 $
 */
public class Cooker extends ItemContainer {

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
    private List<Item> ingredients = new ArrayList<Item>();

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
     * 
     * @return error message or null if it worked
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public final String setItemsAvailable(final int index, final Item item)
            throws InvalidTypeException, TooHeavyException, TooLargeException {
        // item exists
        if (null == item) {
            return ITEM_MAY_NOT_BE_NULL;
        }

        // spot is free?
        if ((ingredients.size() > index) && (null != ingredients.get(index))) {
            return ALREADY_OCCUPIED;
        }
        // already in this container?
        if (this.contains(item)) {
            return ALREADY_CONTAINS_THAT_ITEM;
        }
        // is there a requirement to fulfill ?
        final RequirementItem requirementItem = (RequirementItem) recipe
                .getRequirement(index);
        if (null == requirementItem) {
            return NO_SUCH_REQUIREMENT;
        }

        // Does the Item fulfill the Requirement?
        final String rejectionReason = requirementItem.meetsRequirements(item);
        if (null != rejectionReason) {
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
     * 
     * @return the Requirement at this index.
     */
    public final Requirement getRequirement(final int index) {
        return recipe.getRequirement(index);
    }

    /**
     * Return the number of requirements.
     * 
     * 
     * @return the number of requirements.
     */
    public final int getRequirementCount() {
        return recipe.getRequirementCount();
    }

    /**
     * Used the check the requirements are met.
     * 
     * 
     * @return undef if requirements met, otherwise the reason.
     */
    private String requirementsMet() {
        final StringBuilder string = new StringBuilder(40);

        // mana
        int manaRequired = recipe.getMana();
        if (manaRequired > 0) {
            if (null == chef) {
                string.append("No chef set to supply mana");
                string.append(System.lineSeparator());
            } else if (manaRequired > chef.getMana()) {
                string.append("Not enough mana");
                string.append(System.lineSeparator());
            }
        }

        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            if (null == chef) {
                string.append("No chef set to supply action points");
                string.append(System.lineSeparator());
            } else if (actionPointsRequired > chef.getActionPoints()) {
                string.append("Not enough action points");
                string.append(System.lineSeparator());

            }
        }

        // skills
        final Set<Skill> required = recipe.getSkills();
        if ((null != required) && (required.size() > 0)) {
            if (null == chef) {
                string.append("No chef to supply skills");
                string.append(System.lineSeparator());
            } else {
                final Set<Skill> chefSkills = chef.getSkills();
                if ((null == chefSkills) || (!chefSkills.containsAll(required))) {
                    string.append("Missing Skills");
                    string.append(System.lineSeparator());
                }
            }
        }

        // Requirement
        final String requiredItems = requirementsItemMet();
        if (null != requiredItems) {
            string.append(requiredItems);
        }

        // Product objects can also have requirements.
        if (recipe.getProductCount() > 0) {
            for (Product product : recipe.getProducts()) {
                String missingRequirement = product.meetsRequirements(this);
                if (null != missingRequirement) {
                    string.append(missingRequirement);
                }
            }

        }
        if (0 == string.length()) {
            return null;
        }
        return string.toString();
    }

    /**
     * Check that every required Requirement is matched by an Item.
     * 
     * 
     * @return null iff all requirements are met.
     */
    public final String requirementsItemMet() {
        StringBuilder errors = new StringBuilder();
        int requirementCount = getRequirementCount();
        // No requirements
        if (0 == requirementCount) {
            return null;
        }
        // Not enough Requirement Items.
        if (requirementCount > ingredients.size()) {
            errors.append("Too few ingredients ");
            errors.append(requirementCount);
            errors.append(" vs ");
            errors.append(ingredients.size());
            errors.append(System.lineSeparator());
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
                    if (null != reason) {
                        errors.append("Missing/bad ingredient index " + index
                                + " because " + reason);
                        errors.append(System.lineSeparator());
                    }
                }
            }
        }
        if (0 == errors.length()) {
            return null;
        }
        return errors.toString();
    }

    /**
     * Remove the Ingredient objects, mana, action points etc. that are
     * consumed.
     */
    private void requirementsConsume() {

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
            final List<Requirement> requirements = recipe.getRequirements();
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
     * 
     * @return null if good, or error string.
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    private String createProducts() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        StringBuilder errors = new StringBuilder();
        int productCount = recipe.getProductCount();
        if (productCount > 0) {
            for (Product product : recipe.getProducts()) {
                String error = product.createProduct(this);
                if (null != error) {
                    errors.append(error);
                }
            }
        }
        if (0 == errors.length()) {
            return null;
        }
        return errors.toString();
    }

    /**
     * We will start with a restriction that all cooking is done at once. e.g.
     * we wan't allow cooking over multiple rounds.
     * 
     * @return String
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public final String cook() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        String requirementsMetError = requirementsMet();
        if (null != requirementsMetError) {
            return requirementsMetError;
        }
        requirementsConsume();
        String createProductsError = createProducts();
        if (null != createProductsError) {
            return createProductsError;
        }
        return null;
    }

    /**
     * A short description.
     * 
     * @return String
     */
    public final String toString() {
        return "Cooker for recipe:" + recipe.getId();
    }

    /**
     * Remove indexed Item and move to specified ItemContainer.
     * 
     * @param container
     *            destination ItemContainer.
     * @param index
     *            int
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public final void clearItemsAvailable(final int index,
            final ItemContainer container) throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        if (null == container) {
            throw new IllegalArgumentException("container may not be null");
        }
        Item item = ingredients.get(index);
        item.move(container);
        ingredients.remove(index);
    }

    /**
     * Method findIngredientByName.
     * 
     * @param name
     *            String
     * @return Item
     */
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
