package au.net.hal9000.heisenberg.fifthed.item;

/**
 * https://www.d20pfsrd.com/equipment/weapons/
 * 
 * @author bruins
 *
 */
import au.net.hal9000.heisenberg.fifthed.PlayerCharacter;

public abstract class Weapon extends Item {

	String damageVsSmall = null;
	String damageVsMedium = null;
	String damageCritical = null;
	private float rangeMax = 0.0f;
	String damageTypes = null;

	// Getters and Setters
	
	/**
	 * @return the damageVsSmall
	 */
	public String getDamageVsSmall() {
		return damageVsSmall;
	}

	/**
	 * @param damageVsSmall
	 *            the damageVsSmall to set
	 */
	public void setDamageVsSmall(String damageVsSmall) {
		this.damageVsSmall = damageVsSmall;
	}

	/**
	 * @return the damageVsMedium
	 */
	public String getDamageVsMedium() {
		return damageVsMedium;
	}

	/**
	 * @param damageVsMedium
	 *            the damageVsMedium to set
	 */
	public void setDamageVsMedium(String damageVsMedium) {
		this.damageVsMedium = damageVsMedium;
	}

	/**
	 * @return the damageCritical
	 */
	public String getDamageCritical() {
		return damageCritical;
	}

	/**
	 * @param damageCritical
	 *            the damageCritical to set
	 */
	public void setDamageCritical(String damageCritical) {
		this.damageCritical = damageCritical;
	}

	/**
	 * @return the rangeMax
	 */
	public float getRangeMax() {
		return rangeMax;
	}

	/**
	 * @param rangeMax
	 *            the rangeMax to set
	 */
	public void setRangeMax(float rangeMax) {
		this.rangeMax = rangeMax;
	}

	/**
	 * @return the damageTypes
	 */
	public String getDamageTypes() {
		return damageTypes;
	}

	/**
	 * @param damageTypes
	 *            the damageTypes to set
	 */
	public void setDamageTypes(String damageTypes) {
		this.damageTypes = damageTypes;
	}

	/** is the opponent within range of this weapon */
	public boolean withinRange(PlayerCharacter attacker, PlayerCharacter opponent) {
		return this.getRangeMax() > attacker.distance(opponent);
	}

	public boolean validAttack(PlayerCharacter attacker, PlayerCharacter opponent) {
		return withinRange(attacker, opponent);
	}
}
