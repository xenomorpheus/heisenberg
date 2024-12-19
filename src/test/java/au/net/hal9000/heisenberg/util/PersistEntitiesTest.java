package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/** */
public class PersistEntitiesTest {

  private Configuration config = null;
  private static final Logger LOGGER = Logger.getLogger(PersistEntitiesTest.class.getName());

  @Before
  public void initialise() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  /** Persistence of basic Item. */
  @Test
  public void item() {
    var item = new Biscuit();
    final var classExpected = item.getClass();
    String nameExpected = "Name of " + item.getSimpleClassName();
    item.setName(nameExpected);
    assertEquals("Name before", nameExpected, item.getName());
    assertEquals(0L, item.getJpaId());
    item.setProperty("WantBAR", "BAR");

    PersistEntities.save(item);
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    // Retrieve Item
    var itemFetched = PersistEntities.find(classExpected, jpaId);
    assertNotNull(itemFetched);
    assertEquals(jpaId, itemFetched.getJpaId());
    assertEquals("WantBar property after", "BAR", itemFetched.getProperty("WantBAR"));
    assertEquals("Name after", nameExpected, itemFetched.getName());
  }

  /**
   * Test Item with character sheet. <br>
   * This tests the persistence of properties that are classes.
   */
  @Test
  public void itemWithCharacterSheet() {
    var item = new Cat();
    final var classExpected = item.getClass();
    assertEquals(0L, item.getJpaId());
    String nameExpected = "Name of " + item.getSimpleClassName();
    item.setName(nameExpected);
    assertEquals("Name", nameExpected, item.getName());
    item.setProperty("WantBAR", "BAR");
    int manaExpected = 555;
    item.getPlayableState().setMana(manaExpected);

    // Persist Item
    PersistEntities.save(item);
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    // Retrieve Item
    var itemFetched = PersistEntities.find(classExpected, jpaId);
    assertNotNull(itemFetched);
    assertEquals(jpaId, itemFetched.getJpaId());
    assertEquals("Name after", nameExpected, itemFetched.getName());
    assertEquals("BAR", itemFetched.getProperty("WantBAR"));
    assertEquals("Mana after", manaExpected, itemFetched.getPlayableState().getMana());
  }

  /**
   * Persist Item with CharacterSheet as member.<br>
   * Using Factory for initial construction.
   */
  @Test
  public void itemFromFactoryHasCharacterSheet() {
    String itemClass = "entity.Human";
    LOGGER.info("Testing " + itemClass);

    // Create a new Item
    Item item = Factory.createItem(itemClass);
    final String nameExpected = "Name of " + item.getSimpleClassName();
    item.setName(nameExpected);
    assertEquals("Name before", nameExpected, item.getName());
    final String descriptionExpected = "This is a " + item.getSimpleClassName() + " Description";
    item.setDescription(descriptionExpected);
    assertEquals("Description before", descriptionExpected, item.getDescription());

    assertEquals(0L, item.getJpaId());
    final var classExpected = item.getClass();

    // Persist Item
    PersistEntities.save(item);
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    // Retrieve Item
    Item retrievedItem = PersistEntities.find(classExpected, jpaId);
    assertNotNull(retrievedItem);
    assertEquals("jpaId", jpaId, retrievedItem.getJpaId());
    assertEquals("Class after", classExpected, retrievedItem.getClass());
    assertEquals("Name after", nameExpected, retrievedItem.getName());
  }

  /**
   * Method oneOfEachItem.
   *
   * @throws ConfigurationError
   */
  @Test
  public void oneOfEachItem() throws ConfigurationError {
    assertNotEquals(0, config.getItemClasses().values().size());
    for (ItemClassConfiguration itemClassConfiguration : config.getItemClasses().values()) {
      String itemClass = itemClassConfiguration.getId();
      LOGGER.info("Testing " + itemClass);

      // Create a new Item
      Item item = Factory.createItem(itemClass);
      final String nameExpected = "Name of " + item.getSimpleClassName();
      item.setName(nameExpected);
      assertEquals("Name before", nameExpected, item.getName());
      final String descriptionExpected = "This is a " + itemClass + " Description";
      item.setDescription(descriptionExpected);
      assertEquals("Description after", descriptionExpected, item.getDescription());

      assertEquals(0L, item.getJpaId());
      final var classExpected = item.getClass();

      // Persist Item
      PersistEntities.save(item);
      final var jpaId = item.getJpaId();
      assertNotEquals(0L, jpaId);

      // Retrieve Item
      Item retrievedItem = PersistEntities.find(classExpected, jpaId);
      assertNotNull(retrievedItem);
      assertEquals(jpaId, retrievedItem.getJpaId());
      assertEquals("Class", classExpected, retrievedItem.getClass());
      assertEquals("Name after", nameExpected, retrievedItem.getName());
      assertEquals("Description after", descriptionExpected, retrievedItem.getDescription());
    }
  }

