package au.net.hal9000.heisenberg.fifthed.characterclass;

import au.net.hal9000.heisenberg.fifthed.spell.Spell;
import java.util.Set;

/* Note that this is only for CharacterClass types that cast spells e.g. Wizard, Magus.
This interface is not for PlayerCharacter types. */
public interface Spellcaster {

  CharacterClass setSpells(Set<Spell> spells);

  Set<Spell> getSpells();

  CharacterClass spellsAdd(Spell spell);

  int getClassLevel();
}
