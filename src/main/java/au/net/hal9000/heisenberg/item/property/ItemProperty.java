package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.Item;

public class ItemProperty {
	public static String IS_MAGICAL = "is magical";
	public static String IS_CLOTHING = "is clothing";
	public static String IS_LIVING = "is living";
	public static String IS_HUMANOID_FOOD = "is humanoid food";

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


}
