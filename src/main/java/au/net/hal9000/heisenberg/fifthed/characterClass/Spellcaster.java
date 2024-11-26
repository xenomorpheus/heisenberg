package au.net.hal9000.heisenberg.fifthed.characterClass;

import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;
import java.util.Set;

public interface Spellcaster {

  CharacterClass setSpells(Set<Spell> spells);

  Set<Spell> getSpells();

  CharacterClass spellsAdd(Spell spell);

  int getClassLevel();

  PlayerCharacter getPlayerCharacter();
}
