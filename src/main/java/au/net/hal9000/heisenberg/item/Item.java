package au.net.hal9000.heisenberg.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;
import java.util.UUID;

import javax.persistence.Column;
// Persistence
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Configuration;

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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    // ** object for logging *./
    // private static final Logger LOGGER =
    // Logger.getLogger(Item.class.getName());

    /** name of this package. */
    private static final String PACKAGE_NAME = Factory.class.getPackage()
            .getName();

    /** For each class of Item, the icon to show when open. */
    // TODO Consider moving out into own class.
    private static final Map<String, Icon> ICON_OPEN_DEFAULT_FOR_CLASS = new TreeMap<String, Icon>();

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
    /** where this item is located. */
    private ItemContainer container = null;
    /** A short description of the item. */
    private String description = null;
    /** The remaining structural integrity of this item. */
    private float hitPoints = 0F;
    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when open.
     **/
    private Icon iconClosed = null;
    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when closed.
     **/
    private Icon iconOpen = null;
    /**
     * If this item is NOT a container and shown in the UI in tree view, then
     * this is the icon to show.
     **/
    private Icon iconLeaf = null;
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

    // Constructors
    /** Constructor. */
    protected Item() {
        super();
        ItemProperty.setMagical(this, false);
        ItemProperty.setClothing(this, false);
        ItemProperty.setLiving(this, false);
        ItemProperty.setHumanoidFood(this, false);

        // TODO add separate Icons for Closed and Leaf
        Icon icon = getIconOpenDefault();
        setIconOpen(icon);
        setIconClosed(icon);
        setIconLeaf(icon);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            the name of this Item.
     */
    protected Item(final String name) {
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
    protected Item(final String name, final String description) {
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

    /**
     * Get the current container.
     * 
     * 
     * @return the current container {@link ItemContainer}.
     */
    public ItemContainer getContainer() {
        return container;
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
     * Get the description.
     * 
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     * 
     * @param description
     *            the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * 
     * @return the structural integrity / health.
     */
    public float getHitPoints() {
        return hitPoints;
    }

    /**
     * @param hitPoints
     *            the structural integrity / health.
     */
    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconClosed() {
        return iconClosed;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconClosed
     *            the Icon to show when the container is closed.
     */
    public void setIconClosed(Icon iconClosed) {
        this.iconClosed = iconClosed;
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconOpen() {
        return iconOpen;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconOpen
     *            the Icon to show when the container is open.
     */
    public void setIconOpen(Icon iconOpen) {
        this.iconOpen = iconOpen;
    }

    /**
     * Return the Icon to draw when displaying this Item in the tree view.
     * 
     * 
     * @return the Icon.
     */
    public Icon getIconLeaf() {
        return iconLeaf;
    }

    /**
     * Set the Icon to draw when displaying this Item in the tree view.
     * 
     * @param iconLeaf
     *            the Icon to show when the non-container item is displayed.
     */
    public void setIconLeaf(Icon iconLeaf) {
        this.iconLeaf = iconLeaf;
    }

    /**
     * get the name of the item.
     * 
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * 
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
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
        this.owner = owner;
    }

    /**
     * 
     * 
     * @return return the position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * set the position of the item.
     * 
     * @param position
     *            the position object.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    // property related
    /**
     * 
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
     * Get a property.
     * 
     * @param key
     *            key name
     * 
     * @return the value
     */
    public Object getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Set a property.
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
     * Remove a property.
     * 
     * @param key
     *            key name
     */
    public void removeProperty(String key) {
        properties.remove(key);
    }

    // value related
    /**
     * value before addition of other items such as those carried.
     * 
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
     * 
     * @return the total value including contained items.
     */
    public Currency getValue() {
        return valueBase;
    }

    /**
     * value before addition of other items such as those carried.
     * 
     * @param valueBase
     *            The value before any contained items.
     */
    public void setValueBase(final Currency valueBase) {
        this.valueBase = valueBase;
    }

    // volume related

    /**
     * For simple items the weight is the weightBase. Will be overridden by
     * collections
     * 
     * 
     * @return the amount of 3D space that this item occupies.
     */

    public float getVolume() {
        return volumeBase;
    }

    /**
     * volume before addition of other items such as those carried.
     * 
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
     *            the new volume.
     */
    public void setVolumeBase(float volumeBase) {
        this.volumeBase = volumeBase;
    }

    // weight related

    /**
     * Set the total weight. For simple items the weight is the weightBase. May
     * be overridden by collections.
     * 
     * 
     * @return the total weight
     */
    public float getWeight() {
        return weightBase;
    }

    /**
     * 
     * @return weight before addition of other items such as those carried
     */
    public float getWeightBase() {
        return weightBase;
    }

    /**
     * @param weightBase
     *            weight before addition of other items such as those carried.
     */
    public void setWeightBase(final float weightBase) {
        this.weightBase = weightBase;
    }

    /* End of Setters and Getters */
    // misc methods

    /**
     * Mostly Auto generated. Note: container not considered to break loops.
     * 
     * 
     * @return hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((null == description) ? 0 : description.hashCode());
        result = prime * result + Float.floatToIntBits(hitPoints);
        result = prime * result
                + ((null == iconClosed) ? 0 : iconClosed.hashCode());
        result = prime * result
                + ((null == iconLeaf) ? 0 : iconLeaf.hashCode());
        result = prime * result
                + ((null == iconOpen) ? 0 : iconOpen.hashCode());
        result = prime * result + ((null == id) ? 0 : id.hashCode());
        result = prime * result + (int) (jpaId ^ (jpaId >>> (prime + 1)));
        result = prime * result + ((null == name) ? 0 : name.hashCode());
        result = prime * result + ((null == owner) ? 0 : owner.hashCode());
        result = prime * result
                + ((null == position) ? 0 : position.hashCode());
        result = prime * result
                + ((null == properties) ? 0 : properties.hashCode());
        result = prime * result
                + ((null == valueBase) ? 0 : valueBase.hashCode());
        result = prime * result + Float.floatToIntBits(volumeBase);
        result = prime * result + Float.floatToIntBits(weightBase);
        return result;
    }

    /**
     * Note field container deliberately not used to break loops. Mostly
     * auto-generated.
     * 
     * @param obj
     *            object under comparison
     * 
     * @return true if equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        if (null == description) {
            if (null != other.description) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (Float.floatToIntBits(hitPoints) != Float
                .floatToIntBits(other.hitPoints)) {
            return false;
        }
        if (null == iconClosed) {
            if (null != other.iconClosed) {
                return false;
            }
        } else if (!iconClosed.equals(other.iconClosed)) {
            return false;
        }
        if (null == iconLeaf) {
            if (null != other.iconLeaf) {
                return false;
            }
        } else if (!iconLeaf.equals(other.iconLeaf)) {
            return false;
        }
        if (null == iconOpen) {
            if (null != other.iconOpen) {
                return false;
            }
        } else if (!iconOpen.equals(other.iconOpen)) {
            return false;
        }
        if (null == id) {
            if (null != other.id) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (jpaId != other.jpaId) {
            return false;
        }
        if (null == name) {
            if (null != other.name) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (null == owner) {
            if (null != other.owner) {
                return false;
            }
        } else if (!owner.equals(other.owner)) {
            return false;
        }
        if (null == position) {
            if (null != other.position) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        if (null == properties) {
            if (null != other.properties) {
                return false;
            }
        } else if (!properties.equals(other.properties)) {
            return false;
        }
        if (null == valueBase) {
            if (null != other.valueBase) {
                return false;
            }
        } else if (!valueBase.equals(other.valueBase)) {
            return false;
        }
        if (Float.floatToIntBits(volumeBase) != Float
                .floatToIntBits(other.volumeBase)) {
            return false;
        }
        if (Float.floatToIntBits(weightBase) != Float
                .floatToIntBits(other.weightBase)) {
            return false;
        }
        return true;
    }

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param item
     *            Item to copy attributes from.
     */
    public void setAllFrom(Item item) {
        setContainer(item.getContainer());
        setDescription(item.getDescription());
        setHitPoints(item.getHitPoints());
        setIconOpen(item.getIconOpen());
        setIconClosed(item.getIconClosed());
        setIconLeaf(item.getIconLeaf());
        setName(item.getName());
        setOwner(item.getOwner());
        setPosition(item.getPosition());
        setProperties(item.getProperties());
        setValueBase(item.getValueBase());
        setVolumeBase(item.getVolumeBase());
        setWeightBase(item.getWeightBase());
    }

    /**
     * Attempt to unlink the item from everything so that it can be garbage
     * collected. Won't work if anything is referencing this item.
     */
    public void beNot() {
        // TODO - Help Required - How do I delete an object that
        // may be referenced by other objects?
        // Perhaps listeners on the containers?
        ItemContainer container = this.getContainer();
        if (null != container) {
            container.remove(this);
        }
    }

    /**
     * Move the item into the ItemContainer. Note: The request can fail or
     * partially complete.<br>
     * E.g Can't pass through walls.
     * 
     * @param container
     *            the container that will hold this item.
     * @param requestedPosition
     *            the requested position within the container.
     * @throws InvalidTypeException
     *             wrong type of Item.
     * @throws TooLargeException
     *             when an overly large Item added to bag.
     * @throws TooHeavyException
     *             when an overly heavy Item added to bag.
     */
    void move(ItemContainer container, Position requestedPosition)
            throws InvalidTypeException, TooHeavyException, TooLargeException {
        container.add(this);
        moveToPoint2d(requestedPosition);
    }

    /**
     * Move the item into the ItemContainer.
     * 
     * @param container
     *            the container that will hold this item.
     * @throws InvalidTypeException
     *             wrong type of Item.
     * @throws TooLargeException
     *             when an overly large Item added to bag.
     * @throws TooHeavyException
     *             when an overly heavy Item added to bag.
     */
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
    void moveToPoint2d(Position requestedPosition) {
        if (null == container) {
            throw new UnsupportedOperationException(
                    "No ItemContainer - Can't move");
        } else {
            container.moveItemAbsolute(this, requestedPosition);
        }
    }

    /**
     * 
     * 
     * @return A short identifying string. e.g. 'Cookie', 'John Smith'
     */
    @Override
    public String toString() {
        String string = getName();
        if ((null == string) || (0 == string.length())) {
            string = this.getClass().getSimpleName().toLowerCase();
        }
        return string;
    }

    /**
     * 
     * @return A description. e.g. 'A lit candle'
     */
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

    /**
     * Visitor Design Pattern.
     * 
     * @param visitor
     *            Item visitor.
     */
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

    // TODO thawFormFile

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
            Class<?> itemClass = Class.forName(PACKAGE_NAME + "." + type);
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
    public String getSimpleClassName() {
        return getClass().getSimpleName();
    }

    /**
     * For this Item type, get the default Icon to show when this item is open.
     * 
     * 
     * @return The Icon.
     */
    public Icon getIconOpenDefault() {
        return getIconOpenDefaultForClass(getSimpleClassName());
    }

    // TODO consider refactoring out into a different class
    /**
     * Set the Icon to show in the UI when collection is opened.
     * 
     * @param simpleClassName
     *            Simple Item name e.g. Cookie.
     * @param imageIcon
     *            Icon to show.
     */
    public static void setIconOpenDefaultForClass(String simpleClassName,
            ImageIcon imageIcon) {
        ICON_OPEN_DEFAULT_FOR_CLASS.put(simpleClassName, imageIcon);
    }

    // TODO consider refactoring out into a different class
    /**
     * Get the Icon to show in the UI when collection is opened.
     * 
     * @param simpleClassName
     *            Simple Item name e.g. Cookie.
     * 
     * @return Icon to show.
     */
    public static Icon getIconOpenDefaultForClass(String simpleClassName) {
        return ICON_OPEN_DEFAULT_FOR_CLASS.get(simpleClassName);
    }

    // TODO consider re-factoring out into a different class
    /**
     * Clear the Icon to show in the UI when collection is opened.
     */
    public static void clearIconOpenDefaultForClass() {
        ICON_OPEN_DEFAULT_FOR_CLASS.clear();
    }

    /**
     * Change the position by the supplied amount.
     * 
     * @param delta
     */
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
