package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.api.Item;

/** Miscellaneous properties of an Item that don't deserve their own field. */
public final class ItemProperty {

  /** Value TRUE is used because Jackson was converting boolean to strings */
  public static final String TRUE_FLAG = "ANYTHING";

  /** ideal value for health metric. */
  public static final float HEALTH_METRIC_IDEAL = 100f;

  /** Field AERATION. (value is ""aeration"") */
  static final String AERATION = "aeration";

  /** Field ENTERTAINMENT. (value is ""entertainment"") */
  static final String ENTERTAINMENT = "entertainment";

  /** Field HYDRATION. (value is ""hydration"") */
  static final String HYDRATION = "hydration";

  /** Field IS_CLOTHING. (value is ""is clothing"") */
  private static final String IS_CLOTHING = "is clothing";

  /** Field IS_HUMANOID_FOOD. (value is ""is humanoid food"") */
  private static final String IS_HUMANOID_FOOD = "is humanoid food";

  /** Field IS_LIVING. (value is ""is living"") */
  private static final String IS_LIVING = "is living";

  /** Field IS_MAGICAL. (value is ""is magical"") */
  private static final String IS_MAGICAL = "is magical";

  /** Field NOURISHMENT. (value is ""nourishment"") */
  static final String NOURISHMENT = "nourishment";

  /** Field REST. (value is ""rest"") */
  static final String REST = "rest";

  /** Utility Class constructor. */
  private ItemProperty() {
    super();
  }

  // Aeration
  /**
   * A measure of how well breathed the entity is. Ideal is 100.
   *
   * @param item the item we are changing the property of.
   * @param amount the level.
   */
  public static void setAeration(Item item, float amount) {
    item.setProperty(AERATION, amount);
  }

  /**
   * Get aeration.
   *
   * @param item the item we are changing the property of.
   * @return float
   */
  public static float getAeration(Item item) {
    return (Float) item.getProperty(AERATION);
  }

  /**
   * Alter the property by the delta amount.
   *
   * @param item the item we are changing the property of.
   * @param delta the amount we wish to change this property.
   * @return float
   */
  public static float alterAeration(Item item, float delta) {
    float total = getAeration(item);
    total += delta;
    setAeration(item, total);
    return total;
  }

  // Clothing
  /**
   * @param item the item we are changing the property of.
   */
  public static void setClothing(Item item) {
    item.setProperty(IS_CLOTHING, TRUE_FLAG);
  }

  /**
   * Determine if is clothing.
   *
   * @param item the item we are changing the property of.
   * @return clothing the status
   */
  public static boolean isClothing(Item item) {
    return null != item.getProperty(IS_CLOTHING);
  }

  /**
   * Remove clothing.
   *
   * @param item the item we are changing the property of.
   */
  public static void clearClothing(Item item) {
    item.removeProperty(IS_CLOTHING);
  }

  // Entertainment
  /**
   * A measure of how well breathed the entity is. Ideal is 100.
   *
   * @param item the item we are changing the property of.
   * @param amount the level.
   */
  public static void setEntertainment(Item item, float amount) {
    item.setProperty(ENTERTAINMENT, amount);
  }

  /**
   * Get entertainment.
   *
   * @param item the item we are changing the property of.
   * @return float
   */
  public static float getEntertainment(Item item) {
    return (Float) item.getProperty(ENTERTAINMENT);
  }

  /**
   * Alter the property by the delta amount.
   *
   * @param item the item we are changing the property of.
   * @param delta the amount we wish to change this property.
   * @return float
   */
  public static float alterEntertainment(Item item, float delta) {
    float total = getEntertainment(item);
    total += delta;
    setEntertainment(item, total);
    return total;
  }

  // HumanoidFood
  /**
   * Set humanoid food.
   *
   * @param item the item we are changing the property of.
   * @param isHumanoidFood the new status
   */
  public static void setHumanoidFood(Item item) {
    item.setProperty(IS_HUMANOID_FOOD, TRUE_FLAG);
  }

  /**
   * Determine if is humanoid food.
   *
   * @param item the item we are changing the property of.
   * @return isHumanoidFood the status
   */
  public static boolean isHumanoidFood(Item item) {
    return null != item.getProperty(IS_HUMANOID_FOOD);
  }

  /** Remove humanoid food.@param item the item we are changing the property of. */
  public static void clearHumanoidFood(Item item) {
    item.removeProperty(IS_HUMANOID_FOOD);
  }

