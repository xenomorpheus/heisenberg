package au.net.hal9000.heisenberg.fifthed.characterClass;

import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.ActionAttackWeapon;
import au.net.hal9000.heisenberg.fifthed.combat.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.item.Item;
import au.net.hal9000.heisenberg.fifthed.item.Weapon;
import au.net.hal9000.heisenberg.fifthed.playerCharacter.PlayerCharacter;
import java.util.HashSet;
import java.util.Set;

public class Fighter extends CharacterClass {

  private Set<CombatFeat> combatFeats = new HashSet<CombatFeat>();

  public Fighter(String name, int level, PlayerCharacter playerCharacter) {
    super(name, level, playerCharacter);
  }

  public Fighter(int level, PlayerCharacter playerCharacter) {
    this("Fighter", level, playerCharacter);
  }

  // Setters and Getters
  /**
   * @return the feats
   */
  public Set<CombatFeat> getCombatFeats() {
    return combatFeats;
  }

  /**
   * @param feats the feats to set
   */
  public Fighter setCombatFeats(Set<CombatFeat> feats) {
    this.combatFeats = feats;
    return this;
  }

  // Misc
  @Override
  public String toString() {
    return getName();
  }

  /**
   * A detailed description.
   *
   * @return
   */
  @Override
  public String details() {
    return details("");
  }

  /**
   * A detailed description.
   *
   * @return
   */
  @Override
  public String details(String prefix) {
    StringBuilder sb = new StringBuilder(10);
    sb.append(super.details(prefix));
    if ((combatFeats == null) || (combatFeats.isEmpty())) {
      sb.append(String.format("%sCombat Feats: None%n", prefix));
    } else {
      sb.append(String.format("%sCombat Feats:%n", prefix));
      for (CombatFeat feat : combatFeats) {
        sb.append(String.format("%s  %s%n", prefix, feat.toString()));
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
    PlayerCharacter pc = getPlayerCharacter();
    Set<Item> equipped = pc.getEquipped();
    for (PlayerCharacter opponent : arena.getOpponents()) {
      for (Item equipment : equipped) {
        if (!(equipment instanceof Weapon)) {
          continue;
        }
        Weapon weapon = (Weapon) equipment;
        // Check single/double hand required
        // Check range
        if (weapon.withinRange(pc, opponent)) {
          actions.add(new ActionAttackWeapon(weapon, opponent));
        }
      }
    }

    if (arena.getTimerRound().isActionDurationAvailable(ActionDuration.FREE)) {
      // TODO actions.add(new ActionFree());
    }
    // TODO Free Actions, etc.

    // Add
    // actions.addAll(super.getActionsCombat(arena, timer));
    return actions;
  }

  public CharacterClass combatFeatAdd(CombatFeat combatFeat) {
    combatFeats.add(combatFeat);
    return this;
  }
}
