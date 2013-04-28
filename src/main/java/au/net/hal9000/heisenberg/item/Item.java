package au.net.hal9000.heisenberg.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;
import java.util.Properties;

// Persistence
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;

import org.apache.log4j.Logger;

// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.*;

/*
 * Item:
 *
 * An item has a globally pseudo-unique identifier.
 * An item has a name which is text.
 * An item has a description which is text.
 * An item has a weight which is measured in pounds which defaults to zero.
 * An item has a valueBase measured in coins which defaults to zero.
 * An item has a location (e.g. ground, a bag, an arm)
 * (discuss) An item has a condition (0-100) 100=new, 0=worn away.  Or hit points?
 * An item may have any number of defences.
 * An item by default does not offer protection (e.g armour, magic resistance)
 * An item may offer any number of protections.
 Actions
 * An item may be dropped, which will cause the location to be changed to
 the ground below the object.
 * An item may be damaged by fire which will...
 * An item may be damaged by acid which will...
 * An item may be repaired which will ...
 */
/**
 * @author bruins
 *
 */
/**
 * @author bruins
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger  logger = Logger.getLogger(Item.class.getName());
    private static String packageName = Factory.class.getPackage().getName();


    // Initialise as many values as possible.
    /**
     * The ID of the object. JPA doesn't care if we change this.
     */
    // Note: Id is required so UI getIndexOfChild() will work
    // when two objects have the same properties.
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    /**
     * Use by JPA.
     */
    @Id
    @Column(name = "jpaid", nullable = false)
    @GeneratedValue
    private long jpaId;
    private Properties properties = new Properties();
    private float weightBase = 0;
    private float volumeBase = 0;
    private Currency valueBase = new Currency();
    private String name = null;
    private String description = null;
    private ItemContainer container = null;
    private float hitPoints = 0F;
    /** Who owns this item. null means no-one. */
    private Item owner = null;
    private Point3d position = null;

    // Constructors
    public Item() {
        super();
        ItemProperty.setMagical(this, false);
        ItemProperty.setClothing(this, false);
        ItemProperty.setLiving(this, false);
        ItemProperty.setHumanoidFood(this, false);
    }

    public Item(final String pName) {
        this();
        this.name = pName;
    }

    public Item(final String pName, final String pDescription) {
        this(pName);
        this.description = pDescription;
    }

    // Getters and Setters

    /**
     * get the Table ID of the item.<br>
     * JPA requires a primary key.
     * 
     * @return the Id
     */
    public long getJpaId() {
        return jpaId;
    }

    /**
     * Set the Table Id. <br>
     * JPA requires a primary key.
     * 
     * @param pJpaId
     *            the Id to set
     */
    public void setJpaId(final long pJpaId) {
        this.jpaId = pJpaId;
    }

    /**
     * get the ID of the item. We need to be ale to tell the difference between<br>
     * two items with the same properties.
     * 
     * @return the Id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set the Id We need to be ale to tell the difference between<br>
     * two items with the same properties.<br>
     * 
     * @param pId
     *            the Id to set
     */
    public void setId(final UUID pId) {
        this.id = pId;
    }

    // property related
    /**
     * @return the properties - A set of key/value pairs that don't warrant real
     *         setters and getters.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *            set the properties object. A set of key/value pairs that don't
     *            warrant real setters and getters.
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Get a property
     * 
     * @param key
     *            key name
     * @return the value
     */
    public Object getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Set a property
     * 
     * @param key
     *            key name
     * @param value
     *            the value to set
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    /**
     * Remove a property
     * 
     * @param key
     *            key name
     */
    public void removeProperty(String key) {
        properties.remove(key);
    }

    // weight related

    /**
     * The total weight. For simple items the weight is the weightBase. Will be
     * overridden by collections.
     * 
     * @return the total weight
     */
    public float getWeight() {
        return weightBase;
    }

    /**
     * @return weight before addition of other items such as those carried
     */
    public float getWeightBase() {
        return weightBase;
    }

    /**
     * @param baseWeight
     *            weight before addition of other items such as those carried.
     */
    public void setWeightBase(final float baseWeight) {
        this.weightBase = baseWeight;
    }

    // volume related

    /**
     * For simple items the weight is the weightBase. Will be overridden by
     * collections
     * 
     * @return the amount of 3D space that this item occupies.
     */

    public float getVolume() {
        return volumeBase;
    }

    /**
     * volume before addition of other items such as those carried.
     * 
     * @return the volume that this item occupies without any contained items.
     */
    public float getVolumeBase() {
        return volumeBase;
    }

    /**
     * Set the volume before addition of other items such as those carried.
     * 
     * @param volumeBase
     */
    public void setVolumeBase(float volumeBase) {
        this.volumeBase = volumeBase;
    }

    /**
     * value before addition of other items such as those carried.
     * 
     * @return The value before any contained items.
     */
    public Currency getValueBase() {
        return valueBase;
    }

    /**
     * For simple items the value is the valueBase. Will be overridden by
     * collections
     * 
     * @return the total value including contained items.
     */
    public Currency getValue() {
        return valueBase;
    }

    /**
     * value before addition of other items such as those carried.
     * 
     * @param pValueBase
     *            The value before any contained items.
     */
    public void setValueBase(final Currency pValueBase) {
        this.valueBase = pValueBase;
    }

    /**
     * Get the current container.
     * 
     * @return the current container {@link ItemContainer}.
     */
    public ItemContainer getContainer() {
        return this.container;
    }

    /**
     * Set the current container.
     * 
     * @param container
     *            the new container {@link ItemContainer}.
     */
    public void setContainer(ItemContainer container) {
        this.container = container;
    }

    /**
     * @return the structural integrity / health.
     */
    public float getHitPoints() {
        return hitPoints;
    }

    /**
     * @param hitPoints
     *            the structural indegrity / health.
     */
    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    /** @return The owner of this item */
    public Item getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            set the owner of this item
     */
    public void setOwner(Item owner) {
        this.owner = (Item) owner;
    }

    /**
     * 
     * @return return the position.
     */
    public Point3d getPosition() {
        return position;
    }

    public void setPosition(Point3d position) {
        this.position = position;
    }

    // misc methods

    /**
     * get the name of the item.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * 
     * @param pName
     *            the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * Get the description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     * 
     * @param pDescription
     *            the description
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
     * Return true iff items are equal.
     * 
     * @param other
     *            other item
     * @return true iff items are equal.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Item)) {
            return false;
        }
        Item otherItem = (Item) other;
        return id.equals(otherItem.getId());
    }

    /**
     * Attempt to unlink the item from everything so that it can be garbage
     * collected. Won't work if anything is referencing this item.
     */
    public void beNot() {
        // TODO - Help Required - How do I delete an object that
        // may be referenced by other objects?
        // Perhaps listeners on the containers?
        ItemContainer container = (ItemContainer) this.getContainer();
        if (container != null) {
            container.remove(this);
        }
    }

    /**
     * Chage the position of the item within the ItemContainer.
     * @param expectedPosition the hoped for position.
     * 
     * Note: The request can fail or partially compete.
     * E.g Can't pass through walls.
     */
    public void move(Point3d expectedPosition) {
        // TODO It is the container's job to move the item
        if (container == null){
            logger.error("No ItemConainer - Can't move");
        }
        else{
            container.moveItem(this,expectedPosition);
        }
    }

    /**
     * @return A short identifying string. e.g. 'Cookie', 'John Smith'
     * 
     */

    public String toString() {
        String string = getName();
        if ((string == null) || (string.length() == 0)) {
            string = this.getClass().getSimpleName().toLowerCase();
        }
        return string;
    }

    /**
     * @return A description. e.g. 'A lit candle'
     */
    public String detailedDescription() {
        StringBuilder str = new StringBuilder(128);
        String temp;

        temp = this.getName();
        if (temp != null) {
            str.append("Name: " + temp + "\n");
        }
        temp = this.getDescription();
        if (temp != null) {
            str.append("Description: " + temp + "\n");
        }
        str.append("Weight Base: " + this.getWeightBase() + "\n"
                + "Volume Base: " + this.getVolumeBase() + "\n");

        Currency valueBase = this.getValueBase();
        if (valueBase != null) {
            str.append("Value Base: " + valueBase + "\n");
        }
        ItemContainer container = this.getContainer();
        if (container != null) {
            str.append("Container: " + container.getName() + "\n");
        }
        Point3d position = this.getPosition();
        if (position != null) {
            str.append("Position: " + position + "\n");
        }
        return str.toString();
    }

    /** Find items that match the criteria */
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }

    // TODO what about Weight/Volume/Currency etc
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // TODO what about Weight/Volume/Currency etc
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        // our "pseudo-constructor"
        in.defaultReadObject();
        // now we are a "live" object again, so let's run rebuild and start
    }

    // Store the object in a file
    public void freezeToFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
        out.close();
    }

    // TODO thawFormFile

    /**
     * This is used for tree traversal.
     * 
     * @return True unless this Item has child Items.<br>
     */
    public boolean isLeaf() {
        return true;
    }

    /**
     * Create a new Item of the specified type.
     * 
     * @param type
     *            the type of Item to create.
     * @return the new Item.
     */
    public final boolean instanceOf(String type) {
        try {
            Class<?> itemClass = Class.forName(packageName + "." + type);
            return itemClass.isInstance(this);
        } catch (ClassNotFoundException e) {
            // NOP
            ;
        }
        return false;
    }
    
    /**
     * Some text that describes the properties of this object.
     * @return the detailed description.
     */
    // TODO complete.  StringBuilder.
    public String getDetailedDescription() {
        String full_desc;
        final String desc = getDescription();
        final String name = getName();

        // Otherwise try to get the name.
        if ((desc != null) && (desc.length() > 0)) {
            full_desc = desc;
        } else if ((name != null) && (name.length() > 0)) {
            full_desc = name;
        } else {
            full_desc = this.getClass().getSimpleName();
        }
        return full_desc;
    }
    
    
    /**
     * Shallow copy properties from one object to another.
     * 
     * @param item
     */
    public void setAllFrom(Item item) {
        setName(item.getName());
        setDescription(item.getDescription());
        setWeightBase(item.getWeightBase());
        setVolumeBase(item.getVolumeBase());
        setValueBase(item.getValueBase());
        setContainer(item.getContainer());
        setHitPoints(item.getHitPoints());
        setOwner(item.getOwner());
        setPosition(item.getPosition());
    }

}
