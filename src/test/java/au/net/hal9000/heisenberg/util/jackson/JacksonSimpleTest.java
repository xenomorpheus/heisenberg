package au.net.hal9000.heisenberg.util.jackson;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

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
        "Deserialized AbstractBase type: A:"
            + deserializedContainer.contents.get(0).getClass().getSimpleName()
            + ", B:"
            + deserializedContainer.contents.get(1).getClass().getSimpleName());
  }

  public static void main(String[] args) throws JsonProcessingException {
    var jacksonSimpleTest = new JacksonSimpleTest();
    jacksonSimpleTest.testItems();
  }
}
