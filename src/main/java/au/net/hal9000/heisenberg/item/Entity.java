package au.net.hal9000.heisenberg.item;

import java.util.Set;
import java.util.TreeSet;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.PcClass;

/**
 * Entity is the bases of conscious entities. <br>
 * May or may not be living.
 * 
 * @author bruins
 * 
 */

public abstract class Entity extends ItemContainer {
    private static final long serialVersionUID = 1L;

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
     * This object has a list of {@link AbilityScore} objects.<br>
     * 
     * To set up, just work through the list on the {@link PcClass}.
     * 
     * Create a new AbilityScore object for each one on PC. Set the modifier to
     * 0. Set the name = pcClassAbility.getName()
     * 
     * Call the reCalculateAllAbilityScores() function<br>
     * Set the value = pcClassAbility.getValue() + (level *
     * pcClassAbility.getModifier()) + modifier;
     */
    String gender;
    /**
     * The {@link Recipe} list that is known by this object.
     */
    private Set<String> recipes;
    /**
     * The {@link Skill} objects required.
     */
    private Set<Skill> skills;
    String size;

    // Constructor
    public Entity(String name, String description) {
        super(name, description);
        // By default PCs are living, but this may be changed at any time.
        ItemProperty.setLiving(this, true);
        ItemProperty.setAeration(this, 100);
        ItemProperty.setEntertainment(this, 80);        
        ItemProperty.setHydration(this, 80);
        ItemProperty.setNourishment(this, 80);
        ItemProperty.setRest(this, 80);
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
     */
    public final void skillsAdd(final String[] newSkills) {
        for (String skillId : newSkills) {
            skillsAdd(new Skill(skillId));
        }
    }

    // Misc

    /**
     * 
     * @return Plain text description of the object
     */
    public String detailedDescription() {
        StringBuilder text = new StringBuilder(1024);

        // Inherit description from super first.
        text.append(super.detailedDescription());
        // Only add properties in this class.

        if (actionPoints != 0) {
            text.append("Action Points: " + actionPoints + "\n");
        }
        if (gender != null) {
            text.append("Gender: " + gender + "\n");
        }
        if (mana != 0) {
            text.append("Mana: " + mana + "\n");
        }

        if (size != null) {
            text.append("Size: " + size + "\n");
        }

        Set<Skill> skills = getSkills();
        if (skills != null && !skills.isEmpty()) {
            text.append("Skills:\n");
            for (Skill skill : skills) {
                text.append("  " + skill + "\n");
            }
        }
        Set<String> recipes = getRecipes();
        if (recipes != null && !recipes.isEmpty()) {
            text.append("Recipes:\n");
            for (String recipeId : recipes) {
                text.append("  " + recipeId + "\n");
            }
        }
        return text.toString();
    }

    /**
     * Create a new cooker
     * 
     * @param recipeId
     * @return a new cooker object
     */
    public Cooker getCooker(String recipeId) {
        Configuration configuration = Configuration.lastConfig();
        Cooker cooker = configuration.getRecipe(recipeId).getNewCooker();
        cooker.setChef(this);
        return cooker;
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
