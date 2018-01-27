package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;

public class Fighter extends CharacterClass {

	private Set<CombatFeat> combatFeats = new HashSet<CombatFeat>();

	public Fighter() {
		super();
		setName("Fighter");
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
	public String toString() {
		return getName();
	}
	/**
	 * A detailed description.
	 * 
	 * @return
	 */
	public String details() {
		return details("");
	}

	/**
	 * A detailed description.
	 * 
	 * @return
	 */
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
	public List<Action> getActions(CombatArena arena, TimerRound timer) {
		// TODO Auto-generated method stub
		return null;
	}

}
