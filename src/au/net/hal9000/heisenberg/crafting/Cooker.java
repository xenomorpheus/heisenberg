package au.net.hal9000.heisenberg.crafting;

import java.util.Vector;
import java.util.Iterator;
import java.util.Set;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.Skill;

/**
 * The Cooker takes the {@link Recipe} and ingredients and build the products. <br>
 * <br>
 * <h3>User process for Recipe:</h3><br>
 * <ol>
 * <li>Recipe requirements represented as a list of Requirement objects.
 * <li>For each requirement load a matching Item into the corresponding position
 * in the Cooker.
 * <li>Item objects that don't meet the requirements will be rejected by the
 * cooker.
 * <li>Set the chef and ensure they have the required actionPoints and mana.
 * <li>Set the newItemLocation if any Item objects will be created.
 * <li>Call cook on the cooker to perform the recipe
 * <li>Any new Item objects will be at the newItemLocation.
 * </ol>
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
     * Where to put any freshly created Items objects.
     */
    private Location newItemLocation = null;

    /**
     * Constructor
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
    public final void setChef(final PcRace chef) {
        this.chef = chef;
    }

    /**
     * Add an Item to a particular index in the list of itemsAvailable
     * 
     * @param index
     *            where to add Item
     * @param item
     * @param container
     *            where to take the item from
     * @return true iff all worked.
     */
    public final boolean setItemsAvailable(final int index, final Item item,
            final ItemContainer container) {
        // spot to add is empty
        boolean successSoFar = (index >= getContentsCount())
                || (this.getChild(index) == null);
        // item exists
        successSoFar = successSoFar && item != null;
        // container exists
        successSoFar = successSoFar && container != null;
        // not already in itemsAvailalbe
        successSoFar = successSoFar && (!this.contains(item));
        // fulfils Requirement requirement
        if (successSoFar) {
            RequirementItem requirementItem = (RequirementItem) recipe
                    .getRequirement(index);
            successSoFar = requirementItem.meetsRequirements(item);
        }
        // If conditions met then accept the item.
        if (successSoFar) {
            container.relocateItem(item, this);
        }
        return successSoFar;
    }

    // newItemLocation
    public final Location getNewItemLocation() {
        return newItemLocation;
    }

    public final void setNewItemLocation(final Location newItemLocation) {
        this.newItemLocation = newItemLocation;
    }

    // misc

    // requirements
    /**
     * Get the list of Requirement objects.
     * 
     * @return the list of Requirement objects.
     */
    public final Vector<Requirement> getRequirements() {
        return recipe.getRequirements();
    }

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
    public final int getRequirementsCount() {
        return recipe.getRequirementsCount();
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

        // powerWords
        Set<PowerWord> powerWordsRequired = recipe.getPowerWords();
        if ((powerWordsRequired != null) && (powerWordsRequired.size() > 0)) {
            if (chef == null) {
                return "Too few Power Words";
            }
            Set<PowerWord> got = chef.getPowerWords();
            if ((powerWordsRequired != null)
                    && (!got.containsAll(powerWordsRequired))) {
                return "Missing Power Words";
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
        Vector<String> products = recipe.getProducts();
        if ((products != null) && (products.size() > 0)
                && (newItemLocation == null)) {
            return "missing location for new products";
        }

        return null;
    }

    /**
     * Check that every required Requirement is matched by an Item.
     * 
     * @return null iff all requirements are met.
     */
    public String requirementsItemMet() {

        Vector<Requirement> required = getRequirements();
        // No requirements
        if (required == null) {
            return null;
        }
        int requiredSize = required.size();
        // Not enough Requirement Items.
        if (requiredSize > this.getChildCount()) {
            return "Too few ingredients";
        }
        for (int index = requiredSize - 1; index >= 0; index--) {
            IItem item = this.getChild(index);
            RequirementItem requirementItem = (RequirementItem) required
                    .get(index);
            if (!requirementItem.meetsRequirements(item)) {
                return "Missing ingredient " + index;
            }
        }
        return null;
    }

    /**
     * Remove the Ingredient objects, mana, action points etc. that are consumed.
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

        Vector<Requirement> required = recipe.getRequirements();

        if (required != null) {

            // Go in reverse so deleting items doesn't effect positions.
            for (int index = required.size() - 1; index >= 0; index--) {
                IItem item = this.getChild(index);
                RequirementItem requirementItem = (RequirementItem) required
                        .get(index);
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
        Vector<String> products = recipe.getProducts();
        if (products != null) {
            Iterator<String> itr = products.iterator();
            while (itr.hasNext()) {
                Item item = Factory.createItem(itr.next());
                newItemLocation.add(item);
            }
        }
    }

    /**
     * We will start with a restriction that all cooking is done at once. e.g.
     * we wan't allow cooking over multiple rounds.
     */
    public String cook() {
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
    public String toString() {
        return "Cooker for recipe:" + recipe.getId();
    }

    /**
     * Remove indexed Item and move to specified ItemContainer.
     * 
     * @param index
     *            index of Item to relocate.
     * @param container
     *            destination ItemContainer.
     * @return true iff successfully moved. TODO consider return type void
     */
    public boolean clearItemsAvailable(int index, ItemContainer container) {
        if (container == null) {
            throw new IllegalArgumentException("container may not be null");
        }
        IItem item = getChild(index);
        this.relocateItem(item, container);
        return true;
    }

}
