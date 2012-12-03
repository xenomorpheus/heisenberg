package au.net.hal9000.heisenberg.item.property;

import au.net.hal9000.heisenberg.item.IItem;

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
    public static void setMagical(IItem item, boolean isMagical) {
        item.setProperty(IS_MAGICAL, isMagical);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return magical the status
     */

    public static boolean isMagical(IItem item) {
        return (Boolean) item.getProperty(IS_MAGICAL);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeMagical(IItem item) {
        item.removeProperty(IS_MAGICAL);
    }

    // Clothing
    /**
     * @param item
     *            the item we are changing the property of.
     * @param clothing
     *            the new status
     */
    public static void setClothing(IItem item, boolean clothing) {
        item.setProperty(IS_CLOTHING, clothing);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return clothing the status
     */

    public static boolean isClothing(IItem item) {
        return (Boolean) item.getProperty(IS_CLOTHING);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeClothing(IItem item) {
        item.removeProperty(IS_CLOTHING);
    }
    // Living
    /**
     * @param item
     *            the item we are changing the property of.
     * @param living
     *            the new status
     */
    public static void setLiving(IItem item, boolean living) {
        item.setProperty(IS_LIVING, living);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return living the status
     */

    public static boolean isLiving(IItem item) {
        return (Boolean) item.getProperty(IS_LIVING);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeLiving(IItem item) {
        item.removeProperty(IS_LIVING);
    }

    // HumanoidFood
    /**
     * @param item
     *            the item we are changing the property of.
     * @param isHumanoidFood
     *            the new status
     */
    public static void setHumanoidFood(IItem item, boolean isHumanoidFood) {
        item.setProperty(IS_HUMANOID_FOOD, isHumanoidFood);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     * @return isHumanoidFood the status
     */

    public static boolean isHumanoidFood(IItem item) {
        return (Boolean) item.getProperty(IS_HUMANOID_FOOD);
    }

    /**
     * @param item
     *            the item we are changing the property of.
     */

    public static void removeHumanoidFood(IItem item) {
        item.removeProperty(IS_HUMANOID_FOOD);
    }


}
