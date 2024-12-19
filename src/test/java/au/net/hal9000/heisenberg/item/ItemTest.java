package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
// Custom
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/** */
public class ItemTest {
  /** Field WITHIN_MARGIN. (value is 9.0E-5) */
  private static final float WITHIN_MARGIN = 0.00009F;

  private Configuration config = null;

  @Before
  public void initialize() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  @Test
  public void testItem() {
    Item item = new Biscuit();
    assertNull("Item() name", item.getName());
    assertNull("Item() description", item.getDescription());
    assertNull("Item() valueBase", item.getValueBase());
    // assertTrue("Item() weightBase", i.getWeightBase().equals(0F));
    // assertTrue("Item() volumeBase", i.getVolumeBase().equals(0F));
    assertNull("Item() location", item.getContainer());
  }

  @Test
  public void testId() {
    Item item = new Biscuit();
    UUID id = item.getId();
    assertNotNull("id", id);
  }

  @Test
  public void testGetJpaId() {
    Item item = new Biscuit();
    long jpaId = item.getJpaId();
    assertEquals("jpaId", 0L, jpaId);
  }

  @Test
  public void testHitPoints() {
    Item item = new Biscuit();
    float expectHitPoints = 1.23f;
    item.setHitPoints(expectHitPoints);
    assertEquals("hit Points", expectHitPoints, item.getHitPoints(), WITHIN_MARGIN);
  }

  @Test
  public void testName() {
    Item item = new Biscuit();
    item.setName("fred");
    assertEquals("setname & getname", "fred", item.getName());
  }

  @Test
  public void testDescription() {
    Item item = new Biscuit();
    item.setDescription("A Description");
    assertEquals("description", "A Description", item.getDescription());
  }

  @Test
  public void testBaseWeight() {
    final float weight = 0.123F;
    Item item = new Biscuit();
    item.setWeightBase(weight);
    assertEquals("weightBase", item.getWeightBase(), weight, WITHIN_MARGIN);
  }

  @Test
  public void testWeight() {
    final float weight = 0.123F;
    Item item = new Biscuit();
    item.setWeightBase(weight);
    assertEquals("weight", item.getWeight(), weight, WITHIN_MARGIN);
  }

  /** test valueBase and value */
  @Test
  public void testValueBase() {
    Item item = new Biscuit();
    Currency currency = new Currency();
    item.setValueBase(currency);
    assertTrue("valueBase", currency == item.getValueBase());
    assertTrue("value", currency == item.getValue());
  }

  @Test
  public void testLocation() {
    Item item = new Biscuit();
    Box box = new Box();
    item.setContainer(box);
    assertEquals("location", box, item.getContainer());
  }

  @Test
  public void testToString() throws ConfigurationError {
    for (ItemClassConfiguration itemClassConfiguration : config.getItemClasses().values()) {
      String itemClass = itemClassConfiguration.getId();
      String type = Factory.createItem(itemClass).toString();
      assertTrue(itemClass + ".toString not null", null != type);
      assertTrue(itemClass + ".toString length", type.length() > 0);
    }
  }

  @Test
  public void testSetVolumeMax() {
    float volumeBase = 20F;
    Item item = new Biscuit();
    item.setVolumeBase(volumeBase);
    float v = item.getVolumeBase();
    assertEquals("getVolumeBase=", volumeBase, v, 0.0001F);
  }

  @Test
  public void testEquals() {
    Item first = new Biscuit();
    Item second = new Biscuit();
    assertTrue("equals true for self", first.equals(first));
    assertFalse("equals false for other", first.equals(second));
  }

  @Test
  public void testProperties() {
    Item item = new Biscuit();
    Properties p = new Properties();
    item.setProperties(p);
    assertEquals("description", p, item.getProperties());
  }

  @Test
  public void testProperty() {
    Item item = new Biscuit();
    item.setProperty("myKey", "myVal");
    assertEquals("get property", "myVal", item.getProperty("myKey"));
    item.removeProperty("myKey");
  }

