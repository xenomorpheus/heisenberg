package au.net.hal9000.heisenberg.crafting;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
    static final String ITEM_MAY_NOT_BE_NULL = "item must exist";
    /** error message. **/
    static final String ALREADY_CONTAINS_THAT_ITEM = "already contains that item";
    /** error message. **/
    private static final String NO_SUCH_REQUIREMENT = "no such requirement";
    /** error message. **/
    static final String ALREADY_OCCUPIED = "already occupied";
    /** error message. **/
    static final String FAILED_TO_ADD = "failed to add";

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
    private Item chef = null;

    /**
     * Ingredients we will cook with.
     */
    private Map<String, Item> ingredients = new TreeMap<>();

    /**
     * Constructor.
     * 
     * @param recipe
     *            the recipe we are cooking.
     */
    Cooker(final Recipe recipe) {
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
    public void setChef(final Item chef) {
        this.chef = chef;
    }

    /**
     * get the chef.
     * 
     * 
     * @return the person doing the cooking.
     */
    public final Item getChef() {
        return chef;
    }

    // misc

    /**
     * Add an Item to a particular key in the list of itemsAvailable.
     * 
     * @param key
     *            where to add Item
     * @param item
     *            the Item we are making available.
     * 
     */
    public final void setItemsAvailable(final String key, final Item item) {
        // item exists
        if (null == item) {
            throw new RuntimeException(ITEM_MAY_NOT_BE_NULL);
        }

        // spot is free?
        if (null != ingredients.get(key)) {
            throw new RuntimeException(ALREADY_OCCUPIED);
        }
        // already in this container?
        if (this.contains(item)) {
            throw new RuntimeException(ALREADY_CONTAINS_THAT_ITEM);
        }
        // is there a requirement to fulfil ?
        final RequirementItem requirementItem = (RequirementItem) recipe
                .getRequirement(key);
        if (null == requirementItem) {
            throw new RuntimeException(NO_SUCH_REQUIREMENT);
        }

        // Does the Item fulfil the Requirement?
        final String rejectionReason = requirementItem.meetsRequirements(item);
        if (null != rejectionReason) {
            throw new RuntimeException(rejectionReason);
        }

        // success
        try {
            this.add(item);
        } catch (TooHeavyException | TooLargeException | InvalidTypeException e) {
            throw new RuntimeException(FAILED_TO_ADD, e);
        }
        ingredients.put(key, item);
    }

    // requirements
    /**
     * Return the Requirement at the specified index.
     * 
     * @param key
     *            the index of the Requirement requested
     * 
     * @return the Requirement at this index.
     */
    final Requirement getRequirement(final String key) {
        return recipe.getRequirement(key);
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
     * Get the list of Requirement objects.
     * 
     * 
     * @return the list of Requirement objects.
     */
    public final Map<String, Requirement> getRequirements() {
        return recipe.getRequirements();
    }

    /**
     * Used the check the requirements are met.<br>
     * Throws runtime exception on failure.
     */
    private void requirementsMet() {
        final StringBuilder string = new StringBuilder(40);

        // mana
        int manaRequired = recipe.getMana();
        if (manaRequired > 0) {
            if (chef instanceof Entity) {
                Entity entity = (Entity) chef;
                if (manaRequired > entity.getMana()) {
                    string.append("Not enough mana");
                    string.append(System.lineSeparator());
                }
            } else {
                string.append("No chef set to supply mana");
                string.append(System.lineSeparator());
            }
        }

        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            if (chef instanceof Entity) {
                Entity entity = (Entity) chef;
                if (actionPointsRequired > entity.getActionPoints()) {
                    string.append("Not enough action points");
                    string.append(System.lineSeparator());
                }
            } else {
                string.append("No chef set to supply action points");
                string.append(System.lineSeparator());
            }
        }

        // skills
        final Set<Skill> required = recipe.getSkills();
        if ((null != required) && (required.size() > 0)) {
            if (chef instanceof Entity) {
                Entity entity = (Entity) chef;
                final Set<Skill> chefSkills = entity.getSkills();
                if ((null == chefSkills) || (!chefSkills.containsAll(required))) {
                    string.append("Missing Skills");
                    string.append(System.lineSeparator());
                }
            } else {
                string.append("No chef to supply skills");
                string.append(System.lineSeparator());
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
        if (0 != string.length()) {
            throw new RuntimeException(string.toString());
        }
    }

    /**
     * Check that every required Requirement is matched by an Item.
     * 
     * 
     * @return null IFF all requirements are met.
     */
    private final String requirementsItemMet() {
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
        Map<String, Requirement> requirements = recipe.getRequirements();
        for (String key : requirements.keySet()) {
            Requirement requirement = requirements.get(key);
            if (requirement instanceof RequirementItem) {
                RequirementItem requirementItem = (RequirementItem) requirement;
                Item item = ingredients.get(key);
                String reason = requirementItem.meetsRequirements(item);
                if (null != reason) {
                    errors.append("Missing/bad ingredient for requirement "
                            + key + " because " + reason);
                    errors.append(System.lineSeparator());
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
            Entity entity = (Entity) chef;
            entity.manaAdjust(-1 * manaRequired);
        }
        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            Entity entity = (Entity) chef;
            entity.actionPointsAdjust(-1 * actionPointsRequired);
        }

        int requirementCount = recipe.getRequirementCount();

        if (requirementCount > 0) {
            final Map<String, Requirement> requirements = recipe
                    .getRequirements();
            for (String key : requirements.keySet()) {
                Requirement requirement = requirements.get(key);
                if (requirement instanceof RequirementItem) {
                    RequirementItem requirementItem = (RequirementItem) requirement;
                    if (requirementItem.isConsumed()) {
                        // unlink from other objects
                        Item item = ingredients.get(key);
                        item.beNot();
                    }
                }
            }
        }
    }

    /**
     * Create all the products of the recipe.<BR>
     * 
     * Throws RuntimeException on failure.
     */
    private void createProducts() {
        int productCount = recipe.getProductCount();
        if (productCount > 0) {
            for (Product product : recipe.getProducts()) {
                product.createProduct(this);
            }
        }
    }

    /**
     * We will start with a restriction that all cooking is done at once. e.g.
     * we wan't allow cooking over multiple rounds.
     * 
     * Throws RuntimeException on failure.
     */
    public final void cook() {
        requirementsMet();
        requirementsConsume();
        createProducts();
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
     * @param key
     *            the ID of the requirement.
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    final void clearItemsAvailable(final String key,
            final ItemContainer container) throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        if (null == container) {
            throw new IllegalArgumentException("container may not be null");
        }
        Item item = ingredients.get(key);
        item.move(container);
        ingredients.remove(key);
    }

    /**
     * Method findIngredientByName.
     * 
     * @param name
     *            String
     * @return Item
     */
    Item findIngredientByName(final String name) {
        return ingredients.get(name);
    }

}
