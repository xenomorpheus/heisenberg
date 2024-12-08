package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

public class BowLong extends WeaponRanged {

  /** "Longbow 75gp 1d6 1d8 x3 100ft. 3lbs. P" */
  public BowLong() {
    super();
    setName("Longbow");
    setUrl("https://www.d20pfsrd.com/equipment/weapons/");
    setCost(new Currency().setGp(75));
    setDamageVsSmall("d6");
    setDamageVsMedium("d8");
    setDamageCritical("x3");
    setRangeMax(100.0f);
    setWeight(3f);
    setDamageTypes("P");
  }
}
