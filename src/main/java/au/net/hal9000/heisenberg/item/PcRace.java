package au.net.hal9000.heisenberg.item;

import java.util.TreeMap;

import au.net.hal9000.heisenberg.util.AbilityScore;
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
    TreeMap<String, AbilityScore> abilityScores;

    // Constructors

    public PcRace(String pName) {
        this(pName, "");
    }

    public PcRace(String string, String description) {
        super(string, description);
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
        setActionPoints(pcClass.getActionPoints());
        health = pcClass.getHealth();
        setMana(pcClass.getMana());

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
        setActionPoints(0);
        setMana(0);
        encumbrance = 0;
        health = 0;
    }

    /**
     * 
     * @return Plain text description of the object
     */
    public String detailedDescription() {
        StringBuilder text = new StringBuilder(1024);

        // Inherit description from super first.
        text.append(super.detailedDescription());
        // Only add properties in this class.

        String race = getRace();

        text.append("Level: " + level + "\nRace: " + race + "\n");

        if (pcClass != null) {
            text.append("Class: " + pcClass.getId() + "\n");
        }

        text.append("Combat Dice: D" + combatDice + "\nMagic Dice: D"
                + magicDice + "\nStealth Dice: D" + stealthDice
                + "\nGeneral Dice: D" + generalDice + "\nHealth: " + health
                + "\nEncumbrance: " + encumbrance + "\n");

        if (abilityScores != null) {
            text.append("Abilities:\n");
            for (AbilityScore abilityScore : abilityScores.values()) {
                text.append("  " + abilityScore + "\n");
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

}
