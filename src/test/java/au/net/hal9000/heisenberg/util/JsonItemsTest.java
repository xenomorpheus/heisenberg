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
    var tempFile = File.createTempFile("heisenberg-export-biscuit-", ".json");
    tempFile.deleteOnExit();
    List<Item> items = new ArrayList<>();
    items.add(new Biscuit());
    JsonItems.export(tempFile, items);
  }

  @Test
  public void testSerDeBiscuit() throws IOException {
    var tempFile = File.createTempFile("heisenberg-SerDe-biscuit-", ".json");
    tempFile.deleteOnExit();
    List<Item> expected = new ArrayList<>();
    var biscuit = new Biscuit();
    biscuit.setName("test");
    expected.add(biscuit);
    JsonItems.export(tempFile, expected);
    List<Item> got = JsonItems.importFromFile(tempFile);
    assertNotNull(got);
    assertEquals(expected, got);
  }

  @Test
  public void testSerDeLocation() throws IOException {
    var tempFile = File.createTempFile("heisenberg-SerDe-location-", ".json");
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
    var tempFile = new File("/tmp/heisenberg-SerDe-location-biscuit.json");
    // TODO when test works - var tempFile =
    // File.createTempFile("/tmp/heisenberg-SerDe-location-biscuit-", ".json");
    List<Item> expected = new ArrayList<>();
    var location = new Location();
    var biscuit = new Biscuit();
    location.add(biscuit);
    expected.add(location);
    JsonItems.export(tempFile, expected);
    List<Item> got = JsonItems.importFromFile(tempFile);
    assertNotNull(got);
    assertEquals(expected.size(), got.size()); // TODO remove when got equals expected
    assertEquals(expected, got);
  }

  public static void main(String[] args) throws IOException {
    var test = new JsonItemsTest();
    test.testSerDeLocationBiscuit();
  }
}
