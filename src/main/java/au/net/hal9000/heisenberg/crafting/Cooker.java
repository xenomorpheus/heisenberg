package au.net.hal9000.heisenberg.crafting;

import java.util.Set;
import java.util.TreeMap;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.PcRace;
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
 * PcRace pc = new Human("Fred the Fighter"); Cooker cooker =
 * pc.getCooker("testFireGround1"); <br>
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
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * recipe describes the process to make the products.
     */
    private Recipe recipe = null;
    /**
     * The PcRace doing the cooking. Supplies any actionPoints and mana.
     */
    private PcRace chef = null;

    /**
     * Ingredients we will cook with.
     */
    private TreeMap<String, Item> ingredients = new TreeMap<String, Item>();

    /* error messages */
    public static String itemMayNotBeNull = "item must exist";
    public static String alreadyContainsThatItem = "already contains that item";
    public static String noSuchRequirement = "no such requirement";
    public static String alreadyOccupied = "already occupied";
    public static String badKey = "bad key";

    /**
     * Constructor.
     * @param pRecipe the recipe we are cooking.
     */
    public Cooker(final Recipe pRecipe) {
        super("Cooker");
        this.recipe = pRecipe;
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
     * @param pChef the person doing the cooking.
     */
    public final void setChef(final PcRace pChef) {
        chef = pChef;
    }

    /**
     * Add an Item to a particular key in the list of itemsAvailable.
     * 
     * @param key
     *            where to add Item
     * @param item the Item we are making available.
     * @return error message or null if it worked
     */
    public final String setItemsAvailable(final String key, final Item item) {
        // item exists
        if (item == null) {
            return itemMayNotBeNull;
        }
        // bad key
        if (key == null) {
            return badKey;
        }
        // spot is free?
        if (ingredients.get(key) != null) {
            return alreadyOccupied;
        }
        // already in this container?
        if (this.contains(item)) {
            return alreadyContainsThatItem;
        }
        // is there a requirement to fulfil ?
        RequirementItem requirementItem = (RequirementItem) recipe
                .getRequirement(key);
        if (requirementItem == null) {
            return noSuchRequirement;
        }

        // Does the Item fulfil the Requirement?
        String rejectionReason = requirementItem.meetsRequirements(item);
        if (rejectionReason != null) {
            return rejectionReason;
        }

        // success
        this.add(item);
        ingredients.put(key, item);
        return null;
    }

    // misc

    // requirements
    /**
     * Return the Requirement at the specified index.
     * 
     * @param key
     *            the index of the Requirement requested
     * @return the Requirement at this index.
     */
    public final Requirement getRequirement(final String key) {
        return recipe.getRequirement(key);
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

        // mana
        int manaRequired = recipe.getMana();
        if (manaRequired > 0) {
            if (chef == null) {
                return "No chef set to supply mana";
            }
            if (manaRequired > chef.getMana()) {
                return "Not enough mana";
            }
        }

        // actionPoints
        int actionPointsRequired = recipe.getActionPoints();
        if (actionPointsRequired > 0) {
            if (chef == null) {
                return "No chef set to supply action points";
            }
            if (actionPointsRequired > chef.getActionPoints()) {
                return "Not enough action points";
            }
        }

        // skills
        Set<Skill> required = recipe.getSkills();
        if ((required != null) && (required.size() > 0)) {
            if (chef == null) {
                return "Too few skills";
            }
            Set<Skill> got = chef.getSkills();
            if ((required != null) && (!got.containsAll(required))) {
                return "Missing Skills";
            }
        }

        // Requirement - Item objects
        String requiredItems = requirementsItemMet();
        if (requiredItems != null) {
            return requiredItems;
        }

        // there needs to be a valid location for any created Items.
        if (recipe.getProductCount() > 0) {
            ItemContainer newItemLocation = (ItemContainer) ingredients
                    .get("Location");
            if (newItemLocation == null) {
                return "missing location for new products";
            }
        }

        return null;
    }

    /**
     * Check that every required Requirement is matched by an Item.
     * 
     * @return null iff all requirements are met.
     */
    public final String requirementsItemMet() {

        int requirementCount = getRequirementCount();
        // No requirements
        if (requirementCount == 0) {
            return null;
        }
        // Not enough Requirement Items.
        if (requirementCount > ingredients.size()) {
            return "Too few ingredients " + requirementCount + " vs "
                    + ingredients.size();
        }
        for (String key : recipe.getRequirements().keySet()) {
            Item item = ingredients.get(key);
            RequirementItem requirementItem = (RequirementItem) getRequirement(key);
            String reason = requirementItem.meetsRequirements(item);
            if (reason != null) {
                return "Missing/bad ingredient named " + key + " because "
                        + reason;
            }
        }
        return null;
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
            for (String key : recipe.getRequirements().keySet()) {
                Item item = ingredients.get(key);
                RequirementItem requirementItem = (RequirementItem) getRequirement(key);
                if (requirementItem.isConsumed()) {
                    // unlink from other objects
                    item.beNot();
                }
            }
        }
    }

    /**
     * Create all the products of the recipe.
     */
    private void createProducts() {
        // TODO Not final version. Only a demonstration.
        int productCount = recipe.getProductCount();
        if (productCount > 0) {
            ItemContainer newItemLocation = (ItemContainer) ingredients
                    .get("Location");
            for (int index = 0; index < productCount; index++) {
                Item item = Factory.createItem(recipe.getProduct(index));
                newItemLocation.add(item);
            }
        }
    }

    /**
     * We will start with a restriction that all cooking is done at once. e.g.
     * we wan't allow cooking over multiple rounds.
     */
    public final String cook() {
        String message = requirementsMet();
        if (message != null) {
            // System.out.println(message);
            return message;
        }
        requirementsConsume();
        createProducts();
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
    public final void clearItemsAvailable(final String key, final ItemContainer container) {
        if (container == null) {
            throw new IllegalArgumentException("container may not be null");
        }
        Item item = ingredients.get(key);
        this.relocateItem(item, container);
        ingredients.remove(key);
    }

}
