package au.net.hal9000.heisenberg.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Properties;

// Persistence
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Point3d;

/*
 * Item:
 *
 * The Item package is the base class for the game objects.
 * e.g. People, equipment, locations, etc.
 *
 * See the ItemContainer class for items that may contain
 * other items, e.g. bags, scabbards, even people.
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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Item.class.getName());
    private static String packageName = Factory.class.getPackage().getName();
    /** For each class of Item, the icon to show when open **/
    // TODO Consider moving out into own class.
    private static TreeMap<String, Icon> iconOpenDefaultForClass = new TreeMap<String, Icon>();

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
    private ItemContainer container = null;
    /** A short description of the item **/
    private String description = null;
    /** The remaining structural integrity of this item **/
    private float hitPoints = 0F;
    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when open
     **/
    private Icon iconClosed = null;
    /**
     * If this item is a container and shown in the UI in tree view, then this
     * is the icon to show when closed
     **/
    private Icon iconOpen = null;
    /**
     * If this item is NOT a container and shown in the UI in tree view, then
     * this is the icon to show
     **/
    private Icon iconLeaf = null;
    /** The name of this item **/
    private String name = null;
    /** Who owns this item. null means no-one. */
    private Item owner = null;
    /** The position within the container **/
    private Point3d position = null;
    /**
     * Misc properties about this item that don't deserve their own setters and
     * getters
     **/
    private Properties properties = new Properties();
    /** The value (in Currency), excludes contents if this is a container **/
    private Currency valueBase = new Currency();
    /** The volume, excludes contents if this is a container **/
    private float volumeBase = 0;
    /** The weight, excludes contents if this is a container **/
    private float weightBase = 0;

    // Constructors
    /** Constructor. */
    public Item() {
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

    /** Constructor. */
    public Item(final String pName) {
        this();
        this.name = pName;
    }

    public Item(final String pName, final String pDescription) {
        this(pName);
        this.description = pDescription;
    }

    // Getters and Setters - Instance
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
     * Get the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     * 
     * @param pDescription
     *            the description
     */
    public void setDescription(final String pDescription) {
        this.description = pDescription;
    }

    /**
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * 
     * @param pName
     *            the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
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

    /**
     * set the position of the item.
     * 
     * @param position
     *            the position object.
     */
    public void setPosition(Point3d position) {
        this.position = position;
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
     * Get a property.
     * 
     * @param key
     *            key name
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

    /* End of Setters and Getters */
    // misc methods

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param item
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
     * Return true iff items are equal.
     * 
     * @param other
     *            other item
     * @return true iff items are equal.
     */
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
     * Move the item into the ItemContainer. Note: The request can fail or
     * partially complete.<br>
     * E.g Can't pass through walls.
     * 
     * @param container
     *            the container that will hold this item.
     * @param requestedPosition
     *            the requested position within the container.
     */
    public void move(ItemContainer container, Point3d requestedPosition) {
        container.add(this);
        move(requestedPosition);
    }

    /**
     * Move the item into the ItemContainer.
     * 
     * @param container
     *            the container that will hold this item.
     */
    public void move(ItemContainer container) {
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
    public void move(Point3d requestedPosition) {
        if (container == null) {
            LOGGER.error("No ItemContainer - Can't move");
        } else {
            container.moveItem(this, requestedPosition);
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
        StringBuilder text = new StringBuilder(128);
        String temp;

        temp = this.getName();
        if (temp != null) {
            text.append("Name: " + temp + "\n");
        }
        text.append("Class: " + getSimpleClassName() + "\n");
        temp = this.getDescription();
        if ((temp != null) && (temp.length() > 0)) {
            text.append("Description: " + temp + "\n");
        }
        text.append("Weight Base: " + this.getWeightBase() + "\n"
                + "Volume Base: " + this.getVolumeBase() + "\n");

        Currency valueBase = this.getValueBase();
        if (valueBase != null) {
            text.append("Value Base: " + valueBase + "\n");
        }
        ItemContainer container = this.getContainer();
        if (container != null) {
            text.append("Container: " + container.getName() + "\n");
        }
        Point3d position = this.getPosition();
        if (position != null) {
            text.append("Position: " + position + "\n");
        }
        if (properties != null && !properties.isEmpty()) {
            text.append("Properties:\n");
            for (Entry<Object, Object> entry : properties.entrySet()) {
                text.append(" " + entry.getKey() + ": " + entry.getValue()
                        + "\n");
            }
        }
        return text.toString();
    }

    /** Find items that match the criteria. */
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
     * Returns the simple type of this item e.g. Cookie, Arrow, etc.
     * 
     * @return the simple type of this item e.g. Cookie, Arrow, etc.
     */
    public String getSimpleClassName() {
        return getClass().getSimpleName();
    }

    /**
     * For this Item type, get the default Icon to show when this item is open.
     * 
     * @return The Icon.
     */
    public Icon getIconOpenDefault() {
        return getIconOpenDefaultForClass(getSimpleClassName());
    }

    // TODO consider refactoring out into a different class
    public static void setIconOpenDefaultForClass(String simpleClassName,
            ImageIcon imageIcon) {
        iconOpenDefaultForClass.put(simpleClassName, imageIcon);
    }

    // TODO consider refactoring out into a different class
    public static Icon getIconOpenDefaultForClass(String simpleClassName) {
        return iconOpenDefaultForClass.get(simpleClassName);
    }

    // TODO consider refactoring out into a different class
    public static void clearIconOpenDefaultForClass() {
        iconOpenDefaultForClass.clear();
    }

    /**
     * Return the distance to the other item.
     * 
     * @param other
     *            other item.
     * @return
     */
    public double distanceEuclidean(Item other) {
        return position.distance(other.getPosition());
    }

}
