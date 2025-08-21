package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.being.Being;
import au.net.hal9000.heisenberg.units.Skill;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * The Cooker takes the {@link Recipe} and ingredients and build the products. <br>
 * <br>
 *
 * <h2>User process for Recipe:</h2>
 *
 * <br>
 *
 * <ol>
 *   <li>Recipe requirements are represented as a list of {@link Requirement} objects.
 *   <li>For each requirement, load a matching Item into the corresponding position in the Cooker.
 *   <li>Item objects that don't meet the requirements will be rejected by the cooker.
 *   <li>Set the chef and ensure they have the required actionPoints and mana.
 *   <li>Set the newItemLocation if any Item objects will be created.
 *   <li>Call cook on the cooker to perform the recipe
 *   <li>Any new Item objects will be at the newItemLocation.
 * </ol>
 *
 * <p>// Example: // Create a SmallGroundFire. <br>
 * var pc = new Human(); <br>
 * Cooker cooker = pc.getCooker("testFireGround1"); <br>
 * cooker.setItemsAvailable("Location", location); <br>
 * cooker.setItemsAvailable("FlintAndTinder", flintAndTinder); <br>
 * cooker.setItemsAvailable("Wood", wood); <br>
 * cooker.cook(); <br>
 */
public final class Cooker {

  /** error message. * */
  static final String ITEM_MAY_NOT_BE_NULL = "item must exist";

  /** error message. * */
  static final String ALREADY_CONTAINS_THAT_ITEM = "already contains that item";

  /** error message. * */
  private static final String NO_SUCH_REQUIREMENT = "no such requirement";

  /** error message. * */
  static final String ALREADY_OCCUPIED = "already occupied";

  /** error message. * */
  static final String FAILED_TO_ADD = "failed to add";

  /** recipe describes the process to make the products. */
  private Recipe recipe = null;

  private Location pot = new Location();

  /** The CharacterSheet doing the cooking. Supplies any actionPoints and mana. */
  private Being chef = null;

  /** Ingredients we will cook with. */
  private Map<String, Item> ingredients = new TreeMap<>();

  /**
   * Constructor.
   *
   * @param recipe the recipe we are cooking.
   */
  Cooker(final Recipe recipe) {
    super();
    this.recipe = recipe;
  }

  // Setters and Getters

  /**
   * set the chef.
   *
   * @param chef the person doing the cooking.
   */
  public void setChef(final Being chef) {
    this.chef = chef;
  }

  /**
   * get the chef.
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
   * @param key where to add Item
   * @param item the Item we are making available.
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
    if (pot.contains(item)) {
      throw new RuntimeException(ALREADY_CONTAINS_THAT_ITEM + ": " + item);
    }
    // is there a requirement to fulfill ?
    final RequirementItem requirementItem = (RequirementItem) recipe.getRequirement(key);
    if (null == requirementItem) {
      throw new RuntimeException(NO_SUCH_REQUIREMENT + ": " + key);
    }

    // Does the Item fulfill the Requirement?
    final String rejectionReason = requirementItem.meetsRequirements(item);
    if (null != rejectionReason) {
      throw new RuntimeException(rejectionReason);
    }

    // success
    pot.add(item);
    ingredients.put(key, item);
  }

  // requirements
  /**
   * Return the Requirement at the specified index.
   *
   * @param key the index of the Requirement requested
   * @return the Requirement at this index.
   */
  final Requirement getRequirement(final String key) {
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
   * Get the list of Requirement objects.
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
      if (null == chef) {
        string.append("No chef set to supply mana" + System.lineSeparator());
      } else if (manaRequired > chef.getPlayableState().getMana()) {
        string.append("Not enough mana" + System.lineSeparator());
      }
    }

    // actionPoints
    int actionPointsRequired = recipe.getActionPoints();
    if (actionPointsRequired > 0) {
      if (null == chef) {
        string.append("No chef set to supply action points" + System.lineSeparator());
      } else if (actionPointsRequired > chef.getPlayableState().getActionPoints()) {
        string.append("Not enough action points" + System.lineSeparator());
      }
    }

    // skills
    final Set<Skill> required = recipe.getSkills();
    if ((null != required) && (required.size() > 0)) {
      if (chef instanceof Being) {
        final Set<Skill> chefSkills = chef.getCharacterSheet().getSkills();
        if ((null == chefSkills) || (!chefSkills.containsAll(required))) {
          string.append("Missing Skills" + System.lineSeparator());
        }
      } else {
        string.append("No chef to supply skills" + System.lineSeparator());
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
      errors.append(
          "Too few ingredients "
              + requirementCount
              + " vs "
              + ingredients.size()
              + System.lineSeparator());
    }
    Map<String, Requirement> requirements = recipe.getRequirements();
    for (String key : requirements.keySet()) {
      Requirement requirement = requirements.get(key);
      if (requirement instanceof RequirementItem) {
        RequirementItem requirementItem = (RequirementItem) requirement;
        Item item = ingredients.get(key);
        String reason = requirementItem.meetsRequirements(item);
        if (null != reason) {
          errors.append(
              "Missing/bad ingredient for requirement "
                  + key
                  + " because "
                  + reason
                  + System.lineSeparator());
        }
      }
    }
    if (0 == errors.length()) {
      return null;
    }
    return errors.toString();
  }

  /** Remove the Ingredient objects, mana, action points etc. that are consumed. */
  private void requirementsConsume() {

    // mana
    int manaRequired = recipe.getMana();
    if (manaRequired > 0) {
      chef.getPlayableState().manaAdjust(-1 * manaRequired);
    }
    // actionPoints
    int actionPointsRequired = recipe.getActionPoints();
    if (actionPointsRequired > 0) {
      chef.getPlayableState().actionPointsAdjust(-1 * actionPointsRequired);
    }

    int requirementCount = recipe.getRequirementCount();
    if (requirementCount > 0) {
      final Map<String, Requirement> requirements = recipe.getRequirements();
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
   * Create all the products of the recipe.<br>
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
   * We will start with a restriction that all cooking is done at once e.g. we won't allow cooking
   * over multiple rounds.
   *
   * <p>Throws RuntimeException on failure.
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
   * @param container destination ItemContainer.
   * @param key the ID of the requirement.
   */
  final void clearItemsAvailable(final String key, final ItemContainer container) {
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
   * @param name String
   * @return Item
   */
  Item findIngredientByName(final String name) {
    return ingredients.get(name);
  }

  public void empty(Location destination) {
    pot.empty(destination);
  }
}
