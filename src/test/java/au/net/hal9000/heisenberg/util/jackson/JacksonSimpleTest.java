package au.net.hal9000.heisenberg.util.jackson;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * This class contains tests for verifying the serialization and deserialization of objects using
 * Jackson.
 */
public class JacksonSimpleTest {

  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(JacksonSimpleTest.class.getName());

  @Test
  public void testSerDeItems() throws JsonProcessingException {
    // Serialize
    final ObjectMapper mapper = new ObjectMapper();
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
    LOGGER.info("Serialised JSON: " + json);

    // Deserialize
    ItemContainer deserializedContainer = mapper.readValue(json, ItemContainer.class);
    assertEquals(Box.class, deserializedContainer.getClass());
    assertEquals(ConcreteA.class, deserializedContainer.contents.get(0).getClass());
    assertEquals(ConcreteB.class, deserializedContainer.contents.get(1).getClass());

    LOGGER.info(
        "Deserialized AbstractBase type: A:"
            + deserializedContainer.contents.get(0).getClass().getSimpleName()
            + ", B:"
            + deserializedContainer.contents.get(1).getClass().getSimpleName());
  }

  /**
   * Main method to run the test.
   *
   * <p>This method creates an instance of the test class and invokes the {@link #testSerDeItems()}
   * method to demonstrate serialization and deserialization of objects using Jackson.
   *
   * @param args command line arguments
   * @throws JsonProcessingException if there is an error during JSON processing
   */
  public static void main(String[] args) throws JsonProcessingException {
    var jacksonSimpleTest = new JacksonSimpleTest();
    jacksonSimpleTest.testSerDeItems();
  }
}
