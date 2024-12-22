package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Cat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

@JsonSubTypes({
  @JsonSubTypes.Type(value = Location.class, name = "Location"),
  @JsonSubTypes.Type(value = Box.class, name = "Box")
})
class ItemDeserializer extends JsonDeserializer<Item> {
  @Override
  public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    var typeNode = node.get("simpleClassName");
    if (null == typeNode) {
      throw new IllegalArgumentException(
          "Failure to deserialize JSON due to missing type 'simpleClassName'");
    }
    String type = typeNode.asText();

    if ("Biscuit".equals(type)) {
      return jp.getCodec().treeToValue(node, Biscuit.class);
    }
    if ("Cat".equals(type)) {
      return jp.getCodec().treeToValue(node, Cat.class);
    }
    if ("Location".equals(type)) {
      return jp.getCodec().treeToValue(node, Location.class);
    }
    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

/** Store/retrieve Item objects to/from a JSON file. */
public class JsonItems {

  private static final Logger LOGGER = Logger.getLogger(JsonItems.class.getName());

  public static void export(File pathname, final List<Item> items) {
    // TODO choose a file to save to
    // Serialise to JSON and save to file
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(pathname, items);
    } catch (StreamWriteException e) {
      // TODO Auto-generated catch block
      LOGGER.info(e);
    } catch (DatabindException e) {
      // TODO Auto-generated catch block
      LOGGER.info(e);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      LOGGER.info(e);
    }
  }

  /** Deserialize from JSON file */
  public static List<Item> importFromFile(File pathname) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerSubtypes(Biscuit.class, Cat.class, Location.class);

    // Register the custom deserializer
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Item.class, new ItemDeserializer());
    objectMapper.registerModule(module);

    List<Item> entities = null;
    try {
      entities =
          objectMapper.readValue(
              pathname,
              objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
    } catch (StreamReadException e) {
      LOGGER.error(e);
    } catch (DatabindException e) {
      LOGGER.error(e);
    } catch (IOException e) {
      LOGGER.error(e);
    }
    return entities;
  }
}
