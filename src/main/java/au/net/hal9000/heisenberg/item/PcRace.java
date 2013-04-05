package au.net.hal9000.heisenberg.item;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Properties;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.Configuration;
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
    TreeMap<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();
    /**
     * The {@link Recipe} list that is known by this object.
     */
    private Set<String> recipes = new TreeSet<String>();
    /**
     * The {@link PowerWord} objects required.
     */
    private Set<PowerWord> powerWords = new TreeSet<PowerWord>();
    /**
     * The {@link Skill} objects required.
     */
    private Set<Skill> skills = new TreeSet<Skill>();

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
        this.level = level;
        abilityScoresRecalculate();
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
            clearClassBasedFields();
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
    public void setAbilityScore(AbilityScore abilityScore) {
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
     * @param set
     *            the set of Recipe objects
     */
    public void setRecipes(Set<String> set) {
        this.recipes = set;
    }

    /**
     * Add to the of Recipe objects this PcRace object knows.
     * 
     * @param recipeId
     *            a Recipe id
     */
    public void recipesAdd(String recipeId) {
        this.recipes.add(recipeId);
    }

    /**
     * The PcRace has learnt new Recipe(s).
     * 
     * @param newRecipes
     *            an array of Recipe IDs to add.
     */
    public final void recipesAdd(final String[] newRecipes) {
        for (int i = newRecipes.length - 1; i >= 0; i--) {
            recipes.add(newRecipes[i]);
        }
    }

    // PowerWords
    /**
     * Get the PowerWord objects.
     * 
     * @return a set of PowerWord objects
     */
    public final Set<PowerWord> getPowerWords() {
        return powerWords;
    }

    public final void setPowerWords(final Set<PowerWord> powerWords) {
        this.powerWords = powerWords;
    }

    /**
     * Set the PowerWord objects
     * 
     * @param newPowerWords
     *            list of powerWord IDs as Strings.
     */
    public void setPowerWords(final String[] newPowerWords) {
        powerWords.clear();
        powerWordsAdd(newPowerWords);
    }

    /**
     * Add extra PowerWords to the list of required ingredients.
     * 
     * @param powerWords
     */
    public final void powerWordsAdd(final Set<PowerWord> powerWords) {
        powerWords.addAll(powerWords);
    }

    /**
     * The PcRace has learnt new PowerWord(s).
     * 
     * @param newPowerWords
     *            an array of PowerWord IDs to add.
     */
    public final void powerWordsAdd(final String[] newPowerWords) {
        for (int i = newPowerWords.length - 1; i >= 0; i--) {
            powerWords.add(new PowerWord(newPowerWords[i]));
        }
    }

    /**
     * The PcRace object has learnt a new PowerWord.
     * 
     * @param powerWord
     *            the freshly learnt PowerWord
     */
    public final void powerWordsAdd(final PowerWord powerWord) {
        powerWords.add(powerWord);
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
     * Set the Skill objects
     * 
     * @param newSkills
     *            list of powerWord IDs as Strings.
     */
    public void setSkills(final String[] newSkills) {
        skills.clear();
        skillsAdd(newSkills);
    }

    /**
     * Add extra Skills to the list of required ingredients.
     * 
     * @param skills
     */
    public final void skillsAdd(final Set<Skill> skills) {
        skills.addAll(skills);
    }

    /**
     * Add extra Skills to the list of required ingredients.
     * 
     * @param newSkills
     */
    public final void skillsAdd(final String[] newSkills) {
        for (int i = newSkills.length - 1; i >= 0; i--) {
            skills.add(new Skill(newSkills[i]));
        }
    }

    /**
     * The PcRace object has learnt a new Skill.
     * 
     * @param skill
     *            The freshly learnt Skill.
     */
    public final void skillsAdd(final Skill skill) {
        skills.add(skill);
    }

    // Misc

    protected void init() {
        if (pcClass == null) {
            clearClassBasedFields();
        } else {
            setBasicsFromPcClass();
            abilityScoresRecalculate();
        }
    }

    /**
     * @return short plan text string for this object.
     */

    // public String toString() {
    // String string = getName();
    // if (pcClass != null) {
    // string += " the " + pcClass.getId();
    // }
    // return string;
    // }

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

        // reset abilityScore objects
        abilityScores.clear();
        // TODO don't reach inside object's data structures - keySet()
        Iterator<String> itr = pcClass.getAbilityScores().keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            AbilityScore abilityScore = new AbilityScore(key, 0, 0);
            abilityScores.put(key, abilityScore);
        }
        abilityScoresRecalculate();
    }

    /**
     * recalculate the AbilityScore objects. <br>
     * e.g. when a PC levels.<br>
     * Should be safe to call any time.
     */
    private void abilityScoresRecalculate() {
        // TODO should we be iterating over keys or values?
        Iterator<String> itr = abilityScores.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            AbilityScore abilityScore = abilityScores.get(key);
            AbilityScore pcClassAbility = pcClass.getAbilityScore(key);
            abilityScore
                    .setValue(pcClassAbility.getValue()
                            + (level * pcClassAbility.getMod())
                            + abilityScore.getMod());
        }
    }

    private void clearClassBasedFields() {

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
        String description = "";

        String name = getName();
        if (name != null) {
            description += "Name: " + getName();
        }

        String race = getRace();

        description += "\nLevel: " + level + "\nRace: " + race;

        if (pcClass != null) {
          description += "\nClass: " + pcClass.getId();
        }

        description += "\nCombat Dice: D" + combatDice + "\nMagic Dice: D"
                + magicDice + "\nStealth Dice: D" + stealthDice
                + "\nGeneral Dice: D" + generalDice + "\nAction Points: "
                + actionPoints + "\nHealth: " + health + "\nMana: " + mana
                + "\nEncumbrance: " + encumbrance + "\n";

        String gender = getGender();
        if (gender != null) {
            description += "\nGender: " + gender;
        }
        String size = getSize();
        if (size != null) {
            description += "\nSize:" + size;
        }
        if (abilityScores != null) {
            description += "Abilities:\n";
            for (AbilityScore abilityScore : abilityScores.values()) {
                description += "  " + abilityScore + "\n";
            }
        }
        if (skills != null && !skills.isEmpty()) {
            description += "Skills:\n";
            for (Skill skill : skills) {
                description += "  " + skill + "\n";
            }
        }
        if (powerWords != null && !powerWords.isEmpty()) {
            description += "Power Words:\n";
            for (PowerWord powerWord : powerWords) {
                description += "  " + powerWord + "\n";
            }
        }
        if (recipes != null && !recipes.isEmpty()) {
            description += "Recipes:\n";
            for (String recipeId : recipes) {
                description += "  " + recipeId + "\n";
            }
        }
        Properties properties = this.getProperties();
        if (properties != null && !properties.isEmpty()) {
            description += "Properties:\n";
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                description += " " + entry.getKey() + ": " + entry.getValue()
                        + "\n";
            }
        }
        description += "Done.\n";
        return description;
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
        setPcClass(pc.getPcClass());
        setCombatDice(pc.getCombatDice());
        setMagicDice(pc.getMagicDice());
        setStealthDice(pc.getStealthDice());
        setGeneralDice(pc.getGeneralDice());
        setEncumbrance(pc.getEncumbrance());
        setHealth(pc.getHealth());
        setActionPoints(pc.getActionPoints());
        setMana(pc.getMana());
        setAbilityScores(pc.getAbilityScores());
        setRecipes(pc.getRecipes());
        setSkills(pc.getSkills());
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

}
