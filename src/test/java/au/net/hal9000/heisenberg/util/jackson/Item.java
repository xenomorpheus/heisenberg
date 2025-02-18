package au.net.hal9000.heisenberg.util.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;

// Abstract base class
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "simpleClassName")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ConcreteA.class, name = "A"),
  @JsonSubTypes.Type(value = ConcreteB.class, name = "B"),
  @JsonSubTypes.Type(value = Box.class, name = "Box")
})
public abstract class Item {
  public String commonField;
  public ItemContainer parent;
}

// Class with abstract field
@JsonSubTypes({@JsonSubTypes.Type(value = Box.class, name = "Box")})
abstract class ItemContainer extends Item {
  public List<Item> contents = new ArrayList<>();
}

class Box extends ItemContainer {}