  private static Location testGetWorld() {
    // Ad-hoc test world
    Location world = new Location();
    world.setWeightMax(-1f);
    world.setVolumeMax(-1f);

    // Scabbard
    Scabbard scabbard = new Scabbard();
    scabbard.add(new Sword());

    Scabbard scabbard2 = new Scabbard();
    scabbard2.add(new Sword());

    Bag boh = new BagOfHolding(1);
    boh.add(new Bag());
    boh.add(new Box());
    boh.add(new Candle());
    boh.add(new Cloak());
    boh.add(new Biscuit());
    boh.add(new Crossbow());
    boh.add(new CrossbowBolt());
    boh.add(new MagicRing());
    boh.add(new Ring());
    boh.add(scabbard);
    boh.add(new Torch());

    // a backpack of stuff
    Bag backpack = new Backpack();
    backpack.setWeightMax(-1);
    backpack.setVolumeMax(-1);
    backpack.add(boh);

    Quiver quiver = new Quiver();
    quiver.add(new Arrow());
    quiver.add(new Arrow());
    quiver.add(new Arrow());

    Bag bag2 = new Bag();
    bag2.add(new Biscuit());
    bag2.add(new Biscuit());
    bag2.add(new Biscuit());
    backpack.add(bag2);

    // a human with a bag of biscuits
    Human human = new Human();
    world.add(human);
    human.setWeightMax(-1);
    human.setVolumeMax(-1);

    human.add(new Shield());
    human.add(scabbard2);
    human.add(quiver);
    human.add(backpack);

    world.add(new Sword());
    world.add(new Horse());

    // bag3
    Bag bag3 = new Bag();
    bag3.add(new Biscuit());
    bag3.add(new Biscuit());
    bag3.add(new Biscuit());
    world.add(bag3);

    return world;
  }

  private class MyItemVisitor implements ItemVisitor {

    private HashMap<String, Integer> summary = new HashMap<>();

    private void incrementCount(Item item) {
      var className = item.getSimpleClassName();
      if (summary.containsKey(className)) {
        summary.merge(className, 1, Integer::sum);
      } else {
        summary.put(className, 1);
      }
    }

    private void printSummary() {
      for (final var entry : summary.entrySet()) {
        LOGGER.info("ClassName: " + entry.getKey() + ", count: " + entry.getValue());
      }
    }

    public HashMap<String, Integer> summary() {
      return summary;
    }

    @Override
    public void visit(Item item) {
      incrementCount(item);
      LOGGER.info(item.detailedDescription());
    }

    @Override
    public void visit(List<Item> items) {
      for (var item : items) {
        incrementCount(item);
        LOGGER.warn("Content: " + item.detailedDescription());
      }
    }
  }

  /** Method testWorld. */
  @Test
  public void world() {

    final long locJpaId;
    final HashMap<String, Integer> summaryExpect;
    {
      Location loc = testGetWorld();
      var visitor = new MyItemVisitor();
      loc.accept(visitor);
      summaryExpect = visitor.summary();
      visitor.printSummary();

      PersistEntities.save(loc);
      locJpaId = loc.getJpaId();
      assertNotEquals(0L, locJpaId);
    }

    {
      // Retrieve Location
      final var locFetched = PersistEntities.find(Location.class, locJpaId);

      var visitor = new MyItemVisitor();
      locFetched.accept(visitor);
      var summaryAfter = visitor.summary();
      assertEquals(summaryExpect, summaryAfter);
      visitor.printSummary();
    }
  }
}
