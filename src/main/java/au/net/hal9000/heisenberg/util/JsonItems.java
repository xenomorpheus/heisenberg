package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.ItemImpl;
import au.net.hal9000.heisenberg.item.api.Item;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/** Store/retrieve Item objects to/from a JSON file. */
public class JsonItems {

  private static final Logger LOGGER = Logger.getLogger(JsonItems.class.getName());

  public static void export(File pathname, final List<Item> items) {
    // Serialise to JSON and save to file
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(pathname, items);
    } catch (StreamWriteException e) {
      LOGGER.error(e);
    } catch (DatabindException e) {
      LOGGER.error(e);
    } catch (IOException e) {
      LOGGER.error(e);
    }
  }

  /** Deserialize from JSON file */
  public static List<Item> importFromFile(File pathname) {
    ObjectMapper objectMapper = new ObjectMapper();

    // Register the custom deserializer
    SimpleModule module = new SimpleModule();
    objectMapper.registerModule(module);

    List<Item> entities = null;
    try {
      entities =
          objectMapper.readValue(
              pathname,
              objectMapper.getTypeFactory().constructCollectionType(List.class, ItemImpl.class));
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
