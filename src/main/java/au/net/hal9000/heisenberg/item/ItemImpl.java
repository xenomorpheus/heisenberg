package au.net.hal9000.heisenberg.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ItemIcon;

/**
 * Base abstract class for all items in this world.
 * 
 * The Item package is the base class for the game objects.<br>
 * e.g. People, equipment, locations, etc.
 * 
 * See the ItemContainer class for items that may contain other items, e.g.
 * bags, scabbards, even people.
 * 
 * An item has a globally pseudo-unique identifier.<br>
 * An item has a name which is text.<br>
 * An item has a description which is text.<br>
 * An item has a weight which is measured in pounds which defaults to zero.<br>
 * An item has a valueBase measured in coins which defaults to zero.<br>
 * An item has a location (e.g. ground, a bag, an arm)<br>
 * (discuss) An item has a condition (0-100) 100=new, 0=worn away. Or hit
 * points? An item may have any number of defences.<br>
 * An item by default does not offer protection (e.g armour, magic resistance)<br>
 * An item may offer any number of protections.<br>
 * 
 * Actions<br>
 * An item may be dropped, which will cause the location to be changed to the
 * ground below the object.<br>
 * An item may be damaged by fire which will...<br>
 * An item may be damaged by acid which will...<br>
 * An item may be repaired which will ...<br>
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */

@MappedSuperclass
public abstract class ItemImpl implements Serializable, Item {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    // ** object for logging *./
    // private static final Logger LOGGER =
    // Logger.getLogger(Item.class.getName());

    // Initialise as many values as possible.
    /**
     * The ID of the object. JPA doesn't care if we change this.
     */
    // Note: Id is required so UI getIndexOfChild() will work
    // when two objects have the same properties.
    @Column(name = "ID")
    protected UUID id = UUID.randomUUID();

    /**
     * Use by JPA.
     */
    @Id
    @Column(name = "jpaid", nullable = false)
    @GeneratedValue
    private long jpaId;
    /** where this item is located. */
    private ItemContainer container = null;
    /** A short description of the item. */
    private String description = null;
    /** The remaining structural integrity of this item. */
    private float hitPoints = 0F;
    /** The name of this item. */
    private String name = null;
    /** Who owns this item. null means no-one. */
    private Item owner = null;
    /** The position within the container. */
    private Position position = null;
    /**
     * Misc properties about this item that don't deserve their own setters and
     * getters.
     **/
    private Properties properties = new Properties();
    /** The value (in Currency), excludes contents if this is a container. */
    private Currency valueBase = new Currency();
    /** The volume, excludes contents if this is a container. */
    private float volumeBase = 0;
    /** The weight, excludes contents if this is a container. */
    private float weightBase = 0;
    /** Icon to show in swing */
    private ItemIcon itemIcon = null;

