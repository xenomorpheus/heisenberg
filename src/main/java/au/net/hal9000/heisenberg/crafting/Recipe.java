package au.net.hal9000.heisenberg.crafting;

import java.util.TreeMap;
import java.util.Vector;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import au.net.hal9000.heisenberg.units.Skill;

/**
 * Recipes describe the process of crafting, spells, cooking etc.
 * 
 * <P>
 * Recipes are comprised of a list of Requirement objects ({@link Requirement}).
 * <P>
 * <P>
 * Recipes produce outcomes, eg. ({@link au.net.hal9000.heisenberg.item.Item}).
 * <P>
 * 
 * <P>
 * In order to perform a Recipe all the requirements must be met.
 * </P>
 * 
 * <h1>Examples:</h1>
 * 
 * <h2>Mundane:</h2>
 * 
 * <h3>Cook a meat pie</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for pie,
 * <li>add X pounds four,
 * <li>add X pounds meat,
 * <li>possess skill(s): cooking
 * <li>possess item(s): cooking utensils (not consumed), small fire (not
 * consumed)
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * <li>X meat pies
 * </ul>
 * 
 * 
 * <h3>Carpenter build a basic wooden chair</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for basic wooden chair,
 * <li>add X pounds of wood (consumed),
 * <li>possess items(s): carpentry tools (not consumed)
 * <li>possess skill(s): carpentry
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * <li>a basic wooden chair, and non-consumed items.
 * </ul>
 * 
 * <h3>Build small open fire:</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for small open fire,
 * <li>add X pounds of wood (consumed),
 * <li>add flint and tinder,
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * <li>a small open fire, and non-consumed items.
 * </ul>
 * 
 * <h2>Spell</h2>
 * 
 * <h3>Wizard casts small light orb</h3> Requires:
 * <ul>
 * <li>Possess spell (recipe) for small light,
 * <li>possess power words(s): as per spell.
 * <li>add X mana
 * <li>add Y action points,
 * <li>round-limit = 1 - Must be done in one round
 * </ul>
 * Produces:
 * <ul>
 * <li>a small orb that produces light for Z rounds.
 * </ul>
 * 
 * <h2>Notes</h2> The following default to being consumed when added: Action
 * points, mana.
 * 
 * <P>
 * Developer Notes: <br>
 * No need for more than one instance of a recipe.<br>
 * Lets try making Recipes immutable and see how it goes.<br>
 * TODO Consider moving Recipe to units package.<br>
 * </P>
 * 
 * @author bruins
 */
public class Recipe {

    /**
     * The identifier for this recipe.
     */
    private String id;
    /**
     * The amount of actionPoints required for this recipe.
     */
    private int actionPoints = 0;
    /**
     * Describe the recipe in terms a game player understands.
     */
    private String description;
    /**
     * The amount of Mana required for this recipe.
     */
    private int mana = 0;
    /**
     * The name given to the subroutine.
     */
    private String process;
    /**
     * What the recipe produces.
     */
    private Vector<String> products = new Vector<String>();
    /**
     * The total items required for this recipe.
     */
    private TreeMap<String, Requirement> requirements;
    /**
     * The {@link Skill} objects required.
     */
    private Set<Skill> skills = new TreeSet<Skill>();
    /**
     * 
     * @param id
     *            Identifier
     * @param description
     *            Description.
     * @param process
     *            The label for the code to run. e.g. produce new Item objects.
     * @param mana
     *            amount of mana required
     * @param actionPoints
     *            number of actionPoints required.
     * @param pRequirements
     *            the Requirement list.
     * @param pSkills
     *            the Skill objects required
     * @param pProducts
     *            the results that will be produced.
     */
    public Recipe(final String id, final String description,
            final String process, final int mana, final int actionPoints,
            final TreeMap<String, Requirement> pRequirements,
            final Set<Skill> pSkills, final Vector<String> pProducts) {
        super();
        this.id = id;
        this.description = description;
        this.process = process;
        this.mana = mana;
        this.actionPoints = actionPoints;
        this.requirements = pRequirements;
        this.skills = pSkills;
        this.products = pProducts;
    }

    // getters and setters
    // id
    /**
     * Get the ID
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

    /**
     * Set the process.
     * 
     * @param pProcess
     *            the process to set
     */
    public final void setProcess(String pProcess) {
        process = pProcess;
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
        if (requirements == null) {
            return 0;
        }
        return requirements.size();
    }

    /**
     * Get the list of Requirement objects.
     * 
     * @return the list of Requirement objects.
     */
    public final TreeMap<String, Requirement> getRequirements() {
        return requirements;
    }

    /**
     * Return the Requirement at the specified index.
     * 
     * @param key
     *            the index of the Requirement requested
     * @return the Requirement at this index.
     */
    public final Requirement getRequirement(final String key) {
        return requirements.get(key);
    }

    // products

    /**
     * @param index
     *            the index of the product we want details of.
     * @return the info for product at the given index.
     */
    public final String getProduct(final int index) {
        return products.get(index);
    }

    /**
     * @return the number of products that this recipe produces.
     */
    public final int getProductCount() {
        if (products == null) {
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
     * 
     * @return A Cooker object for this recipe.
     */
    public final Cooker getNewCooker() {
        return new Cooker(this);
    }

    /**
     * @return a description
     */
    public String toString() {
        return details();
    }

    /**
     * @return a description
     */
    public String details() {
        String string = new String("Id: " + id + "\nDescription: "
                + description + "\nProcess: " + process + "\nMana:" + mana
                + "\nAction Point(s):" + actionPoints + "\n");

        if (skills != null) {
            int index = 0;
            string += "Skill(s):\n";
            for (Iterator<Skill> itr = skills.iterator(); itr.hasNext();) {
                string += "  " + index + ": " + itr.next().toString() + "\n";
                index++;
            }
        }
        if (requirements != null) {
            string += "Requirement(s):\n";
            for (String id : requirements.keySet()) {
                Requirement requirement = requirements.get(id);
                string += "  " + id + ": " + requirement.getDescription()
                        + "\n";
            }
        }
        if (products != null) {
            int index = 0;
            string += "Product(s):\n";
            for (Iterator<String> itr = products.iterator(); itr.hasNext();) {
                string += "  " + index + ": " + itr.next() + "\n";
                index++;
            }
        }
        return string;
    }

}
