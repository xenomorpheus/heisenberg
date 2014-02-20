package au.net.hal9000.heisenberg.item;

import java.util.Set;
import java.util.TreeSet;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.Configuration;

/**
 * Entity is the bases of conscious entities. <br>
 * May living or undead.
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends ItemContainer {

    /** serial version. */
    private static final long serialVersionUID = 1L;

    /** ideal value for health metric. */
    private static final float HEALTH_METRIC_IDEAL = 100L;

    /**
     * A measure of the amount of things the entity can do in the current round.
     * This is the remaining, not the maximum.
     */
    private int actionPoints;
    /**
     * The size of the magic user's fuel tank. This is the remaining, not the
     * maximum.
     */
    private int mana;
    /**
     * This object has a list of {@link au.net.hal9000.heisenberg.util.AbilityScore} objects.<br>
     * 
     * To set up, just work through the list on the {@link PcRace}.
     * 
     * Create a new AbilityScore object for each one on PC. Set the modifier to
     * 0. Set the name = pcClassAbility.getName()
     * 
     * Call the reCalculateAllAbilityScores() function<br>
     * Set the value = pcClassAbility.getValue() + (level *
     * pcClassAbility.getModifier()) + modifier;
     */
    private String gender;
    /**
     * The {@link au.net.hal9000.heisenberg.crafting.Recipe} list that is known by this object.
     */
    private Set<String> recipes;
    /**
     * The {@link Skill} objects required.
     */
    private Set<Skill> skills;
    private String size;

    // Constructor
    public Entity(String name, String description) {
        super(name, description);
        // By default PCs are living, but this may be changed at any time.
        ItemProperty.setLiving(this, true);
        ItemProperty.setAeration(this, HEALTH_METRIC_IDEAL);
        ItemProperty.setEntertainment(this, HEALTH_METRIC_IDEAL);
        ItemProperty.setHydration(this, HEALTH_METRIC_IDEAL);
        ItemProperty.setNourishment(this, HEALTH_METRIC_IDEAL);
        ItemProperty.setRest(this, HEALTH_METRIC_IDEAL);
    }

    public Entity(String name) {
        this(name, "");
    }

    // Methods

    // Getters and Setters
    /**
     * @return the actionPoints
     */
    public final int getActionPoints() {
        return actionPoints;
    }

    /**
     * @param actionPoints
     *            the actionPoints to set
     */
    public final void setActionPoints(final int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * Adjust the amount of action points of this item.
     * 
     * @param adjust
     *            amount to adjust by.
     */
    public void actionPointsAdjust(final int adjust) {
        actionPoints += adjust;
    }

    /**
     * @return the gender
     */
    public final String getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public final void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the mana
     */
    public final int getMana() {
        return mana;
    }

    /**
     * @param mana
     *            the mana to set
     */
    public final void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Adjust the amount of mana.
     * 
     * @param adjust
     *            amount to adjust by.
     */
    public void manaAdjust(int adjust) {
        mana += adjust;
    }

    // Recipe objects
    /**
     * Get the Recipe objects this PcRace object knows.
     * 
     * @return the set of Recipe objects
     */
    public Set<String> getRecipes() {
        return recipes;
    }

    /**
     * Set the Recipe objects this PcRace object knows.
     * 
     * @param recipes
     *            the set of Recipe objects
     */
    public void setRecipes(Set<String> recipes) {
        this.recipes = recipes;
    }

    /**
     * Add to the of Recipe objects this PcRace object knows.
     * 
     * @param recipeId
     *            a Recipe id
     */
    public void recipeAdd(String recipeId) {
        if (recipes == null) {
            recipes = new TreeSet<String>();
        }
        this.recipes.add(recipeId);
    }

    /**
     * The PcRace has learnt new Recipe(s).
     * 
     * @param newRecipes
     *            an array of Recipe IDs to add.
     */
    public final void recipesAdd(final String[] newRecipes) {
        for (String recipeId : newRecipes) {
            recipeAdd(recipeId);
        }
    }

    /**
     * @return the size
     */
    public final String getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public final void setSize(String size) {
        this.size = size;
    }

    // Skills
    /**
     * Get the Skill objects.
     * 
     * @return a set of Skill objects
     */
    public final Set<Skill> getSkills() {
        return skills;
    }

    /**
     * Set the skills of this entity.
     * 
     * @param skills
     *            skills to set.
     */
    public final void setSkills(final Set<Skill> skills) {
        this.skills = skills;
    }

    /**
     * The PcRace object has learnt a new Skill.
     * 
     * @param skill
     *            The freshly learnt Skill.
     */
    public final void skillsAdd(final Skill skill) {
        if (skills == null) {
            skills = new TreeSet<Skill>();
        }
        skills.add(skill);
    }

    /**
     * Add extra Skills to the list of required ingredients.
     * 
     * @param newSkills
     *            additinal skills to set on Entity.
     */
    public final void skillsAdd(final String[] newSkills) {
        for (String skillId : newSkills) {
            skillsAdd(new Skill(skillId));
        }
    }

    // Misc

    /**
     * Return a detailed description of the object.
     * 
     * @return Plain text description of the object
     */
    public String detailedDescription() {
        StringBuilder text = new StringBuilder();

        // Inherit description from super first.
        text.append(super.detailedDescription());
        // Only add properties in this class.

        if (actionPoints != 0) {
            text.append("Action Points: " + actionPoints);
            text.append(System.lineSeparator());

        }
        if (gender != null) {
            text.append("Gender: " + gender);
            text.append(System.lineSeparator());
        }
        if (mana != 0) {
            text.append("Mana: " + mana);
            text.append(System.lineSeparator());
        }

        if (size != null) {
            text.append("Size: " + size);
            text.append(System.lineSeparator());
        }

        Set<Skill> skills = getSkills();
        if (skills != null && !skills.isEmpty()) {
            text.append("Skills:\n");
            for (Skill skill : skills) {
                text.append("  " + skill);
                text.append(System.lineSeparator());
            }
        }
        Set<String> recipes = getRecipes();
        if (recipes != null && !recipes.isEmpty()) {
            text.append("Recipes:\n");
            for (String recipeId : recipes) {
                text.append("  " + recipeId);
                text.append(System.lineSeparator());
            }
        }
        return text.toString();
    }

    /**
     * Create a new cooker.
     * 
     * @param recipeId
     *            The ID of the recipe we wish to use for cooking.
     * @return a new cooker object
     */
    public Cooker getCooker(String recipeId) {
        Configuration configuration = Configuration.lastConfig();
        return configuration.getRecipe(recipeId).getNewCooker(this);
    }

    /**
     * Try to drink some water.
     * 
     * @param item
     *            Water (or sub-class)
     * @return Any error message, or null on success.
     */
    public String drink(Item item) {
        Cooker cooker = getCooker("drinkWater");
        String error = cooker.setItemsAvailable(0, item);
        if (error != null) {
            return error;
        }
        return cooker.cook();
    }

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param entity
     *            source
     */
    public void setAllFrom(Entity entity) {
        setAllFrom((ItemContainer) entity);
        setGender(entity.getGender());
        setSize(entity.getSize());
    }

}
