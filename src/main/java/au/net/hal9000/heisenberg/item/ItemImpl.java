package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

/**
 * Base abstract class for all items in this world.
 *
 * <p>The Item package is the base class for the game objects.<br>
 * e.g. People, equipment, locations, etc.
 *
 * <p>See the ItemContainer class for items that may contain other items, e.g. bags, scabbards, even
 * people.
 *
 * <p>An item has a globally pseudo-unique identifier.<br>
 * An item has a name which is text.<br>
 * An item has a description which is text.<br>
 * An item has a weight which is measured in pounds which defaults to zero.<br>
 * An item has a valueBase measured in coins which defaults to zero.<br>
 * An item has a location (e.g. ground, a bag, an arm)<br>
 * (discuss) An item has a condition (0-100) 100=new, 0=worn away. Or hit points? An item may have
 * any number of defences.<br>
 * An item by default does not offer protection (e.g armour, magic resistance) <br>
 * An item may offer any number of protections.<br>
 * Actions<br>
 * An item may be dropped, which will cause the location to be changed to the ground below the
 * object.<br>
 * An item may be damaged by fire which will...<br>
 * An item may be damaged by acid which will...<br>
 * An item may be repaired which will ...<br>
 */
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "simpleClassName")
@JsonSubTypes({
  @JsonSubTypes.Type(value = Biscuit.class, name = "Biscuit"),
  @JsonSubTypes.Type(value = Box.class, name = "Box"),
  @JsonSubTypes.Type(value = Location.class, name = "Location"),
  @JsonSubTypes.Type(value = Cat.class, name = "Cat")
})
public abstract class ItemImpl implements Serializable, Item {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  // Initialise as many values as possible.
  /**
   * The ID of the object. <br>
   * This field is unique for all Item objects, so no two Item objects can be equals. JPA doesn't
   * care if we change this.
   */
  // Note: Id is required so UI getIndexOfChild() will work
  // when two objects have the same properties.
  @Column(name = "ID")
  protected UUID id;

  /** Use by JPA. */
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

  /** The position within the container. */
  private Position position = null;

  /** Misc properties about this item that don't deserve their own setters and getters. */
  private Properties properties = new Properties();

  /** The value (in Currency), excludes contents if this is a container. */
  private Currency valueBase = null;

  /** The volume, excludes contents if this is a container. */
  private float volumeBase = 0;

  /** The weight, excludes contents if this is a container. */
  private float weightBase = 0;

  // Constructors
  /** Constructor. */
  protected ItemImpl() {
    super();
    id = UUID.randomUUID();
  }

  // Getters and Setters - Instance

  @Override
  public long getJpaId() {
    return jpaId;
  }

  /**
   * get the ID of the item. We need to be ale to tell the difference between <br>
   * two items with the same properties.
   *
   * @return the Id
   */
  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public ItemContainer getContainer() {
    return container;
  }

