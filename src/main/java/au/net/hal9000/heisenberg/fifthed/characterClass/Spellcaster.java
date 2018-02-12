package au.net.hal9000.heisenberg.fifthed.characterClass;

import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.playerCharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;

public interface Spellcaster {

	CharacterClass setSpells(Set<Spell> spells);

	Set<Spell> getSpells();

	CharacterClass spellsAdd(Spell spell);

	int getClassLevel();

	PlayerCharacter getPlayerCharacter();

}
