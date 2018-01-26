package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.HashSet;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.spell.Spell;
public class Magus extends CharacterClass {

	private Set<Spell> spells = new HashSet<Spell>();

	public Magus() {
		super();
		setName("Magus");
	}
public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(super.toString());
	if ((spells == null) || (spells.isEmpty())) {
		sb.append(String.format("Spells: None%n"));
	}
	else {
		sb.append(String.format("Spells:%s"));
		for (Spell spell : spells) {
			sb.append(String.format("  :%s", spell.getName()));
		}
	}
	return sb.toString();
}
}
