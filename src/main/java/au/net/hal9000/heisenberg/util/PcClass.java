package au.net.hal9000.heisenberg.util;

import java.util.TreeMap;
import java.util.Iterator;

/**
 * 
 * TODO Refactor this into two classes:<br>
 * 1) Holder of configuration of a PC Class<br>
 * 2) The actual character sheet class.
 * 
 * Ideas: New class CharacterSheet will hold a PC's stats and maybe items.<br>
 * CharacterSheet will hold the following for each ability score:<br>
 * 1) (no extension) - The current value Equals:<br>
 * CharacterClass ability base + (level * CharacterClass ability inc) + Mod <br>
 * 2) "Mod" - Any modifiers the character has chosen.
 * 
 * @author bruins
 * 
 */

public class PcClass implements Comparable<PcClass> {

    // e.g. "Soldier"
    private String id;
    /** combat dice. */
    private int combatDice = 0;
    /** magic dice. */
    private int magicDice = 0;
    /** stealth dice. */
    private int stealthDice = 0;
    /** general dice. */
    private int generalDice = 0;
    // misc
    private int actionPoints = 0;
    private int health = 0;
    private int mana = 0;
    private String raceAllow;
    private String genderAllow;
    private String sizeAllow;
    private int encumbrance = 0;
    private TreeMap<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();

    public PcClass() {
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(String id) {
        this.id = id;
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
    public final void setCombatDice(int combatDice) {
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
    public final void setStealthDice(int stealthDice) {
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
    public final void setGeneralDice(int generalDice) {
        this.generalDice = generalDice;
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
    public final void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
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
    public final void setHealth(int health) {
        this.health = health;
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
     * @return the raceAllow
     */
    public final String getRaceAllow() {
        return raceAllow;
    }

    /**
     * @param raceAllow
     *            the raceAllow to set
     */
    public final void setRaceAllow(String raceAllow) {
        this.raceAllow = raceAllow;
    }

    /**
     * @return the genderAllow
     */
    public final String getGenderAllow() {
        return genderAllow;
    }

    /**
     * @param genderAllow
     *            the genderAllow to set
     */
    public final void setGenderAllow(String genderAllow) {
        this.genderAllow = genderAllow;
    }

    /**
     * @return the sizeAllow
     */
    public final String getSizeAllow() {
        return sizeAllow;
    }

    /**
     * @param sizeAllow
     *            the sizeAllow to set
     */
    public final void setSizeAllow(String sizeAllow) {
        this.sizeAllow = sizeAllow;
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
    public final void setEncumbrance(int encumbrance) {
        this.encumbrance = encumbrance;
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

    // misc

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

    /**
     * @return the short string.
     */
    public String toString() {
        return id;
    }

    /**
     * @return Plain text description of the object
     */
    public String getDetailedDescription() {
        StringBuilder text = new StringBuilder("Id: ");
        text.append(id);
        text.append(System.lineSeparator());
        text.append("Combat Dice: D");
        text.append(combatDice);
        text.append(System.lineSeparator());
        text.append("Magic Dice: D");
        text.append(magicDice);
        text.append(System.lineSeparator());
        text.append("Stealth Dice: D");
        text.append(stealthDice);
        text.append(System.lineSeparator());
        text.append("General Dice: D");
        text.append(generalDice);
        text.append(System.lineSeparator());
        text.append("Action Points: ");
        text.append(actionPoints);
        text.append(System.lineSeparator());
        text.append("health: ");
        text.append(health);
        text.append(System.lineSeparator());
        text.append("mana: ");
        text.append(mana);
        text.append(System.lineSeparator());
        text.append("raceAllow: ");
        text.append(raceAllow);
        text.append(System.lineSeparator());
        text.append("genderAllow: ");
        text.append(genderAllow);
        text.append(System.lineSeparator());
        text.append("sizeAllow:");
        text.append(sizeAllow);
        text.append(System.lineSeparator());
        text.append("encumbrance: ");
        text.append(encumbrance);
        text.append(System.lineSeparator());

        if (abilityScores != null) {
            text.append("Abilities:");
            text.append(System.lineSeparator());
            Iterator<AbilityScore> itr = abilityScores.values().iterator();
            while (itr.hasNext()) {
                text.append("  ");
                text.append(itr.next());
                text.append(System.lineSeparator());
            }
        }
        return text.toString();
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(PcClass other) {
        return this.id.compareTo(other.getId());
    }

}
