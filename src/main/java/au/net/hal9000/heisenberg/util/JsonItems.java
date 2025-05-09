package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Cat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

    switch (type) {
      case "Biscuit":
      case "au.net.hal9000.heisenberg.item.Biscuit":
        return jp.getCodec().treeToValue(node, Biscuit.class);
      case "Cat":
      case "au.net.hal9000.heisenberg.item.Cat":
        return jp.getCodec().treeToValue(node, Cat.class);
      case "Location":
      case "au.net.hal9000.heisenberg.item.Location":
        return jp.getCodec().treeToValue(node, Location.class);
      case "Box":
      case "au.net.hal9000.heisenberg.item.Box":
        return jp.getCodec().treeToValue(node, Box.class);
      default:
        throw new IllegalArgumentException("Unknown type: " + type);
    }
  }
}

/** Store/retrieve Item objects to/from a JSON file. */
public class JsonItems {

  public static void export(File pathname, final List<Item> items)
      throws StreamWriteException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    // Enable default typing for polymorphic type handling
    objectMapper.activateDefaultTyping(
        objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

    objectMapper.writeValue(pathname, items);
  }

  /**
   * Deserialize from JSON file
   *
   * @throws IOException
   * @throws DatabindException
   * @throws StreamReadException
   */
  public static List<Item> importFromFile(File pathname)
      throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    // Enable default typing for polymorphic type handling
    objectMapper.activateDefaultTyping(
        objectMapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY);

    // Register the custom deserializer
    SimpleModule module = new SimpleModule();
    var itemDeserializer = new ItemDeserializer();
    module.addDeserializer(Item.class, itemDeserializer);
    objectMapper.registerModule(module);

    List<Item> entities =
        objectMapper.readValue(
            pathname,
            objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
    return entities;
  }
}
