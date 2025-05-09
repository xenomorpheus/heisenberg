package au.net.hal9000.heisenberg.fifthed.item;

import au.net.hal9000.heisenberg.units.Currency;

/** Represents a light crossbow weapon in the game. */
public class BowCrossLight extends WeaponRanged {

  /** "Crossbow, light 35 gp 1d6 1d8 19–20/x2 80 ft. 4 lbs. P —" -2 attack one handed. */
  public BowCrossLight() {
    super();
    setName("Crossbow, light");
    setUrl("https://www.d20pfsrd.com/equipment/weapons/weapon-descriptions/crossbow-light");
    setCost(new Currency().setGp(35));
    setDamageVsSmall("d6");
    setDamageVsMedium("d8");
    setDamageCritical("19–20/x2");
    setRangeMax(80.0f);
    setWeight(4f);
    setDamageTypes("P");
    setSingleHandAttackReduction(2);
  }
}
