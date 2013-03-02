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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements IItem, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

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

    @Column(name = "name")
    private String name = null;
    private String description = null;
    private float weightBase = 0;
    private float volumeBase = 0;
    private Currency valueBase = new Currency();
    private IItemContainer location = null;
    private float hitPoints = 0F;
    /** Who owns this item. null means no-one. */
    private Item owner = null;
    private Properties properties = new Properties();

    // Class methods
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

    /**
     * {@inheritDoc}
     * 
     * @throws CloneNotSupportedException
     */
    @Override
    public IItem clone() throws CloneNotSupportedException {
        Item clone = (Item) super.clone();
        this.clone(clone);
        return clone;
    }

    /** {@inheritDoc} */
    @Override
    public IItem clone(IItem clone) throws CloneNotSupportedException {
        // Clones must have a unique ID
        clone.setId(UUID.randomUUID());

        // Make sure the cloning is deep, not shallow.
        // e.g. set the non-mutable, non-primitives

        // valueBase
        final Currency valueBase = this.getValueBase();
        if (valueBase == null) {
            clone.setValueBase(null);
        } else {
            clone.setValueBase(valueBase.clone());
        }

        // Properties
        clone.setProperties((Properties) this.getProperties().clone());

        // location is *NOT* cloned.
        return clone;
    }

    /** {@inheritDoc} */
    @Override
    public Properties getProperties() {
        return properties;
    }

    /** {@inheritDoc} */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    // static methods

    // instance methods

    // Getters and Setters

    /** {@inheritDoc} */
    @Override
    public long getJpaId() {
        return jpaId;
    }

    /** {@inheritDoc} */
    @Override
    public void setJpaId(final long pJpaId) {
        this.jpaId = pJpaId;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getId() {
        return id;
    }

    /** {@inheritDoc} */
    @Override
    public void setId(final UUID pId) {
        this.id = pId;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void setName(final String pName) {
        if (pName == null) {
            throw new IllegalArgumentException("Not null");
        }
        this.name = pName;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return description;
    }

    /** {@inheritDoc} */
    @Override
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /** {@inheritDoc} */
    @Override
    public float getWeightBase() {
        return weightBase;
    }

    /** {@inheritDoc} */
    @Override
    public void setWeightBase(final float baseWeight) {
        this.weightBase = baseWeight;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolume() {
        return volumeBase;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolumeBase() {
        return volumeBase;
    }

    /** {@inheritDoc} */
    @Override
    public void setVolumeBase(float volumeBase) {
        this.volumeBase = volumeBase;
    }

    /** {@inheritDoc} */
    @Override
    public Currency getValueBase() {
        return valueBase;
    }

    /** {@inheritDoc} */
    @Override
    public Currency getValue() {
        return valueBase;
    }

    /** {@inheritDoc} */
    @Override
    public void setValueBase(final Currency pValueBase) {
        if (pValueBase == null) {
            throw new IllegalArgumentException("Not null");
        }
        this.valueBase = pValueBase;
    }

    /** {@inheritDoc} */
    @Override
    public IItemContainer getLocation() {
        return this.location;
    }

    /** {@inheritDoc} */
    @Override
    public void setLocation(IItemContainer location) {
        this.location = location;
    }

    /** {@inheritDoc} */
    @Override
    public float getHitPoints() {
        return hitPoints;
    }

    /** {@inheritDoc} */
    @Override
    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    /** {@inheritDoc} */
    @Override
    public Item getOwner() {
        return owner;
    }

    /** {@inheritDoc} */
    @Override
    public void setOwner(IItem owner) {
        this.owner = (Item) owner;
    }

    /** {@inheritDoc} */
    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    /** {@inheritDoc} */
    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    /** {@inheritDoc} */
    @Override
    public void removeProperty(String key) {
        properties.remove(key);
    }

    // misc methods

    /** {@inheritDoc} */
    @Override
    public float getWeight() {
        return weightBase;
    }

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public void beNot() {
        // TODO - Help Required - How do I delete an object that
        // may be referenced by other objects?
        // Perhaps listeners on the containers?
        IItemContainer location = (IItemContainer) this.getLocation();
        if (location != null) {
            location.remove(this);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /** {@inheritDoc} */
    @Override
    public String detailedDescription() {
        String str = new String();
        String temp;

        temp = this.getName();
        if (temp != null) {
            str += "Name: " + temp + "\n";
        }
        temp = this.getDescription();
        if (temp != null) {
            str += "Description: " + temp + "\n";
        }
        str += "Weight Base: " + this.getWeightBase() + "\n" + "Volume Base: "
                + this.getVolumeBase() + "\n";

        Currency valueBase = this.getValueBase();
        if (valueBase != null) {
            str += "Value Base: " + valueBase + "\n";
        }
        IItemContainer location = this.getLocation();
        if (location != null) {
            str += "Location: " + location.getName() + "\n";
        }
        return str;
    }

    // Find items that match the criteria
    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public boolean isLeaf() {
        return true;
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
        setLocation(item.getLocation());
        setHitPoints(item.getHitPoints());
        setOwner(item.getOwner());
    }

}
