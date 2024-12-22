package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

// Abstract base class
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ConcreteA.class, name = "A"),
  @JsonSubTypes.Type(value = ConcreteB.class, name = "B"),
  @JsonSubTypes.Type(value = Box.class, name = "Box")
})
abstract class Item {
  public String commonField;
  public ItemContainer parent;
}

// Concrete implementations
class ConcreteA extends Item {
  public String fieldA;
}

class ConcreteB extends Item {
  public String fieldB;
}

// Class with abstract field
abstract class ItemContainer extends Item {
  public List<Item> contents = new ArrayList<>();
}

class Box extends ItemContainer {}

public class JacksonSimpleTest {
  @Test
  public void testItems() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    // Serialize
    ItemContainer container = new Box();

    ConcreteA concreteA = new ConcreteA();
    concreteA.commonField = "commonValueA";
    concreteA.fieldA = "specificValueA";
    container.contents.add(concreteA);

    ConcreteB concreteB = new ConcreteB();
    concreteB.commonField = "commonValueB";
    concreteB.fieldB = "specificValueB";
    container.contents.add(concreteB);

    String json = mapper.writeValueAsString(container);
    System.out.println("\n\nSerialized JSON: " + json + "\n\n");
    System.out.flush();

    // Deserialize
    ItemContainer deserializedContainer = mapper.readValue(json, ItemContainer.class);
    assertEquals(Box.class, deserializedContainer.getClass());
    assertEquals(ConcreteA.class, deserializedContainer.contents.get(0).getClass());
    assertEquals(ConcreteB.class, deserializedContainer.contents.get(1).getClass());

    System.out.println(
        "Deserialized AbstractBase type: "
            + deserializedContainer.contents.get(0).getClass().getSimpleName());
  }

  public static void main(String[] args) throws JsonProcessingException {
    var foo = new JacksonSimpleTest();
    foo.testItems();
  }
}
