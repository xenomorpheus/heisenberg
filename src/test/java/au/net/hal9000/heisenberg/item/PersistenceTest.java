package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** */
public class PersistenceTest {

  private Configuration config = null;
  final String persistenceUnitName = "items";
  private EntityManagerFactory factory = null;
  private EntityManager em = null;
  private static final Logger LOGGER = Logger.getLogger(PersistenceTest.class.getName());

  @Before
  public void initialise() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
    open_db();
  }

  @After
  public void finalise() {
    close_db();
  }

  private void open_db() {
    factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    em = factory.createEntityManager();
  }

  private void close_db() {
    em.close();
    em = null;
  }

  private void close_then_open_db() {
    close_db();
    open_db();
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

    // Persist Item
    em.getTransaction().begin();
    em.persist(item);
    em.getTransaction().commit();
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    close_then_open_db();

    // Retrieve Item
    var itemFetched = em.find(classExpected, jpaId);
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
    em.getTransaction().begin();
    em.persist(item);
    em.getTransaction().commit();
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    close_then_open_db();

    // Retrieve Item
    var itemFetched = em.find(classExpected, jpaId);
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
    em.getTransaction().begin();
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
    em.persist(item);
    em.getTransaction().commit();
    final var jpaId = item.getJpaId();
    assertNotEquals(0L, jpaId);

    close_then_open_db();

    // Retrieve Item
    Item retrievedItem = em.find(classExpected, jpaId);
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
      em.getTransaction().begin();
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
      em.persist(item);
      em.getTransaction().commit();
      final var jpaId = item.getJpaId();
      assertNotEquals(0L, jpaId);

      close_then_open_db();

      // Retrieve Item
      Item retrievedItem = em.find(classExpected, jpaId);
      assertNotNull(retrievedItem);
      assertEquals(jpaId, retrievedItem.getJpaId());
      assertEquals("Class", classExpected, retrievedItem.getClass());
      assertEquals("Name after", nameExpected, retrievedItem.getName());
      assertEquals("Description after", descriptionExpected, retrievedItem.getDescription());
    }
  }

  /** Method testItemContainer. */
  @Test
  public void testItemContainer() {

    var factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    var em = factory.createEntityManager();
    assertNotNull(em);

    Bag bag = new Bag();
    String bagNameExpected = "Bag Name";
    bag.setName(bagNameExpected);
    assertEquals("Bag Name", bagNameExpected, bag.getName());
    Biscuit biscuit1 = new Biscuit();
    String biscuit1Name = "Biscuit1 Name";
    biscuit1.setName(biscuit1Name);
    Biscuit biscuit2 = new Biscuit();
    bag.add(biscuit1);
    bag.add(biscuit2);
    LOGGER.info(bag.detailedDescription());
    assertEquals(2, bag.getContents().size());
    assertEquals(0L, biscuit1.getJpaId());
    assertEquals(0L, biscuit2.getJpaId());
    assertEquals(0L, bag.getJpaId());

    // Persist Item
    em.getTransaction().begin();
    em.persist(bag);
    em.persist(biscuit1);
    em.persist(biscuit2);
    em.getTransaction().commit();
    final var bagJpaId = bag.getJpaId();
    assertNotEquals(0L, bagJpaId);
    assertNotEquals(0L, biscuit1.getJpaId());
    assertNotEquals(0L, biscuit2.getJpaId());

    em.close();
    em = null;

    em = factory.createEntityManager();
    // Fetch the bag and contents.
    var bagFetched = em.find(Bag.class, bagJpaId);
    assertEquals("Bag Name", bagNameExpected, bagFetched.getName());
    assertEquals(2, bagFetched.getContents().size());
    LOGGER.info(bagFetched.detailedDescription());
    LOGGER.info("Bag Contents Count: " + bagFetched.getContents().size());
    var biscuit1Fetched = bagFetched.get(0);
    assertEquals("Biscuit1 Name", biscuit1Name, biscuit1Fetched.getName());
    for (var item : bagFetched.getContents()) {
      LOGGER.info("Content: " + item.detailedDescription());
    }
    em.close();
    em = null;
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
    backpack.setWeightMax(100000);
    backpack.setVolumeMax(100000);
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
    human.setWeightMax(100000);
    human.setVolumeMax(100000);

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

      // Persist Location
      em.getTransaction().begin();
      em.persist(loc);
      em.getTransaction().commit();
      locJpaId = loc.getJpaId();
      assertNotEquals(0L, locJpaId);
    }

    close_then_open_db();

    {
      // Retrieve Location
      final var locFetched = em.find(Location.class, locJpaId);

      var visitor = new MyItemVisitor();
      locFetched.accept(visitor);
      var summaryAfter = visitor.summary();
      assertEquals(summaryExpect, summaryAfter);
      visitor.printSummary();
    }
  }
}
