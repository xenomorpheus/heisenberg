package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemClassConfiguration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class PersistenceTest {

    private Configuration config = null;

    @Before
    public void initialize() {
        DemoEnvironment.setup();
        config = Configuration.lastConfig();
    }

    /**
     * Method oneOfEachItem.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void oneOfEachItem() throws ConfigurationError {
        final String persistenceUnitName = "items";
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(persistenceUnitName);
        EntityManager em = factory.createEntityManager();

        for (ItemClassConfiguration itemClassConfiguration : config.getItemClasses().values()) {
            String itemClass = itemClassConfiguration.getId();

            // Create a new Item
            em.getTransaction().begin();
            Item item = Factory.createItem(itemClass);
            item.setName("This is a " + itemClass + " Name");
            item.setDescription("This is a " + itemClass + " Description");
            // TODO assertEquals(0L, item.getJpaId());

            // Persist it
            em.persist(item);
            em.getTransaction().commit();
            /** TODO PersistanceTest
            long jpaId = item.getJpaId();
            assertNotEquals(0L, jpaId);

            Query query = em.createQuery("select c from " + itemClass
                    + " c where c.jpaId = " + jpaId);

            Object resultList = query.getResultList();
            // http://stackoverflow.com/questions/262367/type-safety-unchecked-cast
            @SuppressWarnings("unchecked")
            List<Item> itemList = (List<Item>) resultList;
            assertEquals("select count", 1, itemList.size());
            for (Item item2 : itemList) {
                assertEquals("name", "This is a " + itemClass + " Name",
                        item2.getName());
                assertEquals("description", "This is a " + itemClass
                        + " Description", item2.getDescription());
            }
*/
        }
        em.close();
    }

    /**
     * Method testContainer.
     */
    @Test
    public void testContainer(){
        Bag bag = new Bag();
        Cookie cookie1 = new Cookie();
        Cookie cookie2 = new Cookie();
        bag.add(cookie1);
        bag.add(cookie2);
        assertEquals(0L, cookie1.getJpaId());
        assertEquals(0L, cookie2.getJpaId());
        assertEquals(0L, bag.getJpaId());

        final String persistenceUnitName = "items";
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(persistenceUnitName);
        EntityManager em = factory.createEntityManager();

        // Persist it
        em.getTransaction().begin();
        em.persist(bag);
        em.persist(cookie1);
        em.persist(cookie2);
        em.getTransaction().commit();
        assertNotEquals(0L, bag.getJpaId());
        assertNotEquals(0L, cookie1.getJpaId());
        assertNotEquals(0L, cookie2.getJpaId());

        em.close();
    }

    /**
     * Method testGetWorld.
     * 
     * @return Location
     */
    public static Location testGetWorld(){
        // Ad-hoc test world
        Location world = new Location("World");
        world.setWeightMax(100000);
        world.setVolumeMax(100000);

        // Scabbard
        Scabbard scabbard = new Scabbard();
        scabbard.add(new Sword());

        Scabbard scabbard2 = new Scabbard("Scabbard2");
        scabbard2.add(new Sword());

        Bag boh = new BagOfHolding(1);
        boh.add(new Bag("Bag1"));
        boh.add(new Box("Box1"));
        boh.add(new Candle());
        boh.add(new Cloak());
        boh.add(new Cookie("Cookie1"));
        boh.add(new Crossbow());
        boh.add(new CrossbowBolt());
        boh.add(new MagicRing());
        boh.add(new Ring());
        boh.add(scabbard);
        boh.add(new Torch());

        // a backpack of stuff
        Bag backpack = new Backpack("Backpack1");
        backpack.setWeightMax(100000);
        backpack.setVolumeMax(100000);
        backpack.add(boh);

        Quiver quiver = new Quiver();
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());

        Bag bag2 = new Bag("Bag2");
        bag2.add(new Cookie("Cookie6"));
        bag2.add(new Cookie("Cookie7"));
        bag2.add(new Cookie("Cookie8"));
        backpack.add(bag2);

        // a human with a bag of cookies
        Human human = new Human("Human1");
        world.add(human);
        human.setWeightMax(100000);
        human.setVolumeMax(100000);

        human.wear(new Shield());
        human.wear(scabbard2);
        human.wear(quiver);
        human.wear(backpack);

        world.add(new Sword());
        world.add(new Horse());

        // bag3
        Bag bag3 = new Bag("Bag3");
        bag3.add(new Cookie("Cookie9"));
        bag3.add(new Cookie("Cookie10"));
        bag3.add(new Cookie("Cookie11"));
        world.add(bag3);

        return world;
    }

    /**
     * Method testLocation.
     */
    @Test
    // world persistence
    public void testLocation(){

        Location loc = testGetWorld();

        final String persistenceUnitName = "items";
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(persistenceUnitName);
        EntityManager em = factory.createEntityManager();

        // Persist it
        em.getTransaction().begin();
        // TODO use a visitor pattern to persist every item and location.
        em.persist(loc);
        em.getTransaction().commit();
        assertNotEquals(0L, loc.getJpaId());
        em.close();
    }

}
