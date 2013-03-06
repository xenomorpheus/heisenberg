package au.net.hal9000.heisenberg.item;

import java.util.Properties;
import java.util.UUID;

import au.net.hal9000.heisenberg.units.Currency;

/**
 * @author bruins
 */
public interface IItem {

    // Getters and Setters

    /**
     * get the Table ID of the item.<br>
     * JPA requires a primary key.
     * @return the Id
     */
    long getJpaId();

    /**
     * Set the Table Id. <br>
     * JPA requires a primary key.
     * @param id
     *            the Id to set
     */
    void setJpaId(long id);
    /**
     * get the ID of the item.
     * We need to be ale to tell the difference between<br>
     * two items with the same properties.
     * @return the Id
     */
    UUID getId();

    /**
     * Set the Id
     * We need to be ale to tell the difference between<br>
     * two items with the same properties.<br>
     * @param id the Id to set
     */
    void setId(UUID id);

    /**
     * get the name of the item.
     * 
     * @return the name
     */
    String getName();

    /**
     * Set the name
     * 
     * @param pName
     *            the name to set
     */
    void setName(String pName);

    /**
     * Get the description
     * 
     * @return the description
     */
    String getDescription();

    /**
     * Set the description
     * 
     * @param pDescription
     *            the description
     */
    void setDescription(String pDescription);

    // weight related
    /**
     * @return weight before addition of other items such as those carried
     */
    float getWeightBase();

    /**
     * @param baseWeight
     *            weight before addition of other items such as those carried.
     */
    void setWeightBase(float baseWeight);

    /**
     * The total weight. For simple items the weight is the weightBase. Will be
     * overridden by collections.
     * 
     * @return the total weight
     */
    float getWeight();

    // volume related
    /**
     * volume before addition of other items such as those carried.
     * 
     * @return the volume that this item occupies without any contained items.
     */
    float getVolumeBase();

    void setVolumeBase(float volumeWeight);

    /**
     * For simple items the weight is the weightBase. Will be overridden by
     * collections
     * 
     * @return the amount of 3D space that this item occupies.
     */
    float getVolume();

    // valueBase
    /**
     * value before addition of other items such as those carried.
     * 
     * @return The value before any contained items.
     */
    Currency getValueBase();

    /**
     * For simple items the value is the valueBase. Will be overridden by
     * collections
     * 
     * @return the total value including contained items.
     */
    Currency getValue();

    void setValueBase(Currency pValueBase);

    // location
    IItemContainer getLocation();

    void setLocation(IItemContainer location);

    // hitPoints
    void setHitPoints(float hitPoints);

    float getHitPoints();

    IItem getOwner();

    void setOwner(IItem owner);

    /**
     * Get a property
     * @param key key name
     * @return the value
     */
    Object getProperty(String key);

    /**
     * Set a property
     * @param key key name
     * @param value the value to set
     */
    void setProperty(String key, Object value);
    
    /**
     * Remove a property
     * @param key key name
     */
    void removeProperty(String key);
    
    public Properties getProperties();
    /**
     * 
     * @param properties set the properties object.
     */
    public void setProperties(Properties properties);
    
    // misc methods

    /**
     * Return true iff items are equal.
     * 
     * @param other
     *            other item
     * @return true iff items are equal.
     */
    boolean equals(Object other);

    /**
     * Attempt to unlink the item from everything so that it can be garbage
     * collected. Won't work if anything is referencing this item.
     */
    void beNot();

    String toString();

    String detailedDescription();

    // Find items that match the criteria
    void accept(ItemVisitor visitor);

    /**
     * This is used for tree traversal.
     * 
     * @return True unless this IItem has child Items.<br>
     */
    boolean isLeaf();

}
