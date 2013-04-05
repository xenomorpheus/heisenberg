package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nu.xom.ValidityException;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.util.Configuration;

public class PersistenceTest {

	@Test
	public void oneOfEachItem() throws ValidityException, IOException, Exception {
		Configuration config = new Configuration("src/test/resources/config.xml");
		Vector<String> itemClasses = config.getItemClasses();

		final String PERSISTENCE_UNIT_NAME = "items";
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		for (String itemClass : itemClasses) {

			// Create a new Item
			em.getTransaction().begin();
			Item item = Factory.createItem(itemClass);
			assertEquals(0L, item.getJpaId());
			item.setName("This is a " + itemClass + " Name");
			item.setDescription("This is a " + itemClass + " Description");

			// Persist it
			em.persist(item);
			em.getTransaction().commit();
			long jpaId = item.getJpaId();
			assertNotEquals(0L, jpaId);

			Query q = em.createQuery("select c from " + itemClass
					+ " c where c.jpaId = " + jpaId);

			Object resultList = q.getResultList();

			if (!(resultList instanceof List<?>)) {
				fail("result expected to be a list");
			}
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

		}
		em.close();
	}

	@Test
	public void testContainer() {
		Bag bag = new Bag();
		Cookie cookie1 = new Cookie();
		Cookie cookie2 = new Cookie();
		bag.add(cookie1);
		bag.add(cookie2);
		assertEquals(0L, cookie1.getJpaId());
		assertEquals(0L, cookie2.getJpaId());
		assertEquals(0L, bag.getJpaId());

		final String PERSISTENCE_UNIT_NAME = "items";
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
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

	public static Location testGetWorld() {
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

		human.add(new Shield());
		human.add(scabbard2);
		human.add(quiver);
		human.add(backpack);

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

	// TODO @Test
	public void testLocation() {

		Location loc = testGetWorld();

		final String PERSISTENCE_UNIT_NAME = "items";
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
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
