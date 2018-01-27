package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

/**
 * Dagger: 2gp, 1d3, 1d4, 19-20/x2, 10 ft, 1lb, P or S
 * 
 * @author bruins
 *
 */

public class Dagger extends WeaponMelee {
	public Dagger() {
		super();
		setName("Dagger");
		setUrl("http://www.d20pfsrd.com/equipment/weapons/weapon-descriptions/dagger/");
		setCost(new Currency().setGp(2));
		setDamageVsSmall("1d3");
		setDamageVsMedium("1d4");
		setDamageCritical("19-20/x2");
		setRangeMax(10.0f);
		setWeight(1f);
		setDamageTypes("P or S");
	}

}
