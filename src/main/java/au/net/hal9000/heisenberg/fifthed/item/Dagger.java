package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

/**
 * 
 * 
 * @author bruins
 *
 */

public class Dagger extends WeaponRanged {
	public Dagger() {
		super();
		setName("Dagger");
		setUrl("http://www.d20pfsrd.com/equipment/weapons/weapon-descriptions/dagger/");
		setCost(new Currency().setGp(2));
		setDamageVsSmall("1d3");
		setDamageVsMedium("1d4");
		setDamageCritical("19-20/x2");
		setRangeMax(10.0f);
		setDamageTypes("P or S");
	}

}