    // Constructors
    /** Constructor. */
    protected ItemImpl() {
        super();
        ItemProperty.setMagical(this, false);
        ItemProperty.setClothing(this, false);
        ItemProperty.setLiving(this, false);
        ItemProperty.setHumanoidFood(this, false);
        itemIcon = new ItemIcon(this);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            the name of this Item.
     */
    protected ItemImpl(final String name) {
        this();
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @param name
     *            the name of this Item.
     * @param description
     *            the description of this Item.
     */
    protected ItemImpl(final String name, final String description) {
        this(name);
        this.description = description;
    }

    // Getters and Setters - Instance
    /**
     * get the Table ID of the item.<br>
     * JPA requires a primary key.
     * 
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
     * @param jpaId
     *            the Id to set
     */
    public void setJpaId(final long jpaId) {
        this.jpaId = jpaId;
    }

    /**
     * get the ID of the item. We need to be ale to tell the difference between<br>
     * two items with the same properties.
     * 
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
        id = pId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getContainer()
     */
    @Override
    public ItemContainer getContainer() {
        return container;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setContainer(au.net.hal9000.heisenberg
     * .item.ItemContainer)
     */
    @Override
    public void setContainer(ItemContainer container) {
        this.container = container;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getHitPoints()
     */
    @Override
    public float getHitPoints() {
        return hitPoints;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#setHitPoints(float)
     */
    @Override
    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getOwner()
     */
    @Override
    public Item getOwner() {
        return owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setOwner(au.net.hal9000.heisenberg
     * .item.ItemInt)
     */
    @Override
    public void setOwner(Item owner) {
        this.owner = owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getPosition()
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setPosition(au.net.hal9000.heisenberg
     * .units.Position)
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    // property related
    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getProperties()
     */
    @Override
    public Properties getProperties() {
        return properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setProperties(java.util.Properties
     * )
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getProperty(java.lang.String)
     */
    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#setProperty(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#removeProperty(java.lang.String)
     */
    @Override
    public void removeProperty(String key) {
        properties.remove(key);
    }

    // value related
    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getValueBase()
     */
    @Override
    public Currency getValueBase() {
        return valueBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getValue()
     */
    @Override
    public Currency getValue() {
        return valueBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#setValueBase(au.net.hal9000.heisenberg
     * .units.Currency)
     */
    @Override
    public void setValueBase(final Currency valueBase) {
        this.valueBase = valueBase;
    }

    // volume related

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getVolume()
     */

    @Override
    public float getVolume() {
        return volumeBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getVolumeBase()
     */
    @Override
    public float getVolumeBase() {
        return volumeBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#setVolumeBase(float)
     */
    @Override
    public void setVolumeBase(float volumeBase) {
        this.volumeBase = volumeBase;
    }

    // weight related

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getWeight()
     */
    @Override
    public float getWeight() {
        return weightBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#getWeightBase()
     */
    @Override
    public float getWeightBase() {
        return weightBase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#setWeightBase(float)
     */
    @Override
    public void setWeightBase(final float weightBase) {
        this.weightBase = weightBase;
    }

    @Override
    public ItemIcon getItemIcon() {
        return itemIcon;
    }

    @Override
    public void setItemIcon(ItemIcon itemIcon) {
        this.itemIcon = itemIcon;
    }

    /* End of Setters and Getters */
    // misc methods

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param other
     *            Item to copy attributes from.
     */
    public void setAllFrom(Item other) {
        setContainer(other.getContainer());
        setDescription(other.getDescription());
        setHitPoints(other.getHitPoints());
        setItemIcon(other.getItemIcon().dupicate());
        setName(other.getName());
        setOwner(other.getOwner());
        setPosition(other.getPosition());
        setProperties(other.getProperties());
        setValueBase(other.getValueBase());
        setVolumeBase(other.getVolumeBase());
        setWeightBase(other.getWeightBase());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + Float.floatToIntBits(hitPoints);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((itemIcon == null) ? 0 : itemIcon.hashCode());
        result = prime * result + (int) (jpaId ^ (jpaId >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
        result = prime * result
                + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result
                + ((valueBase == null) ? 0 : valueBase.hashCode());
        result = prime * result + Float.floatToIntBits(volumeBase);
        result = prime * result + Float.floatToIntBits(weightBase);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemImpl other = (ItemImpl) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (Float.floatToIntBits(hitPoints) != Float
                .floatToIntBits(other.hitPoints))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (itemIcon == null) {
            if (other.itemIcon != null)
                return false;
        } else if (!itemIcon.equals(other.itemIcon))
            return false;
        if (jpaId != other.jpaId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (valueBase == null) {
            if (other.valueBase != null)
                return false;
        } else if (!valueBase.equals(other.valueBase))
            return false;
        if (Float.floatToIntBits(volumeBase) != Float
                .floatToIntBits(other.volumeBase))
            return false;
        if (Float.floatToIntBits(weightBase) != Float
                .floatToIntBits(other.weightBase))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#beNot()
     */
    @Override
    public void beNot() {
        // TODO - Help Required - How do I delete an object that
        // may be referenced by other objects?
        // Perhaps listeners on the containers?
        ItemContainer container = this.getContainer();
        if (null != container) {
            container.remove(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#move(au.net.hal9000.heisenberg
     * .item.ItemContainer, au.net.hal9000.heisenberg.units.Position)
     */
    @Override
    public void move(ItemContainer container, Position requestedPosition)
            throws InvalidTypeException, TooHeavyException, TooLargeException {
        container.add(this);
        moveWithinContainer(requestedPosition);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#move(au.net.hal9000.heisenberg
     * .item.ItemContainer)
     */
    @Override
    public void move(ItemContainer container) throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        container.add(this);
    }

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
    public void moveWithinContainer(Position requestedPosition) {
        if (null == container) {
            throw new UnsupportedOperationException(
                    "No ItemContainer - Can't move");
        } else {
            container.moveItemAbsolute(this, requestedPosition);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#toString()
     */
    @Override
    public String toString() {
        String string = getName();
        if ((null == string) || (0 == string.length())) {
            string = this.getClass().getSimpleName().toLowerCase();
        }
        return string;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemInt#detailedDescription()
     */
    @Override
    public String detailedDescription() {
        StringBuilder text = new StringBuilder();
        String temp;

        temp = this.getName();
        if (null != temp) {
            text.append("Name: " + temp);
            text.append(System.lineSeparator());

        }
        text.append("Class: " + getSimpleClassName());
        text.append(System.lineSeparator());

        temp = this.getDescription();
        if ((null != temp) && (temp.length() > 0)) {
            text.append("Description: " + temp);
            text.append(System.lineSeparator());
        }
        text.append("Weight Base: " + this.getWeightBase());
        text.append(System.lineSeparator());
        text.append("Volume Base: " + this.getVolumeBase());
        text.append(System.lineSeparator());

        Currency valueBase = this.getValueBase();
        if (null != valueBase) {
            text.append("Value Base: " + valueBase);
            text.append(System.lineSeparator());
        }
        ItemContainer container = this.getContainer();
        if (null != container) {
            text.append("Container: " + container.getName());
            text.append(System.lineSeparator());
        }
        Position position = this.getPosition();
        if (null != position) {
            text.append("Position: " + position);
            text.append(System.lineSeparator());
        }
        if (properties != null && !properties.isEmpty()) {
            text.append("Properties:");
            text.append(System.lineSeparator());
            for (Entry<Object, Object> entry : properties.entrySet()) {
                text.append(" " + entry.getKey() + ": " + entry.getValue());
                text.append(System.lineSeparator());
            }
        }
        return text.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#accept(au.net.hal9000.heisenberg
     * .item.property.ItemVisitor)
     */
    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Store the object in a file.
     * 
     * @param filename
     *            output file.
     * 
     * @throws IOException
     */
    public void freezeToFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
        out.close();
        fos.close();
    }

    /**
     * Create a new Item of the specified type.
     * 
     * @param type
     *            the type of Item to create.
     * 
     * @return the new Item.
     */
    public final boolean instanceOf(String type) {
        boolean isInstance;
        try {
            Class<?> itemClass = Class.forName(Item.getClassForType(type));
            isInstance = itemClass.isInstance(this);
        } catch (ClassNotFoundException e) {
            isInstance = false; // NOP
        }
        return isInstance;
    }

    /**
     * Returns the simple type of this item e.g. Cookie, Arrow, etc.
     * 
     * 
     * @return the simple type of this item e.g. Cookie, Arrow, etc.
     */
    @Override
    public String getSimpleClassName() {
        return getClass().getSimpleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemInt#applyDelta(au.net.hal9000.heisenberg
     * .units.Position)
     */
    @Override
    public void applyDelta(Position delta) {
        position.applyDelta(delta);
    }

    /**
     * Create a new cooker.
     * 
     * @param recipeId
     *            The ID of the recipe we wish to use for cooking.
     * 
     * @return a new cooker object
     */
    public Cooker getCooker(String recipeId) {
        Configuration configuration = Configuration.lastConfig();
        Recipe recipe = configuration.getRecipe(recipeId);
        if (recipe == null) {
            throw new RuntimeException("Failed to find recipe=" + recipeId);
        }
        return recipe.getNewCooker(this);
    }
}
