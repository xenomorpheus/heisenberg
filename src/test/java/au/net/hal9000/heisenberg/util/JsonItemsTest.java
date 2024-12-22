package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class JsonItemsTest {
  @Test
  public void testExportBiscuit() throws IOException {
    File tempFile = File.createTempFile("heisenberg-", ".json");
    tempFile.deleteOnExit();
    List<Item> items = new ArrayList<>();
    items.add(new Biscuit());
    JsonItems.export(tempFile, items);
  }

  // TODO @Test
  public void testSerDeBiscuit() throws IOException {
    File tempFile = File.createTempFile("heisenberg-", ".json");
    tempFile.deleteOnExit();
    List<Item> expected = new ArrayList<>();
    expected.add(new Biscuit());
    JsonItems.export(tempFile, expected);
    List<Item> got = JsonItems.importFromFile(tempFile);
    assertNotNull(got);
    assertEquals(expected, got);
  }

  // TODO @Test
  public void testSerDeLocation() throws IOException {
    File tempFile = File.createTempFile("heisenberg-", ".json");
    tempFile.deleteOnExit();
    List<Item> expected = new ArrayList<>();
    var location = new Location();
    expected.add(location);
    JsonItems.export(tempFile, expected);
    List<Item> got = JsonItems.importFromFile(tempFile);
    assertNotNull(got);
    assertEquals(expected, got);
  }

  // TODO @Test
  public void testSerDeLocationBiscuit() throws IOException {
    File tempFile = new File("/tmp/heisenberg.json");
    List<Item> expected = new ArrayList<>();
    var location = new Location();
    var biscuit = new Biscuit();
    location.add(biscuit);
    expected.add(location);

    // ObjectMapper objectMapper = new ObjectMapper();
    // String json = objectMapper.writeValueAsString(expected);

    JsonItems.export(tempFile, expected);
    List<Item> got = JsonItems.importFromFile(tempFile);
    assertNotNull(got);
    assertEquals(expected, got);
  }
}