  // Hydration
  /**
   * A measure of how well breathed the entity is. Ideal is 100.
   *
   * @param item the item we are changing the property of.
   * @param amount the level.
   */
  public static void setHydration(Item item, float amount) {
    item.setProperty(HYDRATION, amount);
  }

  /**
   * Get hydration.
   *
   * @param item the item we are changing the property of.
   * @return float
   */
  public static float getHydration(Item item) {
    return (Float) item.getProperty(HYDRATION);
  }

  /**
   * Alter the property by the delta amount.
   *
   * @param item the item we are changing the property of.
   * @param delta the amount we wish to change this property.
   * @return float
   */
  public static float alterHydration(Item item, float delta) {
    float total = getHydration(item);
    total += delta;
    setHydration(item, total);
    return total;
  }

  // Living
  /**
   * Set living.
   *
   * @param item the item we are changing the property of.
   * @param living the new status
   */
  public static void setLiving(Item item) {
    item.setProperty(IS_LIVING, TRUE_FLAG);
  }

  /**
   * Determine if is living.
   *
   * @param item the item we are changing the property of.
   * @return living the status
   */
  public static boolean isLiving(Item item) {
    return null != item.getProperty(IS_LIVING);
  }

  /**
   * Remove is living.
   *
   * @param item the item we are changing the property of.
   */
  public static void clearLiving(Item item) {
    item.removeProperty(IS_LIVING);
  }

  // Magical
  /**
   * Set magical.
   *
   * @param item the item we are changing the property of.
   */
  public static void setMagical(Item item) {
    item.setProperty(IS_MAGICAL, TRUE_FLAG);
  }

  /**
   * Determine if is magical.
   *
   * @param item the item we are changing the property of.
   * @return magical the status
   */
  public static boolean isMagical(Item item) {
    return null != item.getProperty(IS_MAGICAL);
  }

  /**
   * Remove magical.
   *
   * @param item the item we are changing the property of.
   */
  public static void clearMagical(Item item) {
    item.removeProperty(IS_MAGICAL);
  }

  // Nourishment
  /**
   * A measure of how well breathed the entity is. Ideal is 100.
   *
   * @param item the item we are changing the property of.
   * @param amount the level.
   */
  public static void setNourishment(Item item, float amount) {
    item.setProperty(NOURISHMENT, amount);
  }

  /**
   * Get nourishment.
   *
   * @param item the item we are changing the property of.
   * @return float
   */
  public static float getNourishment(Item item) {
    return (Float) item.getProperty(NOURISHMENT);
  }

  /**
   * Alter the property by the delta amount.
   *
   * @param item the item we are changing the property of.
   * @param delta the amount we wish to change this property.
   * @return float
   */
  public static float alterNourishment(Item item, float delta) {
    float total = getNourishment(item);
    total += delta;
    setNourishment(item, total);
    return total;
  }

  // Rest
  /**
   * A measure of how well breathed the entity is. Ideal is 100.
   *
   * @param item the item we are changing the property of.
   * @param amount the level.
   */
  public static void setRest(Item item, float amount) {
    item.setProperty(REST, amount);
  }

  /**
   * Get rest.
   *
   * @param item the item we are changing the property of.
   * @return float
   */
  public static float getRest(Item item) {
    return (Float) item.getProperty(REST);
  }

  /**
   * Alter the property by the delta amount.
   *
   * @param item the item we are changing the property of.
   * @param delta the amount we wish to change this property.
   * @return float
   */
  public static float alterRest(Item item, float delta) {
    float total = getRest(item);
    total += delta;
    setRest(item, total);
    return total;
  }

  // misc
  /**
   * Method alterPropertyByName.
   *
   * @param item Entity
   * @param propertyName String
   * @param propertyDelta float
   */
  public static void alterPropertyByName(Item item, String propertyName, float propertyDelta) {

    if (null == propertyName) {
      throw new RuntimeException("Missing property name");
    } else if (AERATION.equals(propertyName)) {
      ItemProperty.alterAeration(item, propertyDelta);
    } else if (ENTERTAINMENT.equals(propertyName)) {
      ItemProperty.alterEntertainment(item, propertyDelta);
    } else if (HYDRATION.equals(propertyName)) {
      ItemProperty.alterHydration(item, propertyDelta);
    } else if (NOURISHMENT.equals(propertyName)) {
      ItemProperty.alterNourishment(item, propertyDelta);
    } else if (REST.equals(propertyName)) {
      ItemProperty.alterRest(item, propertyDelta);
    } else {
      throw new RuntimeException("Invalid property " + propertyName);
    }
  }
}