  @Test
  public void testSetAllFrom() {
    Item item = new Biscuit();
    Item other = new Biscuit();
    ItemContainer bag = new Bag();
    other.setContainer(bag);
    other.setDescription("other");
    other.setHitPoints(123f);
    other.setName("the biscuit of awesomeness");
    other.setPosition(new Position(1, 2));
    other.setValueBase(new Currency(1, 2, 3, 4));
    other.setVolumeBase(145);
    other.setWeightBase(345);
    item.setAllFrom(other);
    assertEquals("container", item.getContainer(), other.getContainer());
    assertEquals("description", item.getDescription(), other.getDescription());
    assertEquals("hp", item.getHitPoints(), other.getHitPoints(), WITHIN_MARGIN);
    assertEquals("name", item.getName(), other.getName());
    assertEquals("position", item.getPosition(), other.getPosition());
    assertEquals("properties", item.getProperties(), other.getProperties());
    assertEquals("valueBase", item.getValueBase(), other.getValueBase());
    assertEquals("volumeBase", item.getVolumeBase(), other.getVolumeBase(), WITHIN_MARGIN);
    assertEquals("weightBase", item.getWeightBase(), other.getWeightBase(), WITHIN_MARGIN);
  }

  @Test
  public void testHashCode() {

    Item item = new Biscuit();
    ItemContainer bag = new Bag();
    item.setContainer(bag);
    item.setDescription("other");
    item.setHitPoints(123f);
    item.setName("the biscuit of awesomeness");
    item.setPosition(new Position(1, 2));
    item.setValueBase(new Currency(1, 2, 3, 4));
    item.setVolumeBase(145);
    item.setWeightBase(345);
    assertTrue("hashcode", 0 != item.hashCode());
  }

  /** Move without a container. */
  @Test(expected = UnsupportedOperationException.class)
  public void testMoveNoContainer() {
    Item item = new Biscuit();

    // No container - No Movement
    Position expectedPosition = new Position(10, 20, 30);
    // Before test we place in a known position.
    item.setPosition(expectedPosition);
    try {
      // Try to move, but will fail as not in an ItemContainer.
      item.moveWithinContainer(new Position(1, 2, 3));
    } catch (UnsupportedOperationException e) {

      Position actualPosition = item.getPosition();
      assertTrue("No ItemContainer - final pos", expectedPosition.equals(actualPosition));
      throw e;
    }
  }

  /** Move within a container. */
  @Test
  public void testMoveContainer() {
    Item item = new Biscuit();

    Location container = new Location();
    item.setContainer(container);
    Position expectedPosition = new Position(2, 4, 8);
    item.moveWithinContainer(expectedPosition);
    Position actualPosition = item.getPosition();
    assertTrue("Has ItemContainer - final pos", expectedPosition.equals(actualPosition));
  }

  @Test
  public void testApplyDelta() {
    Item item = new Biscuit();
    ItemContainer bag = new Bag();
    bag.add(item);
    Position delta = new Position(1, 2);
    item.applyDelta(delta);
  }

  @Test
  public void testBeNot() {
    ItemContainer container = new Bag();
    Item item = new Biscuit();
    container.add(item);
    assertTrue(container.contains(item));
    item.beNot();
    assertFalse(container.contains(item));
  }

  @Test
  public void testMoveItemContainerPosition() {
    ItemContainer container = new Bag();
    Item item = new Biscuit();
    Position position = new Position(4, 5);
    item.move(container, position);
    assertTrue(container.contains(item));
    assertEquals(container, item.getContainer());
    assertEquals(position, item.getPosition());
  }

  @Test
  public void testMoveItemContainer() {
    ItemContainer container = new Bag();
    Item item = new Biscuit();
    item.move(container);
    assertTrue(container.contains(item));
    assertEquals(container, item.getContainer());
  }

  @Test
  public void testDetailedDescription() {

    Item item = new Biscuit();
    item.setDescription("other");
    item.setHitPoints(123f);
    item.setName("the biscuit of awesomeness");
    item.setPosition(new Position(1, 2));
    item.setValueBase(new Currency(1, 2, 3, 4));
    item.setVolumeBase(145);
    item.setWeightBase(345);
    assertNotNull(item.detailedDescription());
  }

  private class MyItemVisitor implements ItemVisitor {
    boolean visited = false;

    @Override
    public void visit(Item item) {
      visited = true;
    }

    @Override
    public void visit(List<Item> items) {}
  }

  @Test
  public void testAcceptItemVisitor() {
    Item item = new Biscuit();
    ItemVisitor visitor = new MyItemVisitor();
    item.accept(visitor);
    MyItemVisitor myItemVisitor = (MyItemVisitor) visitor;
    assertTrue(myItemVisitor.visited);
  }
}
