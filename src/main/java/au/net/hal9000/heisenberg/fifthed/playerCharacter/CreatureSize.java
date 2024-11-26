package au.net.hal9000.heisenberg.fifthed.playercharacter;

// http://www.d20pfsrd.com/gamemastering/combat/space-reach-threatened-area-templates/

public enum CreatureSize {
  FINE(+8, -8, +8, +16, 0.5f, 0f, "6ft or less", "1/8 lb. or less"),
  DIMINUTIVE(+4, -4, +6, +12, 1f, 0f, "6″ to 1 ft.", "1/8 lb. – 1 lb."),
  TINY(+2, -2, +4, +8, 2.5f, 0, "1′ to 2 ft.", "1-8 lbs."),
  SMALL(+1, -1, +2, +4, 5f, 5f, "2′ to 4 ft.", "8-60 lbs."),
  MEDIUM(+0, +0, +0, +0, 5f, 5f, "4′ to 8 ft.", "60-500 lbs."),
  LARGE_TALL(-1, +1, -2, -4, 10f, 10f, "8′ to 16 ft.", "500-4000 lbs."),
  LARGE_LONG(-1, +1, -2, -4, 10f, 5f, "8′ to 16 ft.", "500-4000 lbs."),
  HUGE_TALL(-2, +2, -4, -8, 15f, 15f, "16′ to 32 ft.", "2-16 tons"),
  HUGE_LONG(-2, +2, -4, -8, 15f, 10f, "16′ to 32 ft.", "2-16 tons"),
  GARGANTUAN_TALL(-4, +4, -6, -12, 20f, 20f, "32′ to 64 ft.", "16 – 125 tons"),
  GARGANTUAN_LONG(-4, +4, -6, -12, 20f, 15f, "32′ to 64 ft.", "16 – 125 tons"),
  COLOSSAL_TALL(-8, +8, -8, -16, 30f, 30f, "64 ft. or more", "125 tons or more"),
  COLOSSAL_LONG(-8, +8, -8, -16, 30f, 20f, "64 ft. or more", "125 tons or more");

  private int sizeModifier;
  private int specialSizeModifier;
  private int sizeModifierToFly;
  private int sizeModifierToStealth;
  private double space; // ft
  private double naturalReach; // ft
  private String typicalHeightLength; // imperial description of length
  private String typicalWeight; // imperial

  CreatureSize(
      int sizeModifier,
      int specialSizeModifier,
      int sizeModifierToFly,
      int sizeModifierToStealth,
      double space,
      double naturalReach,
      String typicalHeightLength,
      String typicalWeight) {
    this.sizeModifier = sizeModifier;
    this.specialSizeModifier = specialSizeModifier;
    this.sizeModifierToFly = sizeModifierToFly;
    this.sizeModifierToStealth = sizeModifierToStealth;
    this.space = space; // ft
    this.naturalReach = naturalReach; // ft
    this.typicalHeightLength = typicalHeightLength; // ft
    this.typicalWeight = typicalWeight; // lb
  }

  /**
   * @return the sizeModifier
   */
  public int getSizeModifier() {
    return sizeModifier;
  }

  /**
   * @return the specialSizeModifier
   */
  public int getSpecialSizeModifier() {
    return specialSizeModifier;
  }

  /**
   * @return the sizeModifierToFly
   */
  public int getSizeModifierToFly() {
    return sizeModifierToFly;
  }

  /**
   * @return the sizeModifierToStealth
   */
  public int getSizeModifierToStealth() {
    return sizeModifierToStealth;
  }

  /**
   * @return the space
   */
  public double getSpace() {
    return space;
  }

  /**
   * @return the naturalReach
   */
  public double getNaturalReach() {
    return naturalReach;
  }

  /**
   * The typical height/length in imperial measurements.
   *
   * @return the typicalHeightLength
   */
  public String getTypicalHeightLength() {
    return typicalHeightLength;
  }

  /**
   * @return the typicalWeight
   */
  public String getTypicalWeight() {
    return typicalWeight;
  }
}
