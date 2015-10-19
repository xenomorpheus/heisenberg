package au.net.hal9000.heisenberg.item.api;

import java.util.Properties;
import java.util.UUID;

import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;
import au.net.hal9000.heisenberg.util.ItemIcon;

public interface Item {
    /** name of this package. Perhaps move this to config */
    static final String PACKAGE_NAME = "au.net.hal9000.heisenberg.item";

    /**
     * Get the current container.
     * 
     * 
     * @return the current container {@link ItemContainer}.
     */
    ItemContainer getContainer();

    /**
     * Set the current container.
     * 
     * @param container
     *            the new container {@link ItemContainer}.
     */
    void setContainer(ItemContainer container);

    /**
     * Get the description.
     * 
     * 
     * @return the description
     */
    String getDescription();

    /**
     * Set the description.
     * 
     * @param description
     *            the description
     */
    void setDescription(String description);

    /**
     * 
     * @return the structural integrity / health.
     */
    float getHitPoints();

    /**
     * @param hitPoints
     *            the structural integrity / health.
     */
    void setHitPoints(float hitPoints);

    /**
     * get the name of the item.
     * 
     * 
     * @return the name
     */
    String getName();

    /**
     * Set the name.
     * 
     * @param name
     *            the name to set
     */
    void setName(String name);

    /**
     * 
     * 
     * @return return the position.
     */
    Position getPosition();

    /**
     * set the position of the item.
     * 
     * @param position
     *            the position object.
     */
    void setPosition(Position position);

    // property related
    /**
     * 
     * @return the properties - A set of key/value pairs that don't warrant real
     *         setters and getters.
     */
    Properties getProperties();

    /**
     * 
     * @param properties
     *            set the properties object. A set of key/value pairs that don't
     *            warrant real setters and getters.
     */
    void setProperties(Properties properties);

    /**
     * Get a property.
     * 
     * @param key
     *            key name
     * 
     * @return the value
     */
    Object getProperty(String key);

    /**
     * Set a property.
     * 
     * @param key
     *            key name
     * @param value
     *            the value to set
     */
    void setProperty(String key, Object value);

    /**
     * Remove a property.
     * 
     * @param key
     *            key name
     */
    void removeProperty(String key);

    // value related
    /**
     * value before addition of other items such as those carried.
     * 
     * 
     * @return The value before any contained items.
     */
    Currency getValueBase();

    /**
     * For simple items the value is the valueBase. Will be overridden by
     * collections
     * 
     * 
     * @return the total value including contained items.
     */
    Currency getValue();

    /**
     * value before addition of other items such as those carried.
     * 
     * @param valueBase
     *            The value before any contained items.
     */
    void setValueBase(Currency valueBase);

    /**
     * For simple items the weight is the weightBase. Will be overridden by
     * collections
     * 
     * 
     * @return the amount of 3D space that this item occupies.
     */

    float getVolume();

    /**
     * volume before addition of other items such as those carried.
     * 
     * 
     * @return the volume that this item occupies without any contained items.
     */
    float getVolumeBase();

    /**
     * Set the volume before addition of other items such as those carried.
     * 
     * @param volumeBase
     *            the new volume.
     */
    void setVolumeBase(float volumeBase);

    /**
     * Set the total weight. For simple items the weight is the weightBase. May
     * be overridden by collections.
     * 
     * 
     * @return the total weight
     */
    float getWeight();

    /**
     * 
     * @return weight before addition of other items such as those carried
     */
    float getWeightBase();

    /**
     * @param weightBase
     *            weight before addition of other items such as those carried.
     */
    void setWeightBase(float weightBase);

    /**
     * Attempt to unlink the item from everything so that it can be garbage
     * collected. Won't work if anything is referencing this item.
     */
    void beNot();

    /**
     * Move the item into the ItemContainer. Note: The request can fail or
     * partially complete.<br>
     * E.g Can't pass through walls.
     * 
     * @param container
     *            the container that will hold this item.
     * @param requestedPosition
     *            the requested position within the container.
     */
    void move(ItemContainer container, Position requestedPosition);

    /**
     * Move the item into the ItemContainer.
     * 
     * @param container
     *            the container that will hold this item.
     */
    void move(ItemContainer container);

    /**
     * 
     * 
     * @return A short identifying string. e.g. 'Cookie', 'John Smith'
     */
    String toString();

    /**
     * 
     * @return A description. e.g. 'A lit candle'
     */
    String detailedDescription();

    /**
     * Visitor Design Pattern.
     * 
     * @param visitor
     *            Item visitor.
     */
    void accept(ItemVisitor visitor);

    /**
     * Change the position by the supplied amount.
     * 
     * @param delta
     */
    void applyDelta(Position delta);

    /**
     * Create a new Item of the specified type.
     * 
     * @param type
     *            the type of Item to create.
     * 
     * @return the new Item.
     */
    boolean instanceOf(String itemType);

    /**
     * get the ID of the item. We need to be ale to tell the difference between
     * <br>
     * two items with the same properties.
     * 
     * 
     * @return the Id
     */
    UUID getId();

    String getSimpleClassName();

    ItemIcon getItemIcon();

    void setItemIcon(ItemIcon itemIcon);

    /**
     * return the class name and package for this Item type.
     * 
     * @param type
     * @return
     */
    public static String getClassForType(String type) {
        ItemClassConfiguration itemTypeConfig = Configuration.lastConfig()
                .getItemClassConfiguration(type);
        String javaClassSuffix = null;
        if (itemTypeConfig != null) {
            javaClassSuffix = itemTypeConfig.getJavaClass();
        }
        if (javaClassSuffix == null) {
            javaClassSuffix = type; // Assume no subclass.
        }
        return PACKAGE_NAME + "." + javaClassSuffix;
    }

    /**
     * get the Table ID of the item.<br>
     * JPA requires a primary key.
     * 
     * 
     * @return the Id
     */
    long getJpaId();

    /**
     * Change the position of the item within the ItemContainer.
     * 
     * Note: The request can fail or partially complete.<br>
     * E.g Can't pass through walls.
     * 
     * @param requestedPosition
     *            the requested position within the container.
     * 
     */
    void moveWithinContainer(Position requestedPosition);

    /**
     * Shallow copy properties from one object to another.<br>
     * In the object inheritance chain, there is one of these methods at each
     * level where there are local fields.<br>
     * Each setAllFrom is responsible for copying the fields directly defined in
     * this class, and calling other setAllFrom in other levels.
     * 
     * @param other
     *            Item to copy attributes from.
     */
    void setAllFrom(Item other);

}
