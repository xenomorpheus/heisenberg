package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.units.Skill;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Recipes describe the process of crafting, spells, cooking etc.
 *
 * <p>Recipes are comprised of a list of Requirement objects ({@link Requirement}).
 *
 * <p>Recipes produce outcomes, eg. ({@link au.net.hal9000.heisenberg.item.Item}).
 *
 * <p>In order to perform a Recipe all the requirements must be met.
 *
 * <h1>Examples:</h1>
 *
 * <h2>Mundane:</h2>
 *
 * <h3>Cook a meat pie</h3>
 *
 * <p>Requires:
 *
 * <ul>
 *   <li>Possess plan (recipe) for pie,
 *   <li>add X pounds four,
 *   <li>add X pounds meat,
 *   <li>possess skill(s): cooking
 *   <li>possess item(s): cooking utensils (not consumed), small fire (not consumed)
 *   <li>add Y action points,
 *   <li>round-limit = 20 - can work on it.
 * </ul>
 *
 * <p>Produces:
 *
 * <ul>
 *   <li>X meat pies
 * </ul>
 *
 * <h3>Carpenter build a basic wooden chair</h3>
 *
 * <p>Requires:
 *
 * <ul>
 *   <li>Possess plan (recipe) for basic wooden chair,
 *   <li>add X pounds of wood (consumed),
 *   <li>possess items(s): carpentry tools (not consumed)
 *   <li>possess skill(s): carpentry
 *   <li>add Y action points,
 *   <li>round-limit = 20 - can work on it.
 * </ul>
 *
 * <p>Produces:
 *
 * <ul>
 *   <li>a basic wooden chair, and non-consumed items.
 * </ul>
 *
 * <h3>Build small open fire:</h3>
 *
 * <p>Requires:
 *
 * <ul>
 *   <li>Possess plan (recipe) for small open fire,
 *   <li>add X pounds of wood (consumed),
 *   <li>add flint and tinder,
 *   <li>add Y action points,
 *   <li>round-limit = 20 - can work on it.
 * </ul>
 *
 * <p>Produces:
 *
 * <ul>
 *   <li>a small open fire, and non-consumed items.
 * </ul>
 *
 * <h2>Spell</h2>
 *
 * <h3>Wizard casts small light orb</h3>
 *
 * <p>Requires:
 *
 * <ul>
 *   <li>Possess spell (recipe) for small light,
 *   <li>possess power words(s): as per spell.
 *   <li>add X mana
 *   <li>add Y action points,
 *   <li>round-limit = 1 - Must be done in one round
 * </ul>
 *
 * <p>Produces:
 *
 * <ul>
 *   <li>a small orb that produces light for Z rounds.
 * </ul>
 *
 * <h2>Notes</h2>
 *
 * <p>The following default to being consumed when added: Action points, mana.
 *
 * <p>Developer Notes: <br>
 * No need for more than one instance of a recipe.<br>
 * Lets try making Recipes immutable and see how it goes.<br>
 * TODO Consider moving Recipe to units package.<br>
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class Recipe {

  /** The identifier for this recipe. */
  private final String id;

  /** The amount of actionPoints required for this recipe. */
  private final int actionPoints;

  /** Describe the recipe in terms a game player understands. */
  private final String description;

  /** The amount of Mana required for this recipe. */
  private final int mana;

  /** The name given to the subroutine. */
  private final String process;

  /** The items required for this recipe. */
  private final Map<String, Requirement> requirements;

  /** What the recipe produces. */
  private final List<Product> products;

  /** The {@link Skill} objects required. */
  private final Set<Skill> skills;

  /**
   * @param id Identifier
   * @param description Description.
   * @param process The label for the code to run. e.g. produce new Item objects.
   * @param mana amount of mana required
   * @param actionPoints number of actionPoints required.
   * @param requirements the Requirement list.
   * @param skills the Skill objects required
   * @param products the results that will be produced.
   */
  public Recipe(
      final String id,
      final String description,
      final String process,
      final int mana,
      final int actionPoints,
      final Map<String, Requirement> requirements,
      final Set<Skill> skills,
      final List<Product> products) {
    super();
    this.id = id;
    this.description = description;
    this.process = process;
    this.mana = mana;
    this.actionPoints = actionPoints;
    this.requirements = requirements;
    this.skills = skills;
    this.products = products;
  }

  // getters and setters
  // id
  /**
   * Get the ID.
   *
   * @return The short identifier for this Recipe.
   */
  public final String getId() {
    return id;
  }

  // description
  /**
   * Get the Description.
   *
   * @return the description
   */
  public final String getDescription() {
    return description;
  }

  /**
   * @return the process
   */
  public final String getProcess() {
    return process;
  }

  // mana
  /**
   * Get the mana.
   *
   * @return the amount of mana required.
   */
  public final int getMana() {
    return mana;
  }

  // actionPoints
  /**
   * Get the action points required.
   *
   * @return the action points required.
   */
  public final int getActionPoints() {
    return actionPoints;
  }

  // requirements
  /**
   * Get the count of Requirement objects.
   *
   * @return the count of Requirement objects.
   */
  public final int getRequirementCount() {
    if (null == requirements) {
      return 0;
    }
    return requirements.size();
  }

  /**
   * Get the list of Requirement objects.
   *
   * @return the list of Requirement objects.
   */
  public final Map<String, Requirement> getRequirements() {
    return requirements;
  }

  /**
   * Get the Requirement at the specified index.
   *
   * @param index the index of the Requirement requested
   * @return the Requirement at this index.
   */
  public final Requirement getRequirement(final String index) {
    return requirements.get(index);
  }

  // products

  /**
   * Get a list of Products.
   *
   * @return List of Products.
   */
  public List<Product> getProducts() {
    return products;
  }

  /**
   * Get the product.
   *
   * @param index the index of the product we want details of.
   * @return the info for product at the given index.
   */
  public final Product getProduct(final int index) {
    return products.get(index);
  }

  /**
   * Get the number of products that this recipe produces.
   *
   * @return the number of products that this recipe produces.
   */
  public final int getProductCount() {
    if (null == products) {
      return 0;
    }
    return products.size();
  }

  // skills
  /**
   * Get the count of Skill objects.
   *
   * @return a count of Skill objects
   */
  public final int getSkillCount() {
    return skills.size();
  }

  /**
   * Get the Skill objects.
   *
   * @return a set of Skill objects
   */
  public final Set<Skill> getSkills() {
    return skills;
  }

  // Misc Methods
  /**
   * A Cooker object for this recipe.
   *
   * @param chef The Entity doing the cooking
   * @return A Cooker object for this recipe.
   */
  public final Cooker getNewCooker(Item chef) {
    Cooker cooker = new Cooker(this);
    cooker.setChef(chef);
    return cooker;
  }

  /**
   * Description as a String.
   *
   * @return a description
   */
  public String toString() {
    return id;
  }

  /**
   * Description as a String.
   *
   * @return a description
   */
  String details() {
    StringBuilder string = new StringBuilder();
    string.append("Id: " + id);
    string.append(System.lineSeparator());
    string.append("Description: " + description);
    string.append(System.lineSeparator());
    string.append("Process: " + process);
    string.append(System.lineSeparator());
    string.append("Mana:" + mana);
    string.append(System.lineSeparator());
    string.append("Action Point(s):" + actionPoints);
    string.append(System.lineSeparator());

    if (null != skills) {
      int index = 0;
      string.append("Skill(s):");
      string.append(System.lineSeparator());
      for (Iterator<Skill> itr = skills.iterator(); itr.hasNext(); ) {
        string.append("  " + index + ": " + itr.next());
        string.append(System.lineSeparator());
        index++;
      }
    }
    if (null != requirements) {
      string.append("Requirement(s):");
      string.append(System.lineSeparator());
      for (Requirement requirement : requirements.values()) {
        string.append("  " + requirement.getId() + ": " + requirement.getDescription());
        string.append(System.lineSeparator());
      }
    }
    if (null != products) {
      string.append("Product(s):");
      string.append(System.lineSeparator());
      for (Product product : products) {
        string.append("  " + product.getId() + ": " + product.getDescription());
        string.append(System.lineSeparator());
      }
    }
    return string.toString();
  }
}
