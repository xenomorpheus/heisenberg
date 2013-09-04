package au.net.hal9000.heisenberg.item.property;

import java.util.Vector;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Item;

public class ItemProperty {
    public static String AERATION = "aeration";
    public static String ENTERTAINMENT = "entertainment";
    public static String HYDRATION = "hydration";
    public static String IS_CLOTHING = "is clothing";
    public static String IS_HUMANOID_FOOD = "is humanoid food";
    public static String IS_LIVING = "is living";
    public static String IS_MAGICAL = "is magical";
    public static String NOURISHMENT = "nourishment";
    public static String REST = "rest";

    // Aeration
    /**
     * A measure of how well breathed the entity is. Ideal is 100.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param amount
     *            the level.
     */
    public static void setAeration(Item item, float amount) {
        item.setProperty(AERATION, amount);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static float getAeration(Item item) {
        return (Float) item.getProperty(AERATION);
    }

    /**
     * Alter the property by the delta amount.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param delta
     *            the amount we wish to change this property.
     */

    public static float alterAeration(Item item, float delta) {
        float total = getAeration(item);
        total += delta;
        setAeration(item, total);
        return total;
    }

    // Clothing
    /**
     * @param item
     *            the item we are changing the property of.
     * @param clothing
     *            the new status
     */
    public static void setClothing(Item item, boolean clothing) {
        item.setProperty(IS_CLOTHING, clothing);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return clothing the status
     */

    public static boolean isClothing(Item item) {
        return (Boolean) item.getProperty(IS_CLOTHING);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeClothing(Item item) {
        item.removeProperty(IS_CLOTHING);
    }

    // Entertainment
    /**
     * A measure of how well breathed the entity is. Ideal is 100.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param amount
     *            the level.
     */
    public static void setEntertainment(Item item, float amount) {
        item.setProperty(ENTERTAINMENT, amount);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static float getEntertainment(Item item) {
        return (Float) item.getProperty(ENTERTAINMENT);
    }

    /**
     * Alter the property by the delta amount.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param delta
     *            the amount we wish to change this property.
     */

    public static float alterEntertainment(Item item, float delta) {
        float total = getEntertainment(item);
        total += delta;
        setEntertainment(item, total);
        return total;
    }

    // HumanoidFood
    /**
     * @param item
     *            the item we are changing the property of.
     * @param isHumanoidFood
     *            the new status
     */
    public static void setHumanoidFood(Item item, boolean isHumanoidFood) {
        item.setProperty(IS_HUMANOID_FOOD, isHumanoidFood);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return isHumanoidFood the status
     */

    public static boolean isHumanoidFood(Item item) {
        return (Boolean) item.getProperty(IS_HUMANOID_FOOD);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeHumanoidFood(Item item) {
        item.removeProperty(IS_HUMANOID_FOOD);
    }

    // Hydration
    /**
     * A measure of how well breathed the entity is. Ideal is 100.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param amount
     *            the level.
     */
    public static void setHydration(Item item, float amount) {
        item.setProperty(HYDRATION, amount);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static float getHydration(Item item) {
        return (Float) item.getProperty(HYDRATION);
    }

    /**
     * Alter the property by the delta amount.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param delta
     *            the amount we wish to change this property.
     */

    public static float alterHydration(Item item, float delta) {
        float total = getHydration(item);
        total += delta;
        setHydration(item, total);
        return total;
    }

    // Living
    /**
     * @param item
     *            the item we are changing the property of.
     * @param living
     *            the new status
     */
    public static void setLiving(Item item, boolean living) {
        item.setProperty(IS_LIVING, living);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return living the status
     */

    public static boolean isLiving(Item item) {
        return (Boolean) item.getProperty(IS_LIVING);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeLiving(Item item) {
        item.removeProperty(IS_LIVING);
    }

    // Magical
    /**
     * @param item
     *            the item we are changing the property of.
     * @param isMagical
     *            the new status
     */
    public static void setMagical(Item item, boolean isMagical) {
        item.setProperty(IS_MAGICAL, isMagical);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return magical the status
     */

    public static boolean isMagical(Item item) {
        return (Boolean) item.getProperty(IS_MAGICAL);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeMagical(Item item) {
        item.removeProperty(IS_MAGICAL);
    }

    // Nourishment
    /**
     * A measure of how well breathed the entity is. Ideal is 100.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param amount
     *            the level.
     */
    public static void setNourishment(Item item, float amount) {
        item.setProperty(NOURISHMENT, amount);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static float getNourishment(Item item) {
        return (Float) item.getProperty(NOURISHMENT);
    }

    /**
     * Alter the property by the delta amount.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param delta
     *            the amount we wish to change this property.
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
     * @param item
     *            the item we are changing the property of.
     * @param amount
     *            the level.
     */
    public static void setRest(Item item, float amount) {
        item.setProperty(REST, amount);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static float getRest(Item item) {
        return (Float) item.getProperty(REST);
    }

    /**
     * Alter the property by the delta amount.
     * 
     * @param item
     *            the item we are changing the property of.
     * @param delta
     *            the amount we wish to change this property.
     */

    public static float alterRest(Item item, float delta) {
        float total = getRest(item);
        total += delta;
        setRest(item, total);
        return total;
    }

    // misc
    public static void alterPropertyByName(Entity entity, String propertyName,
            float propertyDelta) {

        if (propertyName == null) {
            throw new RuntimeException("Missing property name");
        } else if (AERATION.equals(propertyName)) {
            ItemProperty.alterAeration(entity, propertyDelta);
        } else if (ENTERTAINMENT.equals(propertyName)) {
            ItemProperty.alterEntertainment(entity, propertyDelta);
        } else if (HYDRATION.equals(propertyName)) {
            ItemProperty.alterHydration(entity, propertyDelta);
        } else if (NOURISHMENT.equals(propertyName)) {
            ItemProperty.alterNourishment(entity, propertyDelta);
        } else if (REST.equals(propertyName)) {
            ItemProperty.alterRest(entity, propertyDelta);
        } else {
            throw new RuntimeException("Invalid property " + propertyName);
        }

    }
}
