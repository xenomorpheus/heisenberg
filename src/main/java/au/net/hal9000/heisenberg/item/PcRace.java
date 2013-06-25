package au.net.hal9000.heisenberg.item;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Properties;

import javax.swing.Icon;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ItemIcon;
import au.net.hal9000.heisenberg.util.PcClass;

public abstract class PcRace extends Entity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * How skilled in the chosen profession AKA PcClass.
     */
    private int level = 0;
    /**
     * profession e.g. Soldier, Wizard etc.
     */
    protected PcClass pcClass;
    private int combatDice;
    private int magicDice;
    private int stealthDice;
    private int generalDice;
    int encumbrance;
    int health;
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
    TreeMap<String, AbilityScore> abilityScores;
    /**
     * The {@link Recipe} list that is known by this object.
     */
    private Set<String> recipes;
    /**
     * The {@link Skill} objects required.
     */
    private Set<Skill> skills;

    private static Icon openIcon = ItemIcon.get("images/icon/S_Buff05.png");
    private static Icon closedIcon = openIcon;
    private static Icon leafIcon = openIcon;

    // Constructors

    public PcRace(String pName) {
        this(pName, "");
    }

    public PcRace(String string, String description) {
        super(string, description);
        // By default PCs are living, but this may be changed at any time.
        ItemProperty.setLiving(this, true);
    }

    /**
     * Give me a PC that is a {@link PcClass} (e.g. Warrior).
     */
    public PcRace(String pName, PcClass pPcClass) {
        this(pName);
        pcClass = pPcClass;
        init();
    }

    /**
     * Give me a PC that is a {@link PcClass} (e.g. Warrior).
     */
    public PcRace(String name, String description, PcClass pPcClass) {
        this(name, description);
        pcClass = pPcClass;
        init();
    }

    // Getters and Setters
    /**
     * @return the level
     */
    public final int getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public final void setLevel(final int level) {
        int oldLevel = this.level;
        if (level != oldLevel) {
            this.level = level;
            abilityScoresRecalculate();
        }
    }

    /**
     * @return the pcClass
     */
    public final PcClass getPcClass() {
        return pcClass;
    }

    /**
     * @param pcClass
     *            the pcClass to set
     */
    public final void setPcClass(final PcClass pcClass) {
        this.pcClass = pcClass;
        if (pcClass == null) {
            clearClassBaseFields();
        } else {
            setBasicsFromPcClass();
        }
    }

    /**
     * @return the combatDice
     */
    public final int getCombatDice() {
        return combatDice;
    }

    /**
     * @param combatDice
     *            the combatDice to set
     */
    public final void setCombatDice(final int combatDice) {
        this.combatDice = combatDice;
    }

    /**
     * @return the magicDice
     */
    public final int getMagicDice() {
        return magicDice;
    }

    /**
     * @param magicDice
     *            the magicDice to set
     */
    public final void setMagicDice(int magicDice) {
        this.magicDice = magicDice;
    }

    /**
     * @return the stealthDice
     */
    public final int getStealthDice() {
        return stealthDice;
    }

    /**
     * @param stealthDice
     *            the stealthDice to set
     */
    public final void setStealthDice(final int stealthDice) {
        this.stealthDice = stealthDice;
    }

    /**
     * @return the generalDice
     */
    public final int getGeneralDice() {
        return generalDice;
    }

    /**
     * @param generalDice
     *            the generalDice to set
     */
    public final void setGeneralDice(final int generalDice) {
        this.generalDice = generalDice;
    }

    /**
     * @return the encumbrance
     */
    public final int getEncumbrance() {
        return encumbrance;
    }

    /**
     * @param encumbrance
     *            the encumbrance to set
     */
    public final void setEncumbrance(final int encumbrance) {
        this.encumbrance = encumbrance;
    }

    /**
     * @return the health
     */
    public final int getHealth() {
        return health;
    }

    /**
     * @param health
     *            the health to set
     */
    public final void setHealth(final int health) {
        this.health = health;
    }

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

    /**
     * @return the abilityScores
     */
    public final TreeMap<String, AbilityScore> getAbilityScores() {
        return abilityScores;
    }

    /**
     * @param abilityScores
     *            the abilityScores to set
     */
    public final void setAbilityScores(
            TreeMap<String, AbilityScore> abilityScores) {
        this.abilityScores = abilityScores;
    }

    /**
     * @param name
     *            name of AbilityScore
     * @return the AbilityScore object
     */
    public AbilityScore getAbilityScore(String name) {
        return abilityScores.get(name);
    }

    /**
     * @param abilityScore
     *            the AbilityScore to put.<br>
     *            Any existing AbilityScore with this name will be removed.
     */
    public void setAnAbilityScore(AbilityScore abilityScore) {
        if (abilityScores == null) {
            abilityScores = new TreeMap<String, AbilityScore>();
        }
        abilityScores.put(abilityScore.getName(), abilityScore);
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

    protected void init() {
        if (pcClass == null) {
            clearClassBaseFields();
        } else {
            setBasicsFromPcClass();
            abilityScoresRecalculate();
        }
    }

    /**
     * Warning: will reset all abilityScore objects
     */
    private void setBasicsFromPcClass() {
        // dice
        combatDice = pcClass.getCombatDice();
        magicDice = pcClass.getMagicDice();
        stealthDice = pcClass.getStealthDice();
        generalDice = pcClass.getGeneralDice();
        // misc
        encumbrance = pcClass.getEncumbrance();
        actionPoints = pcClass.getActionPoints();
        health = pcClass.getHealth();
        mana = pcClass.getMana();

        abilityScoresEnsureExists();

        // TODO don't reach inside object's data structures - keySet()
        TreeMap<String, AbilityScore> pcClassAbilityScores = pcClass
                .getAbilityScores();
        if (pcClassAbilityScores == null) {
            // TODO do we need to clear existing abilities
            // What about ability scores not from pcClass ? Add a unit test to
            // ensure they are preserved.
        } else {
            for (String key : pcClassAbilityScores.keySet()) {
                AbilityScore abilityScore = new AbilityScore(key, 0, 0);
                abilityScores.put(key, abilityScore);
            }
            abilityScoresRecalculate();
        }
    }

    /**
     * Ensure that the abilityScores
     */
    private void abilityScoresEnsureExists() {
        if (abilityScores == null) {
            abilityScores = new TreeMap<String, AbilityScore>();
        }
    }

    /**
     * recalculate the AbilityScore objects. <br>
     * e.g. when a PC levels.<br>
     * Should be safe to call any time.
     */
    private void abilityScoresRecalculate() {
        // TODO we should be iterating over pcClass's ability key set.
        // We need to make sure that we preserve any ability scores that aren't
        // on the pcClass.
        if (pcClass != null) {
            if (abilityScores == null) {
                abilityScoresEnsureExists();
            }
            for (String key : abilityScores.keySet()) {
                AbilityScore abilityScore = abilityScores.get(key);
                AbilityScore pcClassAbility = pcClass.getAbilityScore(key);
                abilityScore.setValue(pcClassAbility.getValue()
                        + (level * pcClassAbility.getMod())
                        + abilityScore.getMod());
            }
        }
    }

    private void clearClassBaseFields() {

        // dice
        combatDice = 0;
        magicDice = 0;
        stealthDice = 0;
        generalDice = 0;
        // misc
        actionPoints = 0;
        mana = 0;
        encumbrance = 0;
        health = 0;
    }

    /**
     * @return Plain text description of the object
     */
    public String getDetailedDescription() {
        StringBuilder text = new StringBuilder(1024);

        String name = getName();
        if (name != null) {
            text.append("Name: " + name + "\n");
        }

        String race = getRace();

        text.append("Level: " + level + "\nRace: " + race + "\n");

        if (pcClass != null) {
            text.append("Class: " + pcClass.getId() + "\n");
        }
        String description = getDescription();
        if (description != null) {
            text.append("Description: " + description + "\n");
        }

        text.append("Combat Dice: D" + combatDice + "\nMagic Dice: D"
                + magicDice + "\nStealth Dice: D" + stealthDice
                + "\nGeneral Dice: D" + generalDice + "\nAction Points: "
                + actionPoints + "\nHealth: " + health + "\nMana: " + mana
                + "\nEncumbrance: " + encumbrance + "\n");

        String gender = getGender();
        if (gender != null) {
            text.append("Gender: " + gender + "\n");
        }
        String size = getSize();
        if (size != null) {
            text.append("Size: " + size + "\n");
        }
        if (abilityScores != null) {
            text.append("Abilities:\n");
            for (AbilityScore abilityScore : abilityScores.values()) {
                text.append("  " + abilityScore + "\n");
            }
        }
        if (skills != null && !skills.isEmpty()) {
            text.append("Skills:\n");
            for (Skill skill : skills) {
                text.append("  " + skill + "\n");
            }
        }
        if (recipes != null && !recipes.isEmpty()) {
            text.append("Recipes:\n");
            for (String recipeId : recipes) {
                text.append("  " + recipeId + "\n");
            }
        }
        Properties properties = this.getProperties();
        if (properties != null && !properties.isEmpty()) {
            text.append("Properties:\n");
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                text.append(" " + entry.getKey() + ": " + entry.getValue()
                        + "\n");
            }
        }
        return text.toString();
    }

    public abstract String getRace();

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param pc
     *            source
     */
    public void setAllFrom(PcRace pc) {
        setAllFrom((Entity) pc);
        setLevel(pc.getLevel());
        setPcClass(pc.getPcClass()); // TODO ensure results are not linked
        setCombatDice(pc.getCombatDice());
        setMagicDice(pc.getMagicDice());
        setStealthDice(pc.getStealthDice());
        setGeneralDice(pc.getGeneralDice());
        setEncumbrance(pc.getEncumbrance());
        setHealth(pc.getHealth());
        setActionPoints(pc.getActionPoints());
        setMana(pc.getMana());
        setAbilityScores(pc.getAbilityScores()); // TODO ensure results are not
                                                 // linked
        setRecipes(pc.getRecipes()); // TODO ensure results are not linked
        setSkills(pc.getSkills()); // TODO ensure results are not linked
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

    /** {@inheritDoc} */
    @Override
    public Icon getOpenIcon() {
        return openIcon;
    }

    /** {@inheritDoc} */
    @Override
    public Icon getClosedIcon() {
        return closedIcon;
    }

    /** {@inheritDoc} */
    @Override
    public Icon getLeafIcon() {
        return leafIcon;
    }
}
