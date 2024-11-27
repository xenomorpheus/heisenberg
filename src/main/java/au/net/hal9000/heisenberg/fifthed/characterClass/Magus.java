package au.net.hal9000.heisenberg.fifthed.characterClass;

import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.combat.ActionSpellCast;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.combat.TimerRound;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.spell.Casting;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;
import java.util.HashSet;
import java.util.Set;

public class Magus extends Fighter implements Spellcaster {

  private Set<Spell> spells = new HashSet<Spell>();
  private Set<MagusArcana> magusArcana = new HashSet<MagusArcana>();

  public Magus(String name, int level, PlayerCharacter playerCharacter) {
    super(name, level, playerCharacter);
  }

  public Magus(int level, PlayerCharacter playerCharacter) {
    this("Magus", level, playerCharacter);
  }

  // Setters and Getters
  @Override
  public Magus setSpells(Set<Spell> spells) {
    this.spells = spells;
    return this;
  }

  /**
   * @return the spells
   */
  @Override
  public Set<Spell> getSpells() {
    return spells;
  }

  /**
   * @return the magusArcana
   */
  public Set<MagusArcana> getMagusArcana() {
    return magusArcana;
  }

  /**
   * @param magusArcana the magusArcana to set
   */
  public Magus setMagusArcana(Set<MagusArcana> magusArcana) {
    this.magusArcana = magusArcana;
    return this;
  }

  // Misc
  /**
   * A detailed description.
   *
   * @return
   */
  public String details(String prefix) {
    StringBuilder sb = new StringBuilder(10);
    sb.append(super.details(prefix));
    if ((spells == null) || (spells.isEmpty())) {
      sb.append(String.format("%sSpells: None%n", prefix));
    } else {
      sb.append(String.format("%sSpells:%n", prefix));
      for (Spell spell : spells) {
        sb.append(String.format("%s  %s%n", prefix, spell.getName()));
      }
    }
    return sb.toString();
  }

  @Override
  /**
   * Work out what actions may be performed in this amount of time. Explicitly get Magus. Get
   * Fighter from super.
   */
  public Set<Action> getActionsCombat(CombatArena arena) {
    Set<Action> actions = new HashSet<Action>();
    TimerRound timer = arena.getTimerRound();
    for (PlayerCharacter opponent : arena.getOpponents()) {
      for (Spell spell : spells) {

        Casting casting = new Casting(this, spell, opponent, timer);
        if (casting.isActionValid()) {
          actions.add(new ActionSpellCast(spell, opponent));
        }
      }

      if (timer.isActionDurationAvailable(ActionDuration.FREE)) {
        // TODO actions.add(new ActionFree());
      }
    }
    // TODO Free Actions, etc.

    // Add
    actions.addAll(super.getActionsCombat(arena));
    return actions;
  }

  @Override
  public CharacterClass spellsAdd(Spell spell) {
    spells.add(spell);
    return (CharacterClass) this;
  }
}