  @Override
  public void setContainer(ItemContainer container) {
    this.container = container;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(final String description) {
    if (null == this.description) {
      this.description = new String();
    }
    this.description = description;
  }

  @Override
  public float getHitPoints() {
    return hitPoints;
  }

  @Override
  public void setHitPoints(float hitPoints) {
    this.hitPoints = hitPoints;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(final String name) {
    if (null == this.name) {
      this.name = new String();
    }
    this.name = name;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public void setPosition(Position position) {
    this.position = position;
  }

  // property related
  @Override
  public Properties getProperties() {
    return properties;
  }

  @Override
  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  @Override
  public Object getProperty(String key) {
    return properties.get(key);
  }

  @Override
  public void setProperty(String key, Object value) {
    properties.put(key, value);
  }

  @Override
  public void removeProperty(String key) {
    properties.remove(key);
  }

  // value related
  @Override
  public Currency getValueBase() {
    return valueBase;
  }

  @Override
  public Currency totalValue() {
    return valueBase;
  }

  @Override
  public void setValueBase(final Currency valueBase) {
    this.valueBase = valueBase;
  }

  // volume related

  @Override
  public float totalVolume() {
    return volumeBase;
  }

  @Override
  public float getVolumeBase() {
    return volumeBase;
  }

  @Override
  public void setVolumeBase(float volumeBase) {
    this.volumeBase = volumeBase;
  }

  // weight related

  @Override
  public float totalWeight() {
    return weightBase;
  }

  @Override
  public float getWeightBase() {
    return weightBase;
  }

  @Override
  public void setWeightBase(final float weightBase) {
    this.weightBase = weightBase;
  }

  // misc methods

  @Override
  public void setAllFrom(Item other) {
    setContainer(other.getContainer());
    setDescription(other.getDescription());
    setHitPoints(other.getHitPoints());
    setName(other.getName());
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
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + Float.floatToIntBits(hitPoints);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + (int) (jpaId ^ (jpaId >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
    result = prime * result + ((valueBase == null) ? 0 : valueBase.hashCode());
    result = prime * result + Float.floatToIntBits(volumeBase);
    result = prime * result + Float.floatToIntBits(weightBase);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ItemImpl other = (ItemImpl) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    // Code almost certainly won't reach here as id values are random and
    // very likely to be different.
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (Float.floatToIntBits(hitPoints) != Float.floatToIntBits(other.hitPoints)) {
      return false;
    }
    if (jpaId != other.jpaId) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (position == null) {
      if (other.position != null) {
        return false;
      }
    } else if (!position.equals(other.position)) {
      return false;
    }
    if (properties == null) {
      if (other.properties != null) {
        return false;
      }
    } else if (!properties.equals(other.properties)) {
      return false;
    }
    if (valueBase == null) {
      if (other.valueBase != null) {
        return false;
      }
    } else if (!valueBase.equals(other.valueBase)) {
      return false;
    }
    if (Float.floatToIntBits(volumeBase) != Float.floatToIntBits(other.volumeBase)) {
      return false;
    }
    if (Float.floatToIntBits(weightBase) != Float.floatToIntBits(other.weightBase)) {
      return false;
    }
    return true;
  }

  @Override
  public void beNot() {
    // TODO Help Required - How do I delete an object that
    // may be referenced by other objects?
    // Perhaps listeners on the containers?
    ItemContainer container = getContainer();
    if (null != container) {
      container.remove(this);
    }
  }

  @Override
  public void move(ItemContainer container, Position requestedPosition) {
    container.add(this);
    moveWithinContainer(requestedPosition);
  }

  @Override
  public void move(ItemContainer container) {
    container.add(this);
  }

  @Override
  public void moveWithinContainer(Position requestedPosition) {
    if (null == container) {
      throw new UnsupportedOperationException("No ItemContainer - Can't move");
    } else {
      container.moveItemAbsolute(this, requestedPosition);
    }
  }

  @Override
  public String toString() {
    String name = getName();
    if ((null == name) || (0 == name.length())) {
      name = getClass().getSimpleName().toLowerCase();
    }
    return name;
  }

  @Override
  public String detailedDescription() {
    StringBuilder text = new StringBuilder();
    String temp;

    text.append("Id: " + getId() + System.lineSeparator());

    temp = getName();
    if (null != temp) {
      text.append("Name: " + temp + System.lineSeparator());
    }
    text.append("Item Class: " + getSimpleClassName() + System.lineSeparator());

    temp = getDescription();
    if ((null != temp) && (temp.length() > 0)) {
      text.append("Description: " + temp + System.lineSeparator());
    }
    text.append("Weight Base: " + getWeightBase() + System.lineSeparator());
    text.append("Volume Base: " + getVolumeBase() + System.lineSeparator());

    Currency valueBase = getValueBase();
    if (null != valueBase) {
      text.append("Value Base: " + valueBase + System.lineSeparator());
    }
    ItemContainer container = getContainer();
    if (null != container) {
      text.append("Container: " + container.getName() + System.lineSeparator());
    }
    Position position = getPosition();
    if (null != position) {
      text.append("Position: " + position + System.lineSeparator());
    }
    if (properties != null && !properties.isEmpty()) {
      text.append("Properties:" + System.lineSeparator());
      for (Entry<Object, Object> entry : properties.entrySet()) {
        text.append(" " + entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
      }
    }
    return text.toString();
  }

  @Override
  public void accept(ItemVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  @JsonProperty
  public String getSimpleClassName() {
    return getClass().getSimpleName();
  }

  @Override
  @JsonIgnore
  public void setSimpleClassName(String dummy) {}

  @Override
  public void applyDelta(Position delta) {
    Position positionInContainer = getPosition();
    if (null == positionInContainer) {
      position = new Position();
    }
    position.applyDelta(delta);
  }
}
